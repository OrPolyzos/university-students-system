package ore.projects.webapps.controllers.base;

import ore.projects.webapps.domain.User;
import ore.projects.webapps.domain.UserCourse;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

public class BaseController {

    public final String ERROR_MESSAGE = "errorMessage";

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    public Long getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (Long) auth.getPrincipal();
    }

    public void manageCoursesPerSemester(@PathVariable Integer id, Model model, User user) {
        List<UserCourse> userCourses = user.getUserCourses()
                .stream()
                .filter(userCourse -> userCourse.getCourse().getSemester() == id)
                .collect(Collectors.toList());

        model.addAttribute("userCourses", userCourses);
        model.addAttribute("semester", id);
        model.addAttribute("semesterPlusOne", id + 1);
        model.addAttribute("semesterMinusOne", id - 1);
    }

}
