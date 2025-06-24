<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch Sử Yêu Cầu Nghỉ Phép</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
    }
    .container {
        background-color: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 1000px; /* Tăng chiều rộng để hiển thị tốt hơn */
        margin: 20px auto;
    }
    h2 {
        text-align: center;
        color: #333;
        margin-bottom: 20px;
    }
    .action-bar {
        text-align: right;
        margin-bottom: 20px;
    }
    .btn {
        display: inline-block;
        padding: 10px 18px;
        border-radius: 5px;
        text-decoration: none;
        font-size: 16px;
        transition: background-color 0.3s ease;
    }
    .btn-primary {
        background-color: #007bff;
        color: white;
        border: none;
    }
    .btn-primary:hover {
        background-color: #0056b3;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
        color: #333;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    tr:hover {
        background-color: #f1f1f1;
    }
    .status-pending { color: #ffc107; font-weight: bold; } /* Màu vàng */
    .status-approved { color: #28a745; font-weight: bold; } /* Màu xanh lá cây */
    .status-rejected { color: #dc3545; font-weight: bold; } /* Màu đỏ */
    .no-data {
        text-align: center;
        color: #777;
        margin-top: 20px;
        padding: 15px;
        border: 1px dashed #ccc;
        border-radius: 5px;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Lịch Sử Yêu Cầu Nghỉ Phép</h2>

        <div class="action-bar">
            <a href="leaverequest" class="btn btn-primary">
                <i class="fas fa-plus-circle"></i> Đơn Xin Nghỉ Phép Mới
            </a>
        </div>

        <c:choose>
            <c:when test="${not empty leaveRequests}">
                <table>
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Ngày Gửi</th>
                            <th>Họ và Tên Giáo Viên</th>
                            <th>Ngày Bắt Đầu</th>
                            <th>Ngày Kết Thúc</th>
                            <th>Số Ngày Nghỉ</th>
                            <th>Loại Nghỉ Phép</th>
                            <th>Lý Do</th>
                            <th>Trạng Thái</th>
                            </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="request" items="${leaveRequests}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${request.requestDate}</td>
                                <td>${request.teacherName}</td>
                                <td>${request.startDate}</td>
                                <td>${request.endDate}</td>
                                <td>${request.numberOfDays}</td>
                                <td>${request.leaveType}</td>
                                <td>${request.leaveReason}</td>
                                <td>
                                    <span class="status-${request.status.toLowerCase()}">
                                        ${request.status}
                                    </span>
                                </td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p class="no-data">Bạn chưa có yêu cầu nghỉ phép nào.</p>
                <p class="no-data">Hãy click vào nút "Đơn Xin Nghỉ Phép Mới" để gửi yêu cầu đầu tiên của bạn!</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>