package blogger.repositories.interfaces;

import com.sun.istack.NotNull;
import blogger.models.User;
import io.reactivex.Maybe;

import javax.validation.constraints.NotBlank;

public interface UsersRepository {
    Maybe<User> findByEmailId(@NotNull String email);
    Maybe<User> save(@NotBlank String name, @NotBlank String email);
}
