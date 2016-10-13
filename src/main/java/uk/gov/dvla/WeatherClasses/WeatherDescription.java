package uk.gov.dvla.WeatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDescription {

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

    public int getiD() {
        return iD;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public String getDetailedWeatherType() {
        return detailedWeatherType;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }
}
