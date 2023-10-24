/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MealApp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Philip
 */
public abstract class Meal {

    //Private variables
    private String mealName;
    private String mealType;
    private ArrayList<Ingredients> ingredients;

    //Constructor
    public Meal(String name, String type, ArrayList<Ingredients> ingredients) {
        this.mealName = name;
        this.mealType = type;
        this.ingredients = ingredients;
    }

    //Getters and Setters
    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    //Abstract method 
    public abstract String getMealType();
}
