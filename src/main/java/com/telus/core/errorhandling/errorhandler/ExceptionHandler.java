package com.telus.core.errorhandling.errorhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.telus.core.errorhandling.resource.ErrorResultResource;

import java.util.List;

/**
 * Abstract implementation for exception handler. Provides default common functionality
 * for handling all kinds of exceptions.
 */
public abstract class ExceptionHandler {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public abstract List<Class<? extends Throwable>> exceptionClasses();
	public abstract ResponseEntity<ErrorResultResource> handleException(Exception exception);
}
