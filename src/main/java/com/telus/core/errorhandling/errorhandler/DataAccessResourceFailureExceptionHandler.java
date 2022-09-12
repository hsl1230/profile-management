package com.telus.core.errorhandling.errorhandler;

import com.mongodb.MongoTimeoutException;
import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;
import org.springframework.http.HttpStatus;

@ErrorHandler
public class MongoTimeoutExceptionHandler extends AbstractExceptionHandler {

	@Override
	public Class<? extends Throwable> exceptionClass() {
		return MongoTimeoutException.class;
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
