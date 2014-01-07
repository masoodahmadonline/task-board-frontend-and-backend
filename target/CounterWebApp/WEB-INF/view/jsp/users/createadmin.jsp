<%-- 
    Document   : createadmin
    Created on : Sep 7, 2013, 12:30:17 AM
    Author     : syncsys
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        errors:
        <c:forEach var="errorMessages" items="${errorMessages}">
            <tr>
              <td>${errorMessages}</td>
            </tr>
        </c:forEach>
            
            success:
        <c:forEach var="successMessages" items="${successMessages}">
            <tr>
              <td>${successMessages}</td>
            </tr>
        </c:forEach>
    </body>
</html>
