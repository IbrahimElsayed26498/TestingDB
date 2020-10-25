import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInfo {
    private static String userName = "ibrahim";
    private static String password = "ib123";
    private static String connectionString = "jdbc:mysql://localhost/testDB";

    /**
     * This function connects to the database of the
     * user name, password and connection string that
     * are stored at the object's instances.
     * @return Connection object of the database connected to.
     * @throws SQLException
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(
                connectionString, userName, password);
    }

}
