package Billing.service.Billing.service.Exceptions;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BillNotFoundException.class)
    public ResponseEntity<?> handleBillNotFoundException(BillNotFoundException exception)
    {
        return new ResponseEntity<>(buildResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException exception)
    {
        return new ResponseEntity<>(buildResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<?> handleFeignNotFoundException(FeignException.NotFound ex)
    {

        return new ResponseEntity<>(buildResponse("Customer not found in Customer Service"),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        return new ResponseEntity<>(buildResponse("Internal Server Error: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> buildResponse(String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);
        return body;
    }

}
