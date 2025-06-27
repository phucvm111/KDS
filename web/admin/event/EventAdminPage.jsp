<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Admin - Event Management</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/event/boot/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/event/css/style.css"> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminSidebar.css">
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
    </head>
    <body>
        <form action="event" method="POST">
            <jsp:include page="/view/adminSidebar.jsp" /> 
            <div class="dashboard">
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
                                        <td><fmt:formatDate value="${event.eventDate}" pattern="yyyy-MM-dd" /></td>
                                        <td class="long-text">${event.location}</td>
                                        <td><a href="updateevent?id=${event.eventId}" class="action-button edit-button">Edit</a></td>
                                        <td><a href="deleteevent?id=${event.eventId}" class="action-button delete-button" onclick="return confirm('Are you sure you want to delete this event?');">Delete</a></td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty requestScope.eventList}">
                                    <tr>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>