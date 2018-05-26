package gr.unipi.informatics.controllers;

import gr.unipi.informatics.model.LoginForm;
import gr.unipi.informatics.security.LoginAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final String LOGIN_FORM = "loginForm";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model,
                        @RequestParam(name = "error", required = false) String error,
                        HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String view = LoginAuthenticationProvider.getViewDependingOnType(auth);
            if (view != null) {
                return view;
            }
        }
        if (error != null) {
            model.addAttribute("errorMessage", "User not found! Please try again");
        }
        model.addAttribute(LOGIN_FORM, new LoginForm());
        return "login";
    }

}
