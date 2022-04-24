package ox.mox.Rest.Repositories.Role;

import ox.mox.Rest.Models.Role;

import org.springframework.stereotype.*;
import javax.persistence.*;


@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void create(Role role) {
        entityManager.persist(role);
    }


    public boolean isRoleExists(Long id) {

        return entityManager
                .find(Role.class, id) != null;
    }


    @Override
    public Role getRolesByIds(String id) {

        return entityManager
                .find(Role.class, Long.valueOf(id));
    }
}

