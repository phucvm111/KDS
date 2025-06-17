<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Update Admin Account</title>
        <link rel="stylesheet" href="admin/account/css/style.css">
        <link rel="stylesheet" href="admin/account/boot/bootstrap.min.css">
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    </head>
    <body>
        <form action="updateaccount?ida=${s.accountID}" method="POST" style="margin: 30px;">
            <h2 class="text-center mb-4">Update Account</h2>

            <div class="mb-3">
                <label class="form-label">First Name</label>
                <input type="text" name="txtFirstName" class="form-control" value="${s.firstName}">
            </div>

            <div class="mb-3">
                <label class="form-label">Last Name</label>
                <input type="text" name="txtLastName" class="form-control" value="${s.lastName}">
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
                <input type="password" name="txtPassword" class="form-control" value="${s.password}">
            </div>

            <div class="mb-3">
                <label class="form-label">Date of Birth</label>
                <input type="date" name="dob" class="form-control" value="${s.dob}">
            </div>

            <div class="mb-3">
                <label class="form-label">Phone Number</label>
                <input type="text" name="txtPhone" class="form-control" value="${s.phoneNumber}">
            </div>

            <div class="mb-3">
                <label class="form-label">Address</label>
                <input type="text" name="ttAddress" class="form-control" value="${s.address}">
            </div>

            <div class="mb-3">
                <label class="form-label">Image URL</label>
                <input type="text" name="txtImg" class="form-control" value="${s.img}">
            </div>

            <div class="mb-3">
                <label class="form-label">Role</label>
                <select class="form-select" name="slRole">
                    <c:forEach var="role" items="${requestScope.listR}">
                        <option value="${role.roleID}" <c:if test="${role.roleID eq s.role.roleID}">selected</c:if>>
                            ${role.roleName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="text-center mt-4">
                <input type="submit" value="Save" class="btn btn-primary px-4">
            </div>
        </form>
    </body>
</html>
