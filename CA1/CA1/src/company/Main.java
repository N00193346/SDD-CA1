package company;

import java.util.List;
import java.util.Scanner;


public class Main {

    //Scanner for user input
    static Scanner input = new Scanner(System.in);

    static Model model = Model.getInstance();

    public static void main(String[] args) {

        Drink d;

        int opt;
        int menu;

        //Print main menu
        System.out.println("\n\n********* MAIN MENU ********");
        System.out.println("1. Stock Taker Menu");
        System.out.println("2. Employee Menu\n");
        System.out.print("*****Enter option: *******\n");
        //Turn user input into integer
        String inputMenu = input.nextLine();
        menu = Integer.parseInt(inputMenu);

        //Switch Statement for choosing menu
        switch(menu) {

            //Stock Taker's Menu/Functions
            case 1: {

                    do {
                        System.out.println("\n\n********* Stock Taker Menu ********");
                        System.out.println("1. Create new Drink");
                        System.out.println("2. View all Drinks");
                        System.out.println("3. Delete a Drink");
                        System.out.println("4. Update a Drink");
                        System.out.println("5. Update a Drink's Stock");
                        System.out.println("6. Select a Drink by Name");
                        System.out.println("7. Update Drink price");
                        System.out.println("\n9. Exit\n");


                        System.out.print("*****Enter option: *******\n\n");
                        String line = input.nextLine();
                        opt = Integer.parseInt(line);

                        switch (opt) {

                            case 1: {
                                d = readDrink();
                                model.addDrink(d);
                                System.out.println("***** Drink Added to the inventory*****");
                                break;
                            }

                            case 2: {
                                viewDrinks();
                                break;
                            }

                            case 3: {
                                deleteDrink();
                                break;
                            }

                            case 4: {
                                updateDrink();
                                break;
                            }
                            case 5: {
                                updateDrinkStock();
                                break;
                            }
                            case 6: {
                                getDrinksByName();
                                break;
                            }
                            case 7: {
                                updateDrinkPrice();
                                break;
                            }

                        }
                    }
                    while (opt != 9);
                System.out.println("Goodbye");

                }
            //Employee's Menu/Functions
            case 2: {

                do {
                    System.out.println("\n\n********* Employee Menu ********");
                    System.out.println("1. View all Drinks");
                    System.out.println("2. Update a Drink's Stock");
                    System.out.println("3. Select a Drink by Name");
                    System.out.println("\n9. Exit\n");
                    System.out.println();

                    System.out.print("*****Enter option: *******\n");
                    String line = input.nextLine();
                    opt = Integer.parseInt(line);

                    switch (opt) {

                        case 1: {

                            viewDrinks();
                            break;
                        }

                        case 2: {
                            updateDrinkStock();
                            break;
                        }
                        case 3: {
                            getDrinksByName();
                            break;
                        }

                    }
                }
                while (opt != 7);
                System.out.println("Goodbye");

            }

        }


    }

    // Create drink
    private static Drink readDrink() {

        System.out.print("Enter name of drink: ");
        String name = input.nextLine();

        System.out.print("How much does the drink cost?: ");
        double price = input.nextDouble();

        System.out.print("What is the size of the drink?(ml): ");
        double size = input.nextDouble();

        System.out.print("Is the drink alcoholic?(true/false): ");
        int alcoholic = input.nextInt();

        System.out.print("How much of the drink is in stock?: ");
        int stock = input.nextInt();


        input.nextLine();

        // Create the Drink object d
        Drink d = new Drink(name, price, size, alcoholic, stock);

        return d;
    }

    //View all drinks
    private static void viewDrinks() {
        List<Drink> drinks = model.getDrinks();

        System.out.println("***** Printing All Drinks *****");

        for (Drink dr : drinks) {
            System.out.println(dr);
        }

        System.out.println("***** Finished Printing All Drinks *****");
    }

    //Delete Drink
    private static void deleteDrink() {
        System.out.print("Enter the ID of the drink to delete:");
        int id = Integer.parseInt(input.nextLine());


        if (model.deleteDrink(id)) {
            System.out.println("\nDrink deleted");
        } else {
            System.out.println("\nDrink not deleted, check your database to see if a drink with this ID actually exists");
        }
    }

    //Update all of drink
    private static void updateDrink() {

        System.out.println("Please Enter the id of the drink: ");
        int id = Integer.parseInt(input.nextLine());

        System.out.print("Rename the drink: ");
        String name = input.nextLine();

        System.out.print("Change the drink price: ");
        double price = input.nextDouble();

        System.out.print("Change the drink size?(ml): ");
        double size = input.nextDouble();

        System.out.print("Is the drink alcoholic?(true/false): ");
        int alcoholic = input.nextInt();

        System.out.print("Change drink stock?: ");
        int stock = input.nextInt();

        input.nextLine();

        if (model.updateDrink(id, name, price, size, alcoholic, stock)) {
            System.out.println("\nDrink updated.");
        } else {
            System.out.println("\nDrink not updated");
        }
    }

    //Update all of drink
    private static void updateDrinkPrice() {

        System.out.println("Please Enter the name of the drink: ");
        String name = input.nextLine();

        List<Drink> drink = model.getDrink(name);
        System.out.println(drink);

        System.out.println("Please enter new price; ");
        double price = input.nextDouble();

        if (model.updateDrinkPrice(name, price)) {
            System.out.println("\nDrink Price updated.");
        } else {
            System.out.println("\nDrink Price not updated");
        }
    }

    // Update drink stock by name
    private static void updateDrinkStock() {

        System.out.print("Please enter the name the drink: ");
        String name = input.nextLine();

        System.out.print("Change drink stock?: ");
        int stock = input.nextInt();

        input.nextLine();


        if (model.updateDrinkStock(name, stock)) {
            System.out.println("\nDrink Stock updated.");
        } else {
            System.out.println("\nDrink stock not updated");
        }

    }

    //Get drink by name
    private static void getDrinksByName() {
        System.out.println("Please enter the drink name: ");
        String name = input.nextLine();

        List<Drink> drink = model.getDrink(name);

        System.out.println("***** Printing Drink *****");
        System.out.println(drink);
        System.out.println("***** Finished Printing Drink *****");
    }

//    //Delete Drink
//    private static void deleteDrink() {
//        System.out.print("Enter the ID of the drink to delete:");
//        int id = Integer.parseInt(input.nextLine());
//
//        List<Drink> drinks = model.getDrinkById(id);
//
//        for (Drink dr : drinks) {
//            System.out.println(dr);
//        }
//
//        System.out.println("Are you sure you want to delete this drink");
//        System.out.println("1: Yes");
//        System.out.println("2: No");
//
//        int userInput = input.nextInt();
//
//        if (userInput == 1) {
//
//            if(model.deleteDrink(id)) {
//                System.out.println("\nDrink deleted");
//            } else {
//                System.out.println("\nDrink stock not updated");
//            }
//        }
//       else if (userInput == 2) {
//            System.out.println("Delete canceled");
//        }
//    }

}
