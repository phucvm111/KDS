<%-- view/sidebarTeacher.jsp --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="left-side-menu">
    <div class="user-welcome">
        <img class="user-img" src="${sessionScope.account.img != null ? sessionScope.account.img : pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" alt="Ảnh đại diện người dùng">
        <p>${sessionScope.account.firstName} ${sessionScope.account.lastName}</p>
        <span class="user-role">Giáo viên</span>
    </div>
    <div class="menu-item-container">
        <ul class="item-lists">
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/teacher/home">
                    <i class="fa-solid fa-home"></i> Trang chủ
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/leavehistory"> 
                    <i class="fa-solid fa-file-alt"></i> Đơn xin nghỉ phép
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/changepassword">
                    <i class="fa-solid fa-key"></i> Đổi mật khẩu
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/teacher/notifications">
                    <i class="fa-solid fa-bell"></i> Thông báo
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/teacherprofile">
                    <i class="fa-solid fa-user"></i> Thông tin giáo viên
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/teacher/respondform">
                    <i class="fa-solid fa-reply-all"></i> Phản hồi biểu mẫu
                </a>
            </li>
            <li class="menu-item">
                <a href="${pageContext.request.contextPath}/teacher/viewnutrition">
                    <i class="fa-solid fa-carrot"></i> Xem dinh dưỡng
                </a>
            </li>
        </ul>
    </div>
    <div class="logout-container">
        <input type="button" class="log-out_button" onclick="window.location.href='${pageContext.request.contextPath}/logout'" value="Đăng xuất"/>
    </div>
</div>