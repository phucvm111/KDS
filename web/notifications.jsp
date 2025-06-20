<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Send Notification</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f2f5;
                padding: 40px;
            }

            .container {
                max-width: 700px;
                margin: auto;
                background-color: white;
                border-radius: 12px;
                padding: 30px;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            }

            h2 {
                margin-bottom: 20px;
                color: #333;
            }

            label {
                display: block;
                margin-top: 15px;
                font-weight: bold;
            }

            select, textarea {
                width: 100%;
                padding: 10px;
                margin-top: 8px;
                border-radius: 6px;
                border: 1px solid #ccc;
            }

            button {
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 6px;
                cursor: pointer;
            }

            button:hover {
                background-color: #0056b3;
            }

            .msg {
                margin-top: 15px;
                font-weight: bold;
                color: green;
            }

            .error {
                margin-top: 15px;
                font-weight: bold;
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Send Notification to Parent</h2>

            <c:if test="${not empty message}">
                <p class="msg">${message}</p>
            </c:if>

            <c:if test="${not empty messageError}">
                <p class="error" style="text-align: center">${messageError}</p>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/notifications">


                <!-- Chọn phụ huynh -->
                <label for="receiverId">Parent:</label>
                <select name="receiverId" id="receiverId" required>
                    <option value="">-- Select Parent --</option>
                    <c:forEach var="p" items="${parents}">
                        <option value="${p.accountID}">${p.firstName} ${p.lastName}</option>
                    </c:forEach>
                </select>

                <!-- Chọn bé -->
                <label for="kinderId">Child:</label>
                <select name="kinderId" id="kinderId" required>
                    <option value="">-- Select Child --</option>
                    <c:forEach var="kid" items="${children}">
                        <option value="${kid.kinder_id}">
                            ${kid.first_name} ${kid.last_name} (ID: ${kid.kinder_id})
                        </option>
                    </c:forEach>
                </select>
                <!-- Lý do gửi thông báo -->
                <label for="reason">Reason:</label>
                <select name="reason" id="reason" required>
                    <option value="">-- Select Reason --</option>
                    <option value="Absent today">Absent today</option>
                    <option value="Behavior update">Behavior update</option>
                    <option value="Performance feedback">Performance feedback</option>
                    <option value="Health concern">Health concern</option>
                    <option value="General notice">General notice</option>
                </select>

                <!-- Nội dung -->
                <label for="content">Message:</label>
                <textarea name="content" id="content" rows="4" required placeholder="Enter your message..."></textarea>

                <button type="submit">Send Notification</button>
            </form>
            <hr>
            <hr>
            <div style="display: flex; gap: 20px; justify-content: space-between;">
                <!-- Thông báo gửi đến phụ huynh -->
                <div style="flex: 1;">
                    <h3>📩 Sent to Parents</h3>
                    <c:forEach var="n" items="${sentNotifications}">
                        <div style="border: 1px solid #ddd; padding: 12px; margin-bottom: 12px; border-radius: 6px; background: #f9f9f9;">
                            <strong>To:</strong> ${n.receiver.firstName} ${n.receiver.lastName} <br>
                            <strong>Child:</strong> ${n.kindergartner.first_name} ${n.kindergartner.last_name} <br>
                            <strong>Date:</strong> <fmt:formatDate value="${n.notificationDate}" pattern="dd/MM/yyyy HH:mm" /> <br>
                            <strong>Reason:</strong> ${n.reason} <br>
                            <strong>Message:</strong> ${n.content} <br>
                            <c:if test="${!n.read}">
                                <span style="color:red;">[Unread]</span>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>

                <!-- Thông báo từ admin -->
                <div style="flex: 1;">
                    <h3>🛠️ From Admin</h3>
                    <c:forEach var="a" items="${adminNotifications}">
                        <div style="border: 1px solid #ddd; padding: 12px; margin-bottom: 12px; border-radius: 6px; background: #f0f8ff;">
                            <strong>From:</strong> Admin ${a.sender.firstName} ${a.sender.lastName} <br>
                            <strong>Date:</strong> <fmt:formatDate value="${a.notificationDate}" pattern="dd/MM/yyyy HH:mm" /> <br>
                            <strong>Reason:</strong> ${a.reason} <br>
                            <strong>Message:</strong> ${a.content} <br>
                            <c:if test="${!a.read}">
                                <a href="${pageContext.request.contextPath}/markTeacherRead?id=${a.notificationId}" 
                                   style="color:red; text-decoration: underline;">
                                    [Mark as Read]
                                </a>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>

            </div>


        </div>
        <!-- Nút quay lại trang chủ -->
        <div style="text-align: center; margin-top: 30px;">
            <a href="${pageContext.request.contextPath}/attendance" 
               style="display: inline-block; padding: 12px 24px; background-color: #3498db; color: white;
               border-radius: 6px; text-decoration: none; font-weight: bold;">
                🏠 Quay lại trang chủ
            </a>
        </div>

    </body>
</html>
