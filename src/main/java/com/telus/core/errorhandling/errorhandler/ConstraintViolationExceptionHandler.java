package com.telus.core.errorhandling.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import com.telus.core.errorhandling.PlatformErrorCode;
import com.telus.core.errorhandling.exception.PlatformException;
import com.telus.core.errorhandling.resource.ErrorResultResource;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ErrorHandler
public class ConstraintViolationExceptionHandler extends ExceptionHandler {

	@Override
	public Class<? extends Throwable> exceptionClass() {
		return ConstraintViolationException.class;
	}

	@Override
	public ResponseEntity<ErrorResultResource> handleException(Exception exception) {
		ConstraintViolationException ex = (ConstraintViolationException) exception;
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		List<FieldError> fieldErrors = violations.stream()
				.map(violation -> new FieldError(
						violation.getRootBeanClass().getName(),
						violation.getPropertyPath().toString(),
						violation.getInvalidValue(),
						false,
						null,
						null,
						violation.getMessage()))
				.toList();

		PlatformException platformException = new PlatformException(
				HttpStatus.BAD_REQUEST,
				PlatformErrorCode.INVALID_FIELD,
				fieldErrors,
				ex);
		return new ResponseEntityBuilder().platformException(platformException).build();
	}

}
