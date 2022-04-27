package ox.mox.Rest.Services.Role;

import ox.mox.Rest.Models.Role;

import java.util.*;

public interface RoleService {

    Set<Role> getRolesByIds(String id);

    void create(Role role);

    boolean isRoleExists(Long roleId);
}
