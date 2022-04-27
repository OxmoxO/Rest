package ox.mox.Rest;

import ox.mox.Rest.Models.Role;
import ox.mox.Rest.Models.User;
import ox.mox.Rest.Services.Role.RoleService;
import ox.mox.Rest.Services.User.UserService;

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
        User tom = new User
                ("tom", "tom", "tom@tom.ru", 25);

        User afan = new User
                ("afan", "afan", "afan@afan.ru", 35);

        u.setRolesSelectedFromForm("1");
        a.setRolesSelectedFromForm("1,2");
        tom.setRolesSelectedFromForm("1,2");
        afan.setRolesSelectedFromForm("1");

        userService.create(afan);
        userService.create(tom);
        userService.create(u);
        userService.create(a);
        return true;
    }
}