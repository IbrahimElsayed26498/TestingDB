import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection;
        Statement statement;
        ResultSet resultSet = null;

        // connecting to the database
        connection = DBInfo.connect();

        // write the query
        statement = connection.createStatement(
                resultSet.TYPE_SCROLL_INSENSITIVE,
                resultSet.CONCUR_READ_ONLY);

        String query = "SELECT * from city";

        // run the query
        resultSet = statement.executeQuery(query);

        //Moves the cursor to the last row in this ResultSet object.
        resultSet.last();


        System.out.println(resultSet.getRow());


    }
}
