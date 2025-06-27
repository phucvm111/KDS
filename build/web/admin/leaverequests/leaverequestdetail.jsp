<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ATKD ChildCare - Chi Tiết Đơn Xin Nghỉ Phép</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/manageleaverequests.css">
    <%-- Thêm một file CSS riêng cho trang chi tiết nếu cần thiết, ví dụ: --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/leaverequests/css/leaverequestdetail.css">

</head>
<body>
    <div class="wrapper">
        

        <div class="right-side">
            <div class="container detail-container">
                <h2>Chi Tiết Đơn Xin Nghỉ Phép</h2>

                <c:if test="${not empty message}">
                    <div class="message ${messageType}">
                        <c:out value="${message}"/>
                    </div>
                </c:if>

                <c:choose>
                    <c:when test="${not empty leaveRequest}">
                        <div class="detail-grid">
                            <div class="detail-item">
                                <span class="label">ID Đơn:</span>
                                <span class="value">${leaveRequest.requestId}</span>
                            </div>
                            <div class="detail-item">
                                <span class="label">Người Gửi (ID):</span>
                                <span class="value">${leaveRequest.requesterId}</span>
                            </div>
                            <div class="detail-item">
                                <span class="label">Tên Giáo Viên:</span>
                                <span class="value">${leaveRequest.teacherName}</span>
                            </div>
                            <div class="detail-item">
                                <span class="label">Loại Nghỉ Phép:</span>
                                <span class="value">${leaveRequest.leaveType.typeName}</span>
                            </div>
                            <div class="detail-item">
                                <span class="label">Ngày Bắt Đầu:</span>
                                <span class="value">${leaveRequest.startDate}</span>
                            </div>
                            <div class="detail-item">
                                <span class="label">Ngày Kết Thúc:</span>
                                <span class="value">${leaveRequest.endDate}</span>
                            </div>
                            <div class="detail-item">
                                <span class="label">Tổng Ngày:</span>
                                <span class="value">${leaveRequest.totalDays}</span>
                            </div>
                            <div class="detail-item full-width">
                                <span class="label">Lý Do:</span>
                                <p class="value reason-text">${leaveRequest.reason}</p>
                            </div>
                            <div class="detail-item">
                                <span class="label">Ngày Gửi:</span>
                                <span class="value">${leaveRequest.requestDate}</span>
                            </div>
                            <div class="detail-item">
                                <span class="label">Trạng Thái:</span>
                                <span class="value status-${leaveRequest.status.toLowerCase()}">
                                    ${leaveRequest.status}
                                </span>
                            </div>
                        </div>

                        <div class="detail-actions">
                            <%-- Nút quay lại --%>
                            <a href="${pageContext.request.contextPath}/adminleaverequests" class="back-btn">Quay Lại</a>

                            <%-- Các nút Duyệt/Từ chối (nếu bạn muốn xử lý ở đây) --%>
                            <c:if test="${leaveRequest.status == 'Pending'}">
                                <form action="${pageContext.request.contextPath}/admin/leaverequestdetail" method="post" style="display:inline-block;">
                                    <input type="hidden" name="action" value="approve">
                                    <input type="hidden" name="requestId" value="${leaveRequest.requestId}">
                                    <button type="submit" class="approve-btn">Duyệt Đơn</button>
                                </form>
                                <form action="${pageContext.request.contextPath}/admin/leaverequestdetail" method="post" style="display:inline-block;">
                                    <input type="hidden" name="action" value="reject">
                                    <input type="hidden" name="requestId" value="${leaveRequest.requestId}">
                                    <button type="submit" class="reject-btn">Từ Chối Đơn</button>
                                </form>
                            </c:if>
                            <c:if test="${leaveRequest.status != 'Pending'}">
                                <span class="processed-message">Đơn đã được xử lý.</span>
                            </c:if>
                        </div>

                    </c:when>
                    <c:otherwise>
                        <p class="no-data-message">Không tìm thấy thông tin chi tiết đơn xin nghỉ phép.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>