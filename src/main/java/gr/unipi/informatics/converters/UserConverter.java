package gr.unipi.informatics.converters;

import gr.unipi.informatics.domain.User;
import gr.unipi.informatics.model.UserForm;

public class UserConverter {

    public static User buildInsertUserObject(UserForm userForm) {
        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setType(userForm.getType());
        return user;
    }

    public static User buildUpdateUserObject(UserForm userForm) {
        User user = new User();
        //This is what we need for the update (Primary Key)
        user.setUserID(userForm.getUserID());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setType(userForm.getType());
        return user;
    }

    public static UserForm buildUserFormObject(User user) {
        UserForm userForm = new UserForm();
        //User Stuff
        userForm.setUserID(user.getUserID());
        userForm.setPassword(user.getPassword());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setEmail(user.getEmail());
        userForm.setType(user.getType());
        //Address Stuff
        return userForm;
    }
}
