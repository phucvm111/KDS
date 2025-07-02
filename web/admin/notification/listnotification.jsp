<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Admin - Notification Management</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/event/boot/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/event/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminSidebar.css">
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
        <style>
            .action-buttons {
                display: flex;
                flex-wrap: wrap;
                gap: 5px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/view/adminSidebar.jsp" />

        <div class="dashboard">
            <div class="dashboard-content">
                <h2>Notification List</h2>

                <c:if test="${not empty sessionScope.successMessage}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert" style="width:100%; margin-top:10px;">
                        ${sessionScope.successMessage}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                    <c:remove var="successMessage" scope="session"/>
                </c:if>
                <c:if test="${not empty requestScope.message}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert" style="width:100%; margin-top:10px;">
                        ${requestScope.message}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <div class="header-section">
                    <div class="search-add-section">
                        <form action="notification" method="get" style="display:inline-flex;">
                            <input type="text" name="searchContent" placeholder="Search by content" class="search-input"
                                   value="${param.searchContent != null ? param.searchContent : ''}">
                            <button type="submit" class="search-button">Search</button>
                        </form>
                        <a href="notification?action=add" class="add-new-event-button">Add New Notification</a>
                    </div>
                </div>

                <div class="table-container">
                    <table class="event-table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Title</th>
                                <th>Message</th>
                                <th>Created At</th>
                                <th>Sender</th>
                                <th>Target Role</th>
                                <th style="text-align:center">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${notificationList}" var="n" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>${n.title}</td>
                                    <td class="long-text">${n.message}</td>
                                    <td><fmt:formatDate value="${n.createdAt}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td>${n.sender.firstName} ${n.sender.lastName}</td>
                                    <td>${n.targetRole.roleName}</td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="notification?action=edit&id=${n.notificationId}"
                                               class="btn btn-success btn-sm ${n.emailed ? 'disabled' : ''}">
                                                Edit
                                            </a>
                                            <a href="notification?action=delete&id=${n.notificationId}"
                                               class="btn btn-danger btn-sm"
                                               onclick="return confirm('Are you sure you want to delete this notification?');">
                                                Delete
                                            </a>
                                            <c:if test="${!n.emailed}">
                                                <a href="notification?action=sendMail&id=${n.notificationId}"
                                                   class="btn btn-primary btn-sm"
                                                   onclick="return confirm('Send this notification to all users in the target role?');">
                                                    Send Email
                                                </a>
                                            </c:if>
                                            <c:if test="${n.emailed}">
                                                <span class="badge bg-success">Sent</span>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty notificationList}">
                                <tr>
                                    <td colspan="7" style="text-align:center;">No notifications found.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>

                <!-- Optional: pagination if needed -->
                <div class="pagination-container">
                    <c:set var="currentPage" value="${requestScope.currentPage != null ? requestScope.currentPage : 1}" />
                    <c:set var="totalPages" value="${requestScope.totalPages != null ? requestScope.totalPages : 1}" />

                    <c:if test="${currentPage > 1}">
                        <div class="pagination-item">
                            <a href="notification?page=${currentPage - 1}&searchContent=${param.searchContent != null ? param.searchContent : ''}">Previous</a>
                        </div>
                    </c:if>

                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <div class="pagination-item ${currentPage == i ? 'active' : ''}">
                            <a href="notification?page=${i}&searchContent=${param.searchContent != null ? param.searchContent : ''}">${i}</a>
                        </div>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <div class="pagination-item">
                            <a href="notification?page=${currentPage + 1}&searchContent=${param.searchContent != null ? param.searchContent : ''}">Next</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
