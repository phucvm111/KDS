<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            background-color: white;
            margin: 30px auto;  /* căn giữa */
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
            max-width: 1000px;  /* chiều rộng tối đa */
        }
        h2 {
            text-align: center;
            border-bottom: 2px solid #007bff;
            margin-bottom: 20px;
            font-size: 1.8em;
        }
        .filter-form {
            text-align: center;
            margin-bottom: 20px;
        }
        .filter-form label {
            margin-right: 10px;
            font-weight: bold;
        }
        .filter-form select {
            padding: 8px 12px;
            font-size: 1em;
            border-radius: 5px;
        }
        .meeting-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .meeting-table th {
            background-color: #8A2BE2;
            color: white;
            text-transform: uppercase;
            padding: 12px;
        }
        .meeting-table td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }
        .meeting-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .meeting-table tr:hover {
            background-color: #f1f1f1;
        }
        .status-badge {
            padding: 4px 8px;
            border-radius: 5px;
            color: white;
        }
        .status-scheduled {
            background-color: #28a745;
        }
        .status-completed {
            background-color: #6c757d;
        }
        .status-cancelled {
            background-color: #dc3545;
        }
        .no-meeting {
            text-align: center;
            background-color: #ffeeba;
            padding: 15px;
            border: 1px solid #ffcc00;
            margin-top: 20px;
            border-radius: 5px;
            font-style: italic;
        }
    </style>
</head>

<body>
    <div class="wrapper">
        <%-- include the unified sidebar --%>
        <jsp:include page="/view/sidebarParent.jsp" />

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
                                            <c:otherwise>${m.status}</c:otherwise>
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
