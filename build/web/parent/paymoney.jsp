<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Thanh toán học phí</title>
    
    <!-- CSS riêng cho trang thanh toán -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/stylepaymoney.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">

</head>
<body>
    <div style="display: flex; min-height: 100vh;">
        <!-- Sidebar trái -->
        <div class="left-side-menu">
            <jsp:include page="/view/sidebarParent.jsp" />
        </div>

        <!-- Nội dung bên phải -->
        <div class="main-content">
            <h2>💳 Thanh toán học phí</h2>
 <p style="color: red">Nếu bạn đã thanh toán rồi hoặc chưa có đơn nào thì sẽ không có mã đơn ở đây!</p>
            <table class="payment-table">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên bé</th>
                        <th>Số tiền (VND)</th>
                        <th>Hạn thanh toán</th>
                        <th>Trạng thái</th>
                        <th>Thanh toán</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="tf" items="${tuitionFrees}" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${tf.kinder.first_name} ${tf.kinder.last_name}</td>
                            <td>${tf.amount}</td>
                            <td>${tf.due_date}</td>
                            <td>${tf.status}</td>
                            <td>
                                <form action="pay" method="post">
                                    <input type="hidden" name="tuitionId" value="${tf.tuition_id}" />
                                    <button type="submit" class="btn-pay">🔔 Thanh toán</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
