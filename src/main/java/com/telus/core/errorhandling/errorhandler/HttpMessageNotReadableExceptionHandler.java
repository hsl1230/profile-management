package com.telus.core.errorhandling.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.telus.core.errorhandling.PlatformErrorCode;
import com.telus.core.errorhandling.exception.PlatformException;
import com.telus.core.errorhandling.resource.ErrorResultResource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ErrorHandler
public class HttpMessageNotReadableExceptionHandler extends ExceptionHandler {

	@Override
	public Class<? extends Throwable> exceptionClass() {
		return HttpMessageNotReadableException.class;
	}

	@Override
	public ResponseEntity<ErrorResultResource> handleException(Exception ex) {
		if (ex.getCause() instanceof InvalidFormatException) {
			InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();
			final Class<?> targetType = invalidFormatException.getTargetType();
			if (targetType != null) {
				if (targetType.isEnum()) {
					return handleInvalidFormatEnumException(invalidFormatException);
				} else if ("UUID".equals(targetType.getSimpleName())) {
					handleInvalidUuidFormatException(invalidFormatException);
				}
			}
		}
		return new ResponseEntityBuilder().platformException(
				new PlatformException(
						HttpStatus.BAD_REQUEST,
						PlatformErrorCode.REQUEST_NOT_PARSABLE,
						null,
						ex))
				.build();
	}

	private ResponseEntity<ErrorResultResource> handleInvalidUuidFormatException(
			InvalidFormatException invalidFormatException) {

		String invalidUuid = invalidFormatException.getValue().toString();
		String fieldName = retrieveInvalidFormatExceptionFieldName(invalidFormatException);
		String message = String.format("Invalid format for UUID %s", invalidUuid);

		return new ResponseEntityBuilder().platformException(
				new PlatformException(
						HttpStatus.BAD_REQUEST,
						PlatformErrorCode.INVALID_FIELD,
						Collections.singletonList(new FieldError(invalidUuid, fieldName, message)),
						invalidFormatException))
				.build();
	}

	private ResponseEntity<ErrorResultResource> handleInvalidFormatEnumException(
			InvalidFormatException invalidFormatException) {

		final Class<?> targetType = invalidFormatException.getTargetType();
		List<FieldError> fieldErrorList = retrieveFieldErrorList(invalidFormatException, targetType);

		return new ResponseEntityBuilder().platformException(
				new PlatformException(
						HttpStatus.BAD_REQUEST,
						PlatformErrorCode.INVALID_FIELD,
						fieldErrorList,
						invalidFormatException))
				.build();
	}

	private static List<FieldError> retrieveFieldErrorList(InvalidFormatException invalidFormatException,
			final Class<?> targetType) {

		String invalidValue = invalidFormatException.getValue().toString();
		String invalidFieldName = retrieveInvalidFormatExceptionFieldName(invalidFormatException);
		String message = String.format("invalid value '%s', valid values are %s", invalidValue,
				Arrays.asList(targetType.getEnumConstants()));

		return Collections.singletonList(new FieldError(invalidValue, invalidFieldName, message));
	}

	private static String retrieveInvalidFormatExceptionFieldName(final InvalidFormatException ifx) {

		List<JsonMappingException.Reference> referenceList = ifx.getPath();

		if (referenceList == null) {
			return "";
		}

		return referenceList.stream()
				.map(JsonMappingException.Reference::getFieldName)
				.collect(Collectors.joining("."));
	}

}
