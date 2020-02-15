package shiseido.repositories.implementations;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import shiseido.models.User;
import shiseido.repositories.interfaces.UserRepository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    public final EntityManager entityManager;

    public UserRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmailId(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.email = :email",
                User.class
        );
        return query.setParameter("email", email)
                .getSingleResult();
    }

    @Override
    @Transactional
    public User save(@NotBlank String name, @NotBlank String email) {
        User user = new User(name, email);
        entityManager.persist(user);
        return user;
    }
}
