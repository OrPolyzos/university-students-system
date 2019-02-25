package ore.projects.webapps.exceptions.user;

public class DuplicateUserException extends Exception {

    public DuplicateUserException() {
        super();
    }

    public DuplicateUserException(String msg) {
        super(msg);
    }

}
