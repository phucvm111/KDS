<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ATKD ChildCare - Quản Lý Đơn Xin Nghỉ Phép (Admin)</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminSidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/leaverequests/css/manageleaverequests.css">

</head>
<body>
    <div class="wrapper">
        <jsp:include page="/view/adminSidebar.jsp" />

        <div class="right-side">
            <div class="container">
                <h2>Quản Lý Đơn Xin Nghỉ Phép</h2>

                <c:if test="${not empty message}">
                    <div class="message ${messageType}">
                        <c:out value="${message}"/>
                    </div>
                </c:if>

                <table>
                    <thead>
                        <tr>
                            <th>Tên Giáo Viên</th>
                            <%-- ĐÃ XÓA CỘT "Loại Nghỉ Phép" --%>
                            <th>Ngày Bắt Đầu</th>
                            <th>Ngày Kết Thúc</th>
                            <%-- ĐÃ XÓA CỘT "Tổng Ngày" --%>
                            <th>Lý Do</th>
                            <th>Ngày Gửi</th>
                            <%-- ĐÃ XÓA CỘT "Trạng Thái" --%>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty leaveRequests}">
                                <c:forEach var="requestItem" items="${leaveRequests}">
                                    <tr>
                                        <td><c:out value="${requestItem.teacherName != null ? requestItem.teacherName : 'N/A'}"/></td>
                                        <%-- ĐÃ XÓA DỮ LIỆU "Loại Nghỉ Phép" --%>
                                        <td><c:out value="${requestItem.startDate}"/></td>
                                        <td><c:out value="${requestItem.endDate}"/></td>
                                        <%-- ĐÃ XÓA DỮ LIỆU "Tổng Ngày" --%>
                                        <td><c:out value="${requestItem.reason}"/></td>
                                        <td><c:out value="${requestItem.requestDate}"/></td>
                                        <%-- ĐÃ XÓA DỮ LIỆU "Trạng Thái" --%>
                                        <td class="actions">
                                            <%-- Nút Xem Chi Tiết --%>
                                            <a href="${pageContext.request.contextPath}/leaverequestdetail?id=${requestItem.requestId}" class="detail-btn">Xem</a>
                                            
                                            <%-- Sẽ kiểm tra trạng thái trong phần Xem chi tiết hoặc vẫn giữ ở đây --%>
                                            <%-- Nếu bạn muốn chỉ xử lý Duyệt/Từ chối sau khi xem chi tiết,
                                                 hãy di chuyển các form này vào trang leaverequestdetail.jsp --%>
                                            <c:if test="${requestItem.status == 'Pending'}">
                                                <form action="${pageContext.request.contextPath}/adminleaverequests" method="post" style="display:inline;">
                                                    <input type="hidden" name="action" value="approve">
                                                    <input type="hidden" name="requestId" value="${requestItem.requestId}">
                                                    <button type="submit" class="approve-btn">Duyệt</button>
                                                </form>
                                                <form action="${pageContext.request.contextPath}/adminleaverequests" method="post" style="display:inline;">
                                                    <input type="hidden" name="action" value="reject">
                                                    <input type="hidden" name="requestId" value="${requestItem.requestId}">
                                                    <button type="submit" class="reject-btn">Từ Chối</button>
                                                </form>
                                            </c:if>
                                            <c:if test="${requestItem.status != 'Pending'}">
                                                Đã xử lý
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="6" class="no-data-message">Không có yêu cầu nghỉ phép nào.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>