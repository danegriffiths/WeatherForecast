package uk.gov.dvla.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * An interface which is used to communicate with the Postgres database, and either return items from the database, or update the database.
 */
@RegisterMapper(DatabaseResultMapper.class)
public interface WeatherDAO {

    @SqlUpdate("insert into forecasts (city, temperature, forecast, date_time) values (:city,:temperature,:forecast,:date_time)")
    void insert(@Bind("city") String city, @Bind("temperature") String temperature, @Bind("forecast") String forecast, @Bind("date_time") Timestamp date_time);

    @SqlUpdate("delete from forecasts where city = :city")
    void delete(@Bind("city") String city);

    @SqlQuery("select city from forecasts where city = :city")
    String findNameById(@Bind("city") String city);

    //To check if there is a date older than 3 hours.
    @SqlQuery("select city, date_time from forecasts where city = :city and date_time < now() - '3 hours'::interval;")
    String checkOldestDate(@Bind("city") String city);

    @SqlQuery("Select city, date_time, temperature, forecast from forecasts where city = :city")
    List<DatabaseWrapper> getListFromDB(@Bind("city") String city);

    @SqlQuery("Select city, date_time, temperature, forecast from forecasts where city = :city")
    List<DatabaseWrapper> getListFromDBTest(@Bind("city") String city);

    @SqlQuery("Select count(*) from forecasts")
    int countTotalRows();

    /**
     * close with no args is used to close the connection
     */
    void close();
}