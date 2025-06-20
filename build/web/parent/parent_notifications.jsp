<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Báo</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
            background: linear-gradient(135deg, #f0f4f8 0%, #e2e8f0 100%);
            color: #1f2a44;
            line-height: 1.6;
            min-height: 100vh;
            padding: 40px 20px;
            display: flex;
            justify-content: center;
            align-items: flex-start;
        }

        .container {
            max-width: 900px;
            width: 100%;
            margin: 0 auto;
        }

        .header {
            text-align: center;
            margin-bottom: 40px;
            position: relative;
        }

        h2 {
            font-size: 2.25rem;
            font-weight: 700;
            color: #1f2a44;
            display: inline-flex;
            align-items: center;
            gap: 12px;
        }

        .unread-badge {
            background: #dc2626;
            color: #ffffff;
            font-size: 0.85rem;
            font-weight: 600;
            padding: 4px 10px;
            border-radius: 12px;
            margin-left: 8px;
        }

        .filter-group {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 24px;
        }

        .filter-group label {
            font-size: 0.95rem;
            font-weight: 500;
            color: #475569;
            margin-right: 12px;
            align-self: center;
        }

        select {
            width: 180px;
            padding: 10px 14px;
            border: 1px solid #e5e7eb;
            border-radius: 8px;
            font-size: 0.95rem;
            color: #1f2a44;
            background: #ffffff;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
            transition: border-color 0.2s ease, box-shadow 0.2s ease;
            appearance: none;
            background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 24 24' stroke='%23475569' stroke-width='2'%3E%3Cpath stroke-linecap='round' stroke-linejoin='round' d='M19 9l-7 7-7-7'/%3E%3C/svg%3E");
            background-repeat: no-repeat;
            background-position: right 12px center;
            background-size: 16px;
        }

        select:focus {
            outline: none;
            border-color: #2563eb;
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
        }

        .notification-card {
            background: #ffffff;
            border: 1px solid #e5e7eb;
            border-left: 6px solid #2563eb;
            border-radius: 12px;
            padding: 24px;
            margin-bottom: 20px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .notification-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.12);
        }

        .notification-card p {
            margin: 8px 0;
            font-size: 0.95rem;
            color: #374151;
        }

        .notification-card strong {
            color: #1f2a44;
            font-weight: 600;
        }

        .notification-meta {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 12px;
            font-size: 0.9rem;
        }

        .unread-link {
            color: #dc2626;
            font-weight: 600;
            text-decoration: none;
            transition: color 0.2s ease;
        }

        .unread-link:hover {
            color: #b91c1c;
            text-decoration: underline;
        }

        .read-label {
            color: #16a34a;
            font-weight: 600;
        }

        .back-home {
            text-align: center;
            margin-top: 48px;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 12px 24px;
            font-size: 1rem;
            font-weight: 600;
            border-radius: 8px;
            text-decoration: none;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .btn-secondary {
            background: #f1f5f9;
            color: #1f2a44;
            border: 1px solid #e5e7eb;
        }

        .btn-secondary:hover {
            background: #e2e8f0;
            transform: translateY(-2px);
        }

        .no-notifications {
            text-align: center;
            color: #64748b;
            font-size: 1rem;
            padding: 40px;
            background: #ffffff;
            border: 1px solid #e5e7eb;
            border-radius: 12px;
        }

        @media (max-width: 768px) {
            h2 {
                font-size: 1.75rem;
            }

            .notification-card {
                padding: 20px;
            }

            .filter-group {
                justify-content: center;
            }

            select {
                width: 100%;
                max-width: 300px;
            }
        }

        @media (max-width: 480px) {
            body {
                padding: 20px 10px;
            }

            h2 {
                font-size: 1.5rem;
            }

            .notification-card p {
                font-size: 0.9rem;
            }

            .notification-meta {
                flex-direction: column;
                align-items: flex-start;
                gap: 8px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>📬 Thông Báo Dành Cho Bạn
                <c:if test="${not empty notifications}">
                    <c:set var="unreadCount" value="${0}" />
                    <c:forEach var="n" items="${notifications}">
                        <c:if test="${not n.read}">
                            <c:set var="unreadCount" value="${unreadCount + 1}" />
                        </c:if>
                    </c:forEach>
                    <c:if test="${unreadCount > 0}">
                        <span class="unread-badge">${unreadCount}</span>
                    </c:if>
                </c:if>
            </h2>
        </div>

        <div class="filter-group">
            <label for="filter-status">🔎 Lọc trạng thái</label>
            <select id="filter-status">
                <option value="all">Tất cả</option>
                <option value="unread">Chưa đọc</option>
                <option value="read">Đã đọc</option>
            </select>
        </div>

        <c:choose>
            <c:when test="${empty notifications}">
                <div class="no-notifications">Không có thông báo nào để hiển thị.</div>
            </c:when>
            <c:otherwise>
                <c:forEach var="n" items="${notifications}">
                    <div class="notification-card" data-status="${n.read ? 'read' : 'unread'}">
                        <p><strong>Người gửi:</strong> ${n.sender.firstName} ${n.sender.lastName}</p>
                        <p><strong>Trẻ:</strong> ${n.kindergartner.first_name} ${n.kindergartner.last_name}</p>
                        <p><strong>Lý do:</strong> ${n.reason}</p>
                        <p><strong>Nội dung:</strong> ${n.content}</p>
                        <div class="notification-meta">
                            <span><strong>Ngày gửi:</strong> <fmt:formatDate value="${n.notificationDate}" pattern="dd/MM/yyyy HH:mm"/></span>
                            <span>
                                <c:choose>
                                    <c:when test="${not n.read}">
                                        <a href="${pageContext.request.contextPath}/markRead?id=${n.notificationId}" class="unread-link">[Đánh dấu đã đọc]</a>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="read-label">[Đã đọc]</span>
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>

        <div class="back-home">
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">🏠 Quay lại trang chủ</a>
        </div>
    </div>

    <script>
        document.getElementById('filter-status').addEventListener('change', function () {
            const filterValue = this.value;
            const cards = document.querySelectorAll('.notification-card');

            cards.forEach(card => {
                const status = card.getAttribute('data-status');
                card.style.display = filterValue === 'all' || filterValue === status ? 'block' : 'none';
            });
        });
    </script>
</body>
</html>