<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Notifications</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f2f4f8;
                padding: 30px;
                margin: 0;
            }

            h2 {
                text-align: center;
                color: #2c3e50;
                margin-bottom: 30px;
            }

            .notification-card {
                background-color: #ffffff;
                border: 1px solid #ddd;
                border-left: 6px solid #3498db;
                padding: 20px;
                margin: 15px auto;
                border-radius: 10px;
                box-shadow: 0 2px 6px rgba(0,0,0,0.05);
                max-width: 700px;
            }

            .notification-card strong {
                color: #34495e;
            }

            .notification-card span {
                display: block;
                margin-top: 5px;
            }

            .unread-link {
                color: #e74c3c;
                font-weight: bold;
                text-decoration: none;
            }

            .read-label {
                color: green;
                font-weight: bold;
            }

            .back-home {
                text-align: center;
                margin-top: 40px;
            }

            .back-home a {
                background-color: #3498db;
                color: white;
                padding: 12px 24px;
                border-radius: 8px;
                text-decoration: none;
                font-size: 16px;
                font-weight: bold;
                transition: background-color 0.3s ease;
            }

            .back-home a:hover {
                background-color: #2980b9;
            }
        </style>
    </head>
    <body>

        <h2>📬 Notifications for You</h2>

        <c:forEach var="n" items="${notifications}">
            <div class="notification-card">
                <strong>From:</strong> ${n.sender.firstName} ${n.sender.lastName} <br>
                <strong>Child:</strong> ${n.kindergartner.first_name} ${n.kindergartner.last_name} <br>
                <strong>Date:</strong> ${n.notificationDate} <br>
                <strong>Reason:</strong> ${n.reason} <br>
                <strong>Message:</strong> ${n.content} <br>
                
                <c:if test="${!n.read}">
                    <a href="${pageContext.request.contextPath}/markRead?id=${n.notificationId}" class="unread-link">[Mark as Read]</a>
                </c:if>
                <c:if test="${n.read}">
                    <span class="read-label">[Read]</span>
                </c:if>
            </div>
        </c:forEach>

        <div class="back-home">
            <a href="${pageContext.request.contextPath}/index.jsp">🏠 Quay lại trang chủ</a>
        </div>
    </body>
</html>
