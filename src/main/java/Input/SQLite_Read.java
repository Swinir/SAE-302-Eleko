package Input;

import Data.Jour;

import java.sql.*;

public class SQLite_Read {

    Data.Jour[] data_array_all;
    Integer id;
    String data;

    public SQLite_Read() {
        Connection connection = null;
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:ecowatt.db"); //Connecting to the database which we just downloaded
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Infos"); //Selecting all the data from the table Infos

            int i = 0;

            while (rs.next()) {
                id = rs.getInt("id");
                data = rs.getString("data");
                // read the data from the cursor

                String newdata = "[" + data + "]"; //Adding the brackets to the data to make it a JSON format

                JSON_Parser json_parsed_data = new JSON_Parser(newdata); //Parsing the data
                Jour[] data_array = json_parsed_data.return_data(); //Getting the data from the JSON_Parser class

                if (i == 0) {
                    data_array_all = data_array; //If it's the first time we are getting data, we just set the data_array_all to the data_array
                } else {
                    int lenght_data = data_array.length; //Getting the length of the data
                    int lenght_data_all = data_array_all.length; //Getting the length of the data_all
                    Jour[] new_data_all = new Data.Jour[lenght_data_all + lenght_data]; //Creating a new array with the length of the two arrays added together
                    System.arraycopy(data_array_all,0,new_data_all,0,lenght_data_all); //Copying the data from the data_all array to the new_data_all array
                    System.arraycopy(data_array,0,new_data_all,lenght_data_all,lenght_data); //Copying the data from the data array to the new_data_all array
                    data_array_all = new_data_all; //Setting the data_all array to the new_data_all array
                }
                i++;
            }
            System.out.println("The data has been successfully parsed");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close(); //Closing the connection
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
    public Data.Jour[] return_data() {
        return data_array_all;
    }
}

