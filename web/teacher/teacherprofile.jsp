<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
    <title>KDS - Teacher Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css">
<!--    <link rel="stylesheet" href="${pageContext.request.contextPath}/teacher/css/teacherInfor.css">-->
</head>
<body>
    <div class="wrapper">
        <%-- include sidebar teacher dùng chung --%>
        <jsp:include page="/view/sidebarTeacher.jsp" />

        <div class="right-side">
            <div class="page-content">
                <div class="kid-profile">
                    <div class="kid-profile_header">
                        <div class="img-section">
                            <img src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" alt="Teacher Avatar">
                        </div>
                        <div class="personel-section">
                            <h1>${sessionScope.account.firstName} ${sessionScope.account.lastName}</h1>
                            <p>${sessionScope.account.dob}</p>
                        </div>
                    </div>

                    <div class="kid-profile_content">
                        <div class="class content-item">
                            <div class="item-title"><strong>First Name</strong></div>
                            <p class="parent-infor">${sessionScope.account.firstName}</p>
                        </div>
                        <div class="content-item phone">
                            <div class="item-title"><strong>Last Name</strong></div>
                            <p class="parent-infor">${sessionScope.account.lastName}</p>
                        </div>
                        <div class="content-item phone">
                            <div class="item-title"><strong>Date of birth</strong></div>
                            <p class="parent-infor">${sessionScope.account.dob}</p>
                        </div>   
                        <div class="content-item address">
                            <div class="item-title"><strong>Gender</strong></div>
                            <c:if test="${sessionScope.account.gender == true}">
                                <p class="parent-infor">Male</p>
                            </c:if>
                            <c:if test="${sessionScope.account.gender == false}">
                                <p class="parent-infor">Female</p>
                            </c:if>
                        </div>
                        <div class="content-item address">
                            <div class="item-title"><strong>Phone</strong></div>
                            <p class="parent-infor">${sessionScope.account.phoneNumber}</p>
                        </div>
                        <div class="content-item description">
                            <div class="item-title"><strong>Address</strong></div>
                            <p class="parent-infor">${sessionScope.account.address}</p>
                        </div>
                        <div class="content-item address">
                            <div class="item-title"><strong>Email</strong></div>
                            <p class="parent-infor">${sessionScope.account.email}</p>
                        </div>
                        <div class="mb-6" style="margin-top: 30px; margin-bottom: 30px; text-align:center;">
                            <a href="${pageContext.request.contextPath}/updateteacher">
                                <input type="button" class="button" value="Update profile"/>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
