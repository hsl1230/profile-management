package com.telus.core.errorhandling.errorhandler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;

public class MethodArgumentNotValidExceptionHandler extends AbstractExceptionHandler<MethodArgumentNotValidException> {

	@Override
	protected ErrorCode errorCode(MethodArgumentNotValidException exception) {
		return PlatformErrorCode.INVALID_FIELD;
	}

	@Override
	protected HttpStatus httpStatus(MethodArgumentNotValidException exception) {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	protected List<FieldError> fieldErrors(MethodArgumentNotValidException ex) {
		return ex.getFieldErrors();
	}

}
