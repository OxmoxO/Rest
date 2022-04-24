package ox.mox.Rest.Repositories.User;

import ox.mox.Rest.Models.User;

import org.hibernate.*;
import org.springframework.stereotype.*;

import javax.persistence.Query;
import javax.persistence.*;
import java.util.*;


@Repository
public class UserRepositoryImp implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public UserRepositoryImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public boolean create(User user) {
        entityManager.persist(user);
        return true;
    }


    @Override
    public User readById(Long id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public User readByUsername(String username) {
        User user = null;

        try {
            user = entityManager
                    .createQuery("from User  where username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (NoResultException e) {

        }
        return user;
    }


    public User readByEmail(String email) {

        User user = null;

        try {
            user = entityManager
                    .createQuery("from User where email = :email", User.class)
                    .setParameter("email", email).getSingleResult();

            Hibernate.initialize(user.getRoles());

        } catch (NoResultException e) {

        }
        return user;
    }


    @Override
    public void update(User user) {
        entityManager.merge(user);
    }


    @Override
    public boolean delete(Long id) {
        Query query = entityManager
                .createQuery("delete from User where id=:id");

        query.setParameter("id", id);
        query.executeUpdate();
        return true;
    }


    @Override
    public List<User> getUsers() {

        Query query = entityManager
                .createQuery("from User");

        return (List<User>) query.getResultList();
    }

}

