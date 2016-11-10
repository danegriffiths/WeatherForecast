package uk.gov.dvla.jdbi;

import java.sql.Timestamp;

/**
 * A class which wraps rows of data from the SQL database into a java object.
 */
public class DatabaseWrapper {

    private String city;
    private Timestamp date_time;
    private String temperature;
    private String forecast;

    public DatabaseWrapper(String city, Timestamp date_time, String temperature, String forecast) {
        this.city = city;
        this.date_time = date_time;
        this.temperature = temperature;
        this.forecast = forecast;
    }

    /**
     * Method to get the city name from the DatabaseWrapper.
     * @return a String with the city name.
     */
    public String getCity() {
        return city;
    }

    /**
     * Method to get the date and time from the DatabaseWrapper, and convert this from a Timestamp to a String.
     * @return a String with the date/time.
     */
    public String getDateTime() {
        return date_time.toString();
    }

    /**
     * Method to get the temperature name from the DatabaseWrapper.
     * @return a String with the temperature.
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Method to get the forecast from the DatabaseWrapper.
     * @return a String with the forecast.
     */
    public String getForecast() {
        return forecast;
    }
}

