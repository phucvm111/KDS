<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gửi Thông Báo Tới Giáo Viên</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
            background-color: #f5f7fa;
            color: #1a202c;
            line-height: 1.6;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
        }

        .container {
            max-width: 700px;
            width: 100%;
            background-color: #ffffff;
            border-radius: 16px;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
            padding: 40px;
            margin: 20px auto;
        }

        h2 {
            font-size: 2rem;
            font-weight: 700;
            color: #2d3748;
            text-align: center;
            margin-bottom: 30px;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
        }

        h3 {
            font-size: 1.5rem;
            font-weight: 600;
            color: #2d3748;
            text-align: center;
            margin: 40px 0 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-size: 1rem;
            font-weight: 600;
            color: #4a5568;
            display: block;
            margin-bottom: 8px;
        }

        input[type="text"],
        textarea,
        select {
            width: 100%;
            padding: 12px 16px;
            border: 1px solid #e2e8f0;
            border-radius: 8px;
            font-size: 1rem;
            color: #2d3748;
            background-color: #f7fafc;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }

        input[type="text"]:focus,
        textarea:focus,
        select:focus {
            outline: none;
            border-color: #3182ce;
            box-shadow: 0 0 0 3px rgba(49, 130, 206, 0.1);
        }

        textarea {
            resize: vertical;
            min-height: 120px;
        }

        .btn {
            display: block;
            width: 100%;
            padding: 12px;
            font-size: 1rem;
            font-weight: 600;
            text-align: center;
            border-radius: 8px;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .btn-primary {
            background-color: #3182ce;
            color: #ffffff;
        }

        .btn-primary:hover {
            background-color: #2b6cb0;
            transform: translateY(-1px);
        }

        .btn-secondary {
            background-color: #edf2f7;
            color: #2d3748;
            margin-top: 10px;
        }

        .btn-secondary:hover {
            background-color: #e2e8f0;
            transform: translateY(-1px);
        }

        .message {
            text-align: center;
            font-size: 1rem;
            font-weight: 500;
            color: #48bb78;
            margin-bottom: 20px;
            padding: 10px;
            background-color: #f0fff4;
            border-radius: 8px;
        }
         .messageError {
            text-align: center;
            font-size: 1rem;
            font-weight: 500;
            color: red;
            background: white;
            margin-bottom: 20px;
            padding: 10px;
            border-radius: 8px;
        }

        .notification-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            overflow: hidden;
        }

        .notification-table th,
        .notification-table td {
            padding: 12px 16px;
            text-align: left;
            border-bottom: 1px solid #e2e8f0;
        }

        .notification-table th {
            background-color: #f7fafc;
            font-weight: 600;
            color: #4a5568;
            font-size: 0.9rem;
            text-transform: uppercase;
        }

        .notification-table td {
            font-size: 0.95rem;
            color: #2d3748;
        }

        .status-unread {
            color: #e53e3e;
            font-weight: 500;
        }

        .status-read {
            color: #48bb78;
            font-weight: 500;
        }

        .filter-group {
            margin-bottom: 20px;
        }

        @media (max-width: 600px) {
            .container {
                padding: 20px;
            }

            h2 {
                font-size: 1.5rem;
            }

            .notification-table th,
            .notification-table td {
                padding: 10px;
                font-size: 0.85rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>📢 Gửi Thông Báo Tới Giáo Viên</h2>

        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>
        <c:if test="${not empty messageError}">
            <div class="messageError">${messageError}</div>
        </c:if>    

        <form action="${pageContext.request.contextPath}/adminNotifyTeachers" method="post">
            <div class="form-group">
                <label for="reason">🔍 Lý do</label>
                <input type="text" name="reason" value="${requestScope.reason}" id="reason" required>
            </div>

            <div class="form-group">
                <label for="content">✏️ Nội dung thông báo</label>
                <textarea name="content" id="content"  rows="6" required>${requestScope.content}</textarea>
            </div>

            <button type="submit" class="btn btn-primary">📨 Gửi thông báo</button>
        </form>

        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">🏠 Quay lại trang</a>

        <c:if test="${not empty sentNotifications}">
            <h3>📋 Thông Báo Đã Gửi</h3>
            <div class="filter-group">
                <label for="filter-status">🔎 Lọc theo trạng thái</label>
                <select id="filter-status">
                    <option value="all">Tất cả</option>
                    <option value="unread">Chưa đọc</option>
                    <option value="read">Đã đọc</option>
                </select>
            </div>
            <table class="notification-table">
                <thead>
                    <tr>
                        <th scope="col">🆔 ID</th>
                        <th scope="col">Lý do</th>
                        <th scope="col">Nội dung</th>
                        <th scope="col">Ngày gửi</th>
                        <th scope="col">Trạng thái</th>
                    </tr>
                </thead>
                <tbody id="notification-table-body">
                    <c:forEach var="noti" items="${sentNotifications}" varStatus="status">
                        <tr class="notification-row" data-status="${noti.read ? 'read' : 'unread'}">
                            <td>${status.index + 1}</td>
                            <td>${noti.reason}</td>
                            <td>${noti.content}</td>
                            <td><fmt:formatDate value="${noti.notificationDate}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${not noti.read}">
                                        <span class="status-unread">[Chưa đọc]</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status-read">[Đã đọc]</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>

    <script>
        document.getElementById('filter-status').addEventListener('change', function () {
            const filterValue = this.value;
            const rows = document.querySelectorAll('.notification-row');

            rows.forEach(row => {
                const status = row.getAttribute('data-status');
                if (filterValue === 'all' || filterValue === status) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });
    </script>
</body>
</html>