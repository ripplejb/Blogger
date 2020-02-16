package shiseido.controllers;

import com.fasterxml.jackson.annotation.JsonValue;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import shiseido.services.users.UsersService;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Map;

@Controller
public class UserInfoController {

    @Inject
    private final UsersService userService;

    public UserInfoController(UsersService userService) {
        this.userService = userService;
    }


    @Secured(SecurityRule.IS_AUTHENTICATED)
    @JsonValue
    @Get("/api/userInfo")
    public Map<String, Object> get(@Nullable Authentication authentication) {

        assert authentication != null;

        userService.save(authentication.getAttributes().get("name").toString(),
                authentication.getAttributes().get("email").toString());

        return authentication.getAttributes();

    }

}
