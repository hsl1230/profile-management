package com.telus.core.errorhandling.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@Schema(description = "A link the resources that can be looked up by the client.")
public class Link {
	@JsonProperty()
	@Schema(description = "The relation this link represents.")
	private LinkType linkType;

	@JsonProperty()
	@Schema(description = "The actual URI this link is pointing to.")
	private String href;
}
