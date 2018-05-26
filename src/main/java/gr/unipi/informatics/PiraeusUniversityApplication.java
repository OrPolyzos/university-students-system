package gr.unipi.informatics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = {PiraeusUniversityApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
@ComponentScan("gr.unipi.*")
public class PiraeusUniversityApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiraeusUniversityApplication.class, args);
    }
}
