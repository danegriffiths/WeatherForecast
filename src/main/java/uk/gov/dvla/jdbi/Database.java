package uk.gov.dvla.jdbi;

import java.sql.Timestamp;

/**
 * Created by dane on 26/10/16.
 */
public  class Database {

    private String city;
    private Timestamp date_time;
    private String temperature;
    private String forecast;
    private MyDAO mydao;

    public Database(MyDAO mydao) {
        this.mydao = mydao;
    }

    public Database(String city, Timestamp date_time, String temperature, String forecast) {
        this.city = city;
        this.date_time = date_time;
        this.temperature = temperature;
        this.forecast = forecast;
    }

    public String getCity() {
        return city;
    }

    public String getDateTime() {
        return date_time.toString();
    }

    public String getTemperature() {
        return temperature;
    }

    public String getForecast() {
        return forecast;
    }
}

