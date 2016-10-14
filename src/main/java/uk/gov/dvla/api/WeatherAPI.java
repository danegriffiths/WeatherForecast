package uk.gov.dvla.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvla.WeatherClasses.CityDetails;

/**
 * Created by dane on 14/10/16.
 */
public class WeatherAPI {

    private long id;

    public WeatherAPI() {
        // Jackson deserialization
    }

    public WeatherAPI(long id) {
        this.id =id;
    }

    @JsonProperty
    public long getID() {
        return id;
    }

    @JsonProperty
    public String getCityName(CityDetails cityDetails) {
        String cityName = cityDetails.getCityName();
        return cityName;
    }
}
