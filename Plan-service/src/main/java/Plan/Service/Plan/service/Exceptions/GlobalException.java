package Plan.Service.Plan.service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(PlanNotFoundException.class)
    public ResponseEntity<String> handlePlanNotFoundException(PlanNotFoundException exception)
    {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception)
    {
        return new ResponseEntity<>("Something went wrong " +exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
