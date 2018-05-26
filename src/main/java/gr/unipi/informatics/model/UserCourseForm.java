package gr.unipi.informatics.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserCourseForm {

    private Long userID;

    @NotNull(message = "This field is required!")
    @Pattern(regexp = "^[1-9]|[1-9][0-9]{1,9}", message = "The courseID must be equal or greater than 1!")
    private Long courseID;

    @NotNull(message = "This field is required!")
    @Size(min = 6, max = 16, message = "The room must be up to 6!")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{6}", message = "The room can contain alphanumerical characters!")
    private String room;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
