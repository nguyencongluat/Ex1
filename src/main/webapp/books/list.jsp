<%-- 
    Document   : list.jsp
    Created on : Apr 11, 2021, 3:04:24 PM
    Author     : nguyencongluat
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <html>

     <%@include file="head.html" %>

        <body>

            <%@include file="header.html" %>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Books</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/books?action=new" class="btn btn-success">Add
     New Book</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Publisher</th>
                                <th>Price</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="book" items="${books}">

                                <tr>
                                    <td>
                                        <c:out value="${book.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${book.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${book.publisher}" />
                                    </td>
                                    <td>
                                        <c:out value="${book.price}" />
                                    </td>
                                    <td><a href="books?action=edit&id=<c:out value='${book.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="books?action=delete&id=<c:out value='${book.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

</html>
