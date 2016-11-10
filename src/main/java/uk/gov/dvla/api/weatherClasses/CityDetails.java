package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which reads in data from website, and assigns the json property to the specific variable within CityDetails.
 * Created by dane on 10/10/16.
 */
public class CityDetails {

    private int cityID;
    private String cityName;
    private Coordinates coords;
    private String country;
    private int population;
    private SystemPopulation systPop;

    @JsonCreator
    public CityDetails(@JsonProperty("id") int cityID, @JsonProperty("name") String cityName, @JsonProperty("coord")
                        Coordinates coords, @JsonProperty("country") String country, @JsonProperty("population")
                        int population, @JsonProperty("sys") SystemPopulation systPop) {
        this.cityID=cityID;
        this.cityName=cityName;
        this.coords=coords;
        this.country=country;
        this.population=population;
        this.systPop=systPop;
    }

    /**
     * Method to get the name of the city.
     * @return a string containing the city name.
     */
    public String getCityName() {
        return cityName;
    }
}

