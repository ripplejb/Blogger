package shiseido.services.users;

import com.sun.istack.NotNull;
import shiseido.models.User;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public interface UsersService {
    User findByEmailId(@NotNull String email);
    User save(@NotBlank String name, @NotBlank String email);

}
