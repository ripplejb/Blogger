package shiseido.repositories.interfaces;

import com.sun.istack.NotNull;
import shiseido.models.User;

import javax.validation.constraints.NotBlank;

public interface UsersRepository {
    User findByEmailId(@NotNull String email);
    User save(@NotBlank String name, @NotBlank String email);
}
