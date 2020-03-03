package blogger.services.users;

import blogger.models.User;

import javax.validation.constraints.NotBlank;

public interface UsersService {

    User findByEmail(String email);
    User save(@NotBlank String name, @NotBlank String email);

}
