<%-- 
    Document   : adminAccountPage
    Created on : Jun 22, 2022, 11:14:47 PM
    Author     : win
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!----======== CSS ======== -->
        <link rel="stylesheet" href="admin/class/css/style.css">
        <link rel="stylesheet" href="admin/class/boot/bootstrap.min.css">
        <link rel="stylesheet" href="admin/class/boot/bootstrap.css">

        <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare</title>
        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <!--<title>Admin Dashboard Panel</title>-->
    </head>

    <body>
        <form action="listclass" method="POST">
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
                        <li><a href="listclass">
                                <i class="uil uil-thumbs-up"></i>
                                <span class="link-name">Class</span>
                            </a></li>
                        <li><a href="listschedule">
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
                        <li><a href="logout">
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
                    <a href="#" style="text-decoration: none">${sessionScope.account.firstName}</a>
                </div>


                <div class="sl-id" style="display: flex;">


                    <div>
                        <button  type="submit" style="width: 40%;margin-left: 70px;margin-top: 20px;">
                            <a href="addclass" style="text-decoration: none">Add</a> 
                        </button> 
                    </div>
                </div>


                <div class="dash-bottomtable">
                    <table class="table" >
                        <thead>
                            <tr>
                                <th scope="col">Class ID</th>
                                <th scope="col">Class Name</th>
                                <th scope="col">Grade</th>
                                <th scope="col">Class Description</th>
                                <th scope="col">Teacher name</th>

                                <th scope="col">Update</th>
                                <th scope="col">Delete</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.list}" var="lsc" >
                                <tr>
                                    <th scope="row">${lsc.class_id}</th>
                                    <td>${lsc.class_name}</td>
                                    <td>${lsc.grade}</td>
                                    <td>${lsc.class_description}</td>                           
                                    <td>${lsc.acc.lastName}</td>

                                    <td><a href="updateclass?sid=${lsc.class_id}">Update</a></td>
                                    <td><a href="deleteclass?sid=${lsc.class_id}">Delete</a></td>
                                </tr>

                            </c:forEach>


                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </body>
</html>