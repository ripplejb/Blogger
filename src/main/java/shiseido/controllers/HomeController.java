package shiseido.controllers;

import com.fasterxml.jackson.annotation.JsonValue;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @Secured(SecurityRule.IS_ANONYMOUS)
    @JsonValue
    @Get("/api/home/{name}")
    public Map<String, String> index(String name) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Hello", name);
        return map;
    }

}
