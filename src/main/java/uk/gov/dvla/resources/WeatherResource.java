package uk.gov.dvla.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.gov.dvla.WeatherClasses.CityForecastData;
import uk.gov.dvla.api.WeatherAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Optional;

/**
 * Created by dane on 14/10/16.
 */
@Path("weather-info")
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {

    public WeatherResource() {
    }

    @GET
    @Timed
    public WeatherAPI getWeather(@QueryParam("city") Optional<String> city) {
        try {
            final URL url = new URL(String.format("http://api.openweathermap.org/data/2.5/forecast/weather?q=%s&APPID=3e90ae313c2c602239cded5167a9aa54", URLEncoder.encode( city.orElse(""), "UTF-8" ) ) );
            final URLConnection urlConn=url.openConnection();
            ObjectMapper objMap = new ObjectMapper();
            CityForecastData cfd = objMap.readValue(urlConn.getInputStream(),CityForecastData.class);
            final String value = String.valueOf(city);
            return new WeatherAPI(value, cfd);
        }
        catch (MalformedURLException ex ) {
            return null;
        }
        catch (UnsupportedEncodingException uce) {
            return null;
        }
        catch (IOException e) {
            return null;
        }
    }
}
