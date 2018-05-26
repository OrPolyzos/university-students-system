package gr.unipi.informatics.controllers.admin;

import gr.unipi.informatics.domain.Course;
import gr.unipi.informatics.domain.User;
import gr.unipi.informatics.services.CourseService;
import gr.unipi.informatics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String adminHome(Model model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Long userID = (Long) auth.getPrincipal();
            User user = userService.findOne(userID);
            model.addAttribute("user", user);
            List<Course> courseList = courseService.findAll();
            model.addAttribute("courseList", courseList);
        } else {
            model.addAttribute("errorMessage", "User not logged in anymore!");
        }
        return "adminIndex";
    }
}
