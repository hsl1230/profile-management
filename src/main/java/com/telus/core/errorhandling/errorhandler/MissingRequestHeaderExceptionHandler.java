package com.telus.core.errorhandling.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;

@ErrorHandler
public class MissingRequestHeaderExceptionHandler extends AbstractExceptionHandler<MissingRequestHeaderException> {

	@Override
	public Class<? extends Throwable> exceptionClass() {
		return MissingRequestHeaderException.class;
	}

	@Override
	protected ErrorCode errorCode(MissingRequestHeaderException exception) {
		return PlatformErrorCode.INVALID_REQUEST_HEADER;
	}

	@Override
	protected HttpStatus httpStatus(MissingRequestHeaderException exception) {
		return HttpStatus.BAD_REQUEST;
	}
}
