package uk.gov.dvla.jdbi;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseAPIMapper implements ResultSetMapper<Database> {

    public Database map(int index, ResultSet rs, StatementContext ctx) throws SQLException {

        return new Database(rs.getString("city"), rs.getTimestamp("date_time"), rs.getString("temperature"), rs.getString("forecast"));
    }
}
