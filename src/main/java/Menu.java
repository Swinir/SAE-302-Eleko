import java.util.Scanner;

public class Menu {

    public void Menu() {
        //This is the menu to choose the data source and other options
        System.out.println("Welcome to the backend Eleko program");
        System.out.println("Please choose the data source");
        System.out.println("1. Web (http://infort.gautero.fr)");
        System.out.println("2. TODO"); //TODO: change this question
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                new Main().choice1();
                break;
            case 2:
                new Main().choice2();
                break;
            default:
                System.out.println("Please enter a valid choice");
                break;
        }
    }
}
