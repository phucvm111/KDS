<%-- 
    Document   : checkAttendence
    Created on : Jun 1, 2022, 8:04:30 AM
    Author     : Windows 10 TIMT
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare</title>
        <link rel="stylesheet" href="teacher/css/teacherhome.css">
        <link rel="stylesheet" href="teacher/css/kidprofile.css">
        <script src="https://kit.fontawesome.com/67b5c45612.js" crossorigin="anonymous"></script>
        <script src="teacher/js/teacherhome.js"></script>
        <link rel="stylesheet" href="teacher/css/checkattendence.css">
        <style>

        </style>
    </head>

    <body>
        <div class="wrapper">
            <div class="home">
                <div class="left-side-menu">
                    <div class="vertical-menu">
                        <div class="user-welcome">
                            <img class="user-img" src="teacher/img/userImg/dummy-user-img.png" style="width: 80px; height: 80px;" alt="">
                            <p>Harry Porter</p>
                        </div>
                        <div class="menu-item-container">
                            <ul class="item-lists">
                                <li class="menu-item ">
                                    <a href="attendance">Home</a>
                                </li>
                                <!--                                <li class="menu-item">
                                                                    <a href="">View schedule</a>
                                                                </li>-->
                                <li class="menu-item current1">
                                    <a href="#" style="color: #fff;">View student</a>
                                </li>
                            </ul>
                        </div>
                        <div class="log-out">
                            <a href="logout">Log out</a>
                        </div>
                    </div>
                </div>

                <div class="right-side">
                    <div class="page-content">
                        <div class="kid-profile">
                            <c:set var="kinder" value="${requestScope.kinder}"/>
                            <div class="kid-profile_header">
                                <div class="img-section">
                                    <img src="teacher/img/userImg/dummy-user-img.png" alt="">
                                </div>

                                <div class="personel-section">
                                    <h1>${kinder.first_name} ${kinder.last_name}</h1>
                                    <p>${kinder.dob}</p>
                                </div>
                            </div>
                            <div class="list-option" id="options">
                                <div class="attendence option-item current">
                                    <a href="#">Attendance</a>
                                </div>
                                <div class="feedback option-item">
                                    <a href="studentinfor?action=feedback&kid_id=${kinder.kinder_id}">Feedback</a>
                                </div>
                                <div class="profile option-item">
                                    <a href="kidprofile?kid_id=${kinder.kinder_id}">Profile</a>
                                </div>
                            </div>

                            <div class="attendence-section">
                                <div class="table-attendance">
                                    <c:forEach var="date" items="${requestScope.listDate}" varStatus="loop1">
                                        <c:set var="isTrue" value="false"/>
                                        <c:forEach var="check" items="${requestScope.attendance}" varStatus="loop">
                                            <c:if test="${check.check_date == date && check.status==2}">
                                                <div class="attendance-box present">
                                                    <p>${loop1.index +1}</p>
                                                    <c:set var="isTrue" value="true"/>
                                                </div>
                                            </c:if>
                                            <c:if test="${check.check_date == date && check.status==1}">
                                                <div class="attendance-box neutral">
                                                    <p>${loop1.index +1}</p>
                                                    <c:set var="isTrue" value="true"/>
                                                </div>
                                            </c:if>
                                            <c:if test="${isTrue == true}">
                                                <c:set var="check" value="${requestScope.maxindexobject}"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${isTrue==false}">
                                            <div class="attendance-box absent">
                                                <p>${loop1.index +1}</p>
                                                <c:set var="isTrue" value="false"/>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                    <div class="attendance-box notInclude">
                                        <p></p>
                                    </div>
                                    <div class="attendance-box notInclude">
                                        <p></p>
                                    </div>
                                    <div class="attendance-box notInclude">
                                        <p></p>
                                    </div>
                                    <div class="attendance-box notInclude">
                                        <p></p>
                                    </div>
                                </div>
                                <div class="guide">
                                    <i class="fa-solid fa-square" style="color: #EB5353;"><p>Absent or not take attendance yet</p></i>
                                    <i class="fa-solid fa-square" style="color: #f0ef21;"><p>Checked in</p></i>
                                    <i class="fa-solid fa-square" style="color: #36AE7C;"><p>Checked out</p></i>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>