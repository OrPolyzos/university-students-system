package ore.projects.webapps.repositories;

import ore.projects.webapps.domain.UserCourse;
import ore.projects.webapps.domain.UserCourseID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends CrudRepository<UserCourse, UserCourseID> {

    List<UserCourse> findAll();

    List<UserCourse> findAllByUserID(Long userID);

    List<UserCourse> findAllByCourseID(Long courseID);

    UserCourse findByUserIDAndCourseID(Long userID, Long courseID);

    UserCourse save(UserCourse userCourse);

    void deleteByUserIDAndCourseID(Long userID, Long courseID);

}
