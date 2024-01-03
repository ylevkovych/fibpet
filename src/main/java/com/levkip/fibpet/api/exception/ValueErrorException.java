package com.levkip.fibpet.api.exception;

public class ValueErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public ValueErrorException(String message) {
        super(message);
    }
}
