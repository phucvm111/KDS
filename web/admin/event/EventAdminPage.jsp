

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Event Management</title>
    <link rel="stylesheet" href="admin/class/css/style.css">
    <link rel="stylesheet" href="admin/class/boot/bootstrap.min.css">
    <link rel="stylesheet" href="admin/class/boot/bootstrap.css">
    <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
</head>
<body>
    <form action="event" method="POST">
        <nav>
            <div class="logo-name">
                <div class="logo-image">
                    <img src="images/logo.jpg" alt="">
                </div>
                <span class="logo_name"><a href="listaccount" style="text-decoration: none;color: black">Admin Page</a></span>
            </div>
            <div class="menu-items">
                <ul class="nav-links">
                    <li><a href="listaccount"><i class="uil uil-estate"></i><span class="link-name">Account</span></a></li>
                    <li><a href="listkinder"><i class="uil uil-chart"></i><span class="link-name">Kindergartner</span></a></li>
                    <li><a href="listclass"><i class="uil uil-thumbs-up"></i><span class="link-name">Class</span></a></li>
                    <li><a href="listschedule"><i class="uil uil-comments"></i><span class="link-name">Schedule</span></a></li>
                    <li><a href="event"><i class="uil uil-calendar-alt"></i><span class="link-name">Event</span></a></li>
                </ul>
                <ul class="logout-mode">
                    <li><a href="logout"><i class="uil uil-signout"></i><span class="link-name">Logout</span></a></li>
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
                    <button type="button" style="width: 40%; margin-left: 70px; margin-top: 20px;">
                        <a href="addevent" style="text-decoration: none">Add Event</a>
                    </button>
                </div>
            </div>
            
            
        <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert" style="width: 80%; margin: 10px auto;">
                ${sessionScope.successMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <c:remove var="successMessage" scope="session"/>
        </c:if>

   
        <c:if test="${not empty requestScope.message}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 80%; margin: 10px auto;">
                ${requestScope.message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            
        </c:if>


            <div class="dash-bottomtable">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Event ID</th>
                            <th scope="col">Title</th>
                            <th scope="col">Description</th>
                            <th scope="col">Date</th> <%-- Đổi từ Start Time --%>
                            <th scope="col">Location</th> <%-- Thêm cột Location --%>
                            <th scope="col">Update</th>
                            <th scope="col">Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.eventList}" var="event">
                            <tr>
                                <td>${event.eventId}</td>
                                <td>${event.eventName}</td>
                                <td>${event.eventDescription}</td>
                                <td><fmt:formatDate value="${event.eventDate}" pattern="yyyy-MM-dd" /></td> <%-- Đổi từ startDateTime và chỉ hiển thị ngày --%>
                                <td>${event.location}</td> <%-- Hiển thị location --%>
                                <td><a href="updateevent?id=${event.eventId}">Update</a></td> <%-- Sửa id thành eventId --%>
                                <td><a href="deleteevent?id=${event.eventId}" onclick="return confirm('Are you sure you want to delete this event?');">Delete</a></td> <%-- Sửa id thành eventId và thêm xác nhận --%>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </form>
</body>
</html>