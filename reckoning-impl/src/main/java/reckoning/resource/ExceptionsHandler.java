package reckoning.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reckoning.exception.RepeatPasswordNotSameException;

/**
 * Exceptions handler.
 */
@ControllerAdvice
public class ExceptionsHandler {

    /**
     * Method for handling repeatPasswordNotSameException
     *
     * @param repeatPasswordNotSameException for work with exception
     * @return response object
     */
    @ExceptionHandler(value = RepeatPasswordNotSameException.class)
    public ResponseEntity<Object> passwordNotSameException(RepeatPasswordNotSameException repeatPasswordNotSameException) {
        return new ResponseEntity<>(repeatPasswordNotSameException.getCause(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> argumentNotValidException(MethodArgumentNotValidException argumentNotValidException) {
        return new ResponseEntity<>(argumentNotValidException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
