package com.telus.core.errorhandling.resource;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.telus.core.errorhandling.exception.PlatformException;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@Schema(description = "The standard Error result returned by the REST error when a problem as occurred.")
public class ErrorResultResource {
	private String id;
	private ErrorResource error;
	private List<Link> links;

	public static ErrorResultResource from(PlatformException platformException) {
		return new ErrorResultResource()
				.id(platformException.id())
				.error(ErrorResource.from(platformException.errorCode())
						.details(platformException.details())
						.fieldErrorsFrom(platformException.fieldErrors()))
				.links(platformException.links());
	}
}
