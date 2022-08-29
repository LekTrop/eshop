package ua.hnure.eshop.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.Instant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class that handle all occurred exceptions
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle {@link EntityNotFoundException}
     *
     * @param ex that was thrown
     * @return {@link ResponseEntity<ErrorResponse> with {@link HttpStatus#NOT_FOUND}
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(final EntityNotFoundException ex) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                                                         .errorCode(ex.getErrorCode())
                                                         .message(ex.getMessage())
                                                         .timestamp(Instant.now())
                                                         .build();

        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }
}
