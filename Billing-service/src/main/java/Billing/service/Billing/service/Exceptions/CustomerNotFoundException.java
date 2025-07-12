package Billing.service.Billing.service.Exceptions;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}
