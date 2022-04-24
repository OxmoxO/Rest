package ox.mox.Rest;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication(scanBasePackages = {"ox.mox.Rest"})
public class RestApp {

    public static void main(String[] args) {

        SpringApplication.run(RestApp.class, args);
    }

}
