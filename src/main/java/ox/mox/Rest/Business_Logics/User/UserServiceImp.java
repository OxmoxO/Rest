package ox.mox.Rest.Business_Logics.User;

import ox.mox.Rest.Business_Logics.Role.RoleService;
import ox.mox.Rest.Repositories.User.UserRepository;
import ox.mox.Rest.Models.*;

import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import java.util.*;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImp(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    @Transactional
    public boolean create(User user) {

        if (userRepository
                .readByEmail(user.getEmail()) != null) {

            return false;
        }

        Set<Role> roles = roleService.getRolesByIds(user
                .getRolesSelectedFromForm());

        user.setRoles(roles);

        user.setPassword(passwordEncoder
                .encode(user.getPassword()));

        return userRepository.create(user);
    }


    @Override
    public User readById(Long id) {
        return userRepository.readById(id);
    }


    public User readByUsername(String username) {

        return userRepository
                .readByUsername(username);
    }


    @Override
    @Transactional
    public void update(User userFromFront, Long id) {

        Set<Role> roles = roleService.getRolesByIds(userFromFront
                .getRolesSelectedFromForm());

        userFromFront.setRoles(roles);

        User userFromDB = userRepository.readById(id);

        if (userFromFront.getPassword().length() > 0) {

            userFromFront.setPassword(passwordEncoder
                            .encode(userFromFront.getPassword()));

        } else {
            userFromFront
                    .setPassword(userFromDB.getPassword());
        }

        userRepository.update(userFromFront);
    }


    @Override
    @Transactional
    public boolean delete(Long id) {
        return userRepository.delete(id);
    }

    @Override
    public List<User> getUsers() {

        StringBuilder string = new StringBuilder();

        List<User> dbList = userRepository.getUsers();

        for (User u : dbList) {
            Set<Role> rolesSet = u.getRoles();

            for (Role r : rolesSet) {
               string
                       .append(r.getAuthority()).append(",");
            }

            u.setRolesSelectedFromForm(string.toString());
            string = new StringBuilder();

        }
        return dbList;
    }


}
