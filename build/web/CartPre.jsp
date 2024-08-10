<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Subscription Plans</title>
        <style>

        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="css/cartpre.css">
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
                <button class="button-side-bar" onclick="location.href = 'home.jsp';">🕛 Home</button>
                <button class="button-side-bar" onclick="location.href = 'Account';">👤 Account</button>
                <!--<button class="button-side-bar" onclick="location.href = 'history';">🧾 History</button>-->
                <!--<button class="button-side-bar" onclick="location.href = 'trash';">🗑️ Trash</button>-->
            </nav>
            <button onclick="location.href = 'logout';" class="sign-out button-side-bar">🕛 Log Out</button>
            <c:if test="${acc == null}">
                <button onclick="location.href = 'login';" class="sign-out button-side-bar">👤 Log In</button>
            </c:if>
        </aside>
        <main class="content">
            <h1 style="text-align:center; margin-top: 20px;">Good Grades Start With Great Writing</h1>
            <div class="carousel-container ">

                <div class="carousel-track">


                    <c:choose>
                        <c:when test="${empty ListP}">
                            <p>No products available.</p>
                        </c:when>

                        <c:otherwise>

                            <c:forEach var="product" items="${ListP}">
                               
                                <div class="plan ${product.productName == 'FREE' ? 'center' : ''}">
                                    <div class="plan-header">${product.productName}</div>
                                    <div class="plan-price">
                                        <c:choose>
                                            <c:when test="${product.productName == 'FREE'}">
                                                ${product.price} VND / month
                                            </c:when>
                                            <c:otherwise>
                                                
                                                ${product.price} VND / duration
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="plan-description">${product.description}</div> 
                                    <ul class="plan-features">
                                        <c:forEach var="feature" items="${product.features}">
                                            <li>${feature}</li>
                                            
                                            </c:forEach>
                                    </ul>
                                   
                                        
                                        <c:choose>
                                            <c:when  test="${product.productName == 'FREE'}">
                                                <button style = "font-size: 9px" type="submit" onclick="window.location.href = 'text';" class="plan-button" >
                                                    Current Plan
                                                    </button>
                                            </c:when>
                                            <c:otherwise>
                                                 <form action="PaymentController" method ="get">
                                                     <input type ="hidden" name="id" value="${product.id}">
                                                <button style = "font-size: 0.5vw ; height: 1.5vh ;width : 10vw;padding-bottom: 3vh;" type="submit" class="plan-button" >
                                                Start ${product.productName} Plan
                                                </button>
                                               </form>
                                            </c:otherwise>
                                        </c:choose>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                </div>
                <div class ="left">
                    <button class="" onclick="moveCarousel(-1)">&#9664;</button>
                    <button class="" onclick="moveCarousel(1)">&#9654;</button>
                </div>
            </div>

            <script>
                let currentIndex = 0;
                const track = document.querySelector('.carousel-track');
                const plans = Array.from(document.querySelectorAll('.plan'));
                const totalPlans = plans.length;

                function moveCarousel(direction) {
                    currentIndex = (currentIndex + direction + totalPlans) % totalPlans;
                    updateCarousel();
                }

                function updateCarousel() {
                    plans.forEach((plan, index) => {
                        const offset = (index - currentIndex + totalPlans) % totalPlans;
                        plan.style.order = offset;
                        if (offset === 2) {
                            plan.classList.add('center');
                            plan.classList.remove('hidden');
                        } else if (offset === 2 || offset === totalPlans - 3) {
                            plan.classList.remove('center');
                            plan.classList.remove('hidden');
                        } else {
                            plan.classList.remove('center');
                            plan.classList.add('hidden');
                        }
                    });
                }

                document.addEventListener('DOMContentLoaded', updateCarousel);
            </script>
        </main>
    </body>
</html>
