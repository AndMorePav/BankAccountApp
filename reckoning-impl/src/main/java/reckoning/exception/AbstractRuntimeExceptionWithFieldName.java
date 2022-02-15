package reckoning.exception;

import lombok.Getter;

/**
 * Base class for exceptions, which containing field indication
 * Used to interpret message and field name, when handling descendant exceptions
 */
@Getter
public abstract class AbstractRuntimeExceptionWithFieldName extends RuntimeException {

    private final String fieldName;

    AbstractRuntimeExceptionWithFieldName(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
}
