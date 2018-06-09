package razvan.Tema_finala.model.exception;

public class AuthorizationException extends RuntimeException{

    public AuthorizationException(String message){
        super(message);
    }
}
