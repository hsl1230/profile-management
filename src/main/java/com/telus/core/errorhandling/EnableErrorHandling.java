package com.telus.core.errorhandling;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import com.telus.core.errorhandling.config.ExceptionHandlersConfiguration;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ExceptionHandlersConfiguration.class})
@ComponentScan(
        value = "com.telus",
        includeFilters= {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ExceptionHandler.class)}
)
public @interface EnableErrorHandling {

}
