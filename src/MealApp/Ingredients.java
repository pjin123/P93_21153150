/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MealApp;

/**
 *
 * @author Philip
 */
public class Ingredients {

    private String ingredientName;

    //Constructor
    public Ingredients(String ingredient) {
        this.ingredientName = ingredient;
    }

    //Getters and Setters
    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public String toString() {
        return ingredientName;
    }
}
