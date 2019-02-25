package ore.projects.webapps.controllers.admin;

import ore.projects.webapps.controllers.base.BaseController;
import ore.projects.webapps.converters.CourseConverter;
import ore.projects.webapps.domain.Course;
import ore.projects.webapps.exceptions.course.CourseNotFoundException;
import ore.projects.webapps.model.CourseForm;
import ore.projects.webapps.model.CourseSearchForm;
import ore.projects.webapps.services.CourseService;
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
public class CourseController extends BaseController {

    private static final String COURSE_FORM = "courseForm";
    private static final String SEARCH_FORM = "courseSearchForm";
    private static final String COURSE_LIST = "courseList";
    private static final String NOT_FOUND = "searchNotFoundMessage";

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses")
    public String showCoursesView(Model model) {
        if (!model.containsAttribute(COURSE_FORM)) {
            model.addAttribute(COURSE_FORM, new CourseForm());
        }
        if (!model.containsAttribute(SEARCH_FORM)) {
            model.addAttribute(SEARCH_FORM, new CourseSearchForm());
        }
        return "courses";
    }

    @PostMapping(value = "/courses/create")
    public String processCreateCourse(@Valid @ModelAttribute(COURSE_FORM) CourseForm courseForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + COURSE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(COURSE_FORM, courseForm);
            return "redirect:/admin/courses";
        }

        try {
            Course course = CourseConverter.buildInsertCourseObject(courseForm);
            courseService.save(course);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Course was created!");
        } catch (Exception dex) {
            redirectAttributes.addFlashAttribute(courseForm);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, dex.getMessage());
        }
        return "redirect:/admin/courses";
    }

    @PostMapping(value = "/courses/delete/{id}")
    public String processDeleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        courseService.deleteByCourseID(id);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Course was deleted!");
        return "redirect:/admin/courses";
    }

    @GetMapping(value = "/courses/search")
    public String processSearchCourse(@Valid @ModelAttribute(SEARCH_FORM) CourseSearchForm courseSearchForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + SEARCH_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(SEARCH_FORM, courseSearchForm);
        }
        List<Course> courseList;
        if (courseSearchForm.getTitle() == null) {
            courseList = courseService.findAll();
        } else {
            courseList = courseService.findByTitle(courseSearchForm.getTitle());
        }
        if (courseList.isEmpty()) {
            redirectAttributes.addFlashAttribute(NOT_FOUND, "No records were found!");
        } else {
            redirectAttributes.addFlashAttribute(COURSE_LIST, courseList);
        }
        return "redirect:/admin/courses";
    }

    @GetMapping(value = "/courses/edit/{id}")
    public String showEditCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Course course = courseService.findOne(id);
            CourseForm courseForm = CourseConverter.buildCourseFormObject(course);
            redirectAttributes.addFlashAttribute(courseForm);
        } catch (CourseNotFoundException unfex) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, unfex.getMessage());
            return "redirect:/admin/courses";
        }
        return "redirect:/admin/courses/editCourse";
    }

    @GetMapping(value = "/courses/editCourse")
    public String showEditCourseView(Model model) {
        if (!model.containsAttribute(COURSE_FORM)) {
            return "redirect:/admin/courses";
        }
        return "editCourse";
    }

    @PostMapping(value = "/courses/editCourse")
    public String processEditCourse(@Valid @ModelAttribute(COURSE_FORM) CourseForm courseForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + COURSE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(COURSE_FORM, courseForm);
            return "redirect:/admin/courses/editCourse";
        }
        try {
            Course course = CourseConverter.buildUpdateCourseObject(courseForm);
            courseService.save(course);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Course was updated");
            return "redirect:/admin/courses";
        } catch (Exception duex) {
            redirectAttributes.addFlashAttribute(COURSE_FORM, courseForm);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, duex.getMessage());
            return "redirect:/admin/courses/editCourse";
        }
    }

}
