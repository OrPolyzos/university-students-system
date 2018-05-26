package gr.unipi.informatics.converters;

import gr.unipi.informatics.domain.Course;
import gr.unipi.informatics.domain.User;
import gr.unipi.informatics.model.CourseForm;
import gr.unipi.informatics.model.UserForm;

public class CourseConverter {

    public static Course buildInsertCourseObject(CourseForm courseForm) {
        Course course = new Course();
        course.setTitle(courseForm.getTitle());
        course.setSemester(Integer.valueOf(courseForm.getSemester()));
        return course;
    }

    public static Course buildUpdateCourseObject(CourseForm courseForm) {
        Course course = new Course();
        course.setCourseID(courseForm.getCourseID());
        course.setTitle(courseForm.getTitle());
        course.setSemester(Integer.valueOf(courseForm.getSemester()));
        return course;
    }

    public static CourseForm buildCourseFormObject(Course course) {

        CourseForm courseForm = new CourseForm();
        courseForm.setCourseID(course.getCourseID());
        courseForm.setSemester(String.valueOf(course.getSemester()));
        courseForm.setTitle(course.getTitle());
        return courseForm;
    }
}
