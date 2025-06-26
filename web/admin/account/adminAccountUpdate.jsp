<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Admin Account</title>

    <!-- Bootstrap CSS từ CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .update-form {
            max-width: 800px;
            margin: 50px auto;
            padding: 30px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        }

        h2 {
            font-weight: bold;
            margin-bottom: 25px;
        }

        label {
            font-weight: 500;
        }

        .btn-primary {
            padding: 10px 30px;
            font-weight: bold;
        }
    </style>
</head>
<body>

<form action="updateaccount?ida=${s.accountID}" method="POST" class="update-form">
    <h2 class="text-center">Update Account</h2>

    <div class="mb-3">
        <label class="form-label">First Name</label>
        <input type="text" name="txtFirstName" class="form-control" value="${s.firstName}" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Last Name</label>
        <input type="text" name="txtLastName" class="form-control" value="${s.lastName}" required>
    </div>

    <div class="mb-3">
        <label class="form-label d-block">Gender</label>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender" value="male"
                   <c:if test="${s.gender}">checked</c:if>>
            <label class="form-check-label">Male</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender" value="female"
                   <c:if test="${!s.gender}">checked</c:if>>
            <label class="form-check-label">Female</label>
        </div>
    </div>

    <div class="mb-3">
        <label class="form-label">Email</label>
        <input type="email" name="txtEmail" class="form-control" value="${s.email}" readonly>
    </div>

    <div class="mb-3">
        <label class="form-label">Password</label>
        <input type="password" name="txtPassword" class="form-control" value="${s.password}" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Date of Birth</label>
        <input type="date" name="dob" class="form-control" value="${s.dob}" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Phone Number</label>
        <input type="text" name="txtPhone" class="form-control" value="${s.phoneNumber}" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Address</label>
        <input type="text" name="ttAddress" class="form-control" value="${s.address}" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Image URL</label>
        <input type="text" name="txtImg" class="form-control" value="${s.img}">
    </div>

    <div class="mb-3">
        <label class="form-label">Role</label>
        <select class="form-select" name="slRole" required>
            <c:forEach var="role" items="${requestScope.listR}">
                <option value="${role.roleID}" <c:if test="${role.roleID eq s.role.roleID}">selected</c:if>>
                    ${role.roleName}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="text-center mt-4">
        <input type="submit" value="Save" class="btn btn-primary">
    </div>
</form>

</body>
</html>
