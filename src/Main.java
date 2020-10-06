import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        String userName = "ibrahim";
        String password = "ib123";
        String connectionString = "jdbc:mysql://localhost/testDB";
        Connection connection;
        Statement statement;
        ResultSet resultSet = null;

        // connecting to the database
        connection = DriverManager.getConnection(
                connectionString, userName, password);

        // write the query
        statement = connection.createStatement(
                resultSet.TYPE_SCROLL_INSENSITIVE,
                resultSet.CONCUR_READ_ONLY);

        String query = "SELECT * from city";

        // run the query
        resultSet = statement.executeQuery(query);
        resultSet.last();

        
        System.out.println(resultSet.getRow());


    }
}
