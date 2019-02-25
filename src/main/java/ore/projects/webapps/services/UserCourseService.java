package ore.projects.webapps.services;

import ore.projects.webapps.domain.UserCourse;
import ore.projects.webapps.exceptions.user.DuplicateUserException;
import ore.projects.webapps.exceptions.user.UserNotFoundException;

import java.util.List;

public interface UserCourseService {

    List<UserCourse> findAll();

    List<UserCourse> findAllByUserID(Long repairID);

    List<UserCourse> findAllByCourseID(Long partID);

    UserCourse findByUserIDAndCourseID(Long userID, Long courseID);

    void save(UserCourse repairPart) throws DuplicateUserException, UserNotFoundException;

    void deleteByUserIDAndCourseID(Long repairID, Long CourseID) throws DuplicateUserException, UserNotFoundException;

}
