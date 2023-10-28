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

    private static final String URL = "jdbc:derby:MealDB_Ebd; create=true";
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "123";

    Connection conn;

    public Database() {
        establishConnection();
        createTable();
    }

    public static void main(String[] args) {
        Database database = new Database();
        System.out.println(database.getConnection());
    }

    public void createTable() {
        if (conn == null) {
            System.out.println("Connection is null. Unable to create table.");
            return;
        }

        String createTableSQL = "CREATE TABLE MEALS ("
                + "ID INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "MEAL_TYPE VARCHAR(255),"
                + "MEAL_NAME VARCHAR(255),"
                + "INGREDIENTS VARCHAR(1024)"
                + ")";
        try ( Statement statement = conn.createStatement()) {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "MEALS", null);
            // Check if table already exists
            if (!tables.next()) {
                statement.execute(createTableSQL);
                System.out.println("Table MEALS created successfully...");
            }
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
        //Connection connection = this.conn;
        Statement statement = null;

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

    public ResultSet getMeals(String mealType) {
        //Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.createStatement();
            String sql = "SELECT * FROM MEALS";
            if (!mealType.equals("All")) {
                sql += " WHERE MEAL_TYPE = '" + mealType + "'";
            }
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public void removeMeal(String mealName) {
        Connection connection = this.conn;
        Statement statement = null;

        try {
            statement = connection.createStatement();
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
