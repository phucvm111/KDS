<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!----======== CSS ======== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <link rel="stylesheet" href="admin/schedule/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare</title>

        <!--script-->
        <script src="https://kit.fontawesome.com/28a1dc3e10.js" crossorigin="anonymous"></script>
        <!--<title>Admin Dashboard Panel</title>-->
    </head>

    <body>
        <nav>
            <div class="logo-name">
                <div class="logo-image">
                    <!--<img src="images/logo.png" alt="">-->
                </div>

                <span class="logo_name">Admin Page</span>
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
                    <li><a href="changepassword">
                            <i class="uil uil-lock-alt"></i>
                            <span class="link-name">Change Password</span>
                        </a></li>
                    <li><a href="day_class">
                            <i class="uil uil-user-square"></i>
                            <span class="link-name">Nutrition</span>
                        </a></li>

                    <!--                    <li><a href="#">
                                                <i class="uil uil-share"></i>
                                                <span class="link-name">Attendance</span>
                                            </a></li>-->
                </ul>

                <ul class="logout-mode">
                    <li>
                        <a href="logout">
                            <i class="uil uil-signout"></i>
                            <span class="link-name">Logout</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="dashboard">
            <div class="dash-lefttop">
                <img src="https://i.pinimg.com/originals/72/45/fb/7245fb0ca786bb4a98fb8465e437c5bb.jpg" alt="">
                <a href="#">${sessionScope.account.firstName} </a>
            </div>
            <div class="test">
                Hello
                ${sessionScope.cid}
                =
                firstMonday ${requestScope.firstMonday}
                = 
                recentMonday ${requestScope.recentMonday}
            </div>
            <form id="f" action="listschedule" method="post">
                <div class="select-menu">
                    <!--class-->
                    <div class="class-select">


                        <select class="form-select form-select-sm" aria-label=".form-select-sm example" name="cid"
                                style="height: 36px;">
                            <!--<option selected>Select Class</option>-->
                            <c:forEach items="${requestScope.classs}" var="cl">
                                <option value= "${cl.class_id}" >${cl.class_name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>



                    <select name="datee" id="ddlViewBy">
                        <c:forEach var="entry" items="${weeks}">
                            <c:set var="currentKey" value="${currentweek.keySet().toArray()[0]}" />
                            <option value="${entry.key}" <c:if test="${entry.key == currentKey}">selected</c:if>>
                                ${entry.value}
                            </option>
                        </c:forEach>
                    </select>


                    <button class="btn btn-outline-success" type="submit"
                            onclick="changeDate()">Search</button>
            </form>
        </div>

        <div class="dash-bottomtable">
            <table class="table" style="margin-bottom: 0">
                <thead>
                    <tr>
                        <th>Mon</th>
                        <th>Tue</th>
                        <th>Wed</th>
                        <th>Thu</th>
                        <th>Fri</th>
                        <th>Sat</th>
                        <th>Sun</th>
                    </tr>

                    <tr>
                        <c:if test="${not empty daysOfWeek}">
                        <c:forEach var="day" items="${daysOfWeek}">
                        <th><fmt:formatDate value="${day}"/></th>
                        </c:forEach>
                    </c:if>
                    </tr>



                <!-- TODO: render hàng thứ hai hiển thị ngày (dd/MM) -->
                </thead>

                <!-- TODO: render từng dòng slot từ 1 đến 7 -->
                <!-- mỗi dòng là một slot, mỗi cột là một ngày -->
                <!-- ô có thể có schedule hoặc rỗng để thêm mới -->
            </table>

        </div>
    </div>



    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="admin/schedule/js/schedule.js"></script> 
</body>

</html>