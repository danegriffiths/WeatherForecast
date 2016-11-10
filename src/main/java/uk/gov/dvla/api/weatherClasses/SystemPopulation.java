package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which reads in data from website, and assigns the json property to the specific variable within SystemPopulation.
 * Created by dane on 10/10/16.
 */
public class SystemPopulation {

    private int population;

    @JsonCreator
    public SystemPopulation(@JsonProperty("population") int population) {
        this.population=population;
    }

    /**
     * Method to return the system population of the city.
     * @return an integer containing the data.
     */
    public int getPopulation() {
        return population;
    }
}


