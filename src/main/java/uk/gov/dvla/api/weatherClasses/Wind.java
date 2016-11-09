package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dane on 10/10/16.
 */
public class Wind {

    private double windSpeed;
    private int windDegrees;

    @JsonCreator
    private Wind(@JsonProperty("speed") double speed, @JsonProperty("deg") int windDegrees) {
        this.windSpeed=speed;
        this.windDegrees=windDegrees;
    }

    private Wind getWind() {
        return new Wind(windSpeed, windDegrees);
    }
}