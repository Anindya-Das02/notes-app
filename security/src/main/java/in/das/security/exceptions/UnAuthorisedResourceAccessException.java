package in.das.security.exceptions;

public class UnAuthorisedResourceAccessException extends RuntimeException{
    public UnAuthorisedResourceAccessException(String message){
        super(message);
    }
}
