package pl.edu.pjatk.luftzug.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={ScheduleNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
