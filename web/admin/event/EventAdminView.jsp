<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Event Details</title>
    <link rel="stylesheet" href="admin/event/boot/bootstrap.min.css">
    <link rel="stylesheet" href="admin/event/boot/bootstrap.css">
    <link rel="stylesheet" href="admin/event/css/style.css"> <%-- Đảm bảo link đến CSS chung --%>
    <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <style>
        /* CSS specific to EventAdminView.jsp if needed, can be in style.css too */
        .event-details-card {
            background-color: var(--panel-color); /* Matches sidebar background */
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            max-width: 800px;
            margin: 30px auto; /* Center the card */
        }
        .event-details-card h2 {
            color: var(--primary-color);
            margin-bottom: 25px;
            text-align: center;
        }
        .event-details-card p {
            margin-bottom: 15px;
            font-size: 16px;
            line-height: 1.6;
        }
        .event-details-card p strong {
            display: inline-block;
            width: 120px; /* Adjust for alignment */
            color: var(--black-light-color);
        }
        .back-button-container {
            text-align: center;
            margin-top: 30px;
        }
        .back-button {
            background-color: orange;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            display: inline-block;
        }
        .back-button:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
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
        
        <div class="dashboard-content">
            <c:if test="${not empty requestScope.event}">
                <div class="event-details-card">
                    <h2>Event Details</h2>
                    <p><strong>Event ID:</strong> ${requestScope.event.eventId}</p>
                    <p><strong>Title:</strong> ${requestScope.event.eventName}</p>
                    <p><strong>Description:</strong> ${requestScope.event.eventDescription}</p>
                    <p><strong>Date:</strong> <fmt:formatDate value="${requestScope.event.eventDate}" pattern="yyyy-MM-dd" /></p>
                    <p><strong>Location:</strong> ${requestScope.event.location}</p>
                    
                    <div class="back-button-container">
                        <a href="event" class="back-button">Back to List</a>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty requestScope.event}">
                 <div class="alert alert-warning" role="alert">
                    No event details found.
                    <div class="back-button-container">
                        <a href="event" class="back-button">Back to List</a>
                    </div>
                </div>
            </c:if>
             <c:if test="${not empty requestScope.message}">
                <div class="alert alert-danger" role="alert" style="margin-top: 20px;">
                    ${requestScope.message}
                    <div class="back-button-container">
                        <a href="event" class="back-button">Back to List</a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>