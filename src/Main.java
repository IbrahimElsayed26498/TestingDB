import java.sql.*;

public class Main {
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    public static void main(String[] args) throws SQLException {


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
            /*if (createTable()){
                System.out.println("MyCity table created successfully.");
            }else{
                System.out.println("Error");
            }*/
            if (update()){
                System.out.println("Updated successfully.");
            }
            else {
                System.out.println("Error in update");
            }
            System.out.println("My city...\n----------");
            insertColumn();

            resultSet = statement.executeQuery("select * from mycity");
            System.out.printf("%-2s %-10s%n", "ID", "Person");
            resultSet.first();
            System.out.printf("%-2d %-10s%n",
                    resultSet.getInt("ID"),
                    resultSet.getString("person"));

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

    private static boolean createTable(){
        try{
            String sql = "DROP TABLE IF EXISTS MyCity";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE MyCity(" +
                        " ID int, " +
                        "person varchar(20)" +
                    ")";
        statement.executeUpdate(sql);
        return true;

        }catch (Exception e){
            return false;
        }
    }
    private static boolean insertColumn(){
        try{
            String sql = "INSERT INTO mycity (person) VALUES (\"Ibrahim\")";

            statement.executeUpdate(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private static boolean update(){
        try{
            String sql = "UPDATE mycity set person='Osama' WHERE ID = 2";

            statement.executeUpdate(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
