package by.antohakon.jdbctest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @AllArgsConstructor
    @Getter
    private static class ErrorResponse {

        private String message;
        private Instant timestamp;
        private String errorType;

    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> taskNotFoundException(RuntimeException ex) {
        log.error("Book not found: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        ex.getMessage(),
                        Instant.now(),
                        ex.getClass().getSimpleName()
                ));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> duplicateKeyException(RuntimeException ex) {
        log.error("Duplicate key: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("такая книга уже существует");
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        ex.getMessage(),
                        Instant.now(),
                        ex.getClass().getSimpleName()
                ));
    }

}
