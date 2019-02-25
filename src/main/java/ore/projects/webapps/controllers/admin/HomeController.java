package ore.projects.webapps.controllers.admin;

import ore.projects.webapps.controllers.base.BaseController;
import ore.projects.webapps.domain.Course;
import ore.projects.webapps.domain.User;
import ore.projects.webapps.services.CourseService;
import ore.projects.webapps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class HomeController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "")
    public String adminHome(Model model) throws Exception {
        User user = userService.findOne(getUserId());
        model.addAttribute("user", user);
        List<Course> courseList = courseService.findAll();
        model.addAttribute("courseList", courseList);
        return "adminIndex";
    }
}
