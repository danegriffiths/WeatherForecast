package uk.gov.dvla;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import uk.gov.dvla.health.TemplateHealthCheck;
import uk.gov.dvla.resources.WeatherResource;

public class WeatherForecastGenerator extends Application<WeatherConfig>{

    public static void main(String[] args) throws Exception {
        new WeatherForecastGenerator().run(args);
    }

    private WeatherForecastGenerator() {

//        con.setDoOutput(true); // we want the response
//        con.setRequestProperty("Cookie", "myCookie=test123");
    }

    public String getName() {
        return "WeatherForecast";
    }

    @Override
    public void initialize(Bootstrap<WeatherConfig> bootstrap) {
    }

    public void run(WeatherConfig configuration, Environment environment) throws Exception{

        final WeatherResource resource = new WeatherResource();
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.jersey().register(resource);
        environment.healthChecks().register("template", healthCheck);
    }

}
