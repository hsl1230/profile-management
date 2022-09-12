package com.telus.core.errorhandling.errorhandler;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;

public class MissingServletRequestParameterExceptionHandler
		extends AbstractExceptionHandler<MissingServletRequestParameterException> {

	@Override
	protected ErrorCode errorCode(MissingServletRequestParameterException exception) {
		return PlatformErrorCode.INVALID_FIELD;
	}

	@Override
	protected HttpStatus httpStatus(MissingServletRequestParameterException exception) {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	protected List<FieldError> fieldErrors(MissingServletRequestParameterException ex) {
		return Collections.singletonList(
				new FieldError(
						"object",
						ex.getParameterName(),
						"Value is required."));
	}
}
