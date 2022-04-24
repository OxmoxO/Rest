package ox.mox.Rest;

import ox.mox.Rest.Models.Role;
import ox.mox.Rest.Models.User;
import ox.mox.Rest.Business_Logics.Role.RoleService;
import ox.mox.Rest.Business_Logics.User.UserService;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.context.event.*;
import org.springframework.context.event.*;
import org.springframework.stereotype.*;





@Service
public class Init {

    RoleService roleService;
    UserService userService;

    @Autowired
    public Init(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    public Init() {
    }

    @EventListener(ApplicationReadyEvent.class)
    public boolean dbInitial() {

        if (roleService.isRoleExists(1L)) {
            return false;
        }

        Role uRole = new Role(1L, "ROLE_USER");
        Role aRole = new Role(2L, "ROLE_ADMIN");

        roleService.create(uRole);
        roleService.create(aRole);

        User u = new User
                ("user", "user", "user@user.ru", 25);

        User a = new User
                ("admin", "admin", "admin@admin.ru", 35);

        u.setRolesSelectedFromForm("1");
        a.setRolesSelectedFromForm("1,2");
        userService.create(u);
        userService.create(a);
        return true;
    }
}