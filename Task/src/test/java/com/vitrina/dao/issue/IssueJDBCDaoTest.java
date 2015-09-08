package com.vitrina.dao.issue;

import com.vitrina.util.FactoryDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * @author Lazarchuk Aleksandr
 * @version miratex-master
 * @date 08/09/2015
 * **********************************
 * {@link http://dbunit.sourceforge.net/howto.html}
 * {@link https://www.playframework.com/documentation/2.4.x/JavaTestingWithDatabases}
 * {@link http://www.onjava.com/pub/a/onjava/2004/01/21/dbunit.html}
 * {@link http://www.danhaywood.com/2011/12/20/db-unit-testing-with-dbunit-json-hsqldb-and-junit-rules/}
 * {@link http://www.marcphilipp.de/blog/2012/03/13/database-tests-with-dbunit-part-1/}
 * {@link http://stackoverflow.com/questions/30315760/could-not-get-jdbc-connection-in-junit}
 *
 */
public class IssueJDBCDaoTest {

    @Before
    public void setUp() {
        Locale.setDefault(Locale.US);
    }

//    /**
//     * Test load driver...
//     */
//    @Test
//    public void testIsDriverLoader(){
//        try {
//            Class.forName("oracle.jdbc.OracleDriver");
//        } catch (ClassNotFoundException e){
//            assertFalse("Test load '"+e.getMessage()+"'", true);
//        }
//    }

//    /**
//     * Test load driver...
//     * Test open Connection to DataBase
//     */
//    @Test
//    public void testDBConnect(){
//        String   driver = "oracle.jdbc.OracleDriver";
//        String      url = "jdbc:oracle:thin:@//10.13.71.34:1521/xe";
//        String     user = "HR";
//        String password = "hr";
//
//        Connection connection = null;
//        try {
//            Class.forName(driver);
//            connection = DriverManager.getConnection(url,user,password);
//
//            assertNotNull("Connection is Not Null", connection);
//            assertFalse("Connection is closed", connection.isClosed());
//            assertEquals("Database Driver Name", "Oracle JDBC driver", connection.getMetaData().getDriverName());
//            assertEquals("Database Product Name", "Oracle", connection.getMetaData().getDatabaseProductName());
//        } catch (SQLException e) {
//            assertFalse("Test Connect to DataBase '"+e.getMessage()+"'", true);
//        } catch (ClassNotFoundException e){
//            assertFalse("Test load '" + e.getMessage() + "'", true);
//        } finally {
//            try {
//                if (!connection.isClosed())
//                    connection.close();
//            } catch (SQLException e) {
//                assertFalse("Connection doesn't close '" + e.getMessage() + "'", true);
//            }
//        }
//    }

    /**
     * Test load driver...
     * Test open Connection to DataBase
     */
    @Test
    public void testDBConnect(){
        Connection connection = null;
        try {
            connection = FactoryDriver.getConnection("jdbc_oracle.properties");

            assertNotNull("Connection is Not Null", connection);
            assertFalse("Connection is closed", connection.isClosed());
            assertEquals("Database Driver Name", "Oracle JDBC driver", connection.getMetaData().getDriverName());
            assertEquals("Database Product Name", "Oracle", connection.getMetaData().getDatabaseProductName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            assertFalse("Test Connect to DataBase '"+e.getMessage()+"'", true);
        } finally {
            try {
                if (!connection.isClosed())
                    connection.close();
            } catch (SQLException e) {
                assertFalse("Connection doesn't close '" + e.getMessage() + "'", true);
            }
        }
    }







//    @Test
//    public void testCreateManagerFroPostgresDatabase() throws Exception {
//        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
//        when(metaData.getDatabaseProductName()).thenReturn("PostgreSQL");
//
//        System.err.println( metaData.getDatabaseProductName() );
//
//        Connection connection = mock(Connection.class);
//        when(connection.getMetaData()).thenReturn(metaData);
//
////        RollbackManagerI rollbackManager = RollbackManagerFactory.createRollbackManager(connection);
////        assertNotNull(rollbackManager);
//    }
//
////    @Test(expected = RuntimeException.class)
//    @Test
//    public void testCreateManagerForNotPostgresDatabase() throws Exception {
//        DatabaseMetaData metaData = mock(DatabaseMetaData.class);
//        when(metaData.getDatabaseProductName()).thenReturn("Sybase ASE");
//
//        Connection connection = mock(Connection.class);
//        when(connection.getMetaData()).thenReturn(metaData);
//
//        System.err.println( connection.getMetaData() );
//
////        RollbackManagerFactory.createRollbackManager(connection);
//    }


//    /**
//     * ...
//     */
//    @Test
//    public void testConnect2() {
//        try {
//            Class.forName("oracle.jdbc.OracleDriver"); //Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.13.71.34:1521/xe","HR","hr");
//        } catch (ClassNotFoundException e) { System.err.println(e.getMessage());
//        } catch (SQLException e) { System.err.println(e.getMessage()); }
//    }




//    /**
//     * ...
//     */
//    @Test
//    public void testConnect1(){
//        try {
//            connection = FactoryDriver.getConnection("jdbc_oracle.properties");
//            System.out.println( connection.toString() );
////            System.out.println( connection.getSchema() );
//            System.out.println( connection.getMetaData() );
//            System.out.println( connection.getMetaData().getDatabaseMinorVersion() );
//            System.out.println( connection.getMetaData().getDatabaseMajorVersion() );
//            System.out.println( connection.getMetaData().getDatabaseProductName() );
//            System.out.println( connection.getMetaData().getDatabaseProductVersion() );
//            System.out.println( connection.getMetaData().getDriverName() );
//            System.out.println( connection.getMetaData().getDriverVersion() );
//            System.out.println( connection==null ? null : connection );
//            System.out.println( connection.isClosed() );
////            System.out.println( connection.isValid(1000) );
//
//            System.out.println( connection.getMetaData().getSchemaTerm() );
//
////            getDatabaseMetaData(connection);
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//    }

//    public void getDatabaseMetaData(Connection conn) {
//        try {
//            DatabaseMetaData dbmd = conn.getMetaData();
//            String[] TYPES = {"TABLE"};
//            ResultSet rs = dbmd.getTables(null, null, "%", TYPES); //ResultSet rs = dbmd.getTables(null, null, "%", types);
//            while (rs.next())
//                System.out.println(rs.getString("TABLE_NAME"));
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


//    /**
//     * Initializes DatabaseFactory.
//     */
//    public synchronized static void init(){
//        if (dataSource != null) {
//            return;
//        }
//        try {
//            DatabaseConfig.DATABASE_DRIVER.newInstance();
//        }
//        catch (  Exception e) {
//            log.fatal("Error obtaining DB driver",e);
//            throw new Error("DB Driver doesnt exist!");
//        }
//        connectionPool=new GenericObjectPool();
//        if (DatabaseConfig.DATABASE_CONNECTIONS_MIN > DatabaseConfig.DATABASE_CONNECTIONS_MAX) {
//            log.error("Please check your database configuration. Minimum amount of connections is > maximum");
//            DatabaseConfig.DATABASE_CONNECTIONS_MAX=DatabaseConfig.DATABASE_CONNECTIONS_MIN;
//        }
//        connectionPool.setMaxIdle(DatabaseConfig.DATABASE_CONNECTIONS_MIN);
//        connectionPool.setMaxActive(DatabaseConfig.DATABASE_CONNECTIONS_MAX);
//        try {
//            dataSource=setupDataSource();
//            Connection c=getConnection();
//            DatabaseMetaData dmd=c.getMetaData();
//            databaseName=dmd.getDatabaseProductName();
//            databaseMajorVersion=dmd.getDatabaseMajorVersion();
//            databaseMinorVersion=dmd.getDatabaseMinorVersion();
//            c.close();
//        }
//        catch (  Exception e) {
//            log.fatal("Error with connection string: " + DatabaseConfig.DATABASE_URL,e);
//            throw new Error("DatabaseFactory not initialized!");
//        }
//        log.info("Successfully connected to database");
//    }


    private Connection connection;
    private final String SQL_GET_ALL = "SELECT * FROM issue";
}
