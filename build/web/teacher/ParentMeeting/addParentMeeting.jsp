<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thêm cuộc họp phụ huynh</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background: #f8f9fa;
                margin: 0;
                padding: 20px;
            }
            .container {
                background: #fff;
                padding: 25px;
                border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                max-width: 600px;
                margin: 0 auto;
            }
            h2 {
                margin-bottom: 20px;
                color: #4e73df;
            }
            label {
                display: block;
                margin-top: 15px;
                font-weight: 600;
            }
            .required {
                color: red;
                margin-left: 4px;
            }
            input, select, textarea {
                width: 100%;
                padding: 8px;
                margin-top: 4px;
                border: 1px solid #ccc;
                border-radius: 6px;
            }
            button {
                margin-top: 20px;
                background-color: #4e73df;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            button:hover {
                background-color: #375ab6;
            }
            .back-btn {
                margin-top: 15px;
                display: inline-block;
                color: #4e73df;
                text-decoration: none;
            }
            .error {
                color: red;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2><i class="fa-solid fa-plus"></i> Thêm cuộc họp phụ huynh</h2>

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>

            <form method="post">
                <label>Chủ đề <span class="required">*</span></label>
                <input type="text" name="topic" required value="${param.topic != null ? param.topic : ''}"/>

                <label>Lớp <span class="required">*</span></label>
                <select name="classId" required>
                    <c:forEach var="c" items="${classList}">
                        <option value="${c.class_id}">${c.class_name}</option>
                    </c:forEach>
                </select>

                <label>Ngày giờ họp <span class="required">*</span></label>
                <input type="datetime-local" name="meetingDate" required/>

                <label>Ghi chú</label>
                <textarea name="notes" rows="4">${param.notes != null ? param.notes : ''}</textarea>

                <button type="submit"><i class="fa-solid fa-save"></i> Lưu</button>
            </form>

            <a href="${pageContext.request.contextPath}/teacher/parentmeetings" class="back-btn">
                <i class="fa-solid fa-arrow-left"></i> Quay lại
            </a>
        </div>
    </body>
</html>
