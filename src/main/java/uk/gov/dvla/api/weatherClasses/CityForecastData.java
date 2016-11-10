package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Class which reads in data from website, and assigns the json property to the specific variable within CityForecastData.
 * Created by dane on 10/10/16.
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

    /**
     * Method to return the details of the city.
     * @return object CityDetails.
     */
    public CityDetails getCity() {
        return city;
    }

    /**
     * Method to get the list of forecasts for the city.
     * @return an arraylist which is made of a ListOfForecasts object.
     */
    public ArrayList<ListOfForecasts> getForecasts() {
        return forecasts;
    }
}
