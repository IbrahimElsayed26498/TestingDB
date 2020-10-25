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
                    resultSet.TYPE_SCROLL_INSENSITIVE, // make the resultSet cursor scrollable.
                    resultSet.CONCUR_READ_ONLY);
            statement.setMaxRows(30);
            System.out.println("Connected!");
            String query = "SELECT * from city " + "limit 4,4";

            // run the query
            resultSet = statement.executeQuery(query);


            // print number of rows.
            System.out.print("Number of rows: ");
            resultSet.last(); //Moves the cursor to the last row in this ResultSet object.
            System.out.println(resultSet.getRow());


            System.out.printf("%-5s %-33s %-12s %-20s %-7s%n",
                    "ID", "Name", "CountryCode",
                    "District", "Population");

            // select all the columns.
            printTable(resultSet);


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

    private static void printTable(ResultSet resultSet) throws SQLException {
        resultSet.beforeFirst();
        while(resultSet.next()){
            System.out.printf("%-5d %-33s %-12s %-20s %-7d%n",
                    resultSet.getInt("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("CountryCode"),
                    resultSet.getString("District"),
                    resultSet.getInt("Population"));
        }
    }
}
