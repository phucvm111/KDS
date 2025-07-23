<%-- 
    Document   : viewFeedbackForParent.jsp
    Created on : Jul 23, 2025, 11:51:50 AM
    Author     : Vu Tuan Hai <HE176383>
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Parent's Feedback</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">

        <style>
            :root {
                --primary-color: #6a5af9;
                --text-color: #333;
                --text-secondary: #6c757d;
                --background-color: #f8f9fa;
                --white-color: #fff;
                --border-color: #e0e0e0;
                --box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
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

            h1 {
                text-align: center;
                color: var(--primary-color);
                margin-bottom: 30px;
            }

            .table-container {
                background-color: var(--white-color);
                border-radius: 16px;
                box-shadow: var(--box-shadow);
                padding: 25px;
                max-width: 1000px;
                margin: 0 auto;
                overflow-x: auto;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                font-size: 0.95em;
            }

            thead {
                background-color: var(--primary-color);
                color: white;
            }

            th, td {
                padding: 12px 16px;
                text-align: left;
                border-bottom: 1px solid var(--border-color);
            }

            tbody tr:nth-child(even) {
                background-color: #f1f1f1;
            }

            tbody tr:hover {
                background-color: #e8f0fe;
            }

            @media (max-width: 768px) {
                .right-side {
                    padding: 20px;
                }

                th, td {
                    padding: 10px;
                    font-size: 0.9em;
                }
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/view/sidebarParent.jsp" />

            <div class="right-side">
                <h1>Nhận xét từ giáo viên dành cho con bạn</h1>

                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Mã</th>
                                <th>Tên học sinh</th>
                                <th>Giáo viên</th>
                                <th>Nội dung</th>
                                <th>Đánh giá</th>
                                <th>Ngày</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="feedback" items="${feedbackList}">
                                <tr>
                                    <td>${feedback.feedback_id}</td>
                                    <td>${feedback.kid_name}</td>
                                    <td>${feedback.teacher_name}</td>
                                    <td>${feedback.fb_content}</td>
                                    <td>
                                        <c:forEach begin="1" end="${feedback.rating}">
                                            <i class="fa fa-star" style="color: #f39c12;"></i>
                                        </c:forEach>
                                    </td>
                                    <td>${feedback.fb_date}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
