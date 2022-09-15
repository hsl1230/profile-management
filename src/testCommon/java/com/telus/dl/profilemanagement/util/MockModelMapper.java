package com.telus.dl.profilemanagement.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class MockModelMapper extends ModelMapper {
    public MockModelMapper() {
        this.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        Collection<PropertyMap> propertyMaps = findPropertyMaps("com.telus");
        for (PropertyMap mapClass : propertyMaps) {
            this.addMappings(mapClass);
        }
    }

    private List<PropertyMap> findPropertyMaps(String scanPackage) {

        ClassPathScanningCandidateComponentProvider provider = createComponentScanner();
        ArrayList<PropertyMap> propertyMaps = new ArrayList<>();
        for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) {
            try {
                Class<PropertyMap> clazz = (Class<PropertyMap>) Class.forName(beanDef.getBeanClassName());
                propertyMaps.add(clazz.getConstructor().newInstance());
            } catch (Exception ex) {
                throw new RuntimeException("Error occurred when loading property maps to ModelMap", ex);
            }
        }
        return propertyMaps;
    }

    private ClassPathScanningCandidateComponentProvider createComponentScanner() {
        ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(PropertyMap.class));

        return provider;
    }
}
