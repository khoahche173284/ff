<%-- 
    Document   : testcomments
    Created on : Jul 18, 2024, 2:06:37 AM
    Author     : Quang
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
        <c:if test="${not empty listreply}">Hello</c:if>
           
                   
                         <c:forEach var="repcmt" items="${listreply}">
                     
                            <img src="${repcmt.img}" alt="User Image" width="40" height="40">
                          
                                <div class="author">${repcmt.realName}</div>
                                <div class="meta">${repcmt.ngayDang}</div>
                                <div class="text">${repcmt.noiDung}</div>

                    </c:forEach>
    </body>
</html>
