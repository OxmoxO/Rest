package ox.mox.Rest.Configurations;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        ViewControllerRegistration admin = registry.
                addViewController("admin");

        admin.setViewName("/admin");

        ViewControllerRegistration login = registry
                .addViewController("login");

        login.setViewName("/login");

        ViewControllerRegistration user = registry
                .addViewController("user");

        user.setViewName("/user");
    }
}
