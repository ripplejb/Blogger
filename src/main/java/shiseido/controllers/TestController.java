package shiseido.controllers;

import com.fasterxml.jackson.annotation.JsonValue;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller
public class TestController {

//    @Inject
//    public PgPool client;

    @Secured(SecurityRule.IS_ANONYMOUS)
    @JsonValue
    @Get("/api/test")
    public String index() {

        return "Waiting...";

    }
}
