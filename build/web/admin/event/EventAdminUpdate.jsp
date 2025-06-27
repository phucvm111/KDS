<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/class/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/class/boot/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminSidebar.css">
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <title>Update Event</title> 
    </head>

    <body>
        <form action="updateevent?id=${event.eventId}" method="POST">
            <jsp:include page="/view/adminSidebar.jsp" />
            <div class="dashboard">
                <div class="form-title" style="text-align: center;font-size: 50px;">Update Event</div>
                <c:if test="${not empty message}">
                    <div class="alert alert-danger" role="alert" style="width: 80%; margin-left: 10%; margin-top: 10px;">
                        ${message}
                    </div>
                </c:if>

                <div class="form-content" style="width: 80%;height: auto; margin-left: 10%; padding-top: 0;padding-bottom: 0;">

                    <div class="mb-3">
                        <label for="eventId" class="form-label">Event ID</label>
                        <input type="text" class="form-control" id="eventId" value="${event.eventId}" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="event_name" class="form-label">Event Name</label>
                        <input type="text" class="form-control" id="event_name" name="event_name" value="${event.eventName}" required>
                    </div>

                    <div class="mb-3">
                        <label for="event_description" class="form-label">Event Description</label>
                        <textarea class="form-control" id="event_description" name="event_description" rows="3" required>${event.eventDescription}</textarea>
                    </div>

                    <div class="mb-3">
                        <label for="event_date" class="form-label">Event Date</label>
                        <input type="date" class="form-control" id="event_date" name="event_date" value="<fmt:formatDate value='${event.eventDate}' pattern='yyyy-MM-dd' />" required>
                    </div>

                    <div class="mb-3">
                        <label for="location" class="form-label">Location</label>
                        <input type="text" class="form-control" id="location" name="location" value="${event.location}" required>
                    </div>

                    <div class="d-grid gap-2 d-md-block" style="margin-top: 30px; margin-bottom: 30px;">
                        <input type="submit" class="btn btn-primary" value="Save Changes">
                        <a href="event" class="btn btn-secondary">Cancel</a> 
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>