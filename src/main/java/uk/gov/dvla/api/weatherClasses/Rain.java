package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which reads in data from website, and assigns the json property to the specific variables within Rain.
 * Created by dane on 10/10/16.
 */
public class Rain {

    private double rainVolume;
    private double rainDurationInHours;

    @JsonCreator
    public Rain(@JsonProperty("3h") double volume) {
        this.rainVolume=volume;
    }

    /**
     * Method to get the amount of rain due at a specific time in a city.
     * @return a double containing the amount of rain.
     */
    public double getRainVolume() {
        return rainVolume;
    }
}
