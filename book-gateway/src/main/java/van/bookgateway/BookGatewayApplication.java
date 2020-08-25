package van.bookgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class BookGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookGatewayApplication.class, args);
    }

}
