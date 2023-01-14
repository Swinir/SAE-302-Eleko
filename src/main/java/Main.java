import Input.JSON_Parser;
import Input.Web_input;

import Input.Database_Import;
import com.sun.jdi.connect.spi.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) {

        //Variables

        //This is the main class of the project

        new Menu().Menu();


    }

    public void choice1() {
        Web_input web_url = new Web_input("http://isis.unice.fr/~mgautero/ext/sae302/index.php");
        String data_from_web = web_url.lireURL("action=get");

        System.out.println(data_from_web); //TODO: remove this line after testing

        JSON_Parser json_parser = new JSON_Parser(data_from_web);
        Data.Jour[] data_array = json_parser.return_data();
        System.out.println("The data has been successfully parsed and saved in the software");

        System.out.println(data_array); //TODO: remove this line after testing

    }

    public void choice2() {
        //TODO: add the code for the choice
        System.Out.println("Choice 2");
        try {
            Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eleko", "Admin", "ii594AqcthJj6DL7");
            system.Out.println("Connection established");
            Database_Import db_import = new Database_Import(dbConnection);
            system.Out.println("Database imported");
            db_import.storeData(data_array);
            System.out.println("Data has been successfully stored in the database");
        } catch (SQLException e) {
            System.Out.println("Connection failed");
            e.printStackTrace();
        }
    }

}

