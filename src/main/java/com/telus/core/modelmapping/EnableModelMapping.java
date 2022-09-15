package com.telus.core.modelmapping;

import com.telus.core.modelmapping.config.ModelMapperConfiguration;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ModelMapperConfiguration.class})
@ComponentScan(
        value = "com.telus",
        includeFilters= {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = PropertyMap.class)}
)
public @interface EnableModelMapping {
}
