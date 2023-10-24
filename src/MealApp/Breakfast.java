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
public class Breakfast extends Meal {

    //Constructor
    public Breakfast(String name, String type, ArrayList<Ingredients> ingredients) {
        super(name, type, ingredients);
    }

    //Overrides the abstract method in Meal class to return meal type
    @Override
    public String getMealType() {
        return "Breakfast";
    }
}
