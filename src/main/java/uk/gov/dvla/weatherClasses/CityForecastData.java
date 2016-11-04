package uk.gov.dvla.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by dane on 13/10/16.
 */
public class CityForecastData {

    private CityDetails city;
    private int openWeatherInternalParameter;
    private double openWeatherInternalMessage;
    private int countOfCitiesCirculatingCityChoice;
    private ArrayList<ListOfForecasts> forecasts;

    @JsonCreator
    public CityForecastData(@JsonProperty("city") CityDetails city, @JsonProperty("cod") int openWeatherInternalParameter,
                            @JsonProperty("message") double openWeatherInternalMessage, @JsonProperty("cnt") int countOfCitiesCirculatingCityChoice,
                            @JsonProperty("list") ArrayList<ListOfForecasts> forecasts){
        this.city=city;
        this.openWeatherInternalParameter=openWeatherInternalParameter;
        this.openWeatherInternalMessage=openWeatherInternalMessage;
        this.countOfCitiesCirculatingCityChoice=countOfCitiesCirculatingCityChoice;
        this.forecasts=forecasts;
    }

    public CityDetails getCity() {
        return city;
    }

    public int getOpenWeatherInternalParameter() {
        return openWeatherInternalParameter;
    }

    public double getOpenWeatherInternalMessage() {
        return openWeatherInternalMessage;
    }

    public int getCountOfCitiesCirculatingCityChoice() {
        return countOfCitiesCirculatingCityChoice;
    }

    public ArrayList<ListOfForecasts> getForecasts() {
        return forecasts;
    }
}
