package uk.gov.dvla.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
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

    private Coordinates getCoords() {
        return new Coordinates(longitude, latitude);
    }
}
