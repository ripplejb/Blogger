package shiseido.repositories.interfaces;

import com.sun.istack.NotNull;
import shiseido.models.User;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public interface UserRepository {
    User findByEmailId(@NotNull String email);
    User save(@NotBlank String name, @NotBlank String email);
}
