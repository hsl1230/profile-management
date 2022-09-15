package com.telus.core.errorhandling.exception;

import com.telus.core.errorhandling.PlatformErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.NOT_FOUND,
    reason = "Entity not found."
)
public class EntityNotFoundException extends PlatformException {
	public EntityNotFoundException(String msg) {
		super(HttpStatus.NOT_FOUND, PlatformErrorCode.ENTITY_NOT_FOUND, null);
		this.addDetails(msg);
	}	
}
