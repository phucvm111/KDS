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
            /* Custom CSS for Event List page to match the image */
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

            /* Removed .pagination-container and related styles */
            /* because pagination is no longer used */

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
        <form action="event" method="GET"> 
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
                        <li><a href="adminNotifyTeachers">
                                <i class="uil uil-bell"></i>
                                <span class="link-name">Send Notification</span>
                            </a></li>
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

                            <input type="text" name="searchName" placeholder="Enter Event Name" class="search-input" value="${requestScope.searchName}"> <%-- Corrected value to requestScope.searchName --%>
                            <button type="submit" class="search-button">Search</button>
                        </div>
                        <a href="addevent" class="add-new-event-button">Add New Event</a>
                    </div>

                    <div class="table-container">
                        <table class="event-table">
                            <thead>
                                <tr>
                                    <th>STT</th> 
                                    <th>Title</th>
                                    <th>Description</th>
                                    <th>Date</th>
                                    <th>Location</th>
                                    <th colspan="2">Action</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.eventList}" var="event" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td> 
                                        <td>${event.eventName}</td>
                                        <td>${event.eventDescription}</td>
                                        <td><fmt:formatDate value="${event.eventDate}" pattern="yyyy-MM-dd" /></td>
                                        <td>${event.location}</td>
                                        <td>
                                            <a href="updateevent?id=${event.eventId}" class="action-button edit-button">Edit</a>
                                            <a href="deleteevent?id=${event.eventId}" class="action-button delete-button" onclick="return confirm('Are you sure you want to delete this event?');">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty requestScope.eventList}">
                                    <tr>
                                        <td colspan="6" style="text-align: center;">No events found.</td> <%-- Updated colspan --%>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>


                </div>
            </div>
        </form>
    </body>
</html>