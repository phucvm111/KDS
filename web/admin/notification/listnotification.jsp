<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Quản lý thông báo</title>
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
                <h2>📢 Danh sách thông báo</h2>

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
                        <form action="notification" method="get" style="display:inline-flex; gap:5px;">
                            <input type="text" name="searchContent" placeholder="Tìm theo nội dung" class="search-input"
                                   value="${param.searchContent != null ? param.searchContent : ''}">
                            <select name="emailStatus" class="form-select">
                                <option value="">-- Tất cả trạng thái --</option>
                                <option value="sent" ${param.emailStatus == 'sent' ? 'selected' : ''}>Đã gửi</option>
                                <option value="notsent" ${param.emailStatus == 'notsent' ? 'selected' : ''}>Chưa gửi</option>
                            </select>
                            <button type="submit" class="search-button">🔍 Tìm kiếm</button>
                        </form>
                        <a href="notification?action=add" class="add-new-event-button">➕ Thêm mới</a>
                    </div>
                </div>

                <div class="table-container">
                    <table class="event-table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Tiêu đề</th>
                                <th>Nội dung</th>
                                <th>Ngày tạo</th>
                                <th>Người gửi</th>
                                <th>Vai trò nhận</th>
                                <th style="text-align:center">Thao tác</th>
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
                                                Sửa
                                            </a>
                                            <a href="notification?action=delete&id=${n.notificationId}"
                                               class="btn btn-danger btn-sm"
                                               onclick="return confirm('Bạn có chắc chắn muốn xóa thông báo này?');">
                                                Xóa
                                            </a>
                                            <c:if test="${!n.emailed}">
                                                <a href="notification?action=sendMail&id=${n.notificationId}"
                                                   class="btn btn-primary btn-sm"
                                                   onclick="return confirm('Gửi thông báo này đến tất cả người dùng theo vai trò?');">
                                                    Gửi Email
                                                </a>
                                            </c:if>
                                            <c:if test="${n.emailed}">
                                                <span class="badge bg-success">Đã gửi</span>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty notificationList}">
                                <tr>
                                    <td colspan="7" style="text-align:center;">Không có thông báo nào.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>

                <!-- phân trang -->
                <div class="pagination-container">
                    <c:set var="currentPage" value="${requestScope.currentPage != null ? requestScope.currentPage : 1}" />
                    <c:set var="totalPages" value="${requestScope.totalPages != null ? requestScope.totalPages : 1}" />

                    <c:if test="${currentPage > 1}">
                        <div class="pagination-item">
                            <a href="notification?page=${currentPage - 1}&searchContent=${param.searchContent != null ? param.searchContent : ''}&emailStatus=${param.emailStatus}">
                                Trang trước
                            </a>
                        </div>
                    </c:if>

                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <div class="pagination-item ${currentPage == i ? 'active' : ''}">
                            <a href="notification?page=${i}&searchContent=${param.searchContent != null ? param.searchContent : ''}&emailStatus=${param.emailStatus}">
                                ${i}
                            </a>
                        </div>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <div class="pagination-item">
                            <a href="notification?page=${currentPage + 1}&searchContent=${param.searchContent != null ? param.searchContent : ''}&emailStatus=${param.emailStatus}">
                                Trang sau
                            </a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
