

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Event</title>
    <link rel="stylesheet" href="admin/class/css/style.css">
    <link rel="stylesheet" href="admin/class/boot/bootstrap.min.css">
    <link rel="stylesheet" href="admin/class/boot/bootstrap.css">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
</head>
<body>
    <form action="addevent" method="POST">
        <nav>
            <div class="logo-name">
                <div class="logo-image">
                    <img src="images/logo.jpg" alt="Logo">
                </div>
                <span class="logo_name"><a href="listaccount" style="text-decoration: none; color: black">Admin Page</a></span>
            </div>

            <div class="menu-items">
                <ul class="nav-links">
                    <li><a href="listaccount"><i class="uil uil-estate"></i><span class="link-name">Account</span></a></li>
                    <li><a href="listkinder"><i class="uil uil-chart"></i><span class="link-name">Kindergartner</span></a></li>
                    <li><a href="listclass"><i class="uil uil-thumbs-up"></i><span class="link-name">Class</span></a></li>
                    <li><a href="listschedule"><i class="uil uil-comments"></i><span class="link-name">Schedule</span></a></li>
                    <%-- THAY ĐỔI NÀY: Đường dẫn Event trong menu --%>
                    <li><a href="event"><i class="uil uil-calendar-alt"></i><span class="link-name">Event</span></a></li>
                </ul>
                <ul class="logout-mode">
                    <li><a href="logout"><i class="uil uil-signout"></i><span class="link-name">Logout</span></a></li>
                </ul>
            </div>
        </nav>

        <div class="dashboard">
            <div class="dash-lefttop">
                <img src="https://i.pinimg.com/originals/72/45/fb/7245fb0ca786bb4a98fb8465e437c5bb.jpg" alt="User">
                <a href="#">${sessionScope.account.firstName}</a>
            </div>

            <div class="form-title" style="text-align: center; font-size: 40px;">Add Event</div>
            <div class="form-content" style="width: 80%; margin-left: 10%; padding-top: 0;">
                <c:if test="${not empty requestScope.errorMessage}">
                    <div class="alert alert-danger" role="alert">
                        ${requestScope.errorMessage}
                    </div>
                </c:if>

                <div class="mb-3">
                    <label for="event_name" class="form-label">Event Name</label>
                    <input type="text" class="form-control" id="event_name" name="event_name" required value="${requestScope.oldEventName != null ? requestScope.oldEventName : ''}">
                </div>

                <div class="mb-3">
                    <label for="event_description" class="form-label">Event Description</label>
                    <textarea class="form-control" id="event_description" name="event_description" rows="3" required>${requestScope.oldEventDescription != null ? requestScope.oldEventDescription : ''}</textarea>
                </div>

                <div class="mb-3">
                    <label for="event_date" class="form-label">Event Date</label>
                    <input type="date" class="form-control" id="event_date" name="event_date" required value="${requestScope.oldEventDate != null ? requestScope.oldEventDate : ''}">
                </div>

                <div class="mb-3">
                    <label for="location" class="form-label">Event Location</label>
                    <input type="text" class="form-control" id="location" name="location" required value="${requestScope.oldLocation != null ? requestScope.oldLocation : ''}">
                </div>

                <div class="d-grid gap-2 d-md-block" style="margin-top: 30px; margin-bottom: 30px;">
                    <input type="submit" class="btn btn-primary" value="Add Event">
                  
                    <a href="event" class="btn btn-secondary">Cancel</a> 
                </div>

            </div>
        </div>
    </form>
</body>
</html>