package ore.projects.webapps.converters;

import ore.projects.webapps.domain.UserCourse;
import ore.projects.webapps.model.UserCourseForm;

public class UserCourseConverter {

    public static UserCourse buildUserCourseObject(UserCourseForm courseForm) {
        UserCourse userCourse = new UserCourse();
        userCourse.setCourseID(Long.valueOf(courseForm.getCourseID()));
        userCourse.setUserID(Long.valueOf(courseForm.getUserID()));
        userCourse.setRoom(courseForm.getRoom());
        return userCourse;
    }
}
