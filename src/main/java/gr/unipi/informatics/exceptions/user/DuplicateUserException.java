package gr.unipi.informatics.exceptions.user;

public class DuplicateUserException extends Exception{

    public DuplicateUserException() {
        super();
    }

    public DuplicateUserException(String msg) {
        super(msg);
    }

}
