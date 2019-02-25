package ore.projects.webapps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = {UniversityStudentSystemApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
@ComponentScan("ore.projects.webapps.**")
public class UniversityStudentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityStudentSystemApplication.class, args);
    }
}
