package uk.gov.dvla.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import uk.gov.dvla.api.weatherClasses.CityForecastData;
import uk.gov.dvla.jdbi.DatabaseWrapper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A class which is constructed in several ways, which prints the 5 day weatherList forecast of a city to the web browser.
 */
public class WeatherAPI {

    private String city;
    private String dateTime;
    private String forecast;
    private String temperature;
    private List<WeatherAPI> weatherList;

    private WeatherAPI(String city, String dateTime, String forecast, String temperature) {
        this.city=city;
        this.dateTime=dateTime;
        this.forecast=forecast;
        this.temperature=temperature;
    }

    private WeatherAPI() {
        weatherList = new ArrayList<>();
    }

    /**
     * Create a Weather API based on data from the website.
     * @param dataFromURL a CityForecast object which contains deserialised JSON data.
     */
    public WeatherAPI(CityForecastData dataFromURL) {
        this();
        weatherList = populateWithURLData(dataFromURL);
    }

    /**
     * Create a Weather API based on data from the database.
     * @param dataFromDatabase a DatabaseWrapper object which contains data already saved to the database.
     */
    public WeatherAPI(List<DatabaseWrapper> dataFromDatabase) {
        this();
        weatherList = getDBData(dataFromDatabase);
    }

    /**
     * Method to get the name of the city.
     * @return string containing city name.
     */
    private String getCity() {
        return city;
    }

    /**
     * Method to get the date/time of day.
     * @return string containing date and time.
     */
    private String getDateTime() {
        return dateTime;
    }

    /**
     * Method to get the forecast at the time of day.
     * @return string containing the forecast.
     */
    private String getForecast() {
        return forecast;
    }

    /**
     * Method to get the temperature at the time of day.
     * @return string containing temperature.
     */
    private String getTemperature() {
        return temperature;
    }

    /**
     * Method to get convert the deserialised JSON data into a WeatherAPI object..
     * @return a list of type WeatherAPI containing the entire forecast for a city.
     */
    private List<WeatherAPI> populateWithURLData(CityForecastData cfd) {

        DecimalFormat df2 = new DecimalFormat("#.#\u00B0C");

        for (int i = 0; i < cfd.getForecasts().size(); i++) {
            this.city = cfd.getCity().getCityName();
            this.dateTime = cfd.getForecasts().get(i).getDateTime();
            this.forecast = cfd.getForecasts().get(i).getWeatherDescription().get(0).getDetailedWeatherType();
            this.temperature = df2.format(cfd.getForecasts().get(i).getWeatherTemp().getTemperature());

            weatherList.add(new WeatherAPI(city, dateTime, forecast, temperature));
        }
        return weatherList;
    }

    /**
     * Method to get convert the data from the database into a WeatherAPI object..
     * @return a list of type WeatherAPI containing the entire forecast for a city.
     */
    private List<WeatherAPI> getDBData(List<DatabaseWrapper> db) {

        DecimalFormat df2 = new DecimalFormat("#.#\u00B0C");

        for (DatabaseWrapper aDb : db) {
            this.city = aDb.getCity().trim();
            this.dateTime = aDb.getDateTime().trim();
            this.forecast = aDb.getForecast().trim();
            this.temperature =aDb.getTemperature().trim();

            weatherList.add(new WeatherAPI(city, dateTime, forecast, temperature));
        }
        return weatherList;
    }

    /**
     * Method which converts the WeatherAPI objects into JSON format, and adds them to a JSON array, which will print the object to the browser.
     * @return a JSON array containing the weather forecast data.
     */
    @JsonProperty
    public JSONArray getWeatherForecast() {

        JSONArray jsonArray = new JSONArray();

        for (WeatherAPI listOfForecasts : weatherList) {

            //DecimalFormat df2 = new DecimalFormat("#.#\u00B0C");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("city", listOfForecasts.getCity());
            jsonObject.put("date/time", listOfForecasts.getDateTime());
            jsonObject.put("forecast", listOfForecasts.getForecast());
            jsonObject.put("temp", listOfForecasts.getTemperature());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
