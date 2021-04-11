<%-- 
    Document   : new-book-form
    Created on : Apr 11, 2021, 5:32:07 PM
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
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${book != null}">
                            <form action="./books?action=update" method="post">
                        </c:if>
                        <c:if test="${book == null}">
                            <form action="./books?action=insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${book != null}">
                                    Edit Book
                                </c:if>
                                <c:if test="${book == null}">
                                    Add New Book
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${book != null}">
                            <input type="hidden" name="id" value="<c:out value='${book.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Book Name</label> <input type="text" value="<c:out value='${book.name}' />" class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Book Publisher</label> <input type="text" value="<c:out value='${book.publisher}' />" class="form-control" name="publisher">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Book Price</label> <input type="number" value="<c:out value='${book.price}' />" class="form-control" name="price">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

</html>
