package gr.unipi.informatics.domain;

import javax.persistence.*;
import java.util.List;

/*
    A course has a name and a semester for now to identify it.
    In the future description could be added along with other stuff.
 */
@Entity(name = "Courses")
public class Course {

    @Id
    @Column(name = "CourseID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseID;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "semester", nullable = false)
    private int semester;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", targetEntity = UserCourse.class)
    private List<UserCourse> userCourses;

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

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List<UserCourse> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(List<UserCourse> userCourses) {
        this.userCourses = userCourses;
    }
}
