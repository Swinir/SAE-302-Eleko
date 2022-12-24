import Input.JSON_Parser;
import Input.Web_input;

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
    }

}
