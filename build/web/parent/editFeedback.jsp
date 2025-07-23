<%-- 
    Document   : editFeedback.jsp
    Created on : Jul 23, 2025, 11:03:32 AM
    Author     : Vu Tuan Hai <HE176383>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Chỉnh sửa nhận xét</title>
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
                background-color: var(--background-color);
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
                display: block;
                margin-top: 15px;
                margin-bottom: 6px;
                font-weight: 600;
                color: var(--text-secondary);
            }

            textarea, input[type="number"] {
                width: 100%;
                padding: 10px;
                font-size: 1em;
                border: 1px solid var(--border-color);
                border-radius: 8px;
                box-sizing: border-box;
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

            @media (max-width: 768px) {
                .right-side {
                    padding: 20px;
                }

                .feedback-form-container {
                    padding: 20px;
                }
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/view/sidebarParent.jsp" />

            <div class="right-side">
                <div class="feedback-form-container">
                    <h2>Chỉnh sửa nhận xét</h2>

                    <!-- Feedback edit form -->
                    <form action="EditFeedbackServlet" method="post">
                        <input type="hidden" name="feedbackId" value="${feedback.feedbackId}">

                        <label for="fbContent">Nội dung nhận xét:</label>
                        <textarea name="fbContent" id="fbContent" rows="4" required>${feedback.fbContent}</textarea>

                        <label for="rating">Đánh giá (1-5):</label>
                        <input type="number" name="rating" id="rating" min="1" max="5" step="0.1" value="${feedback.rating}" required>

                        <input type="submit" value="Cập nhật nhận xét">
                    </form>
                </div>
            </div>
        </div>
    </body>

</html>
