<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
    <title>ATKD ChildCare - Parent Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/parentprofile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">
</head>

<body>
    <div class="wrapper">
        <%-- Include the sidebar here --%>
        <jsp:include page="/view/sidebarParent.jsp" /> 

        <div class="right-side">
            <div class="profile-card">
                <div class="profile-header">
                    <div class="img-section">
                        <img src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" alt="Profile Picture">
                    </div>
                    <div class="personal-section">
                        <h1>${sessionScope.account.firstName} ${sessionScope.account.lastName}</h1>
                        <p>Date of birth: ${sessionScope.account.dob}</p>
                    </div>
                </div>

                <div class="profile-content">
                    <div class="profile-grid">
                        <div class="content-item">
                            <div class="item-title">First Name</div>
                            <p class="parent-info">${sessionScope.account.firstName}</p>
                        </div>

                        <div class="content-item">
                            <div class="item-title">Last Name</div>
                            <p class="parent-info">${sessionScope.account.lastName}</p>
                        </div>
                        
                        <div class="content-item">
                            <div class="item-title">Gender</div>
                            <p class="parent-info">
                                <c:if test="${sessionScope.account.gender == true}">Male</c:if>
                                <c:if test="${sessionScope.account.gender == false}">Female</c:if>
                            </p>
                        </div>

                        <div class="content-item">
                            <div class="item-title">Phone</div>
                            <p class="parent-info">${sessionScope.account.phoneNumber}</p>
                        </div>
                        
                        <div class="content-item full-width">
                            <div class="item-title">Email</div>
                            <p class="parent-info">${sessionScope.account.email}</p>
                        </div>

                        <div class="content-item full-width">
                            <div class="item-title">Address</div>
                            <p class="parent-info">${sessionScope.account.address}</p>
                        </div>
                    </div>
                    
                    <div class="profile-actions">
                        <a href="${pageContext.request.contextPath}/parent/parentupdateprofile.jsp">
                            <input type="button" class="update-button" value="Update Profile"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</body>
</html>