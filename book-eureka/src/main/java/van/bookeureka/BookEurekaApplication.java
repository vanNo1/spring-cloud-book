package van.bookeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookEurekaApplication.class, args);
    }

}
