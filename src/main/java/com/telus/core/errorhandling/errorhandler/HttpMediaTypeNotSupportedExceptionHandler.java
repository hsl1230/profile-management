package com.telus.core.errorhandling.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;

public class HttpMediaTypeNotSupportedExceptionHandler extends AbstractExceptionHandler {

	@Override
	public Class<? extends Throwable> exceptionClass() {
		return HttpMediaTypeNotSupportedException.class;
	}

	@Override
	protected ErrorCode errorCode() {
		return PlatformErrorCode.UNSUPPORTED_CONTENT_TYPE;
	}

	@Override
	protected HttpStatus httpStatus() {
		return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
	}
}
