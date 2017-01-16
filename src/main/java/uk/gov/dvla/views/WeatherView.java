package uk.gov.dvla.views;

import io.dropwizard.views.View;
import org.json.simple.JSONObject;
import uk.gov.dvla.api.WeatherAPI;

import java.io.FileNotFoundException;
import java.util.Optional;

/**
 * Created by dane on 09/01/17.
 */
public class WeatherView extends View {

    private final JSONObject json;

    public WeatherView(Optional<WeatherAPI> json) throws FileNotFoundException {
        super("weather.mustache");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("city",json.get().getCity());
        jsonObject.put("forecasts",json.get().getWeatherForecast());
        System.out.println(jsonObject);
        this.json = jsonObject;
    }

    public JSONObject getWeatherForecast() {
        return json;
    }
}