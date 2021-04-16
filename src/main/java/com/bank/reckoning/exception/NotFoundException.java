package com.bank.reckoning.exception;

/**
 * Generated if entity is not found.
 */
public class NotFoundException extends AbstractRuntimeExceptionWithFieldName {

    public NotFoundException(String message, String fieldName) {
        super(message, fieldName);
    }
}