package blogger.controllers;

import blogger.models.User;
import com.fasterxml.jackson.annotation.JsonValue;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import blogger.mappers.interfaces.UserContractMapper;
import blogger.service_contracts.UserContract;
import blogger.services.users.UsersService;
import io.reactivex.Maybe;

import javax.annotation.Nullable;
import javax.inject.Inject;

@Controller
public class UsersController {

    @Inject
    private final UsersService userService;
    @Inject
    private final UserContractMapper userContractMapper;

    public UsersController(UsersService userService, UserContractMapper userContractMapper) {
        this.userService = userService;
        this.userContractMapper = userContractMapper;
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @JsonValue
    @Get("/api/users")
    public HttpResponse<UserContract> get(@Nullable Authentication authentication) {

        assert authentication != null;

        userService.save(authentication.getAttributes().get("name").toString(),
                authentication.getAttributes().get("email").toString());
        return HttpResponse.ok(userContractMapper.fromAuthentication(authentication));

    }

}
