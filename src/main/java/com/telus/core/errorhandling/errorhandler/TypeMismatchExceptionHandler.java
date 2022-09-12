package com.telus.core.errorhandling.errorhandler;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;
import com.telus.core.errorhandling.exception.PlatformException;

@ErrorHandler
public class TypeMismatchExceptionHandler extends AbstractExceptionHandler<TypeMismatchException> {

	@Override
	public Class<? extends Throwable> exceptionClass() {
		return TypeMismatchException.class;
	}

	@Override
	protected ErrorCode errorCode(TypeMismatchException ex) {
		if (ex.getMostSpecificCause() instanceof IllegalArgumentException) {
			return PlatformErrorCode.ITEM_NOT_FOUND;
		} else {
			return PlatformErrorCode.REQUEST_NOT_PARSABLE;
		}
	}

	@Override
	protected HttpStatus httpStatus(TypeMismatchException ex) {
		if (ex.getMostSpecificCause() instanceof IllegalArgumentException) {
			return HttpStatus.NOT_FOUND;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}

	@Override
	protected void processPlatformException(PlatformException platformException, TypeMismatchException ex) {
		String detail = ex.getMostSpecificCause().getMessage();
		platformException.addDetails(detail);
	}

}
