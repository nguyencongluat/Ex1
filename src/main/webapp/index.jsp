<%-- 
    Document   : index
    Created on : Apr 11, 2021, 6:27:36 PM
    Author     : nguyencongluat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="books/head.html" %>
    <body>
        <%@include file="books/header.html" %>
        <br>
        <br>
        <br>

        <form action="./login" method="POST" style="width: 500px; margin: auto">
            <div class="form-group">
              <label for="username">Username</label>
              <input type="text" class="form-control" name="username" placeholder="Enter username" id="username">
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </body>
</html>
