<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png" />
        <title>ATKD ChildCare - Children Information</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/childdetails.css" />
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet" />
        <script src="https://kit.fontawesome.com/67b5c45612.js" crossorigin="anonymous"></script>
        <style>
            :root {
                --primary-color: #6a5af9;
                --secondary-color: #00bcd4;
                --background-color: #f8f9fa;
                --white-color: #fff;
                --box-shadow: 0 4px 15px rgba(0, 0, 0, 0.07);
            }

            .right-side {
                flex-grow: 1;
                padding: 40px;
                overflow-y: auto;
            }

            .children-list {
                background: var(--white-color);
                border-radius: 16px;
                box-shadow: var(--box-shadow);
                padding: 40px;
            }

            .page-title {
                text-align: center;
                font-size: 32px;
                font-weight: 600;
                margin-bottom: 40px;
                color: #333;
                position: relative;
            }

            .page-title::after {
                content: "";
                position: absolute;
                bottom: -10px;
                left: 50%;
                transform: translateX(-50%);
                width: 70px;
                height: 4px;
                background: linear-gradient(90deg, #FF9671, #FF6F91);
                border-radius: 2px;
            }

            .children-grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
                gap: 30px;
            }

            .child-card {
                background: var(--white-color);
                border-radius: 16px;
                box-shadow: var(--box-shadow);
                overflow: hidden;
                position: relative;
                transition: transform 0.3s;
            }

            .child-card:hover {
                transform: translateY(-5px);
            }

            .card-header {
                background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
                height: 120px;
                position: relative;
            }

            .gender-icon {
                position: absolute;
                top: 15px;
                right: 15px;
                width: 30px;
                height: 30px;
                background: rgba(255, 255, 255, 0.8);
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .male-icon {
                color: #71B7E6;
            }

            .female-icon {
                color: #FF6F91;
            }

            .child-image-container {
                position: absolute;
                bottom: -50px;
                left: 50%;
                transform: translateX(-50%);
                width: 100px;
                height: 100px;
                border-radius: 50%;
                background: #fff;
                padding: 5px;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            }

            .child-image {
                width: 100%;
                height: 100%;
                border-radius: 50%;
                object-fit: cover;
            }

            .card-body {
                padding: 60px 20px 20px;
                text-align: center;
            }

            .child-name {
                font-size: 22px;
                font-weight: 600;
                color: #333;
                margin-bottom: 15px;
            }

            .child-details-grid {
                display: grid;
                grid-template-columns: auto auto;
                gap: 10px;
                text-align: left;
                margin-bottom: 20px;
            }

            .detail-label {
                font-weight: 500;
                color: #666;
            }

            .detail-value {
                color: #333;
            }

            .view-button {
                background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
                color: #fff;
                border: none;
                padding: 12px 25px;
                border-radius: 30px;
                font-size: 14px;
                cursor: pointer;
                text-decoration: none;
                display: inline-block;
                transition: all 0.3s;
            }

            .view-button:hover {
                opacity: 0.9;
            }

            .no-children {
                text-align: center;
                padding: 60px 20px;
            }

            .no-children p {
                font-size: 18px;
                margin: 10px 0;
            }

            .empty-state-icon {
                font-size: 60px;
                color: #ccc;
                margin-bottom: 20px;
            }
        </style>
    </head>

    <body>
        <div class="wrapper">
            <jsp:include page="/view/sidebarParent.jsp" />
            <div class="right-side">
                <div class="children-list">
                    <h1 class="page-title">Your Children Information</h1>

                    <c:if test="${empty kindergartners}">
                        <div class="no-children">
                            <div class="empty-state-icon">
                                <i class="fas fa-child"></i>
                            </div>
                            <p>You don't have any registered children yet.</p>
                            <p>Go to <a href="${pageContext.request.contextPath}/childregister">Child Register</a> to register a child.</p>
                        </div>
                    </c:if>

                    <c:if test="${not empty kindergartners}">
                        <div class="children-grid">
                            <c:forEach items="${kindergartners}" var="child">
                                <div class="child-card">
                                    <div class="card-header">
                                        <c:choose>
                                            <c:when test="${child.gender == true}">
                                                <div class="gender-icon male-icon"><i class="fas fa-mars"></i></div>
                                                </c:when>
                                                <c:otherwise>
                                                <div class="gender-icon female-icon"><i class="fas fa-venus"></i></div>
                                                </c:otherwise>
                                            </c:choose>
                                        <div class="child-image-container">
                                            <c:choose>
                                                <c:when test="${not empty child.img}">
                                                    <img src="${pageContext.request.contextPath}/uploads/${child.img}" alt="Child Photo" class="child-image">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" alt="Default Child Photo" class="child-image">
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>

                                    <div class="card-body">
                                        <div class="child-name">${child.first_name} ${child.last_name}</div>
                                        <div class="child-details-grid">
                                            <div class="detail-label">Date of Birth:</div>
                                            <div class="detail-value">${child.dob}</div>
                                            <div class="detail-label">Gender:</div>
                                            <div class="detail-value">
                                                <c:choose>
                                                    <c:when test="${child.gender == true}">Male</c:when>
                                                    <c:otherwise>Female</c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="detail-label">Class:</div>
                                            <div class="detail-value">${child.className}</div>
                                            <div class="detail-label">Address:</div>
                                            <div class="detail-value">${child.address}</div>
                                        </div>
                                        <a href="${pageContext.request.contextPath}/childrendetail?id=${child.kinder_id}" class="view-button">
                                            <i class="fas fa-eye"></i> View Details
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
