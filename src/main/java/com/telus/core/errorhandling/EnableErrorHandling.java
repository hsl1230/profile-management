package com.telus.core.errorhandling;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.telus.core.errorhandling.config.ExceptionHandlersConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ExceptionHandlersConfiguration.class})
public @interface EnableErrorHandling {
	
}
