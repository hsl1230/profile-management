package com.telus.core.errorhandling.errorhandler;

import com.telus.core.errorhandling.exception.PlatformException;
import com.telus.core.errorhandling.resource.ErrorResultResource;
import com.telus.core.errorhandling.resource.HttpErrorHeaders;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true)
public final class ResponseEntityBuilder {
	private PlatformException platformException;
	private HttpHeaders headers;

	public ResponseEntity<ErrorResultResource> build() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.set(HttpErrorHeaders.X_APPLICATION_ERROR_CODE, platformException.errorCode().getCode());
        responseHeaders.set(HttpErrorHeaders.X_APPLICATION_ERROR_MESSAGE, platformException.errorCode().getMessage());
        responseHeaders.set(HttpErrorHeaders.X_APPLICATION_ERROR_DESCRIPTION, platformException.errorCode().getDescription());

        if (headers != null && !headers.isEmpty()) {
            responseHeaders.addAll(headers);
        }

        ErrorResultResource errorResult = ErrorResultResource.from(platformException);

        return new ResponseEntity<>(errorResult, responseHeaders, platformException.httpStatus());

    }
	
}
