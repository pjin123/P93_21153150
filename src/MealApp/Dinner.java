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
public class Dinner extends Meal {

    //Constructor
    public Dinner(String name, String type, ArrayList<Ingredients> ingredients) {
        super(name, type, ingredients);
    }

    //Overrides the abstract method in Meal class to return meal type
    @Override
    public String getMealType() {
        return "Dinner";

    }
}
