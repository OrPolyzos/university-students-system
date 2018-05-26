package gr.unipi.informatics.services;

import gr.unipi.informatics.domain.Course;
import gr.unipi.informatics.domain.User;
import gr.unipi.informatics.exceptions.InvalidCredentialsException;
import gr.unipi.informatics.exceptions.course.CourseNotFoundException;

import java.util.List;

public interface CourseService {

    Course findOne(Long courseID) throws CourseNotFoundException;

    List<Course> findAll();

    List<Course> findBySemester(int semester);

    List<Course> findByTitle(String title);

    Course save(Course course);

    void deleteByCourseID(Long courseID);
}
