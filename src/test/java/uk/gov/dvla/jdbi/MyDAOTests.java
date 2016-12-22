//package uk.gov.dvla.jdbi;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.Connection;
//import java.sql.Statement;
//
//import static java.sql.DriverManager.getConnection;
//
///**
// * Created by dane on 15/12/16.
// */
//public class MyDAOTests extends IntegrationTests{
//
//    Connection connection;
//
//    @Before
//    public void before() {
//        connection = getConnection("ase");
//    }
//
//    @After
//    public void after() {
//        SybaseDBConnection.closeConnection(connection);
//    }
//
//    @Test
//    public void closeStatementShouldCloseStatement() {
//        Statement statement = connection.createStatement();
//        SybaseDBConnection.closeStatement(statement);
//        assertTrue(statement.isClosed());
//    }
//
//    @Test
//    public void closeStatementWithNullShouldNotThrow() {
//        SybaseDBConnection.closeStatement(null);
//    }
//}
//
//    jdbc:postgresql://localhost:5432/Weather_Forecasts
//    WeatherDAO dao = new WeatherDAO();
//
//    @Test
//    public void createValidUser() {
//        WeatherAPI validUser = new WeatherAPI("swansea","2016-10-10 10:30:00", "sunny", "9.5°C");
//
//        dao.insert("test","9.5°C","9.5°C", Timestamp.valueOf("2016-10-10 10:30:00"));
//
//
//        assertEntityCreatedInDB(validUser);
//    }
//
//    @Test
//    public void attemptToCreateInvalidUser() {
//        //User invalidUser = new User("", null, null, "", null, "XY");
//
//        dao.create(invalidUser);
//
//        // This really shouldn't be done this way, as DAOs are not supposed
//        // to manage transactions; instead, a suitable, descriptive
//        // exception should be thrown by the DAO and checked in the test.
//        assertTransactionWasRolledBack();
//    }
//}
