package gr.unipi.informatics.controllers.teacher;

import gr.unipi.informatics.domain.Course;
import gr.unipi.informatics.domain.User;
import gr.unipi.informatics.domain.UserCourse;
import gr.unipi.informatics.exceptions.course.CourseNotFoundException;
import gr.unipi.informatics.exceptions.user.DuplicateUserException;
import gr.unipi.informatics.exceptions.user.UserNotFoundException;
import gr.unipi.informatics.services.CourseService;
import gr.unipi.informatics.services.UserCourseService;
import gr.unipi.informatics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserCourseService userCourseService;
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showDashBoard(Model model) throws UserNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Long userID = (Long) auth.getPrincipal();
            model.addAttribute("user", userService.findOne(userID));
        } else {
            model.addAttribute("errorMessage", "User not logged in anymore!");
        }
        return "teacherDashboard";
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
        return "teacherCoursesPerSemester";
    }

    @RequestMapping("/courses/{courseID}/students")
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
            model.addAttribute("errorMessage", "User not logged in anymore!");
        }
        return "teacherGradesPerStudent";
    }

    @RequestMapping(value = "/courses/{courseID}/students/{studentID}", method = RequestMethod.POST)
    public String showStudentOfCourse(@PathVariable Long studentID, @PathVariable Long courseID, Long grade, Model model) throws UserNotFoundException, CourseNotFoundException, DuplicateUserException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            UserCourse userCourse = userCourseService.findByUserIDAndCourseID(studentID, courseID);
            userCourse.setGrade(grade);
            userCourseService.save(userCourse);
        } else {
            model.addAttribute("errorMessage", "User not logged in anymore!");
        }
        return "teacherGradesPerStudent";
    }
}
