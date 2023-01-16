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
            switch (value){
                case 1:
                    new Main().choice1();
                    System.exit(0);
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
            }
        } else {
            System.out.println("No arguments provided.");
        }

        //This is the main class of the project
        new Menu(). Menu();


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
        System.out.println("Choice 2");
        try {
            Web_input web_url = new Web_input("http://isis.unice.fr/~mgautero/ext/sae302/index.php");
            String data_from_web = web_url.lireURL("action=get");
            JSON_Parser json_parser = new JSON_Parser(data_from_web);
            Data.Jour[] data_array = json_parser.return_data();
            System.out.println("The data has been successfully parsed and saved in the software");

            Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eleko", "Admin", "ii594AqcthJj6DL7");
            System.out.println("Connection established");
            Database_Import db_import = new Database_Import((java.sql.Connection) dbConnection);
            System.out.println("Database imported");
            db_import.storeData(data_array,"datas");
            System.out.println("Data has been successfully stored in the database");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    public void choice3() throws SQLException, ClassNotFoundException {
        try {
            System.out.println("Choice 3");
            HTTP_File_Download.downloadFile("http://isis.unice.fr/~mgautero/ext/sae302/bd/ecowatt.db", "ecowatt.db");
            System.out.println("The database has been successfully downloaded");
            SQLite_Read SQLite_read = new SQLite_Read();
            Data.Jour[] data_array = SQLite_read.return_data();

            Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eleko", "Admin", "ii594AqcthJj6DL7");
            System.out.println("Connection established");
            Database_Import db_import = new Database_Import((java.sql.Connection) dbConnection);
            System.out.println("Database imported");
            db_import.storeData(data_array,"datas");
            System.out.println("Data has been successfully stored in the database");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
    public void choice4() throws SQLException, ClassNotFoundException{
        Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eleko", "Admin", "ii594AqcthJj6DL7");
        System.out.println("Connection established");
        String insertSQL = "DELETE FROM datas";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertSQL);
        preparedStatement.executeUpdate();
        System.out.println("Data has been successfully deleted from the database");
    }

    public void choice5() throws IOException {
        try {
        OAuth2API api = new OAuth2API("MTUyN2YzOTUtNWQwNi00Y2E1LTk0Y2ItOWVmZjJhZjdlZTViOjI5MmE0ZGJjLThlMDQtNDlkYy1hM2FhLTlhNDg5ZTUxNDllZg==");
        api.getAccessToken();
        String data_from_web = api.getData("/signals/");

        data_from_web = "[" + data_from_web + "]";

        JSON_Parser_RTE json_parser = new JSON_Parser_RTE(data_from_web);
        Data.Jour[] data_array = json_parser.return_data();
        System.out.println("The data has been successfully parsed and saved in the software");

        Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eleko", "Admin", "ii594AqcthJj6DL7");
        System.out.println("Connection established");
        Database_Import db_import = new Database_Import((java.sql.Connection) dbConnection);
        System.out.println("Database imported");
        db_import.storeData(data_array,"datas_rte");
        System.out.println("Data has been successfully stored in the database");
    } catch (SQLException | ClassNotFoundException e) {
        System.out.println("Connection failed");
        e.printStackTrace();
    }
    }

    public void choice6() throws SQLException, ClassNotFoundException{
        Connection dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/eleko", "Admin", "ii594AqcthJj6DL7");
        System.out.println("Connection established");
        String insertSQL = "DELETE FROM datas_rte";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertSQL);
        preparedStatement.executeUpdate();
        System.out.println("Data has been successfully deleted from the RTE database");
    }

}

