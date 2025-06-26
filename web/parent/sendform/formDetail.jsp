<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Xem phản hồi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f1f3f5;
            padding: 40px;
        }
        .response-box {
            max-width: 700px;
            margin: auto;
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.1);
        }
        .response-title {
            font-size: 26px;
            font-weight: bold;
            margin-bottom: 25px;
            text-align: center;
        }
        .form-section {
            margin-bottom: 20px;
        }
        .form-label {
            font-weight: 600;
        }
    </style>
</head>
<body>

<div class="response-box">
    <div class="response-title">Phản hồi từ nhà trường</div>

    <div class="form-section">
        <label class="form-label">Tiêu đề đơn:</label>
        <p>${form.title}</p>
    </div>

    <div class="form-section">
        <label class="form-label">Nội dung đơn:</label>
        <p>${form.content}</p>
    </div>

    <div class="form-section">
        <label class="form-label">Phản hồi:</label>
        <c:choose>
            <c:when test="${not empty form.reply}">
                <p style="white-space: pre-line;">${form.reply}</p>
            </c:when>
            <c:otherwise>
                <p class="text-muted fst-italic">Chưa có phản hồi từ nhà trường.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/historyform" class="btn btn-secondary">← Quay lại</a>
    </div>
</div>

</body>
</html>
