package ore.projects.webapps.controllers;

import ore.projects.webapps.security.LoginAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String showIndex() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String view = LoginAuthenticationProvider.getViewDependingOnType(auth);
            if (view != null) {
                return view;
            }
        }
        return "index";
    }
}
