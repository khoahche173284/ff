<%-- 
    Document   : reply
    Created on : Jun 21, 2024, 9:01:51 AM
    Author     : pgb31
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title> 
         <style>
            body {
                font-size: 18px;
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            form {
                display: flex;
                flex-direction: column;
                align-items: flex-start;
            }
            select, textarea, input[type="submit"] {
                font-size: 20px;
                padding: 10px;
                margin-bottom: 10px;
            }
            textarea {
                width: 60%;
                height: 200px;
            }
            input[type="submit"] {
                cursor: pointer;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
            } 
             button {
                background-color: #4CAF50;
                font-size: 24px; /* Larger font size for the button */
                padding: 15px 30px; /* Larger padding for the button */ 
                     border: none;
                border-radius: 5px;
                  color: white;
            }
            
            
        </style>
    </head>
    <body>  
           <button type="button" onclick="location.href = 'FeedbackManager'">Back</button>
        <c:if test="${not empty curntuser}" >
        <h3>
            Send email to: ${curntuser.email}
        </h3> 
        <br>  
        <form action="replyfeedback" method="POST"> 
            
        <textarea name="content" id="content"></textarea>  
          <input type="hidden" name="gmail" value=" ${curntuser.email}">
           <input type="submit" value="Submit" /> 
         
           
        </form> 
    </c:if> 
        
        <c:if test="${empty curntuser}" >
            <h2>${tb}</h2>
        </c:if>
        
    </body>
</html>
