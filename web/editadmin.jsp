<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
    <link rel="stylesheet" href="css/edit-admin.css">
</head>
<body>
    <div class="container">
        <h2>Edit User</h2>
        <form action="adminedit" method="post">
            <label for="userId">ID:</label>
            <input type="text" name="userId" value="${users.id}" readonly>

            <label for="userName">User Name:</label>
            <input type="text" name="userName" value="${users.username}" required>

            <label for="realname">Name:</label>
            <input type="text" name="name" value="${users.realname}" required>

            <label for="email">Email:</label>
            <input type="email" name="email" value="${users.email}" required>

            <label for="password">Password:</label>
            <input type="password" name="password" value="${users.password}" required>

            <label for="img">Image URL:</label>
            <input type="text" name="img" value="${users.img}">

            <label for="createdDate">Created Date:</label>
            <input type="text" name="createdDate" value="${users.createdDate}" readonly>

             <label for="roleID">RoleID</label>
            <input type="text" name="roleid" value="${users.roleId}" readonly>
            
            <button type="submit">Save</button>
        </form>
        <a href="admin" class="back-button">Back to Admin</a>
    </div>
</body>
</html>
