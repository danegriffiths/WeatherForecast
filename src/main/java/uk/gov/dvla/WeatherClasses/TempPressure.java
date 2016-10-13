package uk.gov.dvla.WeatherClasses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    public double getTemperature() {
        return temperature -273.15;
    }

    public double getMinimumTemp() {
        return minimumTemp;
    }

    public double getMaximumTemp() {
        return maximumTemp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public double getGroundLevel() {
        return groundLevel;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTempKF() {
        return tempKF;
    }

}
