<%-- 
    Document   : userfeedback
    Created on : Jun 21, 2024, 8:09:49 AM
    Author     : pgb31
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Feedback</title>
        <link rel="stylesheet" href="css/home.css">
        <style>
            body {
                
            }
            form {
                display: flex;
                flex-direction: column;
                align-items: flex-start;
            }
            select, textarea, input[type="submit"] {
                font-size: 15px;
                padding: 10px;
                margin-bottom: 10px;
                border-radius:   10px;
                border-color : #D3D3D3;
                 box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            }
            textarea {
                width: 60%;
                height: 200px;
            }
            input[type="submit"] {
                cursor: pointer;
                background-color: #AF6DE2;
                color: white;
                border: none;
                border-radius: 5px;
                 box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            } 
         
            
        </style>
    </head>
    <body> 
         <header>
            <div class="logo">
                <img onclick="location.href = 'home.jsp';"
                     src="https://static.vecteezy.com/system/resources/previews/012/986/609/non_2x/network-sharing-circle-logo-icon-free-png.png">
            </div>
            <h3 class="title-home">Fantasy Grammarly</h3>
           
            <c:if test="${acc != null}">
                <h5 class="accountname">Hello ${acc.realname}</h5>
                <img class="avatar" src="${acc.img}" alt="avatar"/>
            </c:if>
        </header>
           <aside class="sidebar">
            <nav>
                <button class="button-side-bar" onclick="location.href = 'Account';">👤 Account</button>
                <button class="button-side-bar" onclick="location.href = 'history';">🧾 History</button>
                <button class="button-side-bar" onclick="location.href = 'trash';">🗑️ Trash</button> 
                
                
                
                <button class="button-side-bar" onclick="location.href = 'feedback';">💡 Feedback </button>  
                <c:if test="${acc.roleId == 2}">
                <button class="button-side-bar" onclick="location.href = 'ProductController';">💡 Upgrade</button> 
                    </c:if>
                
                  <c:if test="${acc.roleId == 3}">
                <button class="button-side-bar" onclick="location.href = 'StatusCartController';">💡 Status Cart </button>
                
                   </c:if>
                    <c:if test="${acc.roleId == 1}">
                    <button onclick="location.href  = 'adminhomejsp.jsp';" class="button-side-bar">💡 Manage</button>
                    </c:if>
                    <button class="button-side-bar" onclick="location.href = 'userfeedbacklist';">📫 My Feedback</button>
             
            </nav>
            <button onclick="location.href = 'logout';" class="sign-out button-side-bar">🕛 Log Out</button>
            <c:if test="${acc == null}">
                <button onclick="location.href = 'login';" class="sign-out button-side-bar">👤 Log In</button>
            </c:if>
        </aside>
          <main class="content">
        
              
        <br>
       
        <form action="feedback" method="POST"> 
          
            <h3 >Feedback Type</h3>
            
            <select name="FeedbackType" id="feedbacktype"> 
                <c:forEach items="${fbt}" var="c">
                    <option value="${c.feedbackName}" ${c.feedbackName==feedbackName ? 'selected="selected"' : ''}>
                        ${c.feedbackName}
                    </option> 
                </c:forEach>
            </select>
             
             <h3>Feedback Content</h3>
             
            <textarea placeholder="Type your text here..." name="content" id="content"></textarea>
            
            <input type="submit" value="Submit" />
        </form> 
         <p style="color: #4CAF50">${tb}</p>
        <p style="color: red">${er}</p>
        </main>
    </body>
</html>
