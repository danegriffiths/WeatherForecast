package uk.gov.dvla.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import uk.gov.dvla.weatherClasses.CityForecastData;
import uk.gov.dvla.jdbi.MyDAO;

import java.text.DecimalFormat;

public class WeatherAPI{

    private CityForecastData cfd;
    private int numberOfForecasts;
    private MyDAO dao;
    private DatabaseAPI dbAPI;

    public WeatherAPI() {
        // Jackson deserialization
    }

    public WeatherAPI(CityForecastData cfd) {
        this.cfd=cfd;
        this.numberOfForecasts=cfd.getForecasts().size();
        this.dbAPI = new DatabaseAPI();
    }

    @JsonProperty
    public String getCityName() {

        return cfd.getCity().getCityName();
    }

    @JsonProperty
    public JSONArray get3_Hourly_Weather_Forecast() {

        JSONArray dateTime = new JSONArray();

        for (int i = 0; i < numberOfForecasts; i++) {

            char upperCaseLetter = Character.toUpperCase(cfd.getForecasts().get(i).getWeatherDescription().get(0).
                    getDetailedWeatherType().charAt(0));
            String concatenation = upperCaseLetter + cfd.getForecasts().get(i).getWeatherDescription().get(0).getDetailedWeatherType().substring(1);

            DecimalFormat df2 = new DecimalFormat(".#\u00B0C");

            JSONObject jo = new JSONObject();
            jo.put("date/time",cfd.getForecasts().get(i).getDateTime());
            jo.put("forecast",concatenation);
            jo.put("temp",df2.format(cfd.getForecasts().get(i).getWeatherTemp().getTemperature()));
            jo.put("timestamp",cfd.getForecasts().get(i).getUnixTimeStamp());
            dateTime.add(jo);


        }
        return dateTime;
    }


}
