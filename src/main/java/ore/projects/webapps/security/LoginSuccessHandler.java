package ore.projects.webapps.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final String ADMIN_SUCCESS_URI = "/admin";
    private static final String TEACHER_SUCCESS_URI = "/teacher/dashboard";
    private static final String STUDENT_SUCCESS_URI = "/student/dashboard";

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.stream().findFirst().isPresent()) {
            if (authorities.stream().findFirst().get().toString().equals("Admin")) {
                redirectToSuccessUrl(request, response, ADMIN_SUCCESS_URI);
            } else if (authorities.stream().findFirst().get().toString().equals("Teacher")) {
                redirectToSuccessUrl(request, response, TEACHER_SUCCESS_URI);
            } else if (authorities.stream().findFirst().get().toString().equals("Student")) {
                redirectToSuccessUrl(request, response, STUDENT_SUCCESS_URI);
            }
        }
    }

    private void redirectToSuccessUrl(HttpServletRequest request, HttpServletResponse response, String success_url) throws IOException {
        RedirectStrategy redirectStrategy = super.getRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, success_url);
    }

}
