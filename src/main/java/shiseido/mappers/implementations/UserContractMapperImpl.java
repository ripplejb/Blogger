package shiseido.mappers.implementations;

import io.micronaut.security.authentication.Authentication;
import shiseido.mappers.interfaces.UserContractMapper;
import shiseido.service_contracts.UserContract;

import javax.inject.Singleton;

@Singleton
public class UserContractMapperImpl implements UserContractMapper {

    @Override
    public UserContract fromAuthentication(Authentication authentication) {
        UserContract userContract = new UserContract();
        userContract.setName(authentication.getAttributes().get("name").toString());
        userContract.setPicture(authentication.getAttributes().get("picture").toString());
        return userContract;
    }
}
