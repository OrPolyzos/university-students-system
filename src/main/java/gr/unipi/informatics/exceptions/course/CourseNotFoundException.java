package gr.unipi.informatics.exceptions.course;

public class CourseNotFoundException extends CourseException {

    public CourseNotFoundException() {
        super();
    }

    public CourseNotFoundException(String msg) {
        super(msg);
    }
}
