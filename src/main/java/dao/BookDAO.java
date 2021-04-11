/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.Book;
import model.User;

/**
 *
 * @author nguyencongluat
 */
public class BookDAO {
    @Resource(name = "jdbc/db_17")
    private DataSource dataSource;
    
    private Connection connection = null;
    private static final String INSERT_BOOKS_SQL = "INSERT INTO book" + "  (name, publisher, price) VALUES " +
        " (?, ?, ?);";
    private static final String SELECT_BOOK_BY_ID = "select name, publisher, price from book where id = ?";
    private static final String SELECT_ALL_BOOKS = "select * from book";
    private static final String UPDATE_BOOK_SQL = "update book set name = ?,publisher= ?, price =? where id = ?;";
    private static final String DELETE_BOOK_SQL = "delete from book where id = ?;";

    public BookDAO() {}

    private void getConnection() throws SQLException, NamingException {
        if (connection == null) {
            dataSource = InitialContext.doLookup("java:comp/env/jdbc/db_17");
            connection = this.dataSource.getConnection();
        }
    }

    /**
     *
     * @param id
     * @param username
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws javax.naming.NamingException
     */
    public Book selectBook(Integer id) throws ClassNotFoundException, SQLException, NamingException {
        Book book = null;
        getConnection();
        // Step 1: Establishing a Connection
        try (
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID);) {
            preparedStatement.setString(1, id.toString());
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String publisher = rs.getString("publisher");
                Integer price = rs.getInt("price");
                book = new Book(id, name, publisher, price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return book;
    }
    
    public ArrayList<Book> selectAllBooks() throws ClassNotFoundException, SQLException, NamingException {
        ArrayList<Book> books = new ArrayList<Book>();
        getConnection();
        // Step 1: Establishing a Connection
        try (
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);) {
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String publisher = rs.getString("publisher");
                Integer price = rs.getInt("price");
                books.add(new Book(id, name, publisher, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return books;
    }
    
    public void insertBook(Book book) throws SQLException, NamingException {
        getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKS_SQL)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getPublisher());
            preparedStatement.setInt(3, book.getPrice());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
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

    public boolean deleteBook(Integer id) throws SQLException, NamingException {
        boolean rowDeleted;
        getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateBook(Book book) throws SQLException, NamingException {
        boolean rowUpdated;
        getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK_SQL);) {
            statement.setString(1, book.getName());
            statement.setString(2, book.getPublisher());
            statement.setInt(3, book.getPrice());
            statement.setInt(4, book.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
