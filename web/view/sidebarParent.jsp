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
                <a href="${pageContext.request.contextPath}/childrenlist">
                    <i class="fa-solid fa-child"></i> Thông tin trẻ
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/parentprofile">
                    <i class="fa-solid fa-user"></i> Thông tin phụ huynh
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/childregister">
                    <i class="fa-solid fa-file-signature"></i> Đăng ký học cho trẻ
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/changepassword">
                    <i class="fa-solid fa-key"></i> Đổi mật khẩu
                </a>
            </li>


            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/viewmeetings">
                    <i class="fa-solid fa-file-signature"></i> Họp phụ huynh
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/sendform">
                    <i class="fa-solid fa-file-signature"></i> Viết đơn
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/day_class">
                    <i class="fa-solid fa-file-signature"></i> Thực đơn
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/parent/viewImages">
                    <i class="fa-solid fa-image"></i> Xem ảnh hằng ngày
                </a>
            </li>

        </ul>
    </div>
    <div class="logout-container">
        <input type="button" class="log-out_button" onclick="window.location.href = '${pageContext.request.contextPath}/login'" value="Log Out"/>
    </div>
</div>