<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
    <title>ATKD ChildCare - Hồ sơ Phụ huynh</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/parentprofile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">
</head>

<body>
    <div class="wrapper">
        <jsp:include page="/view/sidebarParent.jsp" /> 

        <div class="right-side">
            
            <c:if test="${not empty requestScope.errorMessage}">
                <div class="error-message">
                    <p><strong>Lỗi:</strong> ${requestScope.errorMessage}</p>
                </div>
            </c:if>

            <c:if test="${not empty requestScope.successMessage}">
                <div class="success-message">
                    <p><strong>Thành công:</strong> ${requestScope.successMessage}</p>
                </div>
            </c:if>

            <div class="profile-card">
                <div class="profile-header">
                    <div class="img-section">
                        <img src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" alt="Ảnh đại diện">
                    </div>
                    <div class="personal-section">
                        <h1>${requestScope.parentAccount.firstName} ${requestScope.parentAccount.lastName}</h1>
                        <p>Ngày sinh: ${requestScope.parentAccount.dob}</p>
                    </div>
                </div>

                <div class="profile-content">
                    <div class="profile-grid">
                        <div class="content-item">
                            <div class="item-title">Tên</div>
                            <p class="parent-info">${requestScope.parentAccount.firstName}</p>
                        </div>

                        <div class="content-item">
                            <div class="item-title">Họ</div>
                            <p class="parent-info">${requestScope.parentAccount.lastName}</p>
                        </div>
                        
                        <div class="content-item">
                            <div class="item-title">Giới tính</div>
                            <p class="parent-info">
                                <c:if test="${requestScope.parentAccount.gender == true}">Nam</c:if>
                                <c:if test="${requestScope.parentAccount.gender == false}">Nữ</c:if>
                            </p>
                        </div>

                        <div class="content-item">
                            <div class="item-title">Số điện thoại</div>
                            <p class="parent-info">${requestScope.parentAccount.phoneNumber}</p>
                        </div>
                        
                        <div class="content-item full-width">
                            <div class="item-title">Email</div>
                            <p class="parent-info">${requestScope.parentAccount.email}</p>
                        </div>

                        <div class="content-item full-width">
                            <div class="item-title">Địa chỉ</div>
                            <p class="parent-info">${requestScope.parentAccount.address}</p>
                        </div>
                    </div>
                    
                    <div class="profile-actions">
                        <a href="${pageContext.request.contextPath}/parentupdateprofile">
                            <input type="button" class="update-button" value="Cập nhật hồ sơ"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>