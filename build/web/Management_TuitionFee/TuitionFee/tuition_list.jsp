<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Danh sách học phí</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Management_TuitionFee/TuitionFee/css/tuition_style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminSidebar.css">
    </head>
    <body>
        <div class="page-container">
            <div class="sidebar">
                <jsp:include page="/view/adminSidebar.jsp" />
            </div>

            <div class="content">
                <h2>Danh sách học phí</h2>

                <!-- Form thêm học phí -->
                <h3>➕ Thêm học phí mới</h3>
                <p id="error-msg" style="color:red; font-weight: bold;"></p>

                <form action="${pageContext.request.contextPath}/add_tuition" method="post" class="add-form">
                    <label>Bé:</label>
                    <select name="kinderId">
                        <c:forEach var="k" items="${kinders}">
                            <option value="${k.kinder_id}">${k.first_name} ${k.last_name}</option>
                        </c:forEach>
                    </select>

                    <label>Số tiền (VND):</label>
                    <input type="number" name="amount" required />

                    <label>Hạn thanh toán:</label>
                    <input type="date" name="dueDate" required />

                    <label>Trạng thái:</label>
                    <select name="status">
                        <option value="Chưa nộp">Chưa nộp</option>
                        <option value="Đã nộp">Đã nộp</option>
                       
                    </select>

                    <button type="submit">💾 Lưu học phí</button>
                </form>

               


                    <table class="tuition-table">

                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên bé</th>
                                <th>Tên phụ huynh</th>
                                <th>Số điện thoại</th>
                                <th>Số tiền (VND)</th>
                                <th>Hạn thanh toán</th>
                                <th>Trạng thái</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="tf" items="${tutitionfrees}" varStatus="status">
                                <c:set var="acc" value="${accounts[status.index]}" />
                                <tr>
                                    <td>${tf.tuition_id}</td>
                                    <td>${tf.kinder.first_name} ${tf.kinder.last_name}</td>
                                    <td>${acc.firstName} ${acc.lastName} </td> 
                                    <td>${acc.phoneNumber}</td>
                                    <td>${tf.amount}</td>
                                    <td>${tf.due_date}</td>
                                    <td>${tf.status}</td>
                                    <td>
                                        <form action="remind" method="post">
                                            <input type="hidden" name="tuitionId" value="" />
                                            <button class="remind-btn" type="submit">🔔 Nhắc nhở</button>
                                        </form>
                                    </td>
                                </tr>          
                            </c:forEach>
                        </tbody>
                    </table>
            </div>
        </div>
                    
                    <script>
    const form = document.querySelector('.add-form');
    const errorMsg = document.getElementById('error-msg');

    form.addEventListener('submit', function (event) {
        const amount = document.querySelector('input[name="amount"]').value;
        const dueDate = document.querySelector('input[name="dueDate"]').value;

        // Lấy ngày hôm nay dưới dạng yyyy-MM-dd
        const today = new Date().toISOString().split('T')[0];

        // Kiểm tra số tiền
        if (amount < 1000) {
            errorMsg.textContent = "⚠️ Số tiền phải từ 1,000 VND trở lên.";
            event.preventDefault(); // Ngăn không gửi form
            return;
        }

        if (amount.length > 10) {
            errorMsg.textContent = "⚠️ Số tiền không được vượt quá 10 chữ số.";
            event.preventDefault();
            return;
        }

        // Kiểm tra hạn thanh toán
        if (dueDate < today) {
            errorMsg.textContent = "⚠️ Hạn thanh toán không được nhỏ hơn hôm nay.";
            event.preventDefault();
            return;
        }

        // Nếu mọi thứ hợp lệ
        errorMsg.textContent = ""; // Xóa lỗi nếu có
    });
</script>

    </body>

</html>
