package com.telus.core.errorhandling.config;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.telus.core.errorhandling.errorhandler.ExceptionHandler;
import com.telus.core.errorhandling.errorhandler.ExceptionProcessor;

@Import({DefaultControllerAdvice.class, DefaultExceptionHandlingFilter.class})
public class ExceptionHandlersConfiguration {
	@Bean("exceptionHandlers")
	public Map<Class<? extends Throwable>, ExceptionHandler> exceptionHandlers() {
		Map<Class<? extends Throwable>, ExceptionHandler> handlers = new HashMap<>();
		ServiceLoader<ExceptionHandler> loader = ServiceLoader.load(ExceptionHandler.class);
		for (ExceptionHandler exceptionHandler : loader) {
			handlers.put(exceptionHandler.exceptionClass(), exceptionHandler);
		}
		return handlers;
	}

	@Bean
	public ExceptionProcessor exceptionProcessor(
		@Qualifier("exceptionHandlers") Map<Class<Throwable>, ExceptionHandler> exceptionHandlers) {
		return new ExceptionProcessor(exceptionHandlers);
	}
}
