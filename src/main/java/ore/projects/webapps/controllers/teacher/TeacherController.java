package ore.projects.webapps.controllers.teacher;

import ore.projects.webapps.controllers.base.BaseController;
import ore.projects.webapps.domain.Course;
import ore.projects.webapps.domain.User;
import ore.projects.webapps.domain.UserCourse;
import ore.projects.webapps.exceptions.course.CourseNotFoundException;
import ore.projects.webapps.exceptions.user.DuplicateUserException;
import ore.projects.webapps.exceptions.user.UserNotFoundException;
import ore.projects.webapps.services.CourseService;
import ore.projects.webapps.services.UserCourseService;
import ore.projects.webapps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/dashboard")
    public String showDashBoard(Model model) throws UserNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userID = (Long) auth.getPrincipal();
        model.addAttribute("user", userService.findOne(userID));
        return "teacherDashboard";
    }

    @GetMapping(value = "/semester/{id}")
    public String showPerSemester(@PathVariable Integer id, Model model) throws UserNotFoundException {
        User user = userService.findOne(getUserId());
        model.addAttribute("user", user);
        manageCoursesPerSemester(id, model, user);
        return "teacherCoursesPerSemester";
    }

    @GetMapping("/courses/{courseID}/students")
    public String showStudentOfCourse(@PathVariable Long courseID, Model model) throws UserNotFoundException, CourseNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Long userID = (Long) auth.getPrincipal();
            User user = userService.findOne(userID);
            Course course = courseService.findOne(courseID);
            List<UserCourse> studentCourses = userCourseService.findAllByCourseID(courseID)
                    .stream()
                    .filter(userCourse -> userCourse.getUser().getType().equals("Student"))
                    .collect(Collectors.toList());
            model.addAttribute("course", course);
            model.addAttribute("user", user);
            model.addAttribute("studentCourses", studentCourses);
        } else {
            model.addAttribute(ERROR_MESSAGE, "User not logged in anymore!");
        }
        return "teacherGradesPerStudent";
    }

    @PostMapping(value = "/courses/{courseID}/students/{studentID}")
    public String showStudentOfCourse(@PathVariable Long studentID, @PathVariable Long courseID, Long grade, Model model) throws UserNotFoundException, CourseNotFoundException, DuplicateUserException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            UserCourse userCourse = userCourseService.findByUserIDAndCourseID(studentID, courseID);
            userCourse.setGrade(grade);
            userCourseService.save(userCourse);
        } else {
            model.addAttribute(ERROR_MESSAGE, "User not logged in anymore!");
        }
        return "teacherGradesPerStudent";
    }
}
