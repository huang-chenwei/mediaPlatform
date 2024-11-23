package com.mediaplatform.exception;

import lombok.Getter;


public class CustomException extends RuntimeException {
	
    private final String message;
    
    public CustomException(String message) {
        super(message);
        this.message = message;
    }

	public String getMessage() {
		return message;
	}
    
    
}