<%-- 
    Document   : teacherprofile
    Created on : Jun 13, 2025, 12:19:08 PM
    Author     : Vu Tuan Hai <HE176383>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Teacher Profile</title>
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/parenthome.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/childprofile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/childregister.css">
    </head>
    <body>
        <div class="wrapper">
            <div class="home">
                <div class="left-side-menu">
                    <div class="vertical-menu">
                        <div class="user-welcome">
                            <img class="user-img" src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png"
                                 style="width: 80px; height: 80px;" alt="User">
                            <p>${account.fullname}</p>
                        </div>
                        <div class="menu-item-container">
                            <ul class="item-lists">
                                <li class="menu-item current1">
                                    <a href="#">Teacher Information</a>
                                </li>
                                <li class="menu-item">
                                    <a href="${pageContext.request.contextPath}/logout">Logout</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="right-side">
                    <div class="page-content">
                        <div class="kid-profile">
                            <div class="kid-profile_header">
                                <div class="img-section">
                                    <img src="${account.avatar}" alt="Avatar" width="100" />
                                </div>
                                <div class="personel-section">
                                    <h1>${account.fullname}</h1>
                                    <p>${account.email}</p>
                                </div>
                            </div>

                            <div class="kid-profile_content">
                                <div class="content-item">
                                    <div class="item-title"><strong>Full Name</strong></div>
                                    <p class="parent-infor">${account.fullname}</p>
                                </div>
                                <div class="content-item">
                                    <div class="item-title"><strong>Username</strong></div>
                                    <p class="parent-infor">${account.username}</p>
                                </div>
                                <div class="content-item">
                                    <div class="item-title"><strong>Gender</strong></div>
                                    <p class="parent-infor">
                                        <c:choose>
                                            <c:when test="${account.gender == true}">Male</c:when>
                                            <c:otherwise>Female</c:otherwise>
                                        </c:choose>
                                    </p>
                                </div>
                                <div class="content-item">
                                    <div class="item-title"><strong>Phone</strong></div>
                                    <p class="parent-infor">${account.phoneNumber}</p>
                                </div>
                                <div class="content-item">
                                    <div class="item-title"><strong>Address</strong></div>
                                    <p class="parent-infor">${account.address}</p>
                                </div>
                                <div class="content-item">
                                    <div class="item-title"><strong>Bio</strong></div>
                                    <p class="parent-infor">${account.bio}</p>
                                </div>
                                <div class="content-item">
                                    <div class="item-title"><strong>Role</strong></div>
                                    <p class="parent-infor">${account.role.roleName}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
