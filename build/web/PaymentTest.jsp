<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Payment Test</title>
        <link rel="stylesheet" href="css/home.css">
        <style>
      
        h1 {
            font-size: 24px;
            margin-top: 0;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-bottom: 10px;
            font-size: 14px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #45a049;
        }

        #response {
            margin-top: 10px;
            font-size: 14px;
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
    </header>
    <aside class="sidebar">
        <nav>
            <button class="button-side-bar" onclick="location.href = 'home.jsp';">🕛 Home</button>
            <button class="button-side-bar" onclick="location.href = 'Account';">👤 Account</button>
            <button class="button-side-bar" onclick="location.href = 'history';">🧾 History</button>
            <button class="button-side-bar" onclick="location.href = 'trash';">🗑️ Trash</button>
            <button class="button-side-bar" onclick="location.href = 'ProductController';">💡 Update </button>

        </nav>
        <button onclick="location.href = 'logout';" class="sign-out button-side-bar">🕛 Log Out</button>
        <c:if test="${acc == null}">
            <button onclick="location.href = 'login';" class="sign-out button-side-bar">👤 Log In</button>
        </c:if>
    </aside>
    <main class="content">
          <h1>Payment Form</h1>
        <form id="paymentForm">
            <label for="amount">Amount:</label>
            <input type="text" id="amount" name="amount" value="${price}" placeholder="Enter amount" ><br><br>
            

            <label for="bankCode">Bank Code:</label>
           
            <select id="bankCode" name="bankCode">
                <option value="NCB">NCB</option>
                <option value="NCB">VCB</option>
            </select><br><br>
            <label for="language">Language:</label>
            <select id="language" name="language">
                <option value="vn">Vietnamese</option>
                <option value="en">English</option>
            </select><br><br>

            <button type="button" onclick="submitPayment()">Submit Payment</button>
        </form>

        <div id="response"></div>
    

        <script>
            async function submitPayment() {
                const form = document.getElementById('paymentForm');
                const formData = new FormData(form);

                const data = {
                    amount: formData.get('amount'),
                    bankCode: formData.get('bankCode'),
                    language: formData.get('language')
                };

                try {
                    const response = await fetch('PaymentController', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(data)
                    });
                    const result = await response.json();
                    if (result.code === '00') {
                        window.location.href = result.data;
                    } else {
                        document.getElementById('response').innerText = 'Error: ' + result.message;
                    }
                } catch (error) {
                    document.getElementById('response').innerText = 'Error: ' + error.message;
                }
            }
        </script>
    </main>
</body>
</html>
