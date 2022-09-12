package com.telus.core.errorhandling.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.PlatformErrorCode;
import com.telus.core.errorhandling.resource.Link;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class PlatformException extends RuntimeException {
	private String id;
	private HttpStatus httpStatus;

	@Setter(AccessLevel.NONE)
	private ErrorCode errorCode;

	private List<String> details;
	private List<FieldError> fieldErrors;
	private List<Link> links;

	public PlatformException(
			HttpStatus status,
			ErrorCode errorCode,
			Throwable cause) {
		this(status, errorCode, null, cause);
	}

	public PlatformException(
			HttpStatus status,
			ErrorCode errorCode,
			List<FieldError> fieldErrors,
			Throwable cause) {
		super(ObjectUtils
				.defaultIfNull(errorCode, PlatformErrorCode.INTERNAL_ERROR)
				.getMessage(),
				cause);
		this.errorCode = ObjectUtils
				.defaultIfNull(errorCode, PlatformErrorCode.INTERNAL_ERROR);
		this.httpStatus = status;
		this.fieldErrors = Optional.ofNullable(fieldErrors).orElse(new ArrayList<>());
		this.details = new ArrayList<>();
		if (this.errorCode.getDescription() != null) {
			this.details.add(this.errorCode.getDescription());
			if (cause != null) {
				this.details.add(cause.getMessage());
			}
		}
	}

	public PlatformException addFieldError(FieldError fieldError) {
		this.fieldErrors = Optional.ofNullable(this.fieldErrors).orElse(new ArrayList<>());
		this.fieldErrors.add(fieldError);
		return this;
	}

	public PlatformException addDetails(String detail) {
		this.details = Optional.ofNullable(this.details).orElse(new ArrayList<>());
		this.details.add(detail);
		return this;
	}

	public PlatformException addLink(Link link) {
		this.links = Optional.ofNullable(this.links).orElse(new ArrayList<>());
		this.links.add(link);
		return this;
	}
}
