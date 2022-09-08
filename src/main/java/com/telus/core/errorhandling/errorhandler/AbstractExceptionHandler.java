package com.telus.core.errorhandling.errorhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.exception.PlatformException;
import com.telus.core.errorhandling.resource.ErrorResultResource;

public abstract class AbstractExceptionHandler extends ExceptionHandler {
	protected abstract ErrorCode errorCode();

	protected abstract HttpStatus httpStatus();

	protected HttpHeaders httpHeaders() {
		return HttpHeaders.EMPTY;
	}

	@Override
	public ResponseEntity<ErrorResultResource> handleException(Exception exception) {
		logger.error("Caught Exception", exception);
		PlatformException platformException = new PlatformException(
				httpStatus(),
				errorCode(),
				exception);

		return new ResponseEntityBuilder()
				.platformException(platformException)
				.headers(httpHeaders())
				.build();
	}
}
