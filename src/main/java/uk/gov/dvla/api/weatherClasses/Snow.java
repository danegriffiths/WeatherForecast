package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which reads in data from website, and assigns the json property to the specific variables within Snow.
 * Created by dane on 10/10/16.
 */
public class Snow {
    private double snowVolume;
    private double snowDurationInHours;

    @JsonCreator
    private Snow(@JsonProperty("3h") double volume) {
        this.snowVolume=volume;
    }

    /**
     * Method to get the amount of snow due at a specific time in a city.
     * @return a double containing the amount of snow.
     */
    public double getSnowVolume() {
        return snowVolume;
    }
}
