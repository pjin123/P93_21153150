/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package MealApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philip
 */
public class DatabaseTest {

    public DatabaseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    //Test if inserted meal is added to the MealDB table
    public void testInsertMeal() {
        Database database = new Database();
        String mealType = "Breakfast";
        String mealName = "testBreakfast";
        String ingredients = "testIngredient";
        boolean mealAdded = false;

        database.insertMeal(mealType, mealName, ingredients);
        ResultSet rs = database.getMeals(mealType);

        try {
            while (rs.next()) {
                if (rs.getString("MEAL_TYPE").equals(mealType)
                        && rs.getString("MEAL_NAME").equals(mealName)) {
                    mealAdded = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        assertTrue(mealAdded);
    }

    @Test
    //Test if correct meal type is returned
    public void testGetMealsByType() {
        Database database = new Database();
        String mealTypeToTest = "Breakfast";

        ResultSet rs = database.getMeals(mealTypeToTest);
        boolean correctType = true;
        try {
            while (rs.next()) {
                if (!rs.getString("MEAL_TYPE").equals(mealTypeToTest)) {
                    correctType = false;
                    break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        assertTrue(correctType);
    }

    @Test
    //Check to see if there are entries in the table
    public void testIsTableEmpty() {
        Database database = new Database();

        ResultSet rs = database.getMeals("All");

        boolean tableEmpty = true;
        try {
            if (rs.next()) {
                tableEmpty = false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        assertFalse(tableEmpty);
    }

    @Test
    //Test if a specific meal is retrievable from the database
    public void testRetrieveSpecificMeal() {
        Database database = new Database();
        String mealType = "Breakfast";
        String mealName = "testBreakfast";
        String ingredients = "testIngredient";

        database.insertMeal(mealType, mealName, ingredients);
        String mealNameToTest = "testBreakfast";

        ResultSet rs = database.getMeals("All");

        boolean mealExists = false;
        try {
            while (rs.next()) {
                if (rs.getString("MEAL_NAME").equals(mealNameToTest)) {
                    mealExists = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        assertTrue(mealExists);
    }

    @Test
    //Test if a meal is successfully removed from the database
    public void testRemoveMeal() {
        Database database = new Database();
        String mealType = "Breakfast";
        String mealName = "testBreakfast";
        String ingredients = "testIngredient";

        database.insertMeal(mealType, mealName, ingredients);

        String mealNameToRemove = "testBreakfast";

        database.removeMeal(mealNameToRemove);
        ResultSet rs = database.getMeals("All");

        boolean mealRemoved = true;
        try {
            while (rs.next()) {
                if (rs.getString("MEAL_NAME").equals(mealNameToRemove)) {
                    mealRemoved = false;
                    break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        assertTrue(mealRemoved);
    }
}
