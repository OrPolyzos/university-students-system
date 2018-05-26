package gr.unipi.informatics.controllers.admin;

import gr.unipi.informatics.converters.CourseConverter;
import gr.unipi.informatics.domain.Course;
import gr.unipi.informatics.exceptions.course.CourseNotFoundException;
import gr.unipi.informatics.model.CourseForm;
import gr.unipi.informatics.model.CourseSearchForm;
import gr.unipi.informatics.services.CourseService;
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
public class CoursesController {

    private static final String COURSE_FORM = "courseForm";
    private static final String SEARCH_FORM = "courseSearchForm";
    private static final String COURSE_LIST = "courseList";
    private static final String NOT_FOUND = "searchNotFoundMessage";
    private static final String MESSAGE = "errorMessage";
    @Autowired
    private CourseService courseService;

    //We will use the @InitBinder annotation and the initBinder method to
    //trim all the course's input from spaces
    //For example if the course enters "    John     " it will be trimmed to "John"
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    //The showCoursesView method which maps the "/admin/courses/" GET requests and returns the courses.ftl
    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String showCoursesView(Model model) {
        Map<String, Object> map = model.asMap();
        //If our model does not contain a courseForm, add a new CourseForm()
        if (!map.containsKey(COURSE_FORM)) {
            model.addAttribute(COURSE_FORM, new CourseForm());
        }
        //if our model does not contain a searchForm, add a new SearchForm()
        if (!map.containsKey(SEARCH_FORM)) {
            model.addAttribute(SEARCH_FORM, new CourseSearchForm());
        }
        return "courses";
    }

    //The processCreateCourse() method will map "/admin/courses/create" POST requests
    //and eventually it will redirect to "/admin/courses"
    @RequestMapping(value = "/courses/create", method = RequestMethod.POST)
    public String processCreateCourse(@Valid @ModelAttribute(COURSE_FORM) CourseForm courseForm,
                                      BindingResult bindingResult, Model model,
                                      RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the course again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding courseForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + COURSE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(COURSE_FORM, courseForm);
            return "redirect:/admin/courses";
        }

        try {
            //Trying to build a course from our CourseForm
            Course course = CourseConverter.buildInsertCourseObject(courseForm);
            //Save the course
            courseService.save(course);
            //Send information to the course
            redirectAttributes.addFlashAttribute(MESSAGE, "Course was created!");
        } catch (Exception dex) {
            //if an error occurs show it to the course
            redirectAttributes.addFlashAttribute(courseForm);
            redirectAttributes.addFlashAttribute(MESSAGE, dex.getMessage());
        }
        return "redirect:/admin/courses";
    }

    //The processDeleteCourse() method will map "/admin/courses/delete/{id}" GET requests and
    //will delete a course and redirect to "/admin/courses"
    @RequestMapping(value = "/courses/delete/{id}", method = RequestMethod.POST)
    public String processDeleteCourse(@PathVariable Long id,
                                      RedirectAttributes redirectAttributes) {
        //Delete the course
        courseService.deleteByCourseID(id);
        //Send information to the course
        redirectAttributes.addFlashAttribute(MESSAGE, "Course was deleted!");
        return "redirect:/admin/courses";
    }

    //The processSearchCourse() method will map "/courses/search" GET requests and
    //will search for a course by title
    @RequestMapping(value = "/courses/search", method = RequestMethod.GET)
    public String processSearchCourse(@Valid @ModelAttribute(SEARCH_FORM) CourseSearchForm courseSearchForm,
                                      BindingResult bindingResult, Model model,
                                      RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + SEARCH_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(SEARCH_FORM, courseSearchForm);
        }
        //Initialize a new list of Courses to hold the results of the search
        List<Course> courseList;
        //Getting the searchForm values and checking
        //If both are null
        if (courseSearchForm.getTitle() == null) {
            //Then we retrieve all the courses
            courseList = courseService.findAll();
        } else {
            //We search for Courses based on Email
            courseList = courseService.findByTitle(courseSearchForm.getTitle());
        }
        //If the List is Empty
        if (courseList.isEmpty()) {
            //We send Information to the course
            redirectAttributes.addFlashAttribute(NOT_FOUND, "No records were found!");
        } else {
            //else we send the courseList to our courses.ftl
            redirectAttributes.addFlashAttribute(COURSE_LIST, courseList);
        }
        return "redirect:/admin/courses";
    }

    //The showEditCourse() method will map "/courses/edit/{id} GET requests
    //and will try to find a course based on the id and show the editCourse.ftl
    //so that the Admin can edit his details
    @RequestMapping(value = "/courses/edit/{id}", method = RequestMethod.GET)
    public String showEditCourse(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        //Find the course
        try {
            Course course = courseService.findOne(id);
            //Build a courseForm Object based on the course we found
            CourseForm courseForm = CourseConverter.buildCourseFormObject(course);
            //Send the courseForm to the editCourse.ftl
            redirectAttributes.addFlashAttribute(courseForm);
        } catch (CourseNotFoundException unfex) {
            redirectAttributes.addFlashAttribute(MESSAGE, unfex.getMessage());
            return "redirect:/admin/courses";
        }
        return "redirect:/admin/courses/editCourse";
    }

    //the showEditCourseView will map "/courses/editCourse" GET requests
    //and redirect to /admin/courses or show the "editCourse".ftl
    @RequestMapping(value = "/courses/editCourse", method = RequestMethod.GET)
    public String showEditCourseView(Model model) {
        //Get the model
        Map<String, Object> map = model.asMap();
        //If there is not already a CourseForm something went wrong so we redirect
        if (!map.containsKey(COURSE_FORM)) {
            return "redirect:/admin/courses";
        }
        //If there is not CourseForm
        return "editCourse";
    }

    //The processEditCourse() method will map "/courses/editCourse" POST requests
    //and will try to change the details of a Course
    @RequestMapping(value = "/courses/editCourse", method = RequestMethod.POST)
    public String processEditCourse(@Valid @ModelAttribute(COURSE_FORM) CourseForm courseForm,
                                    BindingResult bindingResult, Model model,
                                    RedirectAttributes redirectAttributes) {
        //If something does not pass our @Valid(ations), then this means that our BindingResult
        //object ".hasErrors()" so we will send the course again to the registration form to correct his mistakes
        if (bindingResult.hasErrors()) {
            //Also we will be adding courseForm to RedirectAttributes so that we can keep his valid inputs and reshow them
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + COURSE_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(COURSE_FORM, courseForm);
            return "redirect:/admin/courses/editCourse";
        }
        try {
            //Trying to build a course from our CourseForm
            //Full means we include courseID also
            Course course = CourseConverter.buildUpdateCourseObject(courseForm);
            //Save the course
            courseService.save(course);
            redirectAttributes.addFlashAttribute(MESSAGE, "Course was updated");
            return "redirect:/admin/courses";
        } catch (Exception duex) {
            //if an error occurs show it to the course
            redirectAttributes.addFlashAttribute(COURSE_FORM, courseForm);
            redirectAttributes.addFlashAttribute(MESSAGE, duex.getMessage());
            return "redirect:/admin/courses/editCourse";
        }
    }

}
