package uk.gov.dvla.WeatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dane on 10/10/16.
 */
public class Snow {
    private double snowVolume;
    private double snowDurationInHours;

    @JsonCreator
    private Snow(@JsonProperty("3h") double volume) {
        this.snowVolume=volume;
    }

    public double getSnowVolume() {
        return snowVolume;
    }
}
