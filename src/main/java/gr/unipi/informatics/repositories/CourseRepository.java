package gr.unipi.informatics.repositories;

import gr.unipi.informatics.domain.Course;
import gr.unipi.informatics.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    Course findOne(Long courseID);

    List<Course> findAll();

    List<Course> findBySemester(int semester);

    List<Course> findCoursesByTitleContaining(String title);

    Course save(Course course);

    void deleteByCourseID(Long courseID);

}
