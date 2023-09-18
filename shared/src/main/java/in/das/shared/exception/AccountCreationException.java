package in.das.shared.exception;

public class AccountCreationException extends RuntimeException{
    public AccountCreationException(String message){
        super(message);
    }
}
