<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>ATKD ChildCare - Lịch họp phụ huynh</title>
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">
        <style>
            .page-content {
                background-color: #ffffff;
                margin: 30px 30px 30px 80px;  /* thêm margin-left 80px để đẩy sang phải */
                padding: 30px;
                border-radius: 12px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                max-width: 1200px; /* tăng max-width */
            }

            h2 {
                text-align: center;
                border-bottom: 3px solid #007bff;
                padding-bottom: 10px;
                margin-bottom: 25px;
                font-size: 1.9em;
                font-weight: bold;
            }
            .filter-form {
                text-align: center;
                margin-bottom: 25px;
            }
            .filter-form label {
                margin-right: 10px;
                font-weight: 600;
            }
            .filter-form select {
                padding: 8px 15px;
                border-radius: 6px;
                border: 1px solid #ccc;
            }
            .meeting-table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            .meeting-table th {
                background-color: #8A2BE2;
                color: #fff;
                padding: 14px;
                text-align: center;
                font-size: 0.95em;
            }
            .meeting-table td {
                padding: 14px;
                text-align: center;
                border-bottom: 1px solid #e1e1e1;
            }
            .meeting-table tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            .meeting-table tr:hover {
                background-color: #f0f0f0;
            }
            .status-badge {
                padding: 6px 10px;
                border-radius: 6px;
                font-weight: 600;
                font-size: 0.9em;
                display: inline-block;
            }
            .status-scheduled {
                background-color: #28a745;
                color: #fff;
            }
            .status-completed {
                background-color: #6c757d;
                color: #fff;
            }
            .status-cancelled {
                background-color: #dc3545;
                color: #fff;
            }
            .no-meeting {
                text-align: center;
                background-color: #ffeeba;
                padding: 15px;
                border: 1px solid #ffcc00;
                margin-top: 20px;
                border-radius: 6px;
                font-style: italic;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/view/sidebarParent.jsp"/>

            <div class="right-side">
                <div class="page-content">
                    <h2>Lịch họp phụ huynh</h2>

                    <form method="get" action="viewmeetings" class="filter-form">
                        <label for="status">Lọc theo trạng thái:</label>
                        <select name="status" id="status" onchange="this.form.submit()">
                            <option value="">Tất cả</option>
                            <option value="Scheduled" ${param.status == 'Scheduled' ? 'selected' : ''}>Đã lên lịch</option>
                            <option value="Completed" ${param.status == 'Completed' ? 'selected' : ''}>Đã hoàn thành</option>
                            <option value="Cancelled" ${param.status == 'Cancelled' ? 'selected' : ''}>Đã hủy</option>
                        </select>
                    </form>

                    <c:if test="${empty meetings}">
                        <div class="no-meeting">Chưa có lịch họp nào.</div>
                    </c:if>

                    <c:if test="${not empty meetings}">
                        <table class="meeting-table">
                            <thead>
                                <tr>
                                    <th>Lớp</th>
                                    <th>Giáo viên</th>
                                    <th>Ngày & giờ</th>
                                    <th>Chủ đề</th>
                                    <th>Ghi chú</th>
                                    <th>Trạng thái</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="m" items="${meetings}">
                                    <tr>
                                        <td>${m.className}</td>
                                        <td>${m.teacherName}</td>
                                        <td><fmt:formatDate value="${m.meetingDate}" pattern="dd/MM/yyyy HH:mm"/></td>
                                        <td>${m.topic}</td>
                                        <td>${m.notes}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${m.status == 'Scheduled'}">
                                                    <span class="status-badge status-scheduled">Đã lên lịch</span>
                                                </c:when>
                                                <c:when test="${m.status == 'Completed'}">
                                                    <span class="status-badge status-completed">Đã hoàn thành</span>
                                                </c:when>
                                                <c:when test="${m.status == 'Cancelled'}">
                                                    <span class="status-badge status-cancelled">Đã hủy</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="status-badge">${m.status}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
