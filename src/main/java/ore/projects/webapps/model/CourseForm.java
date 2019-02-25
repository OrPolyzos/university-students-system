package ore.projects.webapps.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CourseForm {

    private Long courseID;

    @NotNull(message = "This field is required!")
    @Size(min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,128}", message = "Only numbers, uppercase and lowercase characters allowed!")
    private String title;

    @NotNull(message = "This field is required!")
    @Size(min = 1, max = 1, message = "Semester should be 1 digit!")
    @Pattern(regexp = "^[1-8]", message = "Semester should contain only digits!")
    private String semester;

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
