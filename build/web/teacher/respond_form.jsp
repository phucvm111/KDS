<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List of Forms</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 40px;
                background-color: #f7f9fc;
                color: #333;
            }

            h2 {
                margin-bottom: 20px;
                color: #2c3e50;
            }

            .top-buttons {
                margin-bottom: 25px;
            }

            .top-buttons button {
                margin-right: 12px;
                padding: 10px 18px;
                font-size: 14px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-history {
                background-color: #007bff;
                color: white;
            }

            .btn-history:hover {
                background-color: #0056b3;
            }

            .btn-back {
                background-color: #6c757d;
                color: white;
            }

            .btn-back:hover {
                background-color: #5a6268;
            }

            .form-container {
                max-width: 900px;
                margin-bottom: 35px;
                padding: 25px;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
                background-color: #ffffff;
            }

            .form-group {
                margin-bottom: 14px;
            }

            label {
                font-weight: bold;
                color: #34495e;
            }

            p {
                margin: 5px 0 0 0;
                padding: 5px 10px;
                background-color: #ecf0f1;
                border-radius: 4px;
            }

            textarea, select, input[type="submit"] {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 14px;
            }

            input[type="submit"] {
                background-color: #28a745;
                color: white;
                font-weight: bold;
                transition: background-color 0.3s ease;
            }

            input[type="submit"]:hover {
                background-color: #218838;
            }

            .status-message {
                color: green;
                font-weight: bold;
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>

        <div class="top-buttons">
            <button class="btn-history" onclick="window.location.href='historyreply'">📜 Lịch sử phản hồi</button>
            <button class="btn-back" onclick="history.back()">⬅ Quay lại</button>
        </div>

        <h2>📋 Danh sách các đơn gửi đến giáo viên</h2>

        <p class="status-message">${success}</p>
        <p class="status-message">${error}</p>

        <c:forEach var="form" items="${formList}" varStatus="loop">
            <div class="form-container">
                <div class="form-group">
                    <label>📝 Tiêu đề:</label>
                    <p>${form.title}</p>
                </div>

                <div class="form-group">
                    <label>📄 Nội dung:</label>
                    <p>${form.content}</p>
                </div>

                <div class="form-group">
                    <label>👤 Người gửi:</label>
                    <p>${form.getAccount().firstName} ${form.getAccount().lastName}</p>
                </div>

                <div class="form-group">
                    <label>👶 Trẻ em:</label>
                    <p>${form.getKindergartner().first_name} ${form.getKindergartner().last_name}</p>
                </div>

                <div class="form-group">
                    <label>🏫 Lớp:</label>
                    <p>${classList[loop.index].class_name}</p>
                </div>

                <div class="form-group">
                    <label>📅 Ngày gửi:</label>
                    <p>${form.date_submitted}</p>
                </div>

                <form action="respondform" method="post">
                    <input type="hidden" name="form_id" value="${form.form_id}" />
                    <input type="hidden" name="kinder_id" value="${form.getKindergartner().kinder_id}" />

                    <div class="form-group">
                        <label>✏️ Phản hồi:</label>
                        <textarea name="reply" rows="4" required></textarea>
                    </div>

                    <div class="form-group">
                        <label>📌 Trạng thái:</label>
                        <select name="status">
                            <option value="pending">Chờ xử lý</option>
                            <option value="reviewed">Đã xem</option>
                            <option value="completed">Hoàn thành</option>
                        </select>
                    </div>

                    <input type="submit" value="📤 Gửi phản hồi" />
                </form>
            </div>
        </c:forEach>

    </body>
</html>
