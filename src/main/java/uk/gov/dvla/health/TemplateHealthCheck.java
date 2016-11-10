package uk.gov.dvla.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Class to health check the functionality of the dropwizard client.
 */
public class TemplateHealthCheck extends HealthCheck {

    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    //@Override
    protected HealthCheck.Result check() throws Exception {
        final String weatherAPI = String.format(template,"TEST");
        if (!weatherAPI.contains("TEST")) {
            return HealthCheck.Result.unhealthy("template doesn't include a name");
        }
        return HealthCheck.Result.healthy();
    }
}
