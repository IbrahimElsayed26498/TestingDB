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
            System.out.println("Connected!");
            String query = "SELECT * from city";

            // run the query
            resultSet = statement.executeQuery(query);

            //Moves the cursor to the last row in this ResultSet object.
            //resultSet.last();

            // print number of rows.
            System.out.println(resultSet.getRow());

            System.out.printf("%-5s %-15s %-3s %-15s %-7s%n",
                    "ID", "Name", "CountryCode",
                    "District", "Population");

            // select all the columns.
            while(resultSet.next()){
                System.out.printf("%-5d %-33s %-3s %-20s %-7d%n",
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population"));
            }

        }catch (Exception e){

            System.out.println(e.getMessage());

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
