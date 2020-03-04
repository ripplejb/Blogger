package blogger.repositories.implementations;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import blogger.models.User;
import blogger.repositories.interfaces.UsersRepository;
import io.reactivex.Maybe;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;

@Singleton
public class UsersRepositoryImpl implements UsersRepository {

    @PersistenceContext
    public final EntityManager entityManager;

    public UsersRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public Maybe<User> findByEmailId(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.email = :email",
                User.class
        );
        return Maybe.just(query.setParameter("email", email)
                .getSingleResult());
    }

    @Override
    @Transactional
    public Maybe<User> save(@NotBlank String name, @NotBlank String email) {
        User user = new User(name, email);
        entityManager.persist(user);
        return Maybe.just(user);
    }
}
