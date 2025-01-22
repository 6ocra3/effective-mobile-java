package effective.mobile.tracker.exceptions;

public class NotFoundByLoginException extends RuntimeException {
    public <T> NotFoundByLoginException(Class<T> clazz, String login){
        super("User with login = " + login + " not found");
    }
}