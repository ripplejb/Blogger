package blogger.mappers.interfaces;

import io.micronaut.security.authentication.Authentication;
import blogger.service_contracts.UserContract;

public interface UserContractMapper {
    UserContract fromAuthentication(Authentication authentication);
}
