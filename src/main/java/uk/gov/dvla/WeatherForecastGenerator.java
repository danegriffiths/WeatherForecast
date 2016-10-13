package uk.gov.dvla;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.gov.dvla.WeatherClasses.CityForecastData;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class WeatherForecastGenerator {

    public static void main(String[] args) throws IOException {
        new WeatherForecastGenerator();
    }

    private WeatherForecastGenerator() {

        //con.setDoOutput(true); // we want the response
        //con.setRequestProperty("Cookie", "myCookie=test123");
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/weather?q=London&APPID=3e90ae313c2c602239cded5167a9aa54");
            URLConnection conn = url.openConnection();

            ObjectMapper objMap = new ObjectMapper();
            CityForecastData cfd = objMap.readValue(conn.getInputStream(), CityForecastData.class);

            int numberOfForecasts = cfd.getForecasts().size();
            System.out.println("City: " + cfd.getCity().getCityName() + "\n");

            for (int i = 0; i < numberOfForecasts; i++) {
                System.out.printf("Date/Time: %s\n", cfd.getForecasts().get(i).getDateTime());

                char upperCaseLetter = Character.toUpperCase(cfd.getForecasts().get(i).getWeatherDescription().get(0).
                        getDetailedWeatherType().charAt(0));

                System.out.printf("Forecast: %s  -  ", upperCaseLetter + cfd.getForecasts().get(i).getWeatherDescription()
                        .get(0).getDetailedWeatherType().substring(1));
                System.out.printf("Temperature: %.1fC \n\n", cfd.getForecasts().get(i).getWeatherTemp().getTemperature());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
