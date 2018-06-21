package gr.unipi.informatics.domain;

import javax.persistence.*;

@Entity(name = "UserCourses")
@IdClass(UserCourseID.class)
public class UserCourse {

    @Id
    @Column(name = "UserID", nullable = false)
    private Long userID;

    @Id
    @Column(name = "CourseID", nullable = false)
    private Long courseID;

    @Column(name = "Room", nullable = false)
    private String room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID", nullable = false, updatable = false, insertable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CourseID", nullable = false, updatable = false, insertable = false)
    private Course course;

    @Column(name = "Grade")
    private Long grade;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }
}
