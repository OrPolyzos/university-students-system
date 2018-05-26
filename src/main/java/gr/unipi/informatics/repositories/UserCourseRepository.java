package gr.unipi.informatics.repositories;

import gr.unipi.informatics.domain.UserCourse;
import gr.unipi.informatics.domain.UserCourseID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends CrudRepository<UserCourse, UserCourseID> {

    List<UserCourse> findAll();

    List<UserCourse> findAllByUserID(Long userID);

    List<UserCourse> findAllByCourseID(Long courseID);

    UserCourse save(UserCourse userCourse);

    void deleteByUserIDAndCourseID(Long userID, Long courseID);

}
