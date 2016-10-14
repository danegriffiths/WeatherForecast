package uk.gov.dvla.resources;

import com.codahale.metrics.annotation.Timed;
import uk.gov.dvla.api.WeatherAPI;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by dane on 14/10/16.
 */
@Path("/weather-forecast")
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {

    private final String template;
    private final AtomicLong counter;

    public WeatherResource(String template) {
        this.template = template;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public WeatherAPI getWeather(@QueryParam("city") Optional<String> city) {
        final String value = String.format(template, city);
        return new WeatherAPI(counter.incrementAndGet());
    }

    @GET
    @Timed
    @Path("/city={name}")
    public WeatherAPI sayHelloFoo(@PathParam("name") Optional<String> name) {
        final String value = String.format(template, name);
        return new WeatherAPI(counter.incrementAndGet());
    }
}
