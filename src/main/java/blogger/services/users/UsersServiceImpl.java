package blogger.services.users;

import blogger.models.User;
import blogger.repositories.interfaces.UsersRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import io.reactivex.Maybe;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;

public class UsersServiceImpl implements UsersService {

    @Inject
    private UsersRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Maybe<User> findByEmail(String email) {
        return repository.findByEmailId(email);
    }

    @Override
    @Transactional
    public Maybe<User> save(@NotBlank String name, @NotBlank String email) {
        return repository.findByEmailId(email).
                onErrorResumeNext(e -> {
                    System.out.println(e.toString());
                    repository.save(name, email);
                });
    }
}
