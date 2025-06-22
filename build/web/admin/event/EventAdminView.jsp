<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Admin - Event Details</title>
        
        <%-- Các file CSS cho trang --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/event/boot/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/event/css/style.css"> 

        <%-- File CSS cho sidebar mới và thư viện icon --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminSidebar.css">
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
        
        <style>
            /* Các CSS riêng cho trang này có thể giữ lại hoặc chuyển vào file style.css chung */
            .event-details-card {
                background-color: #ffffff; 
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
                max-width: 800px;
                margin: 30px auto; /* Căn thẻ card ra giữa */
            }
            .event-details-card h2 {
                color: #007bff;
                margin-bottom: 25px;
                text-align: center;
                font-weight: 600;
            }
            .event-details-card p {
                margin-bottom: 15px;
                font-size: 16px;
                line-height: 1.7;
            }
            .event-details-card p strong {
                display: inline-block;
                width: 120px;
                color: #707070;
            }
            .back-button-container {
                text-align: center;
                margin-top: 30px;
            }
            .back-button {
                background-color: orange;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                text-decoration: none;
                display: inline-block;
            }
            .back-button:hover {
                opacity: 0.9;
            }
        </style>
    </head>
    <body>
        
        <jsp:include page="/view/adminSidebar.jsp" />

        <%-- Phần nội dung chính của trang --%>
        <div class="dashboard">
            

            <div class="dashboard-content">
                <c:if test="${not empty requestScope.event}">
                    <div class="event-details-card">
                        <h2>Event Details</h2>
                        <p><strong>Event ID:</strong> ${requestScope.event.eventId}</p>
                        <p><strong>Title:</strong> ${requestScope.event.eventName}</p>
                        <p><strong>Description:</strong> ${requestScope.event.eventDescription}</p>
                        <p><strong>Date:</strong> <fmt:formatDate value="${requestScope.event.eventDate}" pattern="yyyy-MM-dd" /></p>
                        <p><strong>Location:</strong> ${requestScope.event.location}</p>

                        <div class="back-button-container">
                            <a href="event" class="back-button">Back to List</a>
                        </div>
                    </div>
                </c:if>

                <c:if test="${empty requestScope.event}">
                    <div class="alert alert-warning" role="alert">
                        No event details found.
                        <div class="back-button-container">
                            <a href="event" class="back-button">Back to List</a>
                        </div>
                    </div>
                </c:if>
                
                <c:if test="${not empty requestScope.message}">
                    <div class="alert alert-danger" role="alert" style="margin-top: 20px;">
                        ${requestScope.message}
                        <div class="back-button-container">
                            <a href="event" class="back-button">Back to List</a>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>