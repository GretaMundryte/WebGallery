package lt.webgallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class WaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaApplication.class, args);
    }

}