package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ListOfForecasts {

    private int unixTimeStamp;
    private TempPressure weatherTemp;
    private ArrayList<WeatherDescription> weatherDescription;
    private Clouds clouds;
    private Wind wind;
    private Rain rain;
    private Snow snow;
    private SystemItem sysItem;
    private String dateTime;

    @JsonCreator
    public ListOfForecasts(@JsonProperty("dt") int unixTimeStamp, @JsonProperty("main") TempPressure weatherTemp,
                           @JsonProperty("weather") ArrayList<WeatherDescription> weatherDescription, @JsonProperty("clouds") Clouds clouds,
                           @JsonProperty("wind") Wind wind, @JsonProperty("rain") Rain rain, @JsonProperty("snow") Snow snow,
                           @JsonProperty("sys") SystemItem sysItem, @JsonProperty("dt_txt") String dateTime) {
        this.unixTimeStamp=unixTimeStamp;
        this.weatherTemp=weatherTemp;
        this.weatherDescription=weatherDescription;
        this.clouds=clouds;
        this.wind=wind;
        this.rain=rain;
        this.snow=snow;
        this.sysItem=sysItem;
        this.dateTime=dateTime;
    }

    public int getUnixTimeStamp() {
        return unixTimeStamp;
    }

    public TempPressure getWeatherTemp() {
        return weatherTemp;
    }

    public ArrayList<WeatherDescription> getWeatherDescription() {
        return weatherDescription;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public Rain getRain() {
        return rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public SystemItem getSysItem() {
        return sysItem;
    }

    public String getDateTime() {
        return dateTime;
    }
}
