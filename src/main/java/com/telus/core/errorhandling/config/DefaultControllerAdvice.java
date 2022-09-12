package com.telus.core.errorhandling.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.telus.core.errorhandling.errorhandler.ExceptionProcessor;
import com.telus.core.errorhandling.resource.ErrorResultResource;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A default {@link ControllerAdvice} that catches general {@link Exception}s
 * and mitigates them to an
 * {@link ExceptionProcessor} of {@link ExceptionHandler}s.
 */
@ControllerAdvice
public class DefaultControllerAdvice {
	private final ExceptionProcessor exceptionProcessor;

	public DefaultControllerAdvice(ExceptionProcessor exceptionProcessor) {
		this.exceptionProcessor = exceptionProcessor;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResultResource> handleException(Exception exception) {
		return exceptionProcessor.process(exception);
	}
}
