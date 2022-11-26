import Input.Web_input;

import java.util.Scanner;

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
    }

    public void choice2() {
        //TODO: add the code for the choice
    }

}
