<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
    <title>ATKD ChildCare - Đơn Xin Nghỉ Phép</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/teacher/css/leaverequestform.css">

</head>
<body>
    <div class="wrapper">
        <jsp:include page="/view/sidebarTeacher.jsp" />    

        <div class="right-side">
            <div class="container">
                <h2>Đơn Xin Nghỉ Phép</h2>

                <c:if test="${not empty message}">
                    <div class="message ${messageType}">
                        ${message}
                    </div>
                </c:if>

                <form action="leaverequest" method="post">
                    <div class="form-group">
                        <label for="startDate">Ngày bắt đầu nghỉ: <span class="required">(*)</span></label>
                        <input type="date" id="startDate" name="startDate" required value="${startDate}">
                    </div>

                    <div class="form-group">
                        <label for="endDate">Ngày kết thúc nghỉ: <span class="required">(*)</span></label>
                        <input type="date" id="endDate" name="endDate" required value="${endDate}">
                    </div>

                    <div class="form-group">
                        <label for="reason">Lý do nghỉ phép: <span class="required">(*)</span></label>
                        <textarea id="reason" name="reason" rows="5" required>${reason}</textarea>
                    </div>

                    <div class="form-group">
                        <label for="leaveTypeId">Loại nghỉ phép: <span class="required">(*)</span></label>
                        <select id="leaveTypeId" name="leaveTypeId">
                            <c:forEach var="type" items="${leaveTypes}">
                                <option value="${type.leaveTypeId}" ${type.leaveTypeId == leaveTypeId ? 'selected' : ''}>
                                    ${type.typeName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="button-group" style="text-align: right; margin-top: 20px;">
                        <button type="submit" class="btn btn-primary" style="margin-right: 10px;">Gửi Đơn</button>
                        <button type="button" class="btn btn-danger" onclick="window.location.href='${pageContext.request.contextPath}/leavehistory';">Hủy</button>
                    </div>
                    
                </form>
            </div>
        </div>
    </div>
</body>
</html>