<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="${pageContext.request.contextPath}//assets/image/logo2-removebg-preview.png">
        <title>Update Teacher Profile</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/teacher/css/teacherUpdate.css">
    </head>
    <body>

        <c:set var="teacher" value="${requestScope.account}" />
        <div>
            <div class="home">
                <div class="left-side-menu">
                    <div class="vertical-menu">
                        <div class="user-welcome">
                            <img class="user-img" src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" style="width: 80px; height: 80px;" alt="">
                            <p>${sessionScope.account.firstName} ${sessionScope.account.lastName}</p>
                        </div>
                        <div class="menu-item-container">
                            <ul class="item-lists">

                                <li class="menu-item current1">
                                    <a href="${pageContext.request.contextPath}/teacher/teacherprofile.jsp">Teacher Information</a>
                                </li>

                                <li class="menu-item">
                                    <a href="${pageContext.request.contextPath}/changepassword">Change Password</a>
                                </li>


                            </ul>
                        </div>
                        <div style="border-top: 3px solid gray;"></div>
                        <div>
                            <input type="button" class="log-out_button" onclick="window.location.replace('${pageContext.request.contextPath}/logout')" value="Log out"/>
                        </div>
                    </div>
                </div>
                <div class="form-container">
                    <h2>Update Profile</h2>
                    <form method="post" action="${pageContext.request.contextPath}/updateteacher">
                        <div class="form-group">
                            <label>First Name</label>
                            <input type="text" name="firstName" value="${teacher.firstName}" required>
                        </div>

                        <div class="form-group">
                            <label>Last Name</label>
                            <input type="text" name="lastName" value="${teacher.lastName}" required>
                        </div>

                        <div class="form-group">
                            <label>Date of Birth</label>
                            <input type="date" name="dob" value="${teacher.dob}" required>
                        </div>

                        <div class="form-group">
                            <label>Gender</label>
                            <select name="gender" required>
                                <option value="true" ${teacher.gender == true ? 'selected' : ''}>Male</option>
                                <option value="false" ${teacher.gender == false ? 'selected' : ''}>Female</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Phone</label>
                            <input type="text" name="phone" value="${teacher.phoneNumber}" required>
                        </div>

                        <div class="form-group">
                            <label>Address</label>
                            <input type="text" name="address" value="${teacher.address}" required>
                        </div>

                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="email" value="${teacher.email}" required>
                        </div>

                        <button type="submit" class="submit-btn">Save Changes</button>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
