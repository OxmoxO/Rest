package ox.mox.Rest.Controllers;

import ox.mox.Rest.Business_Logics.User.UserService;
import ox.mox.Rest.Models.User;

import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class RestUserController {

    private final UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/userInfo")
    public User read(Authentication auth) {
        return userService
                .readByUsername(auth.getName());
    }

}
