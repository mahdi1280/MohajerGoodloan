package ir.mohajer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(value = "ir")
@SpringBootApplication
@EntityScan({"ir.mohajer"})
@EnableJpaRepositories("ir")
public class MohajerProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MohajerProjectApplication.class, args);
    }

}
