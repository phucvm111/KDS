<%-- WebContent/view/teacher/Attendance/attendance_form.jsp --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chọn Ngày và Lớp Học</title>
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css">
        <style>
            .wrapper {
                display: flex;
                min-height: 100vh;
            }

            .main-content {
                margin-left: 250px;
                padding: 20px;
                flex-grow: 1;
            }

            .container {
                max-width: 600px;
                margin-top: 50px;
                margin-left: 80px;
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
            .form-group {
                margin-bottom: 20px;
            }
            .form-group label {
                display: block;
                margin-bottom: 8px;
                font-weight: bold;
                color: #555;
            }
            .form-group select,
            .form-group input[type="date"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
            }
            .form-group input[type="submit"],
            .btn { /* Thêm .btn để style cho nút Xem Báo Cáo */
                background-color: #007bff;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                width: 100%;
                transition: background-color 0.3s ease;
                text-align: center; /* Đảm bảo text căn giữa cho cả input và a.btn */
                display: inline-block; /* Quan trọng để width và padding hoạt động với <a> */
            }
            .form-group input[type="submit"]:hover,
            .btn:hover {
                background-color: #0056b3;
            }
            .btn-info { /* Style riêng cho nút báo cáo */
                background-color: #17a2b8;
            }
            .btn-info:hover {
                background-color: #138496;
            }

            /* CSS mới cho nút tổng quan, đặt nó xuống dưới form */
            .overview-button-container {
                margin-top: 30px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/view/sidebarTeacher.jsp">
                <jsp:param name="currentPage" value="attendance" />
            </jsp:include>

            <div class="main-content">
                <div class="container">
                    <h1>Chọn Ngày và Lớp Học </h1>
                    <form action="attendance" method="get">
                        <div class="form-group">
                            <label for="date">Chọn Ngày:</label>
                            <input type="date" id="date" name="date" value="${requestScope.date != null ? requestScope.date : ''}" required>
                        </div>
                        <div class="form-group">
                            <label for="classId">Chọn Lớp Học:</label>
                            <select id="classId" name="classId" required>
                                <c:forEach var="classObj" items="${requestScope.classes}">
                                    <option value="${classObj.class_id}" ${requestScope.classId eq classObj.class_id ? 'selected' : ''}>
                                        ${classObj.class_name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Xem Danh Sách Điểm Danh">
                        </div>
                    </form>


                </div>
            </div>
        </div>
    </body>
</html>