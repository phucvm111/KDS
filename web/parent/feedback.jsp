<%-- 
    Document   : feedback
    Created on : Jul 23, 2025, 10:37:15 AM
    Author     : Vu Tuan Hai <HE176383>
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Feedback for Teachers</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">

        <style>
            :root {
                --primary-color: #6a5af9;
                --secondary-color: #00bcd4;
                --text-color: #333;
                --text-secondary: #6c757d;
                --background-color: #f8f9fa;
                --white-color: #fff;
                --border-color: #e0e0e0;
                --box-shadow: 0 4px 15px rgba(0, 0, 0, 0.07);
            }

            body {
                font-family: 'Poppins', sans-serif;
                margin: 0;
                background: var(--background-color);
            }

            .wrapper {
                display: flex;
                min-height: 100vh;
            }

            .right-side {
                flex-grow: 1;
                padding: 40px;
            }

            .feedback-form-container {
                background-color: var(--white-color);
                border-radius: 16px;
                box-shadow: var(--box-shadow);
                max-width: 800px;
                margin: 0 auto;
                padding: 30px;
            }

            h2 {
                text-align: center;
                color: var(--primary-color);
                margin-bottom: 25px;
            }

            label {
                font-weight: 600;
                color: var(--text-secondary);
                display: block;
                margin-top: 15px;
                margin-bottom: 6px;
            }

            select, textarea, input[type="number"] {
                width: 100%;
                padding: 10px;
                border-radius: 8px;
                border: 1px solid var(--border-color);
                box-sizing: border-box;
                font-size: 1em;
            }

            textarea {
                resize: vertical;
            }

            input[type="submit"] {
                margin-top: 20px;
                padding: 12px 20px;
                background-color: var(--primary-color);
                color: white;
                font-size: 1em;
                font-weight: 600;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            input[type="submit"]:hover {
                background-color: #5548d9;
            }

            .success-message {
                color: green;
                font-weight: 600;
                text-align: center;
                margin-bottom: 20px;
            }

            .error-message {
                color: red;
                font-weight: 600;
                text-align: center;
                margin-bottom: 20px;
            }

            @media (max-width: 768px) {
                .right-side {
                    padding: 20px;
                }

                .feedback-form-container {
                    padding: 20px;
                }
            }
            .feedback-container {
                background-color: var(--white-color);
                border-radius: 12px;
                box-shadow: var(--box-shadow);
                padding: 20px;
                margin-top: 30px;
                margin-bottom: 20px;
                transition: transform 0.3s ease;
            }

            .feedback-container:hover {
                transform: translateY(-5px);
            }

            .feedback-container h3 {
                margin: 0 0 12px;
                color: var(--primary-color);
                font-size: 1.1em;
            }

            .feedback-container p {
                margin: 6px 0;
                color: var(--text-color);
            }

            .feedback-container strong {
                color: var(--text-secondary);
            }

            .feedback-container a {
                display: inline-block;
                margin-right: 15px;
                margin-top: 12px;
                color: var(--primary-color);
                font-weight: 600;
                text-decoration: none;
                transition: color 0.2s ease;
            }

            .feedback-container a:hover {
                text-decoration: underline;
                color: #4b3bd2;
            }

        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/view/sidebarParent.jsp" />

            <div class="right-side">
                <div class="feedback-form-container">
                    <h2>Gửi nhận xét cho giáo viên</h2>

                    <!-- Display success or error messages -->
                    <c:if test="${not empty message}">
                        <div class="success-message">${message}</div>
                    </c:if>

                    <c:if test="${not empty error}">
                        <div class="error-message">${error}</div>
                    </c:if>

                    <form action="FeedbackTeacherServlet" method="post">
                        <input type="hidden" name="parentId" value="${account.accountID}">

                        <label for="className">Chọn lớp:</label>
                        <select name="className" id="className">
                            <c:forEach var="className" items="${classList}">
                                <option value="${className}">${className}</option>
                            </c:forEach>
                        </select>

                        <label for="teacher">Chọn giáo viên:</label>
                        <select name="teacherId" id="teacher">
                            <c:forEach var="teacher" items="${teachers}">
                                <option value="${teacher.accountID}">${teacher.firstName} ${teacher.lastName}</option>
                            </c:forEach>
                        </select>

                        <label for="fbContent">Nội dung nhận xét:</label>
                        <textarea name="fbContent" id="fbContent" rows="4" required></textarea>

                        <label for="rating">Đánh giá (1-5):</label>
                        <input type="number" name="rating" min="1" max="5" step="0.1" required>

                        <input type="submit" value="Gửi nhận xét">
                    </form>

                    <!-- Display existing feedback for each teacher -->
                    <c:forEach var="feedback" items="${allFeedback}">
                        <div class="feedback-container">
                            <h3>${feedback.teacherId} - Feedback by Parent ID: ${feedback.parentId}</h3>
                            <p><strong>Feedback:</strong> ${feedback.fbContent}</p>
                            <p><strong>Rating:</strong> ${feedback.rating}</p>
                            <p><strong>Date:</strong> ${feedback.fbDate}</p>

                            <!-- Edit and Delete options -->
                            <a href="EditFeedbackServlet?feedbackId=${feedback.feedbackId}">Edit</a>
                            <a href="FeedbackTeacherServlet?feedbackId=${feedback.feedbackId}&action=delete">Delete</a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
