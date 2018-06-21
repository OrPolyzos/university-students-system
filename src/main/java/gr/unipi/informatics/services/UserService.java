package gr.unipi.informatics.services;

import gr.unipi.informatics.domain.User;
import gr.unipi.informatics.exceptions.InvalidCredentialsException;
import gr.unipi.informatics.exceptions.user.DuplicateUserException;
import gr.unipi.informatics.exceptions.user.UserNotFoundException;

import java.util.List;

public interface UserService {

    User findOne(Long userID) throws UserNotFoundException;

    User login(String username, String password) throws InvalidCredentialsException;

    List<User> findAll();

    List<User> findByEmail(String email);

    void save(User user) throws DuplicateUserException;

    void deleteByUserID(Long userID);


}
