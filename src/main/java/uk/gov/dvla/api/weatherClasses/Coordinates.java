package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which reads in data from website, and assigns the json property to the specific variable within Coordinates.
 * Created by dane on 10/10/16.
 */
public class Coordinates {

    private double longitude;
    private double latitude;

    @JsonCreator
    private Coordinates(@JsonProperty("lon") double longitude, @JsonProperty("lat") double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Method to return the longitude and latitude of a city.
     * @return a Coordinates object.
     */
    private Coordinates getCoords() {
        return new Coordinates(longitude, latitude);
    }
}
