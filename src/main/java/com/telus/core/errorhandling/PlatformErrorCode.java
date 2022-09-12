package com.telus.core.errorhandling;

public enum PlatformErrorCode implements ErrorCode {
	INTERNAL_ERROR("1000", "Internal Error", "An internal error occurred."),
	DATABASE_ERROR("1002", "Database Error", "An internal database error occurred."),
	SERVICE_UNAVAILABLE("1010", "Service unavailable", "The service is currently unavailable"),
	UNSUPPORTED_METHOD("5005", "Unsupported method",
			"You submitted an an invalid or unsupported method with your request."),
	ITEM_NOT_FOUND("5016", "Item not found", "The item provided cannot be found."),
	ITEM_DISABLED("5017", "Item disabled", "The item provided is disabled."),
	REQUEST_NOT_PARSABLE("5023", "Request body not parsable", "The request is not parseable."),
	INVALID_FIELD("5068", "Field error(s)", "Either you submitted a request that is "
			+ "missing a mandatory field or the value of a field does not match the format expected."),
	INVALID_REQUEST_HEADER("5070", "Invalid request header", "Either you submitted a request that is "
			+ "missing a mandatory request or the value of the header does not match the format expected."),
	ENTITY_NOT_FOUND("5269", "Entity not found",
			"The ID(s) specified in the URL do not correspond to the values in the system."),
	UNSUPPORTED_RESPONSE_FORMAT("5271", "Unsupported 'Accept' header",
			"You requested a response in the 'Accept' header that is in an unsupported format."),
	UNSUPPORTED_CONTENT_TYPE("5272", "Unsupported 'Content-Type'",
			"The 'Content-Type' in the request header is an unsupported format."),
	NOT_SUPPORTED("5281", "Not supported",
			"The server does not support the request method for the given resource."),
	DUPLICATE_ENTITY("5284", "Duplicate entity",
			"An entity with the specified identifier(s) already exists."),
	;

	private final String code;
	private final String message;
	private final String description;

	PlatformErrorCode(String code, String message, String description) {
		this.code = code;
		this.message = message;
		this.description = description;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append(this.getClass().getName())
				.append("@")
				.append(Integer.toHexString(this.hashCode()))
				.append("[")
				.append("code=").append(this.code)
				.append(",message=").append(this.message)
				.append(",description=").append(this.description)
				.append(",name=").append(this.name())
				.append(",ordinal=").append(this.ordinal())
				.append("]")
				.toString();
	}
}
