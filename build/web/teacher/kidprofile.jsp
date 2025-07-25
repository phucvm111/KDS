<%-- 
    Document   : kidprofile
    Created on : Jun 1, 2022, 8:02:07 AM
    Author     : Windows 10 TIMT
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </head>

    <body>
        <div class="wrapper">
            <div class="home">
                <div class="left-side-menu">
                    <div class="vertical-menu">
                        <div class="user-welcome">
                            <img class="user-img" src="teacher/img/userImg/dummy-user-img.png" style="width: 80px; height: 80px;" alt="">
                            <p>Peter John</p>
                        </div>
                        <div class="menu-item-container">
                            <ul class="item-lists">
                                <li class="menu-item">
                                    <a href="attendance">Home</a>
                                </li>
                               
                                <li class="menu-item current1">
                                    <a href="#" style="color: #fff;">View student</a>
                                </li>
                            </ul>
                        </div>
                        <div class="log-out">
                            <a href="attendance">Log out</a>
                        </div>
                    </div>
                </div>

                <div class="right-side">
                    <div class="page-content">
                        <div class="kid-profile">
                            <div class="kid-profile_header">
                                <c:set var="kid" value="${requestScope.kid_info}" />
                                <div class="img-section">
                                    <img src="teacher/img/userImg/download.png" alt="">
                                </div>

                                <div class="personel-section">
                                    <h1>${kid.first_name} ${kid.last_name}</h1>

                                </div>
                            </div>

                            <div class="list-option" id="options">
                                <div class="attendence option-item">
                                    <a href="studentinfor?action=attendance&kid_id=${kid.kinder_id}">Attendance</a>
                                </div>
                                <div class="feedback option-item">
                                    <a href="studentinfor?action=feedback&kid_id=${kid.kinder_id}">Feedback</a>
                                </div>
                                <div class="profile option-item current1">
                                    <a href="#" style="color: #fff;">Profile</a>
                                </div>
                            </div>

                            <div class="kid-profile_content">
                                <div class="class content-item">
                                    <div class="item-title">
                                        <strong>Class</strong>
                                    </div>
                                    <p>${sessionScope.kinder_class.class_name}</p>
                                </div>
                                <div class="content-item phone">
                                    <div class="item-title">
                                        <strong>Address</strong>
                                    </div>
                                    <c:set var="kid" value="${requestScope.kid_info}"/>
                                    <p>${kid.parentPhone}</p>
                                </div>
                                <div class="content-item address">
                                    <div class="item-title">
                                        <strong>Parent number</strong>
                                    </div>
                                    <p>${kid.address}</p>
                                </div>
                                <!--dathem-->
                                <div class="content-item gender">
                                    <div class="item-title">
                                        <strong>Sex</strong>
                                    </div>
                                    <p>
                                        <c:choose>
                                            <c:when test="${kid.gender == true}">Male</c:when>
                                            <c:when test="${kid.gender == false}">Female</c:when>
                                            <c:otherwise>Other</c:otherwise>
                                        </c:choose>
                                    </p>
                                </div>
                                <div class="content-item dob">
                                    <div class="item-title">
                                        <strong>DOB</strong>
                                    </div>
                                    <p>${kid.dob}</p>
                                </div>
                                <div class="content-item description">
                                    <div class="item-title">
                                        <strong> Description </strong>
                                    </div>
                                    <p>Shrimp allergy</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>