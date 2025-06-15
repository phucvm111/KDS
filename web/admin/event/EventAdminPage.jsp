<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Admin - Event Management</title>

        <link rel="stylesheet" href="admin/event/boot/bootstrap.min.css">
        <link rel="stylesheet" href="admin/event/boot/bootstrap.css">

        <link rel="stylesheet" href="admin/event/css/style.css"> 

        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
    </head>
    <body>
        <form action="event" method="POST">
            <nav>
                <div class="logo-name">
                    <div class="logo-image">
                        <img src="${pageContext.request.contextPath}/assets/image/logo.png" alt="Logo">
                    </div>
                    <span class="logo_name">
                        <a href="listaccount">Admin Page</a>
                    </span>
                </div>

                <div class="menu-items">
                    <ul class="nav-links">
                        <li><a href="dashboard"><i class="uil uil-dashboard"></i>Dashboard</a></li>
                        <li><a href="listaccount"><i class="uil uil-estate"></i>Account</a></li>
                        <li><a href="listkinder"><i class="uil uil-chart"></i>Kindergartner</a></li>
                        <li><a href="listclass"><i class="uil uil-thumbs-up"></i>Class</a></li>
                        <li><a href="listschedule"><i class="uil uil-comments"></i>Schedule</a></li>
                        <li><a href="changepassword"><i class="uil uil-lock-alt"></i><span class="link-name">Change Password</span></a></li>
                        <li><a href="event"><i class="uil uil-calendar-alt"></i><span class="link-name">Event</span></a></li>
                    </ul>
                    <ul class="logout-mode">
                        <li><a href="logout"><i class="uil uil-signout"></i>Logout</a></li>
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
                                    <th>STT</th> 
                                    <th>Title</th>
                                        <%-- XÓA CỘT DESCRIPTION --%>
                                        <%-- <th>Description</th> --%> 
                                    <th>Date</th>
                                    <th>Location</th>
                                    <th colspan="2" style="text-align: center;">Action</th>
                                </tr>
                            </thead>
                            <tbody id="eventTableBody">
                                <c:forEach items="${requestScope.eventList}" var="event" varStatus="loop">
                                    <tr data-event-id="${event.eventId}">
                                        <td>${(requestScope.offset + loop.index) + 1}</td> 
                                        <td class="long-text event-title-cell">${event.eventName}</td>
                                        <%-- XÓA DỮ LIỆU DESCRIPTION --%>
                                        <%-- <td class="long-text description-cell">${event.eventDescription}</td> --%>
                                        <td><fmt:formatDate value="${event.eventDate}" pattern="yyyy-MM-dd" /></td>
                                        <td class="long-text">${event.location}</td>
                                        <td><a href="updateevent?id=${event.eventId}" class="action-button edit-button">Edit</a></td>
                                        <td><a href="deleteevent?id=${event.eventId}" class="action-button delete-button" onclick="return confirm('Are you sure you want to delete this event?');">Delete</a></td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty requestScope.eventList}">
                                    <tr>
                                        <%-- Điều chỉnh colspan từ 7 thành 6 vì đã xóa 1 cột --%>
                                        <td colspan="6" style="text-align: center;">No events found.</td> 
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

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const eventTableBody = document.getElementById('eventTableBody');

                if (eventTableBody) {
                    eventTableBody.addEventListener('dblclick', function (event) {
                        const clickedRow = event.target.closest('tr');

                        if (clickedRow) {
                            const eventId = clickedRow.dataset.eventId;
                            const isTitleCell = event.target.classList.contains('event-title-cell') || event.target.closest('.event-title-cell');

                            if (eventId && isTitleCell) {
                                window.location.href = 'vieweventdetails?id=' + eventId;
                            }
                        }
                    });
                }
            });
        </script>
    </body>
</html>