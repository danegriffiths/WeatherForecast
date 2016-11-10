package uk.gov.dvla.jdbi;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class to map the data from SQL to java objects.
 */
public class DatabaseResultMapper implements ResultSetMapper<DatabaseWrapper> {

    public DatabaseWrapper map(int index, ResultSet rs, StatementContext ctx) throws SQLException {

        return new DatabaseWrapper(rs.getString("city"), rs.getTimestamp("date_time"), rs.getString("temperature"), rs.getString("forecast"));
    }
}
