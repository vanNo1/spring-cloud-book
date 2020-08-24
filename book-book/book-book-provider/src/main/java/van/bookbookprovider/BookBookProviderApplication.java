package van.bookbookprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix

public class BookBookProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookBookProviderApplication.class, args);
    }

}
