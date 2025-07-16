<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/adminSidebar.css"> 
<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

<nav>
    <div class="logo-name">
        <div class="logo-image">
            <img src="${pageContext.request.contextPath}/assets/image/logo.png" alt="Logo">
        </div>
        <span class="logo_name">
            <a href="${pageContext.request.contextPath}/dashboard">Trang quản trị</a>
        </span>
    </div>
    <div class="menu-items">
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/dashboard"><i class="uil uil-dashboard"></i><span class="link-name">Trang chính</span></a></li>
            <li><a href="${pageContext.request.contextPath}/listaccount"><i class="uil uil-estate"></i><span class="link-name">Tài Khoản</span></a></li>
            <li><a href="${pageContext.request.contextPath}/listkinder"><i class="uil uil-chart"></i><span class="link-name">Học sinh</span></a></li>
            <li><a href="${pageContext.request.contextPath}/listclass"><i class="uil uil-thumbs-up"></i><span class="link-name">Lớp</span></a></li>
            <li><a href="${pageContext.request.contextPath}/listschedule"><i class="uil uil-comments"></i><span class="link-name">Lịch Học</span></a></li>
            <li><a href="${pageContext.request.contextPath}/event"><i class="uil uil-calendar-alt"></i><span class="link-name">Sự Kiện</span></a></li>
            <li><a href="${pageContext.request.contextPath}/day_class"><i class="uil uil-utensils-alt"></i><span class="link-name">Dinh Dưỡng</span></a></li>
            <li><a href="${pageContext.request.contextPath}/adminleaverequests"><i class="uil uil-file-alt"></i><span class="link-name">Đơn từ</span></a></li>
            <li><a href="${pageContext.request.contextPath}/notification"><i class="uil uil-bell"></i><span class="link-name">Thông Báo</span></a></li>
            <li><a href="${pageContext.request.contextPath}/changepassword"><i class="uil uil-lock-alt"></i><span class="link-name">Đổi Mật Khẩu</span></a></li>
        </ul>
        <ul class="logout-mode">
            <li><a href="${pageContext.request.contextPath}/logout"><i class="uil uil-signout"></i><span class="link-name">Logout</span></a></li>
        </ul>
    </div>
</nav>
