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
    </body>

</html>
