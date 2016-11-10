package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which reads in data from website, and assigns the json property to the specific variable within Clouds.
 * Created by dane on 10/10/16.
 */
public class Clouds {
    private int cloudPercentage;

    @JsonCreator
    public Clouds(@JsonProperty("all") int cloudPercentage) {
        this.cloudPercentage=cloudPercentage;
    }

    /**
     * Method to get the cloud percentage at a specific time.
     * @return an integer containing the cloud percentage.
     */
    public int getClouds() {
        return cloudPercentage;
    }
}
