<%-- 
    Document   : viewFeedback
    Created on : Jul 18, 2025, 4:52:51 PM
    Author     : Vu Tuan Hai <HE176383>
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Nhận xét giáo viên</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />

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
                overflow-y: auto;
            }

            .profile-card {
                background-color: var(--white-color);
                border-radius: 16px;
                box-shadow: var(--box-shadow);
                max-width: 1000px;
                margin: 0 auto;
                padding: 30px;
            }

            h1 {
                text-align: center;
                color: var(--primary-color);
                margin-bottom: 30px;
            }

            .feedback-container {
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                gap: 20px;
            }

            .feedback-card {
                background-color: var(--white-color);
                box-shadow: var(--box-shadow);
                border-radius: 10px;
                width: 300px;
                padding: 20px;
                transition: transform 0.3s;
                text-align: center;
            }

            .feedback-card:hover {
                transform: translateY(-10px);
            }

            .feedback-content {
                font-size: 1em;
                color: var(--text-color);
                margin-bottom: 12px;
            }

            .rating {
                font-size: 1.1em;
                font-weight: 600;
                color: #f39c12;
                margin-bottom: 10px;
            }

            .card-footer {
                font-size: 0.9em;
                color: var(--text-secondary);
            }

            .error-message {
                color: red;
                text-align: center;
                font-size: 18px;
                margin-top: 20px;
            }

            @media (max-width: 768px) {
                .feedback-card {
                    width: 100%;
                }

                .right-side {
                    padding: 20px;
                }

                .profile-card {
                    padding: 20px;
                }
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/view/sidebarTeacher.jsp" />

            <div class="right-side">
                <div class="profile-card">
                    <h1>Teacher Feedback</h1>

                    <c:if test="${not empty errorMessage}">
                        <div class="error-message">
                            <p>${errorMessage}</p>
                        </div>
                    </c:if>

                    <div class="feedback-container">
                        <c:if test="${not empty feedbackList}">
                            <c:forEach var="feedback" items="${feedbackList}">
                                <div class="feedback-card">
                                    <div class="feedback-content">
                                        ${feedback.fbContent}
                                    </div>
                                    <div class="rating">
                                        Rating: ${feedback.rating}
                                    </div>
                                    <div class="card-footer">
                                        <p>Received on: ${feedback.fbDate}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>

                    <c:if test="${empty feedbackList}">
                        <p class="error-message">No feedback available for this teacher.</p>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
