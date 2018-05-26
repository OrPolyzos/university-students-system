package gr.unipi.informatics.services;

import gr.unipi.informatics.domain.UserCourse;
import gr.unipi.informatics.exceptions.user.DuplicateUserException;
import gr.unipi.informatics.exceptions.user.UserNotFoundException;

import java.util.List;

public interface UserCourseService {

    List<UserCourse> findAll();

    List<UserCourse> findAllByUserID(Long repairID);

    List<UserCourse> findAllByCourseID(Long partID);

    void save(UserCourse repairPart) throws DuplicateUserException, UserNotFoundException;

    void deleteByUserIDAndCourseID(Long repairID, Long CourseID) throws DuplicateUserException, UserNotFoundException;

}
