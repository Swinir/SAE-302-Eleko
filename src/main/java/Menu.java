import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public void Menu() throws SQLException, ClassNotFoundException {
        //This is the menu to choose the data source and other options
        System.out.println("Welcome to the backend Eleko program");
        System.out.println("Please choose the data source");
        System.out.println("1. Web (http://infort.gautero.fr)");
        System.out.println("2. Import data into databse (from json)");
        System.out.println("3. SQLite"); //TODO: change this question
        System.out.println("4. Delete all data from SQL Database");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                new Main().choice1();
                break;
            case 2:
                new Main().choice2();
                break;
            case 3:
                new Main().choice3();
                break;
            case 4:
                new Main().choice4();
                break;
            default:
                System.out.println("Please enter a valid choice");
                break;
        }
    }
}
