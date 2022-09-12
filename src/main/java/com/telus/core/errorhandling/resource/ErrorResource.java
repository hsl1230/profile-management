package com.telus.core.errorhandling.resource;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.telus.core.errorhandling.ErrorCode;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * A standard error response when there are issue with a REST API request.
 */
@Getter
@Setter
@Accessors(fluent = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@Schema(description = "The standard error response container when there are issue with a REST API request.")
public class ErrorResource {
	@JsonProperty()
	@Schema(description = "The link to the resource producing the error")
	private List<Link> links;

	@JsonProperty()
	@Schema(description = "The error code")
	private String code;

	@JsonProperty()
	@Schema(description = "The error message")
	private String message;

	@JsonProperty()
	@Schema(description = "The error details")
	private List<String> details;

	@JsonProperty()
	@Schema(description = "The details of the validation errors")
	private List<FieldErrorResource> fieldErrors;

	public static ErrorResource from(ErrorCode errorCode) {
		return new ErrorResource()
		.code(errorCode.getCode())
		.message(errorCode.getMessage());
	}

	public ErrorResource fieldErrorsFrom(List<FieldError> fieldErrors) {

    if (fieldErrors != null) {
      List<FieldErrorResource> fieldErrorList = new ArrayList<>();
      for (FieldError fieldError : fieldErrors) {
        FieldErrorResource fer = new FieldErrorResource();
        fer.field(fieldError.getField());
        fer.error(fieldError.getDefaultMessage());
        fieldErrorList.add(fer);
      }
      this.fieldErrors = fieldErrorList;
    }

		return this;
  }
}
