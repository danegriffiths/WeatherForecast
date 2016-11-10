package uk.gov.dvla;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import uk.gov.dvla.jdbi.MyDAO;
import uk.gov.dvla.health.TemplateHealthCheck;
import uk.gov.dvla.resources.WeatherResource;

/**
 * The main class which implements the jersey api by passing in a DBI and DAO.
 */
public class WeatherForecastGenerator extends Application<WeatherConfig> {

    public static void main(String[] args) throws Exception {
        new WeatherForecastGenerator().run(args);

    }

    private WeatherForecastGenerator() {
        /*HOW TO GET COOKIES
        con.setDoOutput(true); // we want the response
        con.setRequestProperty("Cookie", "myCookie=test123");
        */
    }

    public String getName() {
        return "WeatherForecast";
    }

    @Override
    public void initialize(Bootstrap<WeatherConfig> bootstrap) {
    }

    public void run(WeatherConfig configuration, Environment environment) throws Exception {

        // database
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final MyDAO dao = jdbi.onDemand(MyDAO.class);
        final WeatherResource weatherResource = new WeatherResource(dao);
        environment.jersey().register(weatherResource);

        // resources
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        //environment.jersey().register(weatherResource);
        environment.healthChecks().register("template", healthCheck);
    }

}
