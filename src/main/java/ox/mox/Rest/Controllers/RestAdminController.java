package ox.mox.Rest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ox.mox.Rest.Services.User.UserService;
import ox.mox.Rest.Models.User;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/admin/users")
@RestController
public class RestAdminController {

    private final UserService userService;

    @Autowired
    public RestAdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public List<User> getAllUsers() {

        return userService.getUsers();
    }


    @GetMapping("/authUserInfo")
    public User authUserInfo(Principal principal) {

        return userService
                .readByUsername(principal.getName());
    }


    @PostMapping("/create")
    public User create(@RequestBody User user) {
        userService.create(user);

        return  userService
                .readById(user.getId());

    }


    @GetMapping("/read/{id}")
    public User edit(@PathVariable("id") String id) {

        return userService
                .readById(Long.valueOf(id));
    }


    @PutMapping("/edit")
    public User update(@RequestBody User user) {

        userService
                .update(user, user.getId());
        return userService
                .readById(user.getId());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
