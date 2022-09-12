package com.telus.core.errorhandling.errorhandler;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;
import com.telus.core.errorhandling.exception.PlatformException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@ErrorHandler
public class DataAccessResourceFailureExceptionHandler extends AbstractExceptionHandler {

	@Override
	public List<Class<? extends Throwable>> exceptionClasses() {
		List<Class<? extends Throwable>> classes = new ArrayList<>();
		classes.add(DataAccessResourceFailureException.class);
		return classes;
	}

	@Override
	protected ErrorCode errorCode() {
		return PlatformErrorCode.DATABASE_ERROR;
	}

	@Override
	protected HttpStatus httpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
