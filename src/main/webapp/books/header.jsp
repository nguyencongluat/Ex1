<%-- 
    Document   : header
    Created on : Apr 11, 2021, 7:32:15 PM
    Author     : nguyencongluat
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #eceff1">
                    <h3>
                       Book Management App
                    </h3>

                    <c:if test="${user != null}">

                        <ul class="navbar-nav" style='margin-left: 30px'>
                            <li><a href="<%=request.getContextPath()%>/books" style="color: black" class="nav-link">Books</a></li>
                        </ul>
                        
                        <form class="input-group" action="books" method='GET' style='margin-left: 30px; width: 500px'>
                            <input type="search" name='key' class="form-control rounded" placeholder="Search" aria-label="Search"
                              aria-describedby="search-addon" />
                            <input type="hidden" name='action' class="form-control rounded" value='search' />
                            <button style='margin-left: 10px' type="submit" class="btn btn-outline-primary">search</button>
                        </form>
                        <div style='margin-left: 80px; text-align: right'>
                                Hello, <em>${user}</em> 
                                <a style='margin-left: 40px' href="<%=request.getContextPath()%>/logout">
                                    Logout
                                </a>
                        </div>
                    </c:if>

                </nav>
</header>
