package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dane on 10/10/16.
 */
class Clouds {
    private int cloudPercentage;

    @JsonCreator
    public Clouds(@JsonProperty("all") int cloudPercentage) {
        this.cloudPercentage=cloudPercentage;
    }

    public int getClouds() {
        return cloudPercentage;
    }
}
