package uk.gov.dvla.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by dane on 24/11/16.
 */
public class WeatherAPITests {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final WeatherAPI weather = new WeatherAPI("swansea","2016-10-10 10:30:00", "sunny", "9.5°C");
        assertThat(MAPPER.writeValueAsString(weather)).isEqualTo(MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/weatherJsonFile.json"), WeatherAPI.class)));
    }



    @Test
    public void deserializesFromJSON() throws Exception {

        final WeatherAPI weather = new WeatherAPI("swansea","2016-10-10 10:30:00","sunny","9.5°C");
        assertThat(MAPPER.readValue(fixture("fixtures/weatherJsonFile.json"), WeatherAPI.class).getWeatherForecast()).isEqualTo(weather.getWeatherForecast());
    }

}
