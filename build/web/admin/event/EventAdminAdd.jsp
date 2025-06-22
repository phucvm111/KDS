<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%-- Các file CSS cho trang --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/class/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/class/boot/bootstrap.min.css">
        
        <%-- File CSS cho sidebar mới và thư viện icon --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminSidebar.css">
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <title>Add Event</title> 
    </head>

    <body>

        <form action="addevent" method="POST">
            
            <%-- BƯỚC 1: XÓA SIDEBAR CŨ --%>
            <%-- BƯỚC 2: THAY THẾ BẰNG DÒNG INCLUDE NÀY --%>
            <%-- Hãy chắc chắn đường dẫn "page" là chính xác tới file adminSidebar.jsp của bạn --%>
            <jsp:include page="/view/adminSidebar.jsp" />

            <%-- Phần nội dung chính của trang được giữ nguyên --%>
            <div class="dashboard">
               

                <div class="form-title" style="text-align: center; font-size: 40px;">Add Event</div>
                
                <div class="form-content" style="width: 80%; margin-left: 10%; padding-top: 0;">
                    <c:if test="${not empty requestScope.errorMessage}">
                        <div class="alert alert-danger" role="alert">
                            ${requestScope.errorMessage}
                        </div>
                    </c:if>

                    <div class="mb-3">
                        <label for="event_name" class="form-label">Event Name</label>
                        <input type="text" class="form-control" id="event_name" name="event_name" required value="${requestScope.oldEventName != null ? requestScope.oldEventName : ''}">
                    </div>

                    <div class="mb-3">
                        <label for="event_description" class="form-label">Event Description</label>
                        <textarea class="form-control" id="event_description" name="event_description" rows="3" required>${requestScope.oldEventDescription != null ? requestScope.oldEventDescription : ''}</textarea>
                    </div>

                    <div class="mb-3">
                        <label for="event_date" class="form-label">Event Date</label>
                        <input type="date" class="form-control" id="event_date" name="event_date" required value="${requestScope.oldEventDate != null ? requestScope.oldEventDate : ''}">
                    </div>

                    <div class="mb-3">
                        <label for="location" class="form-label">Event Location</label>
                        <input type="text" class="form-control" id="location" name="location" required value="${requestScope.oldLocation != null ? requestScope.oldLocation : ''}">
                    </div>

                    <div class="d-grid gap-2 d-md-block" style="margin-top: 30px; margin-bottom: 30px;">
                        <input type="submit" class="btn btn-primary" value="Add Event">
                        <a href="event" class="btn btn-secondary">Cancel</a> 
                    </div>

                </div>
            </div>
        </form>
    </body>
</html>