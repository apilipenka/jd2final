package by.pwt.pilipenko.jd2final.services.exceptions;

/**
 * Created by Darrko on 28.08.2016.
 */
public class AccountNotFoundException extends Exception {
    private Exception exception;

    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

}
