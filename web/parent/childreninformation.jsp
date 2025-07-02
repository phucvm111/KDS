<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
    <title>ATKD ChildCare - Children Information</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/parenthome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/childprofile.css">
    <script src="https://kit.fontawesome.com/67b5c45612.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/childdetails.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <style>
        .children-list {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
        }
        
        .page-title {
            text-align: center;
            margin-bottom: 40px;
            font-size: 32px;
            color: #333;
            position: relative;
            font-weight: 600;
        }
        
        .page-title:after {
            content: '';
            position: absolute;
            width: 70px;
            height: 4px;
            background: linear-gradient(90deg, #FF9671, #FF6F91);
            bottom: -10px;
            left: 50%;
            transform: translateX(-50%);
            border-radius: 2px;
        }
        
        .children-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(340px, 1fr));
            gap: 25px;
            margin-top: 30px;
        }
        
        .child-card {
            background: white;
            border-radius: 16px;
            overflow: hidden;
            box-shadow: 0 10px 20px rgba(0,0,0,0.05);
            transition: all 0.3s ease;
            position: relative;
        }
        
        .child-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 30px rgba(0,0,0,0.1);
        }
        
        .card-header {
            background: linear-gradient(135deg, #71B7E6, #9B59B6);
            height: 120px;
            position: relative;
        }
        
        .child-image-container {
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
            bottom: -50px;
            width: 100px;
            height: 100px;
            border-radius: 50%;
            background: white;
            padding: 5px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
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
            background: linear-gradient(135deg, #71B7E6, #9B59B6);
            color: white;
            border: none;
            padding: 12px 25px;
            border-radius: 30px;
            font-size: 14px;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.3s ease;
            display: inline-block;
            text-decoration: none;
            box-shadow: 0 5px 15px rgba(155, 89, 182, 0.2);
        }
        
        .view-button:hover {
            background: linear-gradient(135deg, #9B59B6, #71B7E6);
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(155, 89, 182, 0.3);
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
        
        .no-children {
            text-align: center;
            padding: 80px 20px;
            background: white;
            border-radius: 16px;
            box-shadow: 0 10px 20px rgba(0,0,0,0.05);
        }
        
        .no-children p {
            color: #666;
            font-size: 18px;
            margin: 10px 0;
        }
        
        .no-children a {
            color: #9B59B6;
            text-decoration: none;
            font-weight: 500;
        }
        
        .no-children a:hover {
            text-decoration: underline;
        }
        
        .empty-state-icon {
            font-size: 60px;
            color: #ccc;
            margin-bottom: 20px;
        }
        
        /* Animations */
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        
        .child-card {
            animation: fadeIn 0.5s ease-out forwards;
        }
        
        .child-card:nth-child(2) {
            animation-delay: 0.1s;
        }
        
        .child-card:nth-child(3) {
            animation-delay: 0.2s;
        }
        
        .child-card:nth-child(4) {
            animation-delay: 0.3s;
        }
    </style>
</head>

<body>
    <div class="wrapper">
        <div class="home">
            <div class="left-side-menu">
                <div class="vertical-menu">
                    <div class="user-welcome">
                        <img class="user-img" src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" style="width: 80px; height: 80px;" alt="">
                        <p>${sessionScope.account.firstName} ${sessionScope.account.lastName}</p>
                    </div>
                    <div class="menu-item-container">
                        <ul class="item-lists">
                            <li class="menu-item current1">
                                <a href="${pageContext.request.contextPath}/childrenlist">Child Information</a>
                            </li>
                            <li class="menu-item">
                                <a href="${pageContext.request.contextPath}/parent/parentprofile.jsp">Parent Information</a>
                            </li>
                            <li class="menu-item">
                                <a href="${pageContext.request.contextPath}/childregister">Child Register</a>
                            </li>
                            <li class="menu-item">
                                <a href="${pageContext.request.contextPath}/changepassword">Change Password</a>
                            </li>
                            <li class="menu-item">
                                <a href="${pageContext.request.contextPath}/viewmeetings">View Meetings</a>
                            </li>
                        </ul>
                    </div>
                    <div style="border-top: 3px solid gray;"></div>
                    <div style="position: absolute;margin-top: 1vh; margin-left: 40px">
                        <input type="button" class="log-out_button" onclick="window.location.replace('${pageContext.request.contextPath}/homepage/events')" value="Log out"/>
                    </div>
                </div>
            </div>
            
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
                                    <!-- Sử dụng c:choose để xác định giới tính từ giá trị boolean -->
                                    <c:choose>
                                        <c:when test="${child.gender == true}">
                                            <div class="gender-icon male-icon">
                                                <i class="fas fa-mars"></i>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="gender-icon female-icon">
                                                <i class="fas fa-venus"></i>
                                            </div>
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


