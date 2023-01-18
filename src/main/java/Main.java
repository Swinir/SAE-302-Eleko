import Data.Jour;
import Input.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        //Check if command line arguments are present
        if (args.length > 0) { // If any arguments provided
            int value = Integer.parseInt(args[0]);
            switch (value){ //Switch to the correct menu option
                case 1:
                    new Main().choice1();
                    System.exit(0); //manually exit the program to not open the menu
                case 2:
                    new Main().choice2();
                    System.exit(0);
                case 3:
                    new Main().choice3();
                    System.exit(0);
                case 4:
                    new Main().choice4();
                    System.exit(0);
                case 5:
                    new Main().choice5();
                    System.exit(0);
                case 6:
                    new Main().choice6();
                    System.exit(0);
            }
        } else {
            System.out.println("No arguments provided.");
        }


        //Creating the menu
        new Menu(). Menu();


    }

    public void choice1() { //Printing the data from the web in the console THIS CHOICE IS NOT INTENDED TO BE USED IN THE FINAL PRODUCT IT IS ONLY FOR TESTING
        Web_input web_url = new Web_input("http://isis.unice.fr/~mgautero/ext/sae302/index.php");
        String data_from_web = web_url.lireURL("action=get");

        System.out.println(data_from_web);

        JSON_Parser json_parser = new JSON_Parser(data_from_web);
        Data.Jour[] data_array = json_parser.return_data();
        System.out.println("The data has been successfully parsed and saved in the software");

        System.out.println(data_array);

    }

    public void choice2() { //Importing the data from the web in the JSON format (isis) into the database
        System.out.println("Choice 2");
        try {
            Web_input web_url = new Web_input("http://isis.unice.fr/~mgautero/ext/sae302/index.php"); //Setting the url
            String data_from_web = web_url.lireURL("action=get"); //Downloading the page from the web
            JSON_Parser json_parser = new JSON_Parser(data_from_web); //Parsing the data from the web
            Data.Jour[] data_array = json_parser.return_data(); //Saving the data in an array
            System.out.println("The data has been successfully parsed and saved in the software");

            Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eleko", "Admin", "ii594AqcthJj6DL7"); //Connecting to the database
            System.out.println("Connection established");
            Database_Import db_import = new Database_Import((java.sql.Connection) dbConnection); //Importing the data into the database
            System.out.println("Database imported");
            db_import.storeData(data_array,"datas"); //Storing the data in the database
            System.out.println("Data has been successfully stored in the database");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection failed"); //Exception if the connection failed
            e.printStackTrace();
        }
    }

    public void choice3() throws SQLException, ClassNotFoundException { //Importing the data from the web in the SQLite format (isis) into the database
        try {
            System.out.println("Choice 3");
            HTTP_File_Download.downloadFile("http://isis.unice.fr/~mgautero/ext/sae302/bd/ecowatt.db", "ecowatt.db"); //Downloading the SQLite file
            System.out.println("The database has been successfully downloaded");
            SQLite_Read SQLite_read = new SQLite_Read(); //Reading the SQLite file
            Data.Jour[] data_array = SQLite_read.return_data(); //Saving the data in an array

            Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eleko", "Admin", "ii594AqcthJj6DL7"); //Connecting to the database
            System.out.println("Connection established");
            Database_Import db_import = new Database_Import((java.sql.Connection) dbConnection); //Importing the data into the database
            System.out.println("Database imported");
            db_import.storeData(data_array,"datas"); //Storing the data in the database
            System.out.println("Data has been successfully stored in the database");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection failed"); //Exception if the connection failed
            e.printStackTrace();
        }
    }
    public void choice4() throws SQLException, ClassNotFoundException{ //Deleting the data from the database (table datas)
        Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eleko", "Admin", "ii594AqcthJj6DL7"); //Connecting to the database
        System.out.println("Connection established");
        String insertSQL = "DELETE FROM datas"; //SQL query to delete the data from the table datas
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertSQL); //Preparing the query
        preparedStatement.executeUpdate(); //Executing the query
        System.out.println("Data has been successfully deleted from the database");
    }

    public void choice5() throws IOException { //Importing data from a JSON file (RTE data srouce) into the database (table datas_rte)
        try {
        OAuth2API api = new OAuth2API("MTUyN2YzOTUtNWQwNi00Y2E1LTk0Y2ItOWVmZjJhZjdlZTViOjI5MmE0ZGJjLThlMDQtNDlkYy1hM2FhLTlhNDg5ZTUxNDllZg=="); //Connecting to the API
        api.getAccessToken(); //Getting the access token form the OAuth2 API
        String data_from_web = api.getData("/signals/"); //Getting the data from the API

        data_from_web = "[" + data_from_web + "]"; //Adding brackets to the data to make it a JSON array

        JSON_Parser_RTE json_parser = new JSON_Parser_RTE(data_from_web); //Parsing the data from the web
        Data.Jour[] data_array = json_parser.return_data(); //Saving the data in an array
        System.out.println("The data has been successfully parsed and saved in the software");

        Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eleko", "Admin", "ii594AqcthJj6DL7"); //Connecting to the database
        System.out.println("Connection established");
        Database_Import db_import = new Database_Import((java.sql.Connection) dbConnection); //Importing the data into the database
        System.out.println("Database imported");
        db_import.storeData(data_array,"datas_rte"); //Storing the data in the database
        System.out.println("Data has been successfully stored in the database");
    } catch (SQLException | ClassNotFoundException e) {
        System.out.println("Connection failed"); //Exception if the connection failed
        e.printStackTrace();
    }
    }

    public void choice6() throws SQLException, ClassNotFoundException{ //Deleting the data from the database (table datas_rte)
        Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eleko", "Admin", "ii594AqcthJj6DL7"); //Connecting to the database
        System.out.println("Connection established");
        String insertSQL = "DELETE FROM datas_rte"; //SQL query to delete the data from the table datas_rte
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertSQL); //Preparing the query
        preparedStatement.executeUpdate(); //Executing the query
        System.out.println("Data has been successfully deleted from the RTE database");
    }

}

