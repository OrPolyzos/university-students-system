package gr.unipi.informatics.services;

import gr.unipi.informatics.domain.Course;
import gr.unipi.informatics.exceptions.course.CourseNotFoundException;
import gr.unipi.informatics.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course findOne(Long courseID) throws CourseNotFoundException {
        Course retrievedCourse = courseRepository.findOne(courseID);
        if (retrievedCourse == null) {
            throw new CourseNotFoundException("Course not found!");
        }
        return retrievedCourse;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findBySemester(int semester) {
        return courseRepository.findBySemester(semester);
    }

    @Override
    public List<Course> findByTitle(String title) {
        return courseRepository.findCoursesByTitleContaining(title);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteByCourseID(Long courseID) {
        courseRepository.deleteByCourseID(courseID);
    }

}
