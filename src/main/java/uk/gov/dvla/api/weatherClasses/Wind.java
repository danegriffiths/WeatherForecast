package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which reads in data from website, and assigns the json property to the specific variables within Wind.
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

    /**
     * Method to ge the wind  information for the time of day for a city.
     * @return a Wind object containing the wind speed and degrees.
     */
    private Wind getWind() {
        return new Wind(windSpeed, windDegrees);
    }
}