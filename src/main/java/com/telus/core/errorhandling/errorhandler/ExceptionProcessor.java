package com.telus.core.errorhandling.errorhandler;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.telus.core.errorhandling.resource.ErrorResultResource;

public class ExceptionProcessor {
	private final Map<Class<? extends Throwable>, ExceptionHandler> exceptionHandlers;

	public ExceptionProcessor(Map<Class<? extends Throwable>, ExceptionHandler> exceptionHandlers) {
		this.exceptionHandlers = exceptionHandlers;
	}

	public ResponseEntity<ErrorResultResource> process(Exception exception) {
		Class<?> exceptionClass = exception.getClass();
		while(!exceptionHandlers.containsKey(exceptionClass)) {
			exceptionClass = exceptionClass.getSuperclass();
		}

		ExceptionHandler exceptionHandler = exceptionHandlers.get(exceptionClass);
		return exceptionHandler.handleException(exception);
	}

}
