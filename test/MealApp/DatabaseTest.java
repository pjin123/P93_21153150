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
    /*
    Test if inserted meal is added to the MealDB table
     */
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
    
}
