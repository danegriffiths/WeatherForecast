package uk.gov.dvla.api;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by dane on 26/10/16.
 */
public  class DatabaseAPI {

    private String city;
    private Timestamp date_time;
    private String temperature;
    private String forecast;

    public DatabaseAPI() { }

    public DatabaseAPI(String city, Timestamp date_time, String temperature, String forecast) {
        this.city = city;
        this.date_time = date_time;
        this.temperature = temperature;
        this.forecast = forecast;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }
}

class DatabaseAPIMapper implements ResultSetMapper<DatabaseAPI>
{
    public DatabaseAPI map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        return new DatabaseAPI(r.getString("city"), r.getTimestamp("date_time"), r.getString("temperature"), r.getString("forecast"));
    }
}