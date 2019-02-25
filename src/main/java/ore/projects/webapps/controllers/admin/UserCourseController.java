package ore.projects.webapps.controllers.admin;

import ore.projects.webapps.controllers.base.BaseController;
import ore.projects.webapps.converters.UserCourseConverter;
import ore.projects.webapps.domain.Course;
import ore.projects.webapps.domain.UserCourse;
import ore.projects.webapps.exceptions.user.DuplicateUserException;
import ore.projects.webapps.exceptions.user.UserNotFoundException;
import ore.projects.webapps.model.UserCourseForm;
import ore.projects.webapps.services.CourseService;
import ore.projects.webapps.services.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserCourseController extends BaseController {

    private static final String USER_COURSE_FORM = "userCourseForm";
    private static final String USER_COURSE_LIST = "userCourseList";
    private static final String WHOLE_COURSES_LIST = "wholeCoursesList";

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserCourseService userCourseService;

    @GetMapping(value = "/users/courses/{userID}")
    public String showRepairPartsView(@PathVariable Long userID, Model model) {
        if (!model.containsAttribute(USER_COURSE_FORM)) {
            UserCourseForm userCourseForm = new UserCourseForm();
            userCourseForm.setUserID(String.valueOf(userID));
            model.addAttribute(USER_COURSE_FORM, userCourseForm);
        }
        List<UserCourse> currentUserCourseList = userCourseService.findAllByUserID(userID);
        if (!currentUserCourseList.isEmpty()) {
            model.addAttribute(USER_COURSE_LIST, currentUserCourseList);
        }
        List<Course> wholeCoursesList = courseService.findAll();
        if (!wholeCoursesList.isEmpty()) {
            model.addAttribute(WHOLE_COURSES_LIST, wholeCoursesList);
        }
        return "userCourses";
    }

    @PostMapping(value = "/users/courses/add")
    public String processAddRepairPart(@Valid @ModelAttribute(USER_COURSE_FORM) UserCourseForm userCourseForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + USER_COURSE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(USER_COURSE_FORM, userCourseForm);
            return "redirect:/admin/users/courses/" + userCourseForm.getUserID();
        }
        try {
            UserCourse userCourse = UserCourseConverter.buildUserCourseObject(userCourseForm);
            userCourseService.save(userCourse);
            return "redirect:/admin/users/courses/" + String.valueOf(userCourseForm.getUserID());

        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, exception.getMessage());
            return "redirect:/admin/users/courses/" + userCourseForm.getUserID();
        }
    }

    @PostMapping(value = "/users/courses/delete/{userID}/{courseID}")
    public String processDeleteRepairPart(@PathVariable Long userID, @PathVariable Long courseID, RedirectAttributes redirectAttributes) {
        try {
            userCourseService.deleteByUserIDAndCourseID(userID, courseID);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "The part was deleted from the repair!");
            return "redirect:/admin/users/courses/" + userID;

        } catch (UserNotFoundException | DuplicateUserException exception) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, exception.getMessage());
            return "redirect:/admin/users/courses/" + userID;

        }
    }
}
