package gr.unipi.informatics.controllers.student;

import gr.unipi.informatics.domain.User;
import gr.unipi.informatics.domain.UserCourse;
import gr.unipi.informatics.exceptions.user.UserNotFoundException;
import gr.unipi.informatics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showDashBoard(Model model) throws UserNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Long userID = (Long) auth.getPrincipal();
            model.addAttribute("user", userService.findOne(userID));
        } else {
            model.addAttribute("errorMessage", "User not logged in anymore!");
        }
        return "studentDashboard";
    }

    @RequestMapping(value = "/semester/{id}", method = RequestMethod.GET)
    public String showPerSemester(@PathVariable Integer id, Model model) throws UserNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Long userID = (Long) auth.getPrincipal();
            User user = userService.findOne(userID);
            model.addAttribute("user", user);
            List<UserCourse> userCourses = user.getUserCourses()
                    .stream()
                    .filter(userCourse -> userCourse.getCourse().getSemester() == id)
                    .collect(Collectors.toList());

            model.addAttribute("userCourses", userCourses);
            model.addAttribute("semester", id);
            model.addAttribute("semesterPlusOne", id + 1);
            model.addAttribute("semesterMinusOne", id - 1);
        } else {
            model.addAttribute("errorMessage", "User not logged in anymore!");
        }
        return "studentCoursesPerSemester";
    }

}
