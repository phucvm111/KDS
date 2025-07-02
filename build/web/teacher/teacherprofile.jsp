<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png" />
    <title>KDS - Teacher Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css" />

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
            overflow: hidden;
            max-width: 900px;
            margin: 0 auto;
        }

        .profile-header {
            display: flex;
            align-items: center;
            padding: 30px;
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
            color: var(--white-color);
        }

        .profile-header img {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            object-fit: cover;
            border: 4px solid var(--white-color);
            margin-right: 30px;
            box-shadow: var(--box-shadow);
        }

        .profile-header h1 {
            margin: 0;
            font-size: 2em;
        }

        .profile-header p {
            margin: 8px 0 0;
            font-size: 0.95em;
        }

        .profile-content {
            padding: 30px;
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 25px;
        }

        .content-item {
            display: flex;
            flex-direction: column;
        }

        .item-title {
            font-size: 0.9em;
            font-weight: 600;
            color: var(--text-secondary);
            margin-bottom: 8px;
        }

        .parent-infor {
            font-size: 1em;
            color: var(--text-color);
        }

        .profile-actions {
            grid-column: 1 / -1;
            text-align: center;
            margin-top: 20px;
        }

        .button {
            padding: 12px 30px;
            font-size: 1em;
            font-weight: 600;
            color: var(--white-color);
            background-color: var(--primary-color);
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
        }

        .button:hover {
            background-color: #5548d9;
            transform: translateY(-2px);
        }

        @media (max-width: 768px) {
            .profile-content {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>

<body>
    <div class="wrapper">
        <jsp:include page="/view/sidebarTeacher.jsp" />

        <div class="right-side">
            <div class="profile-card">
                <div class="profile-header">
                    <img src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" alt="Teacher Avatar" />
                    <div>
                        <h1>${sessionScope.account.firstName} ${sessionScope.account.lastName}</h1>
                        <p>${sessionScope.account.dob}</p>
                    </div>
                </div>

                <div class="profile-content">
                    <div class="content-item">
                        <div class="item-title">First Name</div>
                        <p class="parent-infor">${sessionScope.account.firstName}</p>
                    </div>

                    <div class="content-item">
                        <div class="item-title">Last Name</div>
                        <p class="parent-infor">${sessionScope.account.lastName}</p>
                    </div>

                    <div class="content-item">
                        <div class="item-title">Date of Birth</div>
                        <p class="parent-infor">${sessionScope.account.dob}</p>
                    </div>

                    <div class="content-item">
                        <div class="item-title">Gender</div>
                        <p class="parent-infor">
                            <c:choose>
                                <c:when test="${sessionScope.account.gender}">Male</c:when>
                                <c:otherwise>Female</c:otherwise>
                            </c:choose>
                        </p>
                    </div>

                    <div class="content-item">
                        <div class="item-title">Phone</div>
                        <p class="parent-infor">${sessionScope.account.phoneNumber}</p>
                    </div>

                    <div class="content-item">
                        <div class="item-title">Address</div>
                        <p class="parent-infor">${sessionScope.account.address}</p>
                    </div>

                    <div class="content-item">
                        <div class="item-title">Email</div>
                        <p class="parent-infor">${sessionScope.account.email}</p>
                    </div>

                    <div class="profile-actions">
                        <a href="${pageContext.request.contextPath}/updateTeacher">
                            <input type="button" class="button" value="Update Profile" />
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
