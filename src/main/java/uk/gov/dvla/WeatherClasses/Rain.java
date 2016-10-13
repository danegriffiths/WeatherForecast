package uk.gov.dvla.WeatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dane on 10/10/16.
 */
public class Rain {

    private double rainVolume;
    private double rainDurationInHours;

    @JsonCreator
    private Rain(@JsonProperty("3h") double volume) {
        this.rainVolume=volume;
    }

    public double getRainVolume() {
        return rainVolume;
    }

    public double getRainDurationInHours() {
        return rainDurationInHours;
    }


}
