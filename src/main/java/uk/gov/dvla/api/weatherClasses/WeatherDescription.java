package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDescription {

    /**
     * Class which reads in data from website, and assigns the json property to the specific variables within WeatherDescription.
     * Created by dane on 10/10/16.
     */
    private int iD;
    private String weatherType;
    private String detailedWeatherType;
    private String weatherIcon;

    @JsonCreator
    private WeatherDescription(@JsonProperty("id") int iD, @JsonProperty("main") String weatherType, @JsonProperty("description") String detailedWeatherType,
                               @JsonProperty("icon") String weatherIcon) {
        this.iD=iD;
        this.weatherType=weatherType;
        this.detailedWeatherType=detailedWeatherType;
        this.weatherIcon=weatherIcon;
    }

    /**
     * Method to get the detailed forecast at a particular time for a city.
     * @return a String with the forecast.
     */
    public String getDetailedWeatherType() {
        return detailedWeatherType;
    }
}
