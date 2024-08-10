<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Account</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="acc.css">
    </head>
    <body>
        <header>
            <div class="logo">
                <img onclick="location.href = 'home.jsp';"
                     src="https://static.vecteezy.com/system/resources/previews/012/986/609/non_2x/network-sharing-circle-logo-icon-free-png.png">
            </div>
            <h1 class="title-home">Account</h1>
            <h5 class="accountname"> ${acc.realname}</h5>
            <img class="avatar" src="${acc.img}" alt="avatar"/>
        </header>
        <aside class="sidebar">
            <c:if test="${acc.roleId == 2}">
                <button class="button-side-bar" onclick="location.href = 'ProductController';">Upgrade</button>
            </c:if>
            <button class="button-side-bar" onclick="location.href = 'changePassword.jsp';">Change Password</button> 
            <button class="button-side-bar" onclick="location.href = 'Library';">Personal Dictionary</button>
            <button class="button-side-bar" onclick="location.href = 'LoginHistory';">Recent Activity</button>
            <button onclick="logOut();location.href = 'login';" class="sign-out button-side-bar">Log Out</button>
        </aside>
        <script>
            function logOut() {
                alert("log out");
            }
        </script>
        <main>
            <div class="container mt-3">
                <c:if test="${not empty message}">
                    <div class="alert" role="alert" id="notification">
                        ${message}
                    </div>
                </c:if>
            </div>

            <section class="profile">
                <h2>Profile</h2>
                <img src="${acc.img}" alt="Avatar">
                <button class="button-hihi" data-bs-toggle="modal" data-bs-target="#changeAvatar" data-bs-whatever="@getbootstrap">change</button>
                <p>Name : ${acc.realname}</p>
                <p>User Name: ${acc.username}</p>
                <p>Email: ${acc.email}</p>
            </section>
            <section class="settings">
                <h2>Settings</h2>
                <div class="settings-block">
                    <span id="realname">${acc.realname}</span>
                </div>
                <div class="settings-block">
                    <span id="username">${acc.username}</span>
                </div>
                <div class="settings-block">
                    <span id="realname">${acc.email}</span>
                </div>
                <button type="button" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@getbootstrap">Change</button>
            </section>

            <div class="container mt-3">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="alert alert-success" role="alert">
                        ${success}
                    </div>
                </c:if>
            </div>

            <section class="deleteaccount">
                <h2>Delete Account</h2>
                <form action="deleteaccount" method="get" onsubmit="return confirmDelete();">
                    <input type="hidden" name="id" value="${acc.id}">
                    <button type="submit">Delete</button>
                </form>
            </section>

            <script>
                function confirmDelete() {
                    return confirm('Are you sure you want to delete this account?');
                }

                window.onload = function () {
                    const notification = document.getElementById('notification');
                    if (notification) {
                        setTimeout(() => {
                            notification.style.display = 'none';
                        }, 3000);

                        fetch('changeAvatar').then(response => response.text()).then(data => {
                            console.log('Session messages cleared');
                        });
                    }
                };
            </script>
        </main>

        <div class="modal fade" id="changeAvatar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Change Avatar</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="changeAvatar" method="post" enctype="multipart/form-data">
                            <div class="mb-3">
                                <label for="avatarFile" class="form-label">Choose Image File</label>
                                <input type="file" class="form-control" id="avatarFile" name="avatarFile" accept="image/*">
                            </div>
                            <div class="mb-3">
                                <label for="avatarLink" class="form-label">Or Paste Image URL</label>
                                <input type="text" class="form-control" id="avatarLink" name="avatarLink" placeholder="Paste link here">
                            </div>
                            <div class="text-center">
                                <img id="avatar-preview" src="${acc.img}" alt="Avatar Preview" class="img-thumbnail" style="max-width: 200px; max-height: 200px;">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Update</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Update Information</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="Account" method="post">
                            <div class="mb-3">
                                <label for="modal-realname" class="form-label">Name</label>
                                <input type="text" class="form-control" id="modal-realname" name="realname" value="${acc.realname}" required>
                            </div>
                            <div class="mb-3">
                                <label for="modal-username" class="form-label">Username</label>
                                <input type="text" class="form-control" id="modal-username" name="username" value="${acc.username}" required>
                            </div>
                            <div class="mb-3">
                                <label for="modal-email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="modal-email" name="email" value="${acc.email}" required>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Update</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
