package ore.projects.webapps.controllers.student;

import ore.projects.webapps.controllers.base.BaseController;
import ore.projects.webapps.domain.User;
import ore.projects.webapps.exceptions.user.UserNotFoundException;
import ore.projects.webapps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showDashBoard(Model model) throws UserNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userID = (Long) auth.getPrincipal();
        model.addAttribute("user", userService.findOne(userID));
        return "studentDashboard";
    }

    @RequestMapping(value = "/semester/{id}", method = RequestMethod.GET)
    public String showPerSemester(@PathVariable Integer id, Model model) throws UserNotFoundException {
        User user = userService.findOne(getUserId());
        model.addAttribute("user", user);
        manageCoursesPerSemester(id, model, user);
        return "studentCoursesPerSemester";
    }


}
