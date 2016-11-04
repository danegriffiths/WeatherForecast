package uk.gov.dvla.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.gov.dvla.api.WeatherAPI;
import uk.gov.dvla.jdbi.MyDAO;
import uk.gov.dvla.weatherClasses.CityForecastData;

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
import java.util.Optional;

/**
 * Created by dane on 14/10/16.
 */
@Path("weather-info")
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {

    private MyDAO dao;

    public WeatherResource(MyDAO dao){
        this.dao=dao;
    }

    @GET
    @Timed
    public Optional<WeatherAPI> getWeather(@QueryParam("city") Optional<String> city) {

        Optional<WeatherAPI> weatherResult = Optional.empty();

        String cityFromOptional = city.get();

        if (dao.findNameById(cityFromOptional) == null) {
//            System.out.printf("Checkpoint 1 Null: %s isn't here; add to db!\n", cityFromOptional);
            String date = "2016-11-04 11:00:00";
            Timestamp y = Timestamp.valueOf(date);
            dao.insert(cityFromOptional,"100","sunny",y);
//            System.out.printf("Database updated to include forecast of %s!\n", cityFromOptional);
        }
        else {
//            System.out.printf("Checkpoint 1 notNull: %s is present!\n", cityFromOptional);
            if (dao.oldestDate(cityFromOptional) == null) {
//                System.out.println("Checkpoint 2: Dates up do date. Give data");
            }
            else {
//                System.out.println("Checkpoint 3: Need to update database!!!");
                dao.delete(cityFromOptional);

                String date = "2016-11-04 11:00:00";
                Timestamp y = Timestamp.valueOf(date);
                dao.insert(cityFromOptional,"100","sunny",y);
//                System.out.printf("Database updated to include forecast of %s!\n", dao.oldestDate(cityFromOptional));
            }
        }

        try {
            final URL url = new URL(String.format("http://api.openweathermap.org/data/2.5/forecast/weather?q=%s&APPID=3e90ae313c2c602239cded5167a9aa54", URLEncoder.encode( city.orElse(""), "UTF-8" ) ) );
            final URLConnection urlConn=url.openConnection();
            ObjectMapper objMap = new ObjectMapper();
            CityForecastData cfd = objMap.readValue(urlConn.getInputStream(),CityForecastData.class);

            weatherResult = Optional.of(new WeatherAPI(cfd));
        }
        catch (MalformedURLException ex ) {
            ex.printStackTrace();
        }
        catch (UnsupportedEncodingException uce) {
            uce.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            return weatherResult;
        }
    }



}
