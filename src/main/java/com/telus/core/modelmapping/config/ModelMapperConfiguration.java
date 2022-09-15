package com.telus.core.modelmapping.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper(ListableBeanFactory beanFactory) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        Collection<PropertyMap> propertyMaps = beanFactory.getBeansOfType(PropertyMap.class).values();
        for (PropertyMap mapClass : propertyMaps) {
            modelMapper.addMappings(mapClass);
        }
        return modelMapper;
    }

}
