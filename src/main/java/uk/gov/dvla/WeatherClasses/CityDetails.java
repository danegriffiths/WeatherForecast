package uk.gov.dvla.WeatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
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

    public int getCityID() {
        return cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    public SystemPopulation getSystPop() {
        return systPop;
    }
}

