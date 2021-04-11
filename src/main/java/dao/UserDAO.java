/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author nguyencongluat
 */
public class UserDAO {
    private final String jdbcURL = "jdbc:mysql://db:3306/db_17?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "test123";
    
    @Resource(name = "jdbc/db_17")
    private DataSource dataSource;

    private static final String SELECT_USER_BY_ID = "select username,password from user where username = ?";

    public UserDAO() {}

    public Connection getConnection() throws SQLException {
        Connection connection;
        connection = dataSource.getConnection();
        return connection;
    }

    /**
     *
     * @param username
     * @return
     * @throws java.lang.ClassNotFoundException
     */
    public User selectUser(String username) throws ClassNotFoundException {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String password = rs.getString("password");
                user = new User(username, password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

