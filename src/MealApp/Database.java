/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MealApp;

import java.sql.*;

/**
 *
 * @author Philip
 */
public final class Database {

    //Database connection details
    private static final String URL = "jdbc:derby:MealDB_Ebd; create=true";
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "123";

    Connection conn;

    public Database() {
        establishConnection();
        createTable();
    }

    //createTable method
    public void createTable() {
        //Error message if no connection
        if (conn == null) {
            System.out.println("Connection is null. Unable to create table");
            return;
        }

        //Table Creation
        String createTableSQL = "CREATE TABLE MEALS ("
                + "ID INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "MEAL_TYPE VARCHAR(255),"
                + "MEAL_NAME VARCHAR(255),"
                + "INGREDIENTS VARCHAR(1024)"
                + ")";
        try ( Statement statement = conn.createStatement()) {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "MEALS", null);
            //Create table if table does not already exists in the database
            if (!tables.next()) {
                statement.execute(createTableSQL);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    //establishConnection method
    public void establishConnection() {
        //Use database connection details to connect if there is no connection already
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //closeConnections method
    public void closeConnections() {
        System.out.println("Closing Connections");
        
        //Close connection if there is a connection
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //insertMeal method
    public void insertMeal(String mealType, String mealName, String ingredients) {
        Statement statement = null;

        //Take the parameters of the method and store into database table
        try {
            statement = conn.createStatement();
            String sql = "INSERT INTO MEALS (MEAL_TYPE, MEAL_NAME, INGREDIENTS) VALUES ('"
                    + mealType + "', '"
                    + mealName + "', '"
                    + ingredients + "')";
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //getMeals method
    public ResultSet getMeals(String mealType) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.createStatement();
            String sql = "SELECT * FROM MEALS";
            if (!mealType.equals("All")) {
                sql += " WHERE MEAL_TYPE = '" + mealType + "'";
            }
            //Update resultSet to entry in table
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    //removeMeal method
    public void removeMeal(String mealName) {
        Statement statement = null;

        try {
            statement = conn.createStatement();
            //Remove the entry from the table where mealName is
            String sql = "DELETE FROM MEALS WHERE MEAL_NAME = '" + mealName + "'";
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
