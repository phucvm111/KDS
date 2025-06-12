<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Send Notifications</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .form-container {
            max-width: 600px;
            margin: 0 auto;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        select, textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
        }
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .message {
            color: green;
            margin-bottom: 10px;
        }
        .error {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Send Notification to Teacher</h2>
        <c:if test="${not empty message}">
            <p class="message">${message}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
        <form action="notifications" method="post">
            <input type="hidden" name="action" value="send">
            <div class="form-group">
                <label for="receiverId">Select Teacher:</label>
                <select name="receiverId" id="receiverId" required>
                    <option value="">-- Select Teacher --</option>
                    <c:forEach var="teacher" items="${teachers}">
                        <option value="${teacher.accountID}">${teacher.firstName} ${teacher.lastName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="content">Notification Content:</label>
                <textarea name="content" id="content" rows="5" required></textarea>
            </div>
            <button type="submit">Send Notification</button>
        </form>
    </div>
</body>
</html>