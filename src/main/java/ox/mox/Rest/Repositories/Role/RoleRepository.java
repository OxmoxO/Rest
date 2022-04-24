package ox.mox.Rest.Repositories.Role;

import ox.mox.Rest.Models.Role;


public interface RoleRepository {

    Role getRolesByIds(String id);

    void create(Role role);

    boolean isRoleExists(Long roleId);
}
