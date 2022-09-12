package com.telus.core.errorhandling.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;

@ErrorHandler
public class HttpMediaTypeNotSupportedExceptionHandler
		extends AbstractExceptionHandler<HttpMediaTypeNotSupportedException> {

	@Override
	protected ErrorCode errorCode(HttpMediaTypeNotSupportedException exception) {
		return PlatformErrorCode.UNSUPPORTED_CONTENT_TYPE;
	}

	@Override
	protected HttpStatus httpStatus(HttpMediaTypeNotSupportedException exception) {
		return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
	}
}
