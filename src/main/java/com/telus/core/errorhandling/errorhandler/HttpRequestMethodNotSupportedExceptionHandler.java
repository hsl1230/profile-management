package com.telus.core.errorhandling.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;

@ErrorHandler
public class HttpRequestMethodNotSupportedExceptionHandler
		extends AbstractExceptionHandler<HttpRequestMethodNotSupportedException> {

	@Override
	protected ErrorCode errorCode(HttpRequestMethodNotSupportedException exception) {
		return PlatformErrorCode.NOT_SUPPORTED;
	}

	@Override
	protected HttpStatus httpStatus(HttpRequestMethodNotSupportedException exception) {
		return HttpStatus.METHOD_NOT_ALLOWED;
	}
}
