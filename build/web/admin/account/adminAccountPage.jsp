<%-- 
    Document   : adminAccountPage.jsp
    Created on : Jun 6, 2025
    Author     : Vu Tuan Hai <HE176383>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ATKD ChildCare - Trang quản trị</title>

        <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
        <link rel="stylesheet" href="admin/account/css/style.css">
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

    </head>

    <body>
        <form action="listaccount" method="POST">
            <nav>
                <div class="logo-name">
                    <div class="logo-image">
                        <img src="${pageContext.request.contextPath}/assets/image/logo.png" alt="Logo">
                    </div>
                    <span class="logo_name">
                        <a href="dashboard">Trang quản trị</a>
                    </span>
                </div>

                <div class="menu-items">
                    <jsp:include page="/view/adminSidebar.jsp" />
                </div>
            </nav>

            <div class="dashboard">
                <div class="dash-lefttop">
                    <img src="https://i.pinimg.com/originals/72/45/fb/7245fb0ca786bb4a98fb8465e437c5bb.jpg" alt="">
                    <a href="#">${sessionScope.account.firstName}</a>
                </div>

                <div class="dash-bottom">
                    <input class="search-input" type="search" value="${requestScope.searchName}" name="search" placeholder="Tìm kiếm">
                    <button class="search-button" type="submit">Tìm kiếm</button>
                </div>

                <div class="sl-id">
                    <div>
                        <select name="slRole">
                            <option value="0" selected>Tất cả</option>
                            <c:forEach items="${requestScope.roles}" var="roles">
                                <option value="${roles.roleID}" <c:if test="${roles.roleID == searchRole}">selected</c:if>>${roles.roleName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <button class="add-button" type="submit"><a href="addaccount">Thêm tài khoản</a></button>
                    </div>
                </div>

                <div class="dash-bottomtable">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Họ</th>
                                <th>Tên</th>
                                <th>Tên đầy đủ</th>
                                <th>Giới tính</th>
                                <th>Email</th>
                                <th>Mật khẩu</th>
                                <th>Ngày sinh</th>
                                <th>Số điện thoại</th>
                                <th>Địa chỉ</th>
                                <th>Ảnh</th>
                                <th>Role</th>
                                <th>Chỉnh sửa</th>
                                <th>Xóa</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.ac}" var="t">
                                <tr>
                                    <td>${t.accountID}</td>
                                    <td>${t.firstName}</td>
                                    <td>${t.lastName}</td>
                                    <td>${t.firstName} ${t.lastName}</td>
                                    <td>
                                        <c:if test="${t.gender == true}">Nam</c:if>
                                        <c:if test="${t.gender == false}">Nữ</c:if>
                                        </td>
                                        <td>${t.email}</td>
                                    <td>${t.password}</td>
                                    <td>${t.dob}</td>
                                    <td>${t.phoneNumber}</td>
                                    <td>${t.address}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${t.img == null || t.img.isEmpty()}">
                                                <img src="admin/account/images/avtClone.jpg" alt="default avatar">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${t.img}" alt="user image">
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${t.role.roleName}</td>
                                    <td><a href="updateaccount?sid=${t.accountID}">Chỉnh sửa</a></td>
                                    <td><a href="deleteaccount?sid=${t.accountID}">Xóa</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </body>
</html>
