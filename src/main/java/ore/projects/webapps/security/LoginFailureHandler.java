package ore.projects.webapps.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception) throws IOException {
        if (exception instanceof UsernameNotFoundException) {
            request.getSession().setAttribute("errorMessage", "User not found");
        }

        RedirectStrategy redirectStrategy = super.getRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, "/login");
    }

}
