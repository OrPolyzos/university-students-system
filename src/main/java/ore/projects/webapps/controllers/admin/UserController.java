package ore.projects.webapps.controllers.admin;

import ore.projects.webapps.controllers.base.BaseController;
import ore.projects.webapps.converters.UserConverter;
import ore.projects.webapps.domain.User;
import ore.projects.webapps.exceptions.user.DuplicateUserException;
import ore.projects.webapps.exceptions.user.UserNotFoundException;
import ore.projects.webapps.model.UserForm;
import ore.projects.webapps.model.UserSearchForm;
import ore.projects.webapps.services.UserService;
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
public class UserController extends BaseController {

    private static final String USER_FORM = "userForm";
    private static final String SEARCH_FORM = "userSearchForm";
    private static final String USER_LIST = "userList";
    private static final String NOT_FOUND = "searchNotFoundMessage";

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public String showUsersView(Model model) {
        if (!model.containsAttribute(USER_FORM)) {
            model.addAttribute(USER_FORM, new UserForm());
        }
        if (!model.containsAttribute(SEARCH_FORM)) {
            model.addAttribute(SEARCH_FORM, new UserSearchForm());
        }
        return "users";
    }

    @PostMapping(value = "/users/create")
    public String processCreateUser(@Valid @ModelAttribute(USER_FORM) UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + USER_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(USER_FORM, userForm);
            return "redirect:/admin/users";
        }

        try {
            User user = UserConverter.buildInsertUserObject(userForm);
            userService.save(user);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "User was created!");
        } catch (DuplicateUserException dex) {
            redirectAttributes.addFlashAttribute(userForm);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, dex.getMessage());
        }
        return "redirect:/admin/users";
    }

    @PostMapping(value = "/users/delete/{id}")
    public String processDeleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.deleteByUserID(id);
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "User was deleted!");
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/users/search")
    public String processSearchUser(@Valid @ModelAttribute(SEARCH_FORM) UserSearchForm userSearchForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + SEARCH_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(SEARCH_FORM, userSearchForm);
        }
        List<User> usersList;
        if (userSearchForm.getEmail() == null) {
            usersList = userService.findAll();
        } else {
            usersList = userService.findByEmail(userSearchForm.getEmail());
        }
        if (usersList.isEmpty()) {
            redirectAttributes.addFlashAttribute(NOT_FOUND, "No records were found!");
        } else {
            redirectAttributes.addFlashAttribute(USER_LIST, usersList);
        }
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/users/edit/{id}")
    public String showEditUser(@PathVariable Long id, RedirectAttributes redirectAttributes) throws Exception {
        try {
            User user = userService.findOne(id);
            UserForm userForm = UserConverter.buildUserFormObject(user);
            redirectAttributes.addFlashAttribute(userForm);
        } catch (UserNotFoundException unfex) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, unfex.getMessage());
            return "redirect:/admin/users";
        }
        return "redirect:/admin/users/editUser";
    }

    @GetMapping(value = "/users/editUser")
    public String showEditUserView(Model model) {
        if (!model.containsAttribute(USER_FORM)) {
            return "redirect:/admin/users";
        }
        return "editUser";
    }

    @PostMapping(value = "/users/editUser")
    public String processEditUser(@Valid @ModelAttribute(USER_FORM) UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + USER_FORM, bindingResult);
            redirectAttributes.addFlashAttribute(USER_FORM, userForm);
            return "redirect:/admin/users/editUser";
        }
        try {
            User user = UserConverter.buildUpdateUserObject(userForm);
            userService.save(user);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "User was updated");
            return "redirect:/admin/users";
        } catch (Exception duex) {
            redirectAttributes.addFlashAttribute(USER_FORM, userForm);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, duex.getMessage());
            return "redirect:/admin/users/editUser";
        }
    }

}
