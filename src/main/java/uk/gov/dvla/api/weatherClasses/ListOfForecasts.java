package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Class which reads in data from website, and assigns the json property to the specific variables within ListOfForecasts.
 * Created by dane on 10/10/16.
 */
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

    /**
     * Method to get the temperature/pressure of a city at a given time.
     * @return a TempPressure obect.
     */
    public TempPressure getWeatherTemp() {
        return weatherTemp;
    }

    /**
     * Method to ge tthe weather description of the cirty at a given time.
     * @return an arraylist made up of a WeatherDescription object.
     */
    public ArrayList<WeatherDescription> getWeatherDescription() {
        return weatherDescription;
    }

    /**
     * Method to ge the date/time of the forecast.
     * @return a string containing the date and time.
     */
    public String getDateTime() {
        return dateTime;
    }
}
