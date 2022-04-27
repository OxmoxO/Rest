package ox.mox.Rest.Services.User;

import ox.mox.Rest.Models.User;

import java.util.*;

public interface UserService {

    boolean create(User user);

    User readById(Long id);

    User readByUsername(String username);

    void update(User user, Long id);

    boolean delete(Long id);

    List<User> getUsers();

}
