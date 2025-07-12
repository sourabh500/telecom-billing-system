package Plan.Service.Plan.service.Exceptions;

public class PlanNotFoundException extends RuntimeException{

    public PlanNotFoundException(String message)
    {
        super(message);
    }

}
