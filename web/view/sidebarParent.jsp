<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="left-side-menu">
    <div class="user-welcome">
        <img class="user-img" src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" alt="User Avatar">
        <p>${sessionScope.account.firstName} ${sessionScope.account.lastName}</p>
    </div>
    <div class="menu-item-container">
        <ul class="item-lists">
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/childdetailservlet">
                    <i class="fa-solid fa-child"></i> Child Information
                </a>
            </li>
            <li class="menu-item current">
                <a href="${pageContext.request.contextPath}/parent/parentprofile.jsp">
                    <i class="fa-solid fa-user"></i> Parent Information
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/childregister">
                    <i class="fa-solid fa-file-signature"></i> Child Register
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/changepassword">
                    <i class="fa-solid fa-key"></i> Change Password
                </a>
            </li>
        </ul>
    </div>
    <div class="logout-container">
        <input type="button" class="log-out_button" onclick="window.location.href='${pageContext.request.contextPath}/logout'" value="Log Out"/>
    </div>
</div>