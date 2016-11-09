package uk.gov.dvla.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import uk.gov.dvla.api.weatherClasses.CityForecastData;
import uk.gov.dvla.jdbi.Database;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class WeatherAPI {

    private CityForecastData cfd;
    private ArrayList<Database> dbapi;
    private String city;
    private String dateTime;
    private String forecast;
    private String temperature;
    private ArrayList<WeatherAPI> weather;

    public WeatherAPI(String city, String dateTime, String forecast, String temperature) {
        this.city=city;
        this.dateTime=dateTime;
        this.forecast=forecast;
        this.temperature=temperature;
    }

    public WeatherAPI(CityForecastData cfd) {
        this.cfd=cfd;
    }

    public WeatherAPI(ArrayList<Database> dbapi) {
        this.dbapi = dbapi;
    }

    public String getCity() {
        return city;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getForecast() {
        return forecast;
    }

    public String getTemperature() {
        return temperature;
    }

    private ArrayList<WeatherAPI> getURLData(CityForecastData cfd) {

        DecimalFormat df2 = new DecimalFormat("#.#\u00B0C");

        for (int i = 0; i < cfd.getForecasts().size(); i++) {
            this.city = cfd.getCity().getCityName();
            this.dateTime = cfd.getForecasts().get(i).getDateTime();
            this.forecast = cfd.getForecasts().get(i).getWeatherDescription().get(0).getDetailedWeatherType();
            this.temperature = df2.format(cfd.getForecasts().get(i).getWeatherTemp().getTemperature());

            weather.add(new WeatherAPI(city, dateTime, forecast, temperature));
        }
        return weather;
    }

    private ArrayList<WeatherAPI> getDBData(ArrayList<Database> db) {

        DecimalFormat df2 = new DecimalFormat("#.#\u00B0C");

        for (int i = 0; i < db.size(); i++) {
            this.city = db.get(i).getCity();
            this.dateTime = db.get(i).getDateTime();
            this.forecast = db.get(i).getForecast();
            this.temperature = df2.format(db.get(i).getTemperature());

            weather.add(new WeatherAPI(city, dateTime, forecast, temperature));
        }
        return weather;
    }

    @JsonProperty
    public JSONArray get3HourlyWeatherForecastFromURL() {

        ArrayList<WeatherAPI> weather;

        if (cfd != null) {
            weather = getURLData(cfd);
        }
        else {
            weather = getDBData(dbapi);
        }

        JSONArray dateTime = new JSONArray();

        for (int i = 0; i < weather.size(); i++) {

            DecimalFormat df2 = new DecimalFormat("#.#\u00B0C");
            JSONObject jo = new JSONObject();
            jo.put("city", weather.get(i).city);
            jo.put("date/time", weather.get(i).dateTime);
            jo.put("forecast", weather.get(i).forecast);
            jo.put("temp", weather.get(i).temperature);
            dateTime.add(jo);
        }
        return dateTime;
    }
}
