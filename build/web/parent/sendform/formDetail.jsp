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
            background-color: #f8f9fa;
            padding: 40px;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        }

        .response-box {
            max-width: 700px;
            margin: auto;
            background: #ffffff;
            padding: 35px 40px;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }

        .response-title {
            font-size: 28px;
            font-weight: 700;
            margin-bottom: 30px;
            text-align: center;
            color: #343a40;
        }

        .form-section {
            margin-bottom: 25px;
        }

        .form-label {
            font-weight: 600;
            color: #495057;
            margin-bottom: 6px;
            display: block;
        }

        .form-section p {
            margin: 0;
            font-size: 16px;
            color: #212529;
        }

        .btn-back {
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 8px;
        }

        .text-muted {
            font-size: 15px;
        }
    </style>
</head>
<body>

<div class="response-box">
    <div class="response-title">📩 Phản hồi từ nhà trường</div>

    <div class="form-section">
        <label class="form-label">📌 Tiêu đề đơn:</label>
        <p>${form.title}</p>
    </div>

    <div class="form-section">
        <label class="form-label">📝 Nội dung đơn:</label>
        <p>${form.content}</p>
    </div>

    <div class="form-section">
        <label class="form-label">📬 Phản hồi:</label>
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
        <a href="${pageContext.request.contextPath}/historyform" class="btn btn-secondary btn-back">← Quay lại</a>
    </div>
</div>

</body>
</html>
