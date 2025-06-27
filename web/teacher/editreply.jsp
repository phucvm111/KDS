<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa phản hồi</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
            background-color: #f4f6f9;
        }

        h2 {
            color: #333;
            margin-bottom: 25px;
        }

        .form-container {
            max-width: 700px;
            padding: 25px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 6px;
        }

        p {
            background-color: #f1f1f1;
            padding: 8px 10px;
            border-radius: 5px;
        }

        textarea, select {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .back-btn {
            margin-top: 20px;
        }

        .back-btn button {
            padding: 8px 16px;
            background-color: #6c757d;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .back-btn button:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>

<h2>Chỉnh sửa phản hồi đơn</h2>

<div class="form-container">
    <form action="updatereply" method="post">
        <!-- Ẩn form_id để biết đang sửa đơn nào -->
        <input type="hidden" name="form_id" value="${form.form_id}" />

        <div class="form-group">
            <label>Tiêu đề:</label>
            <p>${form.title}</p>
        </div>

        <div class="form-group">
            <label>Nội dung đơn:</label>
            <p>${form.content}</p>
        </div>

        <div class="form-group">
            <label>Trẻ:</label>
            <p>${form.getKindergartner().first_name} ${form.getKindergartner().last_name}</p>
        </div>

        <div class="form-group">
            <label>Ngày gửi:</label>
            <p>${form.date_submitted}</p>
        </div>

        <div class="form-group">
            <label>Phản hồi:</label>
            <textarea name="reply" rows="4" required>${form.reply}</textarea>
        </div>

        <div class="form-group">
            <label>Trạng thái:</label>
            <select name="status">
                <option value="pending" ${form.status == 'pending' ? 'selected' : ''}>Chờ xử lý</option>
                <option value="reviewed" ${form.status == 'reviewed' ? 'selected' : ''}>Đã xem</option>
                <option value="completed" ${form.status == 'completed' ? 'selected' : ''}>Hoàn thành</option>
            </select>
        </div>

        <input type="submit" value="Cập nhật phản hồi" />
    </form>

    <div class="back-btn">
        <button onclick="history.back()">⬅ Quay lại</button>
    </div>
</div>

</body>
</html>
