package uk.gov.dvla.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.gov.dvla.api.WeatherAPI;
import uk.gov.dvla.api.weatherClasses.CityForecastData;
import uk.gov.dvla.jdbi.Database;
import uk.gov.dvla.jdbi.MyDAO;

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
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by dane on 14/10/16.
 */
@Path("weather-info")
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {

    private MyDAO dao;

    public WeatherResource(MyDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    public Optional<WeatherAPI> getWeather(@QueryParam("city") Optional<String> city) {

        return getForecast(city);
    }

    /**
     * Method to obtain weather forecast.
     * @param city
     * @return
     */
    private Optional<WeatherAPI> getForecast(Optional<String> city) {

        Optional<WeatherAPI> weatherResult = Optional.empty();
        if (city.isPresent()) {
            weatherResult = getForecastFromDatabase(city.get());
        }

        else if (!weatherResult.isPresent()/** || dao.oldestDate(city.get()) != null*/) {
            dao.delete(city.get());
            weatherResult = getForecastFromURL(weatherResult, city);
        }
        return weatherResult;
    }

    /**
     * Method to get data from PostgreSQL.
     * @param city dsfsd
     * @return weatherResult
     */
    private Optional<WeatherAPI> getForecastFromDatabase(String city) {

        ArrayList<Database> ldbapi = new ArrayList<>(dao.getListFromDB(city));

        return Optional.of(new WeatherAPI(ldbapi));
    }

    /**
     * Method to get data from URL.
     * @param city sdf
     * @return d
     */
    private Optional<WeatherAPI> getForecastFromURL(Optional<WeatherAPI> weatherResult, Optional<String> city) {

        try {
            final URL url = new URL(String.format("http://api.openweathermap.org/data/2.5/forecast/weather?q=%s&APPID=3e90ae313c2c602239cded5167a9aa54", URLEncoder.encode(city.orElse(""), "UTF-8")));
            final URLConnection urlConn = url.openConnection();
            ObjectMapper objMap = new ObjectMapper();
            CityForecastData cfd = objMap.readValue(urlConn.getInputStream(), CityForecastData.class);

            DecimalFormat df2 = new DecimalFormat("#.#\u00B0C");
            for (int i = 0; i < cfd.getForecasts().size(); i++){

                String cityName = cfd.getCity().getCityName().toLowerCase();
                String temp = df2.format(cfd.getForecasts().get(i).getWeatherTemp().getTemperature());
                String forecast = cfd.getForecasts().get(i).getWeatherDescription().get(0).getDetailedWeatherType();
                Timestamp dateTime = Timestamp.valueOf(cfd.getForecasts().get(i).getDateTime());

                dao.insert(cityName,temp,forecast, dateTime);
            }

            weatherResult = Optional.of(new WeatherAPI(cfd));
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException uce) {
            uce.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return weatherResult;
        }
    }
}

