package gr.unipi.informatics.converters;

import gr.unipi.informatics.domain.UserCourse;
import gr.unipi.informatics.model.UserCourseForm;

public class UserCourseConverter {

    public static UserCourse buildUserCourseObject(UserCourseForm courseForm) {
        UserCourse userCourse = new UserCourse();
        userCourse.setCourseID(Long.valueOf(courseForm.getCourseID()));
        userCourse.setUserID(Long.valueOf(courseForm.getUserID()));
        userCourse.setRoom(courseForm.getRoom());
        return userCourse;
    }
}
