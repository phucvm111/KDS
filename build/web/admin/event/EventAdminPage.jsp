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
    <style>
        .dashboard-content {
            padding: 20px;
            background-color: var(--panel-color); /* Matches sidebar background */
            min-height: calc(100vh - 70px); /* Adjust height based on top bar */
            box-sizing: border-box;
        }

        .header-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .search-add-section {
            display: flex;
            gap: 10px; /* Space between search input and button */
        }

        .search-input {
            padding: 8px 12px;
            border: 1px solid var(--border-color);
            border-radius: 5px;
            font-size: 16px;
            width: 300px; /* Adjust as needed */
        }

        .search-button {
            background-color: orange;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        
        .search-button:hover {
            opacity: 0.9;
        }

        .add-new-event-button {
            background-color: #5cb85c; /* Green color */
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none; /* For the <a> tag */
            display: inline-block; /* For the <a> tag */
            line-height: normal; /* Ensure text aligns in button */
        }
        
        .add-new-event-button:hover {
            background-color: #4cae4c;
        }

        .table-container {
            margin-top: 20px;
            border: 1px solid var(--border-color); /* Add border around the table container */
            border-radius: 5px;
            overflow: hidden; /* Ensures rounded corners are applied to content */
        }

        .event-table {
            width: 100%;
            border-collapse: collapse;
            font-size: 15px;
        }

        .event-table th, .event-table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid var(--border-color);
        }

        .event-table thead th {
            background-color: #e9ecef; /* Light gray for header */
            color: var(--text-color);
            font-weight: 600;
        }

        .event-table tbody tr:nth-child(even) {
            background-color: #f8f9fa; /* Light stripe for even rows */
        }

        .event-table tbody tr:hover {
            background-color: #e2e6ea; /* Hover effect */
        }

        .action-button {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            color: white;
            text-decoration: none;
            font-size: 14px;
            display: inline-block; /* For alignment */
            margin-right: 5px; /* Space between buttons */
        }

        .edit-button {
            background-color: #28a745; /* Green for Edit */
        }

        .delete-button {
            background-color: #dc3545; /* Red for Delete */
        }
        
        .action-button:hover {
            opacity: 0.9;
        }

        .pagination-container {
            display: flex;
            justify-content: center;
            padding: 20px 0;
            margin-top: 20px;
        }

        .pagination-item {
            display: inline-block;
            margin: 0 5px;
        }

        .pagination-item a {
            text-decoration: none;
            color: var(--primary-color);
            padding: 8px 12px;
            border: 1px solid var(--border-color);
            border-radius: 4px;
        }

        .pagination-item.active a {
            background-color: var(--primary-color);
            color: white;
            border-color: var(--primary-color);
        }

        .pagination-item a:hover:not(.active) {
            background-color: #e9ecef;
        }
        
        /* Override specific dash-bottomtable styles if they conflict */
        .dashboard .dash-bottomtable {
            height: auto; /* Allow content to dictate height */
            overflow: visible; /* Prevent unwanted scrollbar on this div */
            display: block; /* Change to block to control margin/padding better */
            padding: 0 20px; /* Add horizontal padding to align with other content */
        }

        /* Adjustments for the main content area to align with the image */
        .dashboard {
            background-color: #f4f6f9; /* Lighter background for the main content area */
            padding: 0; /* Remove default padding if any */
        }
        
        .dash-lefttop {
            background-color: white; /* Changed to white as per image */
            justify-content: flex-end; /* Align to the right */
            align-items: center; /* Center vertically */
            padding-right: 30px; /* Add some padding on the right */
            box-shadow: 0 2px 4px rgba(0,0,0,.08); /* Subtle shadow for separation */
        }
        
        .dash-lefttop a {
            color: black;
            font-weight: 500;
        }
        
        .dash-lefttop img {
            margin-right: 10px; /* Space between image and name */
        }
        
        .sl-id {
            display: none; /* Hide this section as it's replaced by the new layout */
        }
    </style>
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
                    <li><a href="changepassword"><i class="uil uil-lock-alt"></i><span class="link-name">Change Password</span></a></li>
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
                <h2>Event List</h2>

                <c:if test="${not empty sessionScope.successMessage}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert" style="width: 100%; margin-top: 10px; margin-bottom: 20px;">
                        ${sessionScope.successMessage}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <c:remove var="successMessage" scope="session"/>
                </c:if>

                <c:if test="${not empty requestScope.message}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width: 100%; margin-top: 10px; margin-bottom: 20px;">
                        ${requestScope.message}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <div class="header-section">
                    <div class="search-add-section">
                        <input type="text" name="searchName" placeholder="Enter Event Name" class="search-input" value="${param.searchName != null ? param.searchName : ''}">
                        <button type="submit" class="search-button">Search</button>
                    </div>
                    <a href="addevent" class="add-new-event-button">Add New Event</a>
                </div>

                <div class="table-container">
                    <table class="event-table">
                        <thead>
                            <tr>
                                <th>Event ID</th>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Date</th>
                                <th>Location</th>
                                <th>Action</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.eventList}" var="event">
                                <tr>
                                    <td>${event.eventId}</td>
                                    <td>${event.eventName}</td>
                                    <td>${event.eventDescription}</td>
                                    <td><fmt:formatDate value="${event.eventDate}" pattern="yyyy-MM-dd" /></td>
                                    <td>${event.location}</td>
                                    <td><a href="updateevent?id=${event.eventId}" class="action-button edit-button">Edit</a></td>
                                    <td><a href="deleteevent?id=${event.eventId}" class="action-button delete-button" onclick="return confirm('Are you sure you want to delete this event?');">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty requestScope.eventList}">
                                <tr>
                                    <td colspan="7" style="text-align: center;">No events found.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>

                <div class="pagination-container">
                    <c:set var="currentPage" value="${requestScope.currentPage != null ? requestScope.currentPage : 1}" />
                    <c:set var="totalPages" value="${requestScope.totalPages != null ? requestScope.totalPages : 1}" />

                    <c:if test="${currentPage > 1}">
                        <div class="pagination-item">
                            <a href="event?page=${currentPage - 1}&searchName=${param.searchName != null ? param.searchName : ''}">Previous</a>
                        </div>
                    </c:if>

                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <div class="pagination-item ${currentPage == i ? 'active' : ''}">
                            <a href="event?page=${i}&searchName=${param.searchName != null ? param.searchName : ''}">${i}</a>
                        </div>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <div class="pagination-item">
                            <a href="event?page=${currentPage + 1}&searchName=${param.searchName != null ? param.searchName : ''}">Next</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </form>
</body>
</html>