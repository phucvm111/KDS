<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Account</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        nav {
            background-color: #fff;
            padding: 1rem;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }
        .logo_name a {
            font-size: 24px;
            font-weight: bold;
        }
        .form-title {
            font-size: 36px;
            font-weight: 600;
            margin: 30px 0 20px 0;
            text-align: center;
        }
        .form-section {
            max-width: 800px;
            margin: auto;
            padding: 30px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }
        .rdCheck {
            display: flex;
            gap: 40px;
            margin-bottom: 1rem;
        }
        footer {
            text-align: center;
            padding: 1rem;
            color: gray;
            font-size: 14px;
        }
    </style>
</head>
<body>

<nav class="d-flex justify-content-between align-items-center">
    <div class="d-flex align-items-center">
        <img src="images/logo.jpg" alt="Logo" width="50" height="50" class="me-3">
        <span class="logo_name"><a href="listaccount" class="text-dark text-decoration-none">Admin Page</a></span>
    </div>
    <a href="#" class="btn btn-outline-danger"><i class="uil uil-signout"></i> Logout</a>
</nav>

<div class="form-title">Add New Account</div>

<form action="addaccount" method="POST" class="form-section">
    <div class="mb-3">
        <label class="form-label">First Name</label>
        <input type="text" class="form-control" name="txtFirstName" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Last Name</label>
        <input type="text" class="form-control" name="txtLastName" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Gender</label>
        <div class="rdCheck">
            <div class="form-check">
                <input class="form-check-input" type="radio" value="male" name="flexRadioDefault" id="genderMale">
                <label class="form-check-label" for="genderMale">Male</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="female" name="flexRadioDefault" id="genderFemale" checked>
                <label class="form-check-label" for="genderFemale">Female</label>
            </div>
        </div>
    </div>

    <div class="mb-3">
        <label class="form-label">Email</label>
        <input type="email" class="form-control" name="txtEmail" placeholder="name@example.com" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Password</label>
        <input type="password" class="form-control" name="txtPassword" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Date of Birth</label>
        <input type="date" class="form-control" name="dob" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Phone Number</label>
        <input type="text" class="form-control" name="txtPhone" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Address</label>
        <textarea class="form-control" name="ttAddress" rows="3" required></textarea>
    </div>

    <div class="mb-3">
        <label class="form-label">Image URL</label>
        <input type="text" class="form-control" name="txtImg">
    </div>

    <div class="mb-3">
        <label class="form-label">Role</label>
        <select class="form-select" name="slRole" required>
            <c:forEach items="${requestScope.roles}" var="role">
                <c:if test="${role.roleName != 'parent'}">
                    <option value="${role.roleID}">${role.roleName}</option>
                </c:if>
            </c:forEach>
        </select>
    </div>

    <div class="d-grid mt-4">
        <button type="submit" class="btn btn-primary">Save Account</button>
    </div>
</form>

<footer>
    &copy; 2025 Admin System - Created by Vu Tuan Hai
</footer>

</body>
</html>
