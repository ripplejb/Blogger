package blogger.services.users;

import blogger.models.User;
import io.reactivex.Maybe;

import javax.validation.constraints.NotBlank;

public interface UsersService {

    Maybe<User> findByEmail(String email);
    Maybe<User> save(@NotBlank String name, @NotBlank String email);

}
