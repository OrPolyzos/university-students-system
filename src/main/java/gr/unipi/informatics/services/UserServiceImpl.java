package gr.unipi.informatics.services;

import gr.unipi.informatics.domain.User;
import gr.unipi.informatics.exceptions.InvalidCredentialsException;
import gr.unipi.informatics.exceptions.user.DuplicateUserException;
import gr.unipi.informatics.exceptions.user.UserNotFoundException;
import gr.unipi.informatics.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(Long userID) throws UserNotFoundException {
        User retrievedUser = userRepository.findOne(userID);
        if (retrievedUser == null) {
            throw new UserNotFoundException("User not found!");
        }
        return retrievedUser;
    }

    @Override
    public User login(String username, String password) throws InvalidCredentialsException {
        User retrievedUser = userRepository.findByEmailAndPassword(username, password);
        if (retrievedUser == null) {
            throw new InvalidCredentialsException("User not found!");
        }

        return retrievedUser;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) throws DuplicateUserException {
        List<User> usersList = userRepository.findByEmail(user.getEmail());
        if (!usersList.isEmpty()) {
            if (!usersList.get(0).getUserID().equals(user.getUserID())) {
                throw new DuplicateUserException("This email already exists!");
            }
        }
        userRepository.save(user);
    }

    @Override
    public void deleteByUserID(Long userID) {
        userRepository.deleteByUserID(userID);
    }
}
