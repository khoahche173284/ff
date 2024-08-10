<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>

        <link rel="stylesheet" href="css/signup.css">
    </head>

    <body>
        <div class="container">
            <form action="signup" method="GET">
                <h1>Create Account</h1>
                <input name="user" type="text" placeholder="Username" />
                <input name="pass" type="password" placeholder="Password" />
                <input name="repass" type="password" placeholder="Repeat Password" /> 
                 <input name="realname" type="text" placeholder="Your Name" /> 

                <input name="email" type="text" placeholder="Gmail" /> 
                
                <h4>${er}</h4>
                <button type="submit">Register</button>
            </form>
        </div>
    </body>

</html>