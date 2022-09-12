package com.telus.core.errorhandling.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;

@ErrorHandler
public class HttpMediaTypeNotAcceptableExceptionHandler
		extends AbstractExceptionHandler<HttpMediaTypeNotAcceptableException> {

	@Override
	public Class<? extends Throwable> exceptionClass() {
		return HttpMediaTypeNotAcceptableException.class;
	}

	@Override
	protected ErrorCode errorCode(HttpMediaTypeNotAcceptableException exception) {
		return PlatformErrorCode.UNSUPPORTED_RESPONSE_FORMAT;
	}

	@Override
	protected HttpStatus httpStatus(HttpMediaTypeNotAcceptableException exception) {
		return HttpStatus.NOT_ACCEPTABLE;
	}
}
