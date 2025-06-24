<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đơn Xin Nghỉ Phép</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }
    .container {
        background-color: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 500px;
    }
    h2 {
        text-align: center;
        color: #333;
        margin-bottom: 20px;
    }
    .form-group {
        margin-bottom: 15px;
    }
    label {
        display: block;
        margin-bottom: 5px;
        color: #555;
        font-weight: bold;
    }
    input[type="text"],
    input[type="date"],
    textarea,
    select {
        width: calc(100% - 22px);
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        box-sizing: border-box;
        font-size: 16px;
    }
    textarea {
        resize: vertical;
        min-height: 80px;
    }
    .btn-submit {
        background-color: #007bff;
        color: white;
        padding: 12px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 18px;
        width: 100%;
        margin-top: 20px;
    }
    .btn-submit:hover {
        background-color: #0056b3;
    }
    .message {
        margin-top: 20px;
        padding: 10px;
        border-radius: 4px;
        text-align: center;
    }
    .message.success {
        background-color: #d4edda;
        color: #155724;
        border: 1px solid #c3e6cb;
    }
    .message.error {
        background-color: #f8d7da;
        color: #721c24;
        border: 1px solid #f5c6cb;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Đơn Xin Nghỉ Phép</h2>

        <%
            // Lấy thông báo từ request attribute (nếu có)
            String message = (String) request.getAttribute("message");
            String messageType = (String) request.getAttribute("messageType");
            if (message != null && !message.isEmpty()) {
        %>
            <div class="message <%= messageType %>">
                <%= message %>
            </div>
        <%
            }
        %>

        <form action="LeaveRequestServlet" method="post">
            <div class="form-group">
                <label for="teacherName">Họ và tên giáo viên:</label>
                <input type="text" id="teacherName" name="teacherName" required>
            </div>

            <div class="form-group">
                <label for="startDate">Ngày bắt đầu nghỉ:</label>
                <input type="date" id="startDate" name="startDate" required>
            </div>

            <div class="form-group">
                <label for="endDate">Ngày kết thúc nghỉ:</label>
                <input type="date" id="endDate" name="endDate" required>
            </div>

            <div class="form-group">
                <label for="leaveReason">Lý do nghỉ phép:</label>
                <textarea id="leaveReason" name="leaveReason" rows="5" required></textarea>
            </div>

            <div class="form-group">
                <label for="leaveType">Loại nghỉ phép:</label>
                <select id="leaveType" name="leaveType">
                    <option value="annual">Nghỉ phép năm</option>
                    <option value="sick">Nghỉ ốm</option>
                    <option value="personal">Nghỉ việc riêng</option>
                    <option value="other">Khác</option>
                </select>
            </div>

            <button type="submit" class="btn-submit">Gửi Đơn</button>
        </form>
    </div>
</body>
</html>