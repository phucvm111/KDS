<%-- 
    Document   : adminAccountAdd
    Created on : Jun 24, 2022, 4:04:14 PM
    Author     : win
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Send notice for all teacher</title>
        <style>
            body {
                font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f2f4f8;
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 600px;
                margin: 60px auto;
                padding: 30px;
                background-color: #ffffff;
                border-radius: 12px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            }

            h2 {
                text-align: center;
                color: #2c3e50;
                margin-bottom: 25px;
            }

            label {
                font-weight: bold;
                display: block;
                margin-bottom: 6px;
                color: #34495e;
            }

            input[type="text"],
            textarea {
                width: 100%;
                padding: 10px 14px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 8px;
                font-size: 14px;
            }

            textarea {
                resize: vertical;
            }

            input[type="submit"],
            .btn-home {
                width: 100%;
                background-color: #3498db;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 8px;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s ease;
                margin-top: 10px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
            }

            input[type="submit"]:hover,
            .btn-home:hover {
                background-color: #2980b9;
            }

            .message {
                text-align: center;
                margin-bottom: 15px;
                font-weight: bold;
                color: green;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>📢 Send notice to all teacher</h2>

            <c:if test="${not empty message}">
                <div class="message">${message}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/adminNotifyTeachers" method="post">
                <label for="reason">🔍 Reason</label>
                <input type="text" name="reason" id="reason" required>

                <label for="content">✏️ Content</label>
                <textarea name="content" id="content" rows="6" required></textarea>

                <input type="submit" value="📨 Send notice">
            </form>

            <!-- Nút quay lại homepage -->
            <a href="${pageContext.request.contextPath}/listaccount" class="btn-home">🏠 Back to Homepage</a>
            <c:if test="${not empty sentNotifications}">
                <h3 style="margin-top: 40px; color: #2c3e50; text-align: center;">📋 List of sent notifications</h3>
                <table style="width: 100%; border-collapse: collapse; margin-top: 10px;">
                    <thead>
                        <tr style="background-color: #f1f1f1;">
                            <th style="padding: 10px; border: 1px solid #ddd;">🆔 ID</th>
                            <th style="padding: 10px; border: 1px solid #ddd;">Reason</th>
                            <th style="padding: 10px; border: 1px solid #ddd;">Content</th>
                            <th style="padding: 10px; border: 1px solid #ddd;">Date</th>
                            <th style="padding: 10px; border: 1px solid #ddd;">Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="noti" items="${sentNotifications}" varStatus="status">
                            <tr>
                                <td style="padding: 10px; border: 1px solid #ddd;">${status.index + 1}</td>
                                <td style="padding: 10px; border: 1px solid #ddd;">${noti.reason}</td>
                                <td style="padding: 10px; border: 1px solid #ddd;">${noti.content}</td>
                                <td style="padding: 10px; border: 1px solid #ddd;">
                                    <fmt:formatDate value="${noti.notificationDate}" pattern="dd/MM/yyyy HH:mm"/>
                                </td>
                                <td style="padding: 10px; border: 1px solid #ddd;">
                                    <c:choose>
                                        <c:when test="${not noti.read}">
                                            <span style="color:red;">[Unread]</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color:green;">[Read]</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

        </div>
    </body>
</html>
