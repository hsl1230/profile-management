package com.telus.core.errorhandling.errorhandler;

import org.springframework.http.ResponseEntity;

import com.telus.core.errorhandling.PlatformErrorCode;
import com.telus.core.errorhandling.exception.InternalErrorException;
import com.telus.core.errorhandling.exception.PlatformException;
import com.telus.core.errorhandling.resource.ErrorResultResource;

public class DefaultExceptionHandler extends ExceptionHandler {

	@Override
	public ResponseEntity<ErrorResultResource> handleException(Exception exception) {

		if (exception instanceof PlatformException) {
			return handlePlatformException((PlatformException) exception);
		}
		return handleOtherException(exception);
	}

	private ResponseEntity<ErrorResultResource> handlePlatformException(PlatformException exception) {
		logger.error("Caught platform exception", exception);
		return new ResponseEntityBuilder().platformException(exception).build();
	}

	private ResponseEntity<ErrorResultResource> handleOtherException(Exception exception) {
		logger.error("Caught unexpected exception: ", exception);
		PlatformException platformException = new InternalErrorException(PlatformErrorCode.INTERNAL_ERROR, exception);
		return new ResponseEntityBuilder().platformException(platformException).build();
	}

	@Override
	public Class<? extends Throwable> exceptionClass() {
		return Exception.class;
	}

	
}
