package gr.unipi.informatics.security;

import com.google.common.collect.ImmutableList;
import gr.unipi.informatics.domain.User;
import gr.unipi.informatics.exceptions.InvalidCredentialsException;
import gr.unipi.informatics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    public static String getViewDependingOnType(Authentication auth) {
        if (auth != null) {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            if (authorities.stream().findFirst().isPresent()) {
                if (authorities.stream().findFirst().get().toString().equals("Admin")) {
                    return "redirect:/admin";
                } else if (authorities.stream().findFirst().get().toString().equals("Teacher")) {
                    return "redirect:/teacher";
                } else if (authorities.stream().findFirst().get().toString().equals("Student")){
                    return "redirect:/student";
                }
            }
        }
        return null;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws InvalidCredentialsException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User retrievedUser = userService.login(username, password);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(retrievedUser.getType());
        return new UsernamePasswordAuthenticationToken(retrievedUser.getUserID(), password, ImmutableList.of(grantedAuthority));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}