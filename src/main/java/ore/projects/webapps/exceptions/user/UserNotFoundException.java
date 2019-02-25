package ore.projects.webapps.exceptions.user;

public class UserNotFoundException extends UserException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
