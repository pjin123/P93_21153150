/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MealApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Philip
 */
public final class Database {

    private static final String URL = "jdbc:derby://localhost:1527/MealDB";
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "123";

    Connection conn;

    public Database() {
        establishConnection();
        createTable();
    }

    public static void main(String[] args) {
        Database dbManager = new Database();
        System.out.println(dbManager.getConnection());

    }
    
    public void createTable() {
    String createTableSQL = "CREATE TABLE MEALS ("
            + "ID INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + "MEAL_TYPE VARCHAR(255),"
            + "MEAL_NAME VARCHAR(255),"
            + "INGREDIENTS VARCHAR(1024)"
            + ")";

    try (Statement statement = conn.createStatement()) {
        statement.execute(createTableSQL);
        System.out.println("Table MEALS created successfully...");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection with database
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " Get Connected Successfully ....");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //Close connection with database
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void insertMeal(String mealType, String mealName, String ingredients) {
        Connection connection = this.conn;
        Statement statement = null;

        try {
            statement = connection.createStatement();
            String sql = "INSERT INTO MEALS (MEAL_TYPE, MEAL_NAME, INGREDIENTS) VALUES ('"
                    + mealType + "', '"
                    + mealName + "', '"
                    + ingredients + "')";
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
