package lt.webgallery.domain.image.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = "There was a mistake";
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = "There was a mistake";
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
