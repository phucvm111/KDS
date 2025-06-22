<%-- 
    Document   : adminClassPage
    Created on : Jun 11, 2025, 1:53:42 PM
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
        <link rel="stylesheet" href="admin/class/css/style.css">
        <link rel="stylesheet" href="admin/class/boot/bootstrap.min.css">
        <link rel="stylesheet" href="admin/class/boot/bootstrap.css">
        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <!--<title>Admin Dashboard Panel</title>-->
    </head>

    <body>
        <form action="addclass" method="POST">
            <nav>
                <div class="logo-name">
                    <div class="logo-image">
                        <img src="images/logo.jpg" alt="Logo">
                    </div>
                    <span class="logo_name">
                        <a href="listaccount">Admin Page</a>
                    </span>
                </div>

                <div class="menu-items">
                    <ul class="nav-links">
                        <li><a href="dashboard"><i class="uil uil-dashboard"></i>Dashboard</a></li>
                        <li><a href="listaccount"><i class="uil uil-estate"></i>Account</a></li>
                        <li><a href="listkinder"><i class="uil uil-chart"></i>Kindergartner</a></li>
                        <li><a href="listclass"><i class="uil uil-thumbs-up"></i>Class</a></li>
                        <li><a href="listschedule"><i class="uil uil-comments"></i>Schedule</a></li>
                        <li><a href="changepassword"><i class="uil uil-lock-alt"></i>Change Password</a></li>
                        <li><a href="event"><i class="uil uil-calendar-alt"></i>Event</a></li>
                        <li>
                            <a href="day_class">
                                <i class="uil uil-utensils-alt"></i>
                                <span class="link-name">Nutrition</span>
                            </a>
                        </li>

                    </ul>
                    <ul class="logout-mode">
                        <li><a href="login"><i class="uil uil-signout"></i>Logout</a></li>
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
        <!-- <script src="agu.js"></script> -->
    </body>

</html>