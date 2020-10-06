import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
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

            // print number of rows.
            System.out.println(resultSet.getRow());

        }catch (Exception e){

            System.err.println(e);

        }finally {

            if(connection != null)
                connection.close();

            if(statement != null)
                statement.close();

            if(resultSet != null)
                resultSet.close();
        }


    }
}
