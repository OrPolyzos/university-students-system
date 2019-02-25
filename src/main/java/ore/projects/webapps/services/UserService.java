package ore.projects.webapps.services;

import ore.projects.webapps.domain.User;
import ore.projects.webapps.exceptions.InvalidCredentialsException;
import ore.projects.webapps.exceptions.user.DuplicateUserException;
import ore.projects.webapps.exceptions.user.UserNotFoundException;

import java.util.List;

public interface UserService {

    User findOne(Long userID) throws UserNotFoundException;

    User login(String username, String password) throws InvalidCredentialsException;

    List<User> findAll();

    List<User> findByEmail(String email);

    void save(User user) throws DuplicateUserException;

    void deleteByUserID(Long userID);


}
