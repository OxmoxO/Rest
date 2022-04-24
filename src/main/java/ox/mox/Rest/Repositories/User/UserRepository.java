package ox.mox.Rest.Repositories.User;

import ox.mox.Rest.Models.User;
import java.util.*;

public interface UserRepository {
    boolean create(User user);

    User readById(Long id);

    User readByUsername(String username);

    User readByEmail(String email);

    void update(User user);

    boolean delete(Long id);

    List<User> getUsers();

}
