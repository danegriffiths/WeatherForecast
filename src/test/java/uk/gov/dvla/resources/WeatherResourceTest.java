package uk.gov.dvla.resources;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;
import uk.gov.dvla.api.WeatherAPI;
import uk.gov.dvla.jdbi.DatabaseWrapper;
import uk.gov.dvla.jdbi.WeatherDAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by dane on 12/12/16.
 */
public class WeatherResourceTest {

    private static final WeatherDAO MOCKDAO = mock(WeatherDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new WeatherResource(MOCKDAO)).build();

    final String CITY = "Swansea";

    @BeforeClass
    public static void setupBeforeClass() {
        System.out.println("setup before");
    }

    @Before
    public void setup() {
        when(MOCKDAO.findNameById(eq(CITY))).thenReturn(null);

        final DatabaseWrapper wrapper = new DatabaseWrapper(CITY, Timestamp.valueOf("2010-01-01 00:00:00"), "", "");
        final List<DatabaseWrapper> listOfRowsToReturn = new ArrayList<DatabaseWrapper>();
        listOfRowsToReturn.add( wrapper );

        when(MOCKDAO.getListFromDB(eq(CITY))).thenReturn(listOfRowsToReturn);
    }

    /**
     * Used to reset the MOCKDAO after each test.
     */
    @After
    public void tearDown(){
        reset(MOCKDAO);
    }

    @Test
    public void testGetCity() {
        //System.out.println(resources.client().target("WEATHER-info?CITY=swansea").request().get(URLDataMappedToSubClasses.class).getCity().getCityName());
//        String response = resources.client().target("/weather-info?CITY=swansea").request().get(String.class);
//        System.out.println( response );


        WeatherAPI weatherResult = resources.client().target("/weather-info?city=" + CITY).request().get(WeatherAPI.class);

        assertThat(weatherResult.getCity()).isEqualTo(CITY);
        System.out.println( "City retrieved: " + weatherResult );

        verify(MOCKDAO).findNameById("Swansea");
    }
}
