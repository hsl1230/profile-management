package com.telus.core.errorhandling.errorhandler;

import com.telus.core.errorhandling.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.telus.core.errorhandling.PlatformErrorCode;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ErrorHandler
public class ConstraintViolationExceptionHandler extends AbstractExceptionHandler<ConstraintViolationException> {

	@Override
	protected ErrorCode errorCode(ConstraintViolationException exception) {
		return PlatformErrorCode.INVALID_FIELD;
	}

	@Override
	protected HttpStatus httpStatus(ConstraintViolationException exception) {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	protected List<FieldError> fieldErrors(ConstraintViolationException exception) {
		Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		return violations.stream()
				.map(violation -> new FieldError(
						violation.getRootBeanClass().getName(),
						violation.getPropertyPath().toString(),
						violation.getInvalidValue(),
						false,
						null,
						null,
						violation.getMessage()))
				.toList();
	}

}
