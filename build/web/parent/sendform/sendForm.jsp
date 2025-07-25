<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gửi đơn</title>

    <!-- Load Font Awesome nếu sidebar có icon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <!-- CSS của sidebar -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css">

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f0f2f5;
            margin: 0;
            padding: 0;
        }

        .wrapper {
            display: flex;
        }

        .main-content {
            flex: 1;
            padding: 40px;
        }

        .container {
            max-width: 700px;
            margin: auto;
            background: #fff;
            padding: 30px 40px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 15px;
            color: #333;
        }

        input, textarea, select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }

        button {
            margin-top: 25px;
            padding: 12px 20px;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            background-color: #007bff;
            color: white;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        .btn-secondary {
            background-color: #6c757d;
            margin-right: 10px;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
        }

        .btn-group {
            text-align: center;
        }
    </style>
</head>
<body>

<div class="wrapper">
    <!-- Include sidebar -->
    <jsp:include page="/view/sidebarParent.jsp" />

    <div class="main-content">
        <div class="container">
            <h2>Gửi đơn</h2>
            <p style="color: red">Lưu ý: hãy cập nhật thông tin trẻ trước khi gửi đơn</p>

            <form action="${pageContext.request.contextPath}/sendform" method="post">

                <label for="formType">Loại đơn:</label>
                <select name="formTypeId" id="formType" required>
                    <c:forEach var="type" items="${formTypes}">
                        <option value="${type.form_type_id}">${type.type_name}</option>
                    </c:forEach>
                </select>

                <label for="kinderId">Chọn bé:</label>
                <select name="kinderId" id="kinderId" required>
                    <c:forEach var="kid" items="${kinderList}">
                        <option value="${kid.kinder_id}">${kid.first_name} ${kid.last_name}</option>
                    </c:forEach>
                </select>

                <label for="title">Tiêu đề:</label>
                <input type="text" name="title" id="title" required>

                <label for="content">Nội dung:</label>
                <textarea name="content" id="content" rows="6" required></textarea>

                <div class="btn-group">
                    <a href="${pageContext.request.contextPath}/historyform" class="btn-secondary" style="text-decoration: none;">
                        <button type="button" class="btn-secondary">Lịch sử gửi đơn</button>
                    </a>
                    <button type="submit">Gửi đơn</button>
                    <p style="color: green">${success}</p>
                    <p style="color: red">${error}</p>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
