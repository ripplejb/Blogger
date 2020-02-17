package shiseido.mappers.interfaces;

import io.micronaut.security.authentication.Authentication;
import shiseido.service_contracts.UserContract;

public interface UserContractMapper {
    UserContract fromAuthentication(Authentication authentication);
}
