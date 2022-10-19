package com.telus.core.errorhandling.errorhandler;

import com.telus.core.errorhandling.resource.ErrorResultResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

/**
 * Abstract implementation for exception handler. Provides default common
 * functionality
 * for handling all kinds of exceptions.
 */
public abstract class ExceptionHandler {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public abstract Class<? extends Throwable> exceptionClass();

	public abstract ResponseEntity<ErrorResultResource> handleException(Exception exception);
}
