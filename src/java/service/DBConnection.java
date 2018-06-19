package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/gincana",
                "root", "123456");
    }
    
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("FOI");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
