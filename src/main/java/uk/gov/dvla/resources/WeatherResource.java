package uk.gov.dvla.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.gov.dvla.api.WeatherAPI;
import uk.gov.dvla.api.weatherClasses.URLDataMappedToSubClasses;
import uk.gov.dvla.jdbi.DatabaseWrapper;
import uk.gov.dvla.jdbi.WeatherDAO;
import uk.gov.dvla.views.WeatherView;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

/**
 * Class to connect to a URL or database to retrieve data.
 * Created by dane on 14/10/16.
 */
@Path("weather-info")
public class WeatherResource {

    private WeatherDAO dao;

    public WeatherResource(WeatherDAO dao) {
        this.dao = dao;
    }

    /**
     * Method to get the weather forecast.
     * @param city an optional string which can either be an empty variable or contain a string value.
     * @return an optional weather forecast of the city entered by the user, if there is one available.
     */
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<WeatherAPI> getWeatherInJSON(@QueryParam("city") Optional<String> city) {

        return getForecast(city);
    }

    public Optional<WeatherAPI> getForecast(Optional<String> city) {

        Optional<WeatherAPI> weatherResult = Optional.empty();

        if (city.isPresent()) { //checks if the city has been entered by the user.

            if (dao.findNameById(city.get()) != null) { //checks if the name of the city is already in database.

                if (dao.checkOldestDate(city.get()) == null) { // if name in database, checks if data is out of date.
                    System.out.println("** -- city found in DB, and data in date");
                    weatherResult = getForecastFromDatabase(city.get()); // if so, get data from database.

                    if (!weatherResult.isPresent()) { // if database is empty, go to URL to get data.
                        System.out.println("** -- database empty");
                        weatherResult = getForecastFromURL(city);
                    }
                } else {
                    dao.delete(city.get()); // if data is out of date, delete, then go to URL to update.
                    System.out.println("** -- city found in DB, but data out of date");
                    weatherResult = getForecastFromURL(city);
                }
            } else {
                System.out.println("** -- city not found in DB");
                weatherResult = getForecastFromURL(city); // if name not already in database, then go to URL to get data.
            }
        }
        return weatherResult;
    }

    /**
     * Method to get data from postgres, based on the name of the city being passed in.
     * @param city a string contianing the city name.
     * @return an optional result which will either be empty, or contain a new WeatherAPI.
     */
    private Optional<WeatherAPI> getForecastFromDatabase(String city) {

        System.out.println("** -- getting data from DB");
        List<DatabaseWrapper> listOfDBForecasts = dao.getListFromDB(city);
        return Optional.of(new WeatherAPI(listOfDBForecasts));
    }

    /**
     * Method to get data from URL, based on the optional city being passed in.
     * @param city an optional string which will either be empty or contain a city name.
     * @return an optional result which will either be empty, or contain a new WeatherAPI.
     */
    private Optional<WeatherAPI> getForecastFromURL(Optional<String> city) {

        System.out.println("** -- getting data from URL");
        Optional<WeatherAPI> weatherResult = Optional.empty();
        try {

            final URL url = new URL(String.format("http://api.openweathermap.org/data/2.5/forecast/weather?q=%s&APPID=3e90ae313c2c602239cded5167a9aa54", URLEncoder.encode(city.orElse(""), "UTF-8")));
            final URLConnection urlConnection = url.openConnection();
            ObjectMapper objMapper = new ObjectMapper();
            URLDataMappedToSubClasses dataMappedFromURL = objMapper.readValue(urlConnection.getInputStream(), URLDataMappedToSubClasses.class);

            DecimalFormat formatTemperature = new DecimalFormat("#.#\u00B0C");
            for (int i = 0; i < dataMappedFromURL.getForecasts().size(); i++) {

                String cityName = dataMappedFromURL.getCity().getCityName().toLowerCase();
                String temp = formatTemperature.format(dataMappedFromURL.getForecasts().get(i).getWeatherTemp().getTemperature());
                String forecast = dataMappedFromURL.getForecasts().get(i).getWeatherDescription().get(0).getDetailedWeatherType();
                Timestamp dateTime = Timestamp.valueOf(dataMappedFromURL.getForecasts().get(i).getDateTime());
                dao.insert(cityName, temp, forecast, dateTime);
            }
            weatherResult = Optional.of(new WeatherAPI(dataMappedFromURL));

        } catch (MalformedURLException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            return weatherResult;
        }
    }

    @Path("/browser-view")
    @Produces(MediaType.TEXT_HTML)
    @Timed
    @GET
    public WeatherView getWeather(@QueryParam("city") Optional<String> city) throws IOException {

        return new WeatherView(getForecast(city));
    }
}

