/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;

/**
 *
 * @author nguyencongluat
 */
public class BookServlet extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action;
        action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "new":
                showNewForm(request, response);
                break;
            case "delete":
                deleteBook(request, response);
                break;
            default:
                listBooks(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action;
        action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "update":
                updateBook(request, response);
                break;
            case "insert":
                insertBook(request, response);
                break;
            default:
                doGet(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listBooks(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Book> books = null;
        try {
            books = new BookDAO().selectAllBooks();
        } catch (ClassNotFoundException | SQLException | NamingException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("books/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("books/book-form.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = null;
        try {
            existingBook = new BookDAO().selectBook(id);
        } catch (ClassNotFoundException | NamingException | SQLException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("books/book-form.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);

    }
    
    private void insertBook(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        String name = request.getParameter("name");
        String publisher = request.getParameter("publisher");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Book newBook = new Book(name, publisher, price);
        try {
            try {
                new BookDAO().insertBook(newBook);
            } catch (SQLException ex) {
                Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("books");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String publisher = request.getParameter("publisher");
        Integer price = Integer.parseInt(request.getParameter("price"));

        Book book = new Book(id, name, publisher, price);
        try {
            try {
                new BookDAO().updateBook(book);
            } catch (SQLException ex) {
                Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("books");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            new BookDAO().deleteBook(id);
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("books");

    }
}
