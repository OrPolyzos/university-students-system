package ore.projects.webapps.domain;

import java.io.Serializable;


public class UserCourseID implements Serializable {
    private Long userID;
    private Long courseID;

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
}
