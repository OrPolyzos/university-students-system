package ore.projects.webapps.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

    private Long userID;

    @NotNull(message = "This field is required!")
    @Size(min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Pattern(regexp = "^[a-zA-Z]{1,128}", message = "Only uppercase and lowercase characters allowed!")
    private String firstName, lastName;

    @NotNull(message = "This field is required!")
    @Size(min = 6, max = 16, message = "The password must be 6-16 characters!")
    @Pattern(regexp = "^[a-zA-Z0-9@#$%^&]{6,16}", message = "The password can contain alphanumerical characters and @#$%^&!")
    private String password;

    @NotNull(message = "This field is required!")
    @Size(min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Email(message = "Not a valid Email address!")
    private String email;

    @NotNull(message = "This field is required!")
    @Pattern(regexp = "^(Admin|Teacher|Student)", message = "Can only be Admin, Teacher or Student!")
    private String type = "User";

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
