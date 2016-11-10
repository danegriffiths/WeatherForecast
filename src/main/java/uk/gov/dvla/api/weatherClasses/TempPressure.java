package uk.gov.dvla.api.weatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which reads in data from website, and assigns the json property to the specific variables within TempPressure.
 * Created by dane on 10/10/16.
 */
public class TempPressure {

    private double temperature;
    private double minimumTemp;
    private double maximumTemp;
    private double pressure;
    private double seaLevel;
    private double groundLevel;
    private int humidity;
    private double tempKF;

    @JsonCreator
    public TempPressure(@JsonProperty("temp") double temperature, @JsonProperty("temp_min") double minimumTemp,
                        @JsonProperty("temp_max") double maximumTemp, @JsonProperty("pressure") double pressure,
                        @JsonProperty("sea_level") double seaLevel, @JsonProperty("grnd_level") double groundLevel,
                        @JsonProperty("humidity") int humidity, @JsonProperty("temp_kf") double tempKF) {
        this.temperature=temperature;
        this.minimumTemp=minimumTemp;
        this.maximumTemp=maximumTemp;
        this.pressure=pressure;
        this.seaLevel=seaLevel;
        this.groundLevel=groundLevel;
        this.humidity=humidity;
        this.tempKF=tempKF;
    }

    /**
     * Method to get the temperate at a specific time in a city.
     * @return a double containing the temperature.
     */
    public double getTemperature() {
        return temperature -273.15;
    }
}
