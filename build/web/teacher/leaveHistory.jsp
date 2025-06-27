<%-- webapp/teacher/leavehistory.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
    <title>ATKD ChildCare - Lịch Sử Yêu Cầu Nghỉ Phép</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/teacher/css/leavehistory.css">

    <style>
        /* CSS cho thông báo (nếu có) - có thể chuyển vào CSS riêng sau */
        .message-box {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid;
            border-radius: 5px;
            text-align: center;
        }
        .success-message {
            color: green;
            border-color: green;
            background-color: #f0fff0;
        }
        .error-message {
            color: red;
            border-color: red;
            background-color: #fff0f0;
        }
    </style>
</head>
<body>
    <div class="wrapper">
        <jsp:include page="/view/sidebarTeacher.jsp" />    

        <div class="right-side">
            <div class="container">
                <h2>Lịch Sử Yêu Cầu Nghỉ Phép</h2>

                <c:if test="${not empty sessionScope.successMessage}">
                    <div class="message-box success-message">
                        <p>${sessionScope.successMessage}</p>
                    </div>
                    <c:remove var="successMessage" scope="session" />
                </c:if>
                <c:if test="${not empty requestScope.errorMessage}">
                    <div class="message-box error-message">
                        <p>${request.errorMessage}</p>
                    </div>
                </c:if>

                <div class="action-bar">
                    <a href="${pageContext.request.contextPath}/leaverequest" class="btn btn-primary">
                        <i class="fas fa-plus-circle"></i> Đơn Xin Nghỉ Phép Mới
                    </a>
                </div>

                <c:choose>
                    <c:when test="${not empty leaveRequests}">
                        <div class="table-responsive"> <%-- Thêm div responsive cho bảng --%>
                            <table>
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Ngày Gửi</th>
                                        <th>Ngày Bắt Đầu</th>
                                        <th>Ngày Kết Thúc</th>
                                        <th>Số Ngày Nghỉ</th>
                                        <th>Loại Nghỉ Phép</th>
                                        <th>Lý Do</th>
                                        <th>Trạng Thái</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="request" items="${leaveRequests}" varStatus="status">
                                        <tr>
                                            <td>${status.index + 1}</td>
                                            <td>${request.requestDate}</td>
                                            <td>${request.startDate}</td>
                                            <td>${request.endDate}</td>
                                            <td>${request.totalDays}</td>
                                            <td>${request.leaveTypeName}</td> 
                                            <td>${request.reason}</td>
                                            <td>
                                                <span class="status-${request.status.toLowerCase()}">
                                                    ${request.status}
                                                </span>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p class="no-data">Bạn chưa có yêu cầu nghỉ phép nào.</p>
                        <p class="no-data">Hãy click vào nút "Đơn Xin Nghỉ Phép Mới" để gửi yêu cầu đầu tiên của bạn!</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>