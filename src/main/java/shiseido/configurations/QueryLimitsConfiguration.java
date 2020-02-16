package shiseido.configurations;

import io.micronaut.context.annotation.EachProperty;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@EachProperty("query_limits")
public class QueryLimitsConfiguration {
    private String name;
    private Integer max;

    public QueryLimitsConfiguration(@Parameter String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
