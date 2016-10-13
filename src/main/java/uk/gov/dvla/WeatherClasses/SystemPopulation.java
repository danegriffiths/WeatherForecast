package uk.gov.dvla.WeatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemPopulation {

    private int population;

    @JsonCreator
    public SystemPopulation(@JsonProperty("population") int population) {
        this.population=population;
    }

    public int getPopulation() {
        return population;
    }
}


