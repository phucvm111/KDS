<%-- 
    Document   : kinderAdminPage
    Created on : Jun 24, 2022, 5:08:41 PM
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
        <link rel="stylesheet" href="admin/kinder/css/style.css">
        <link rel="stylesheet" href="admin/kinder/boot/bootstrap.min.css">
        <link rel="stylesheet" href="admin/kinder/boot/bootstrap.css">

        <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare</title>
        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <!--<title>Admin Dashboard Panel</title>-->
    </head>

    <body>
        <form action="listkinder">

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
                    <a href="#">${sessionScope.account.firstName}</a>
                </div>
                <!--                <div class="dash-bottom">
                
                                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                                    <button class="btn btn-outline-success" type="submit">Search</button>
                
                
                                </div>-->
                <div class="sl-id" style="display: flex;">

                    <!--                    <div>
                                            <button  type="submit" style="width: 40%;margin-left: 70px;margin-top: 20px;"><a href="addkinder">Add</a> </button> 
                                        </div>-->

                </div>
                <div class="dash-bottomtable">
                    <table class="table" >
                        <thead>
                            <tr>
                                <th scope="col">KinderID</th>
                                <th scope="col">ParentName</th>
                                <th scope="col">FirstName</th>
                                <th scope="col">LastName</th>
                                <th scope="col">DOB</th>
                                <th scope="col">Gender</th>
                                <th scope="col">Img</th>
                                <th scope="col">Update</th>
                                <th scope="col">Delete</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.list}" var="lts">
                                <tr>
                                    <th scope="row">${lts.kinder_id}</th>
                                        <%--<c:forEach items="${requestScope.listName}" var="ltnameac">--%>
                                        <%--<c:if test="${lts.parentAccount.lastName == ltnameac.accountID}">--%>
                                    <!--<td>${ltnameac.lastName}</td>-->
                                    <%--</c:if>--%>
                                    <td>${lts.parentAccount.firstName} ${lts.parentAccount.lastName}</td>
                                    <%--</c:forEach>--%>
                                    <td>${lts.first_name}</td>
                                    <td>${lts.last_name}</td>
                                    <td>${lts.dob}</td>
                                    <c:choose>
                                        <c:when test="${lts.gender}">
                                            <td>Male</td>
                                        </c:when>
                                        <c:when test="${!lts.gender}">
                                            <td>FeMale</td>
                                        </c:when>
                                        <c:otherwise>
                                        </c:otherwise>
                                    </c:choose>


                                    <td>
                                        <c:if test="${lts.img == null || lts.img.isEmpty() }" >
                                            <img src="admin/kinder/images/avtClone.jpg" style="height: 96px; width: 96px"/>
                                        </c:if>    
                                        <c:if test="${lts.img != null}" >
                                            <img src="${lts.img}" style="height: 96px; width: 96px"/>

                                        </c:if>
                                    </td>
                                    <td><a href="updatekinder?sid=${lts.kinder_id}">Update</a> </td>
                                    <td><a href="deletekinder?sid=${lts.kinder_id}">Delete</a> </td>
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
