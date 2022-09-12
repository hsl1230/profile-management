package com.telus.core.errorhandling.errorhandler;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;

import java.util.ArrayList;
import java.util.List;

@ErrorHandler
public class HttpMediaTypeNotSupportedExceptionHandler extends AbstractExceptionHandler {

	@Override
	public List<Class<? extends Throwable>> exceptionClasses() {
		List<Class<? extends Throwable>> classes = new ArrayList<>();
		classes.add(HttpMediaTypeNotSupportedException.class);
		return classes;
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
