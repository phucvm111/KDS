<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Edit Notification</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/event/boot/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/event/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminSidebar.css">
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
    </head>
    <body>
        <jsp:include page="/view/adminSidebar.jsp" />

        <div class="dashboard">
            <div class="dashboard-content">
                <h2>Edit Notification</h2>

                <c:if test="${not empty requestScope.message}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${requestScope.message}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <form action="notification?action=update" method="post" style="max-width:600px;margin-top:20px;">
                    <input type="hidden" name="id" value="${notification.notificationId}" />

                    <div class="mb-3">
                        <label for="title" class="form-label">Title<span style="color:red">*</span></label>
                        <input type="text" class="form-control" name="title" id="title"
                               value="${notification.title}" required>
                    </div>

                    <div class="mb-3">
                        <label for="message" class="form-label">Message<span style="color:red">*</span></label>
                        <textarea class="form-control" name="message" id="message" rows="4" required>${notification.message}</textarea>
                    </div>

                    <div class="mb-3">
                        <label for="targetRole" class="form-label">Target Role<span style="color:red">*</span></label>
                        <select class="form-select" name="targetRole" id="targetRole" required>
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.roleID}"
                                        ${role.roleID == notification.targetRole.roleID ? 'selected' : ''}>
                                    ${role.roleName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mt-4">
                        <button type="submit" class="btn btn-primary">Update Notification</button>
                        <a href="notification" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
