package Billing.service.Billing.service.Exceptions;

public class BillNotFoundException extends RuntimeException{

    public BillNotFoundException(String message)
    {
        super(message);
    }
}
