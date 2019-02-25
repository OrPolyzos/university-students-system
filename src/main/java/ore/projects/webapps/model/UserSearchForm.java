package ore.projects.webapps.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

public class UserSearchForm {

    @Size(max = 128, message = "The Email should be up to 128 characters!")
    @Email(message = "Not a valid Email address!")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
