package com.telus.core.errorhandling.errorhandler;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;

@ErrorHandler
public class DataAccessResourceFailureExceptionHandler
		extends AbstractExceptionHandler<DataAccessResourceFailureException> {

	@Override
	public Class<? extends Throwable> exceptionClass() {
		return DataAccessResourceFailureException.class;
	}

	@Override
	protected ErrorCode errorCode(DataAccessResourceFailureException exception) {
		return PlatformErrorCode.DATABASE_ERROR;
	}

	@Override
	protected HttpStatus httpStatus(DataAccessResourceFailureException exception) {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
