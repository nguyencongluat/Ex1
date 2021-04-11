/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author nguyencongluat
 */
public class UserDAO {

    private DataSource dataSource;
    private Connection connection = null;
    private static final String SELECT_USER_BY_ID = "select username,password from user where username = ?";

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void getConnection() throws SQLException, NamingException {
        if (connection == null) {
            connection = this.dataSource.getConnection();
        }
    }

    /**
     *
     * @param username
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws javax.naming.NamingException
     */
    public User selectUser(String username) throws ClassNotFoundException, SQLException, NamingException {
        User user = null;
        getConnection();
        // Step 1: Establishing a Connection
        try (
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

