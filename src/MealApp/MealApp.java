/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MealApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Philip
 */
public class MealApp {

    /**
     * @param args the command line arguments
     */
    //Main Method
    public static void main(String[] args) {

        System.out.println("Welcome to the Meal App");
        System.out.println("Enter 'x' at any time to exit.");

        //Prompt user for input
        while (true) {
            System.out.println("Select an option: ");
            System.out.println("(1) Choose a meal\n(2) Add a meal");
            //Call checkInput method and save return String to input
            String input = checkInput();

            //Surround switch with a try catch to avoid error with user input being non-integer
            try {
                int option = Integer.parseInt(input.trim());
                switch (option) {
                    case 1:
                        //call chooseMeal method if '1' has been entered by the user
                        chooseMeal();
                        break;
                    case 2:
                        //call addMeal method if '2' has been entered by the user
                        addMeal();
                        break;
                    default:
                        //Display to user that their option was not valid(if not 1 or 2)
                        System.out.println("This is not an option.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a valid option.");
            }
        }
    }

    //addMeal method allows the user to add a meal to the meal app
    public static void addMeal() {
        //Initialize empty String and ArrayList to be used in the method
        String mealType = "";
        ArrayList<Ingredients> ingredients = new ArrayList<>();

        //Prompt user for an input
        System.out.println("What type of meal would you like to add?\nSelect an option: ");
        System.out.println("(1) Breakfast\n(2) Lunch\n(3) Dinner\n(4) Dessert");
        //Call checkInput method and store return String to input
        String input = checkInput();

        //Surround switch with a try catch to avoid error with user input being non-integer
        try {
            int option = Integer.parseInt(input.trim());
            //Set mealType depending on user's option
            switch (option) {
                case 1:
                    mealType = "Breakfast";
                    break;
                case 2:
                    mealType = "Lunch";
                    break;
                case 3:
                    mealType = "Dinner";
                    break;
                case 4:
                    mealType = "Dessert";
                    break;
                default:
                    //Display to user that their option was not valid(if not 1,2,3,4) and return
                    System.out.println("This is not an option.");
                    return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }

        //Prompt user for input for the meal name
        System.out.println("Enter the meal name: ");
        //Call checkInput method and store return string to mealName
        String mealName = checkInput();

        //Prompt user to enter ingredients for their meal
        System.out.println("Enter one ingredient at a time(type '1' when finished): ");

        //For each iteration, if the user's input is not '1', add to ingredients ArrayList
        while (true) {
            String ingredient = checkInput();

            if (ingredient.equals("1")) {
                break;
            }

            ingredients.add(new Ingredients(ingredient));
        }

        //Call writeToFile method passing in the meal type, meal name, and ingredients ArrayList
        writeToFile(mealType, mealName, ingredients);
    }

    //writeToFile method takes meal type, meal name, and ingredients and writes them to a text file
    public static void writeToFile(String mealType, String mealName, ArrayList<Ingredients> ingredients) {
        PrintWriter pw = null;
        try {
            //Open and append to the file MealApp.txt
            pw = new PrintWriter(new FileOutputStream("MealApp.txt", true));
            //Write to the file (meal type and meal name)
            pw.print(mealType + ":" + mealName);

            //-----Portion of code generated from ChatGPT------
            //Loop through the ArrayList of ingredients
            for (Ingredients ingredient : ingredients) {
                //Write to the file (each ingredient of the ArrayList)
                pw.print(":" + ingredient);
            }
            //-------------------------------------------------

            pw.println();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

    }

    //readMeal method reads each line of the MealApp.txt file and returns a HashMap
    public static HashMap<Meal, String> readMeal() {

        HashMap<Meal, String> list = new HashMap();
        BufferedReader br = null;

        try {
            //Open MealApp text file
            br = new BufferedReader(new FileReader("MealApp.txt"));
            String line = null;
            //Read each line of the text file until no more new lines
            while ((line = br.readLine()) != null) {
                //Split the line into 3 seperate Strings at the semicolon(:) and store into String array
                String[] str = line.split(":", 3);
                //String at index 0 stored as mealType
                String mealType = str[0];
                //String at index 1 stored as mealName
                String mealName = str[1];

                //------Portion of code generated from ChatGPT-------
                //Check if a third part exists (ingredients), if not set to empty string
                String ingredientsStr = str.length > 2 ? str[2] : "";

                //Further split the third part by colon to get individual ingredients
                String[] ingredientsArray = ingredientsStr.split(":");

                //Create an ArrayList to store individual Ingredients objects
                ArrayList<Ingredients> ingredientsList = new ArrayList<>();

                //Loop through each element in the ingredients array and add it to the ArrayList
                for (String ingredient : ingredientsArray) {
                    ingredientsList.add(new Ingredients(ingredient));
                }
                //---------------------------------------------------

                //Create instance of a meal type based on mealType(Breakfast, etc.)
                Meal meal = null;
                if (mealType.equals("Breakfast")) {
                    meal = new Breakfast(mealName, mealType, ingredientsList);
                } else if (mealType.equals("Lunch")) {
                    meal = new Lunch(mealName, mealType, ingredientsList);
                } else if (mealType.equals("Dinner")) {
                    meal = new Dinner(mealName, mealType, ingredientsList);
                } else if (mealType.equals("Dessert")) {
                    meal = new Dessert(mealName, mealType, ingredientsList);
                }

                //Add the Meal object and the type to the HashMap
                list.put(meal, mealType);
            }
        } catch (IOException ex) {
        } finally {
            if (br != null) {
                try {
                    //Close the file
                    br.close();
                } catch (IOException ex) {

                }
            }
        }

        //Return the HashMap
        return list;
    }

    //chooseMeal method allows user to pick a meal they have created and display the ingredients
    public static void chooseMeal() {

        //Initialize empty String and ArrayList to be used in the method
        String mealType = "";
        ArrayList<Meal> allMeals = new ArrayList<>();
        //Call readMeal method and store the HashMap in mealList
        HashMap<Meal, String> mealList = readMeal();

        //Prompt user to input their selection of meal type
        System.out.println("Which meal type would you like to select: ");
        System.out.println("(1) Breakfast\n(2) Lunch\n(3) Dinner\n(4) Dessert");
        //Call checkInput method and store the return string in input
        String input = checkInput();

        try {
            int option = Integer.parseInt(input.trim());
            //Set mealType depending on user's input
            switch (option) {
                case 1:
                    mealType = "Breakfast";
                    break;
                case 2:
                    mealType = "Lunch";
                    break;
                case 3:
                    mealType = "Dinner";
                    break;
                case 4:
                    mealType = "Dessert";
                    break;
                default:
                    System.out.println("This is not an option.");
                    return;
            }
        } catch (NumberFormatException e) {
            System.out.println("This is not an option.");
        }

        System.out.println("Your meals for " + mealType + " are: ");

        //Loop through the HashMap to find meal types that match mealType
        int counter = 1;
        for (Meal meal : mealList.keySet()) {
            //If the type of meal in the HashMap matches mealType add the meal to allMeals
            if (meal.getMealType().equals(mealType)) {
                allMeals.add(meal);
                System.out.println(counter + ". " + meal.getMealName());
                counter++;
            }
        }

        //Prompt the user to pick a meal
        System.out.println("Enter the number of the meal you would like to pick: ");
        //Call checkInput method and store the return string in input
        input = checkInput();

        try {
            //Use input to determine the index of the meal that the user wants to select
            int indexOfMeal = Integer.parseInt(input.trim()) - 1;

            //Get the Meal object that the user picked
            Meal getMeal = allMeals.get(indexOfMeal);

            //Display the details of the meal
            System.out.println("You have picked: " + getMeal.getMealName());
            System.out.println("Here are the ingredients: ");
            for (Ingredients ingredient : getMeal.getIngredients()) {
                System.out.println("- " + ingredient);
            }
            System.out.println("");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("This is not an option.");
        }
    }

    //Checks for 'x'/'X' to close the program, otherwise returns the user's input
    public static String checkInput() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine().trim();

        if (input.equalsIgnoreCase("x")) {
            System.out.println("Exiting");
            System.exit(0);
        }

        return input;
    }

}
