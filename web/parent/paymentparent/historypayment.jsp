<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Lịch sử thanh toán học phí</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/stylepaymoney.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">
</head>
<body>
<div style="display: flex; min-height: 100vh;">
    <!-- Sidebar trái -->
    <div class="left-side-menu">
        <jsp:include page="/view/sidebarParent.jsp"/>
    </div>

    <!-- Nội dung bên phải -->
    <div class="main-content">
        <h2>📜 Lịch sử thanh toán</h2>

        <table class="payment-table">
            <thead>
            <tr>
                <th>STT</th>
             
                <th>Số tiền (VND)</th>
                <th>Thời gian</th>
                <th>Mã giao dịch</th>
                <th>Trạng thái</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="history" items="${paymentHistories}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>                    
                    <td>${history.amount}</td>
                    <td>${history.paymentTime}</td>
                    <td>${history.transactionCode}</td>
                    <td>
                        <c:choose>
                            <c:when test="${history.status == 'Success'}">
                                <span style="color: green;">✅ Thành công</span>
                            </c:when>
                            <c:when test="${history.status == 'Failed'}">
                                <span style="color: red;">❌ Thất bại</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: orange;">⏳ Đang xử lý</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a href="${pageContext.request.contextPath}/paymoney" class="btn-pay" style="margin-top: 20px; display: inline-block;">
            ⬅ Quay lại thanh toán
        </a>
    </div>
</div>
</body>
</html>
