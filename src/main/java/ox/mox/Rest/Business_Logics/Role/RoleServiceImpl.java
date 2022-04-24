package ox.mox.Rest.Business_Logics.Role;

import ox.mox.Rest.Models.Role;

import org.springframework.stereotype.Service;
import ox.mox.Rest.Repositories.Role.RoleRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public void create(Role role) {
        roleRepository.create(role);
    }


    public Set<Role> getRolesByIds(String rolesIds) {

        HashSet<Role> roles = new HashSet<>();

        String[] arrId = rolesIds.split(",");

        for (String id : arrId) {

            Role role = roleRepository
                    .getRolesByIds(id);
            roles.add(role);
        }
        return roles;
    }


    public boolean isRoleExists(Long roleId) {
        return roleRepository.isRoleExists(roleId);
    }

}
