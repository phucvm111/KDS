<%-- 
    Document   : adminAccountUpdate
    Created on : Jun 24, 2022, 4:32:23 PM
    Author     : win
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<!--=== Coding by CodingLab | www.codinglabweb.com === -->
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!----======== CSS ======== -->
        <link rel="stylesheet" href="admin/class/css/style.css">
        <link rel="stylesheet" href="admin/class/boot/bootstrap.min.css">
        <link rel="stylesheet" href="admin/class/boot/bootstrap.css">
        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <!--<title>Admin Dashboard Panel</title>-->
    </head>

    <body>
        <form action="updateclass?sid=${clUpdate.class_id}" method="POST">
            <nav>
                <div class="logo-name">
                    <div class="logo-image">
                        <img src="images/logo.jpg" alt="">
                    </div>

                    <span class="logo_name"><a href="listaccount" style="text-decoration: none;color: black">Admin Page</a></span>
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
                        <li><a href="ListClass">
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
                        <li><a href="adminNotifyTeachers">
                                <i class="uil uil-bell"></i>
                                <span class="link-name">Send Notification</span>
                            </a></li>

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

                <div class="form-title" style="text-align: center;font-size: 50px;">Update Class</div>
                <div class="form-content" style="width: 80%;height: auto; margin-left: 10%; padding-top: 0;padding-bottom: 0;">
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Class Name</label>
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="txtClassName" value="${clUpdate.class_name}" >
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Grade</label>
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="txtGrade" value="${clUpdate.grade}">
                    </div>



                    <div class="mb-3">
                        <label for="exampleFormControlTextarea1" class="form-label">Description class</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" name="taDesClass"  rows="3">${clUpdate.class_description}</textarea>
                    </div>

                    <label for="exampleFormControlInput1" class="form-label">TeacherName</label>
                    <select class="form-select" name="slRole" aria-label="Default select example">
                        <c:forEach items="${requestScope.listAc}" var="listAc">
                            <option value="${listAc.accountID}"
                                    <c:if test="${listAc.accountID==requestScope.clUpdate.acc.accountID}">
                                        selected
                                    </c:if>
                                    >${listAc.lastName}</option>
                        </c:forEach>

                    </select>
                    <div class="d-grid gap-2 d-md-block" style="margin-top: 30px; margin-bottom: 30px; ;">
                        <input onclick="send()" type="submit" value="Save"/>  
                    </div>
                </div>
            </div>
        </form>

        <!-- <script src="agu.js"></script> -->
    </body>

</html>
