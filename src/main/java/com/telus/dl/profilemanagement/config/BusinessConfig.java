package com.telus.dl.profilemanagement.config;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.ServiceLoader;

@Configuration
public class BusinessConfig {
    @Bean
    public ModelMapper modelMapper() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        ServiceLoader<PropertyMap> loader = ServiceLoader.load(PropertyMap.class);
        for (PropertyMap mapClass : loader) {
            modelMapper.addMappings(mapClass);
        }
        return modelMapper;
    }

}
