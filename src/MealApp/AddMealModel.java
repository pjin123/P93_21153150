/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MealApp;

import java.util.ArrayList;

/**
 *
 * @author Philip
 */
public class AddMealModel {
    public void insertMeal(String mealType, String mealName, ArrayList<Ingredients> ingredients){
        Database database = new Database();
        database.insertMeal(mealType, mealName, mealName);
        database.closeConnections();
    }
}
