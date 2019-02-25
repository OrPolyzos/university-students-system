package ore.projects.webapps.services;

import ore.projects.webapps.domain.Course;
import ore.projects.webapps.exceptions.course.CourseNotFoundException;

import java.util.List;

public interface CourseService {

    Course findOne(Long courseID) throws CourseNotFoundException;

    List<Course> findAll();

    List<Course> findByTitle(String title);

    Course save(Course course);

    void deleteByCourseID(Long courseID);
}
