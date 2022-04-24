package ox.mox.Rest.Business_Logics.Role;

import ox.mox.Rest.Models.Role;

import java.util.*;

public interface RoleService {

    Set<Role> getRolesByIds(String id);

    void create(Role role);

    boolean isRoleExists(Long roleId);
}
