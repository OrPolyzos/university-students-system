package gr.unipi.informatics.controllers.admin;

import gr.unipi.informatics.converters.UserCourseConverter;
import gr.unipi.informatics.domain.Course;
import gr.unipi.informatics.domain.UserCourse;
import gr.unipi.informatics.exceptions.user.DuplicateUserException;
import gr.unipi.informatics.exceptions.user.UserNotFoundException;
import gr.unipi.informatics.model.UserCourseForm;
import gr.unipi.informatics.services.CourseService;
import gr.unipi.informatics.services.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UserCourseController {

    private static final String USER_COURSE_FORM = "userCourseForm";
    private static final String USER_COURSE_LIST = "userCourseList";
    private static final String WHOLE_COURSES_LIST = "wholeCoursesList";
    private static final String MESSAGE = "errorMessage";
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserCourseService userCourseService;

    //We will use the @InitBinder annotation and the initBinder method to
    //trim all the user's input from spaces
    //For example if the user enters "    John     " it will be trimmed to "John"
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(value = "/users/courses/{userID}", method = RequestMethod.GET)
    public String showRepairPartsView(@PathVariable Long userID, Model model) {
        Map<String, Object> map = model.asMap();
        //If our Model does not contain a repairPartForm, add a new RepairPartForm()
        if (!map.containsKey(USER_COURSE_FORM)) {
            UserCourseForm userCourseForm = new UserCourseForm();
            userCourseForm.setUserID(userID);
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

    @RequestMapping(value = "/users/courses/add", method = RequestMethod.POST)
    public String processAddRepairPart(@Valid @ModelAttribute(USER_COURSE_FORM) UserCourseForm userCourseForm,
                                       BindingResult bindingResult, Model model,
                                       RedirectAttributes redirectAttributes) {

        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the user again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding repairPartForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + USER_COURSE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(USER_COURSE_FORM, userCourseForm);
            return "redirect:/admin/users/courses/" + userCourseForm.getUserID();
        }
        try {
            //Trying to build a RepairPart object
            UserCourse userCourse = UserCourseConverter.buildUserCourseObject(userCourseForm);
            userCourseService.save(userCourse);
            return "redirect:/admin/users/courses/" + String.valueOf(userCourseForm.getUserID());

        } catch (Exception exception) {
            //if an error occurs show it to the user :(
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/admin/users/courses/" + String.valueOf(userCourseForm.getUserID());
        }
    }

    //The processDeleteUser() method will map "/admin/users/delete/{id}" GET requests and
    //will delete a user and redirect to "/admin/users"
    @RequestMapping(value = "/users/courses/delete/{userID}/{courseID}", method = RequestMethod.POST)
    public String processDeleteRepairPart(@PathVariable Long userID, @PathVariable Long courseID,
                                          RedirectAttributes redirectAttributes) {
        //Delete the user
        try {
            userCourseService.deleteByUserIDAndCourseID(userID, courseID);
            //Send information to the user
            redirectAttributes.addFlashAttribute(MESSAGE, "The part was deleted from the repair!");
            return "redirect:/admin/users/courses/" + String.valueOf(userID);

        } catch (UserNotFoundException | DuplicateUserException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/admin/users/courses/" + String.valueOf(userID);

        }
    }
}
