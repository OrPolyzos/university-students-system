package ore.projects.webapps.converters;

import ore.projects.webapps.domain.User;
import ore.projects.webapps.model.UserForm;

public class UserConverter {

    public static User buildInsertUserObject(UserForm userForm) {
        User user = new User();
        setCommon(userForm, user);
        return user;
    }

    public static User buildUpdateUserObject(UserForm userForm) {
        User user = new User();
        user.setUserID(userForm.getUserID());
        setCommon(userForm, user);
        return user;
    }

    private static void setCommon(UserForm userForm, User user) {
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setType(userForm.getType());
    }

    public static UserForm buildUserFormObject(User user) {
        UserForm userForm = new UserForm();
        userForm.setUserID(user.getUserID());
        userForm.setPassword(user.getPassword());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setEmail(user.getEmail());
        userForm.setType(user.getType());
        return userForm;
    }
}
