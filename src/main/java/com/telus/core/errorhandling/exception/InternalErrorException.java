package com.telus.core.errorhandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.telus.core.errorhandling.ErrorCode;

@ResponseStatus(
    value = HttpStatus.INTERNAL_SERVER_ERROR,
    reason = "An internal error occurred."
)
public class InternalErrorException extends PlatformException {
	public InternalErrorException(ErrorCode errorCode, Throwable cause) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, cause);
	}	
}
