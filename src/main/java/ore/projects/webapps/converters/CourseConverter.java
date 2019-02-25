package ore.projects.webapps.converters;

import ore.projects.webapps.domain.Course;
import ore.projects.webapps.model.CourseForm;

public class CourseConverter {

    public static Course buildInsertCourseObject(CourseForm courseForm) {
        Course course = new Course();
        setCommon(courseForm, course);
        return course;
    }

    public static Course buildUpdateCourseObject(CourseForm courseForm) {
        Course course = new Course();
        course.setCourseID(courseForm.getCourseID());
        setCommon(courseForm, course);
        return course;
    }

    private static void setCommon(CourseForm courseForm, Course course) {
        course.setTitle(courseForm.getTitle());
        course.setSemester(Integer.valueOf(courseForm.getSemester()));
    }

    public static CourseForm buildCourseFormObject(Course course) {

        CourseForm courseForm = new CourseForm();
        courseForm.setCourseID(course.getCourseID());
        courseForm.setSemester(String.valueOf(course.getSemester()));
        courseForm.setTitle(course.getTitle());
        return courseForm;
    }
}
