package blogger.services.users;

import blogger.models.User;
import blogger.repositories.interfaces.UsersRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import io.reactivex.Maybe;
import org.hibernate.exception.SQLGrammarException;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.constraints.NotBlank;

public class UsersServiceImpl implements UsersService {

    @Inject
    private UsersRepository repository;

    private User GetUserIfExists(String email) {
        try {
            return repository.findByEmailId(email);
        } catch (NoResultException | SQLGrammarException nre) {
            return null;
        }

    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmailId(email);
    }

    @Override
    public User save(@NotBlank String name, @NotBlank String email) {
        User user = GetUserIfExists(email);
        if (user == null)
        {
            user = repository.save(name, email);
        }
        return user;
    }
}
