<%-- 
    Document   : adminAccountAdd..jsp
    Created on : Jun 10, 2025, 11:20:05 PM
    Author     : Vu Tuan Hai <HE176383>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!----======== CSS ======== -->
        <link rel="stylesheet" href="admin/account/css/style.css">
        <link rel="stylesheet" href="admin/account/boot/bootstrap.min.css">
        <link rel="stylesheet" href="admin/account/boot/bootstrap.css">
        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <!--<title>Admin Dashboard Panel</title>-->
    </head>

    <body>
        <form action="addaccount" method="POST">
            <nav>
                <div class="logo-name">
                    <div class="logo-image">
                        <img src="images/logo.jpg" alt="">
                    </div>

                    <span class="logo_name"  ><a href="listaccount" style="text-decoration: none;color: black">Admin Page</a></span>
                </div>

                <div class="menu-items">
                    <ul class="nav-links">
                        <li><a href="listaccount">
                                <i class="uil uil-estate"></i>
                                <span class="link-name">Account</span>
                            </a></li>

                        <li><a href="listkinder">
                                <i class="uil uil-chart"></i>
                                <span class="link-name">Kindergartner</span>
                            </a></li>
                        <li><a href="#">
                                <i class="uil uil-thumbs-up"></i>
                                <span class="link-name">Class</span>
                            </a></li>
                        <li><a href="#">
                                <i class="uil uil-comments"></i>
                                <span class="link-name">Schedule</span>
                            </a></li>
                        <!--                        <li><a href="#">
                                                        <i class="uil uil-share"></i>
                                                        <span class="link-name">Attendance</span>
                                                    </a></li>-->
                    </ul>

                    <ul class="logout-mode">
                        <li><a href="#">
                                <i class="uil uil-signout"></i>
                                <span class="link-name">Logout</span>
                            </a></li>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="dashboard">

                <div class="dash-lefttop">
                    <img src="https://i.pinimg.com/originals/72/45/fb/7245fb0ca786bb4a98fb8465e437c5bb.jpg" alt="">
                    <a href="#">User</a>
                </div>

                <div class="form-title" style="text-align: center;font-size: 50px;">Add Account</div>
                <div class="form-content" style="width: 80%;height: auto; margin-left: 10%; padding-top: 0;padding-bottom: 0;">
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">First Name</label>
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="txtFirstName" required="">
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Last Name</label>
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="txtLastName" required="">
                    </div>
                    <div class="rdCheck" style="display: flex">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" value="male" name="flexRadioDefault" id="flexRadioDefault1">
                            <label class="form-check-label" for="flexRadioDefault1">
                                Male
                            </label>
                        </div>
                        <div class="form-check" style="padding-left: 40px">
                            <input class="form-check-input" type="radio" value="female" name="flexRadioDefault" id="flexRadioDefault2" checked>
                            <label class="form-check-label" for="flexRadioDefault2">
                                FeMale
                            </label>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Email</label>
                        <input type="email" class="form-control" id="exampleFormControlInput1" name="txtEmail" placeholder="name@example.com" required="">
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Password</label>
                        <input type="password" class="form-control" name="txtPassword" id="exampleFormControlInput1" required="">
                    </div>

                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">DOB</label>
                        <input type="date" name="dob" required=""/>(yyyy-MM-dd)<br/>
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Number Phone</label>
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="txtPhone" required="">
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlTextarea1" class="form-label">Address</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" name="ttAddress" rows="3" required=""></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Image</label>
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="txtImg" >
                    </div>
                    <label for="exampleFormControlInput1" class="form-label">Role</label>
                    <select class="form-select" name="slRole" aria-label="Default select example">
                        <!--<option value="0" selected>All</option>-->
                        <c:forEach items="${requestScope.roles}" var="role">
                            <c:if test="${role.roleName != 'parent'}">
                                <option selected="" value="${role.roleID}">${role.roleName}</option>
                            </c:if>

                        </c:forEach>
                    </select>
                    <div class="d-grid gap-2 d-md-block" style="margin-top: 30px; margin-bottom: 30px; ;">
                        <input onclick="send()" type="submit" value="Save"/>  
                    </div>
                </div>
            </div>
        </div>
    </form>
    <!-- <script src="agu.js"></script> -->
</body>

</html>
