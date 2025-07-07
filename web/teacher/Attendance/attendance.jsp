<%-- view/teacher/Attendance/attendance.jsp --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Điểm Danh Lớp Học</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css">
        <style>
            .main-content {
                margin-left: 250px; /* Same width as the sidebar */
                padding: 20px;
                flex-grow: 1;
            }
            .container {
                max-width: 900px;
                margin: 30px 30px 30px 0;
                background-color: #fff;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }


            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 30px;
            }
            .info-bar {
                display: flex;
                justify-content: space-between;
                margin-bottom: 20px;
                padding: 10px;
                background-color: #e9ecef;
                border-radius: 5px;
            }
            .info-item {
                font-weight: bold;
                color: #333;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 12px;
                text-align: left;
            }
            th {
                background-color: #007bff;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            .status-radio label {
                margin-right: 15px;
            }
            .submit-button {
                background-color: #28a745;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                width: 100%;
                transition: background-color 0.3s ease;
            }
            .submit-button:hover {
                background-color: #218838;
            }
            .statistics {
                margin-top: 30px;
                padding: 20px;
                background-color: #e9f7ef;
                border-radius: 8px;
                box-shadow: 0 0 8px rgba(0, 0, 0, 0.05);
            }
            .statistics h2 {
                text-align: center;
                color: #28a745;
                margin-bottom: 20px;
            }
            .stats-item {
                display: flex;
                justify-content: space-between;
                padding: 8px 0;
                border-bottom: 1px dashed #cfe8d7;
            }
            .stats-item:last-child {
                border-bottom: none;
            }
            .stats-item span:first-child {
                font-weight: bold;
            }
            .back-button {
                display: block;
                width: fit-content;
                margin: 20px auto 0;
                padding: 10px 20px;
                background-color: #6c757d;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }
            .back-button:hover {
                background-color: #5a6268;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/view/sidebarTeacher.jsp" />

            <div class="main-content">
                <div class="container">
                    <h1>Điểm Danh Lớp: ${requestScope.className}</h1>
                    <div class="info-bar">
                        <span class="info-item">Ngày: ${requestScope.date}</span>
                        <span class="info-item">Lớp: ${requestScope.className}</span>
                    </div>

                    <form action="attendance" method="post">
                        <input type="hidden" name="date" value="${requestScope.date}">
                        <input type="hidden" name="classId" value="${requestScope.classId}">

                        <table>
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Mã học sinh</th>
                                    <th>Tên học sinh</th>
                                    <th>Trạng thái điểm danh</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="kid" items="${requestScope.kids}" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${kid.kinder_id}</td>
                                        <td>${kid.first_name} ${kid.last_name}</td>
                                        <td>
                                            <div class="status-radio">
                                                <input type="hidden" name="kinder_id" value="${kid.kinder_id}">
                                                <label>
                                                    <input type="radio" name="status_${kid.kinder_id}" value="1" 
                                                           <c:if test="${attList[kid.kinder_id] == 1}">checked</c:if>>
                                                           Có mặt
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="status_${kid.kinder_id}" value="0" 
                                                           <c:if test="${attList[kid.kinder_id] == 0}">checked</c:if>>
                                                           Vắng mặt
                                                    </label>
                                                </div>
                                            </td>
                                        </tr>
                                </c:forEach>
                                <c:if test="${empty requestScope.kids}">
                                    <tr>
                                        <td colspan="4" style="text-align: center;">Không có học sinh nào trong lớp này.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                        <button type="submit" class="submit-button">Lưu Điểm Danh</button>
                    </form>

                    <!--                    <div class="statistics">
                                            <h2>Thống Kê Điểm Danh Tháng Hiện Tại</h2>
                                            <div class="stats-item">
                                                <span>Tỷ lệ có mặt:</span>
                                                <span>${requestScope.stats.presentPercentage != null ? String.format("%.2f", requestScope.stats.presentPercentage) : 'N/A'}%</span>
                                            </div>
                                            <div class="stats-item">
                                                <span>Tỷ lệ vắng mặt:</span>
                                                <span>${requestScope.stats.absentPercentage != null ? String.format("%.2f", requestScope.stats.absentPercentage) : 'N/A'}%</span>
                                            </div>
                                        </div>-->
                    <div style="display: flex; justify-content: space-between; margin-top: 20px ">
                        <a href="${pageContext.request.contextPath}/attendance" class="back-button">Chọn lại ngày/lớp khác</a>
                        <a href="${pageContext.request.contextPath}/attendanceReport?classId=${requestScope.classId}" 
                           class="back-button" 
                           style="background-color:#007bff;">
                            Xem Báo Cáo
                        </a>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>