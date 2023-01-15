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
            connection = DriverManager.getConnection("jdbc:sqlite:ecowatt.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Infos");

            int i = 0;

            while (rs.next()) {
                id = rs.getInt("id");
                data = rs.getString("data");
                // read the data from the cursor

                String newdata = "[" + data + "]";

                JSON_Parser json_parsed_data = new JSON_Parser(newdata);
                Jour[] data_array = json_parsed_data.return_data();

                if (i == 0) {
                    data_array_all = data_array;
                } else {
                    int lenght_data = data_array.length;
                    int lenght_data_all = data_array_all.length;
                    Jour[] new_data_all = new Data.Jour[lenght_data_all + lenght_data];
                    System.arraycopy(data_array_all,0,new_data_all,0,lenght_data_all);
                    System.arraycopy(data_array,0,new_data_all,lenght_data_all,lenght_data);
                    data_array_all = new_data_all;
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
                    connection.close();
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

