<%-- 
    Document   : adminClassPage
    Created on : Jun 11, 2025, 1:53:42 PM
    Author     : Vu Tuan Hai <HE176383>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!----======== CSS ======== -->
        <link rel="stylesheet" href="admin/class/css/style.css">
        <link rel="stylesheet" href="view/css/adminSidebar.css">
        <link rel="stylesheet" href="admin/class/boot/bootstrap.min.css">
        <link rel="stylesheet" href="admin/class/boot/bootstrap.css">
        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <!--<title>Admin Dashboard Panel</title>-->
    </head>

    <body>
        <form action="addclass" method="POST">
            <nav>
                <div class="logo-name">
                    <div class="logo-image">
                        <img src="images/logo.jpg" alt="Logo">
                    </div>
                    <span class="logo_name">
                        <a href="listaccount">Admin Page</a>
                    </span>
                </div>

                <div class="menu-items">
                    <jsp:include page="/view/adminSidebar.jsp" />
                </div>
            </nav>

            <div class="dashboard">

                <div class="dash-lefttop">
                    <img src="https://i.pinimg.com/originals/72/45/fb/7245fb0ca786bb4a98fb8465e437c5bb.jpg" alt="">
                    <a href="#" style="text-decoration: none">${sessionScope.account.firstName}</a>
                </div>


                <div class="sl-id" style="display: flex;">


                    <div>
                        <button  type="submit" style="width: auto;margin-left: 70px;margin-top: 20px;">
                            <a href="addclass" style="text-decoration: none">Thêm lớp mới</a> 
                        </button> 
                    </div>
                </div>


                <div class="dash-bottomtable">
                    <table class="table" >
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Tên lớp</th>
                                <th scope="col">Khối</th>
                                <th scope="col">Thông tin chi tiết</th>
                                <th scope="col">Tên giáo viên phụ trách</th>

                                <th scope="col">Chỉnh sửa</th>
                                <th scope="col">Xóa</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.list}" var="lsc" >
                                <tr>
                                    <th scope="row">${lsc.class_id}</th>
                                    <td>${lsc.class_name}</td>
                                    <td>${lsc.grade}</td>
                                    <td>${lsc.class_description}</td>                           
                                    <td>${lsc.acc.lastName}</td>

                                    <td><a href="updateclass?sid=${lsc.class_id}">Chỉnh sửa</a></td>
                                    <td><a href="deleteclass?sid=${lsc.class_id}">Xóa</a></td>
                                </tr>

                            </c:forEach>


                        </tbody>
                    </table>
                </div>
            </div>

        </form>
        <!-- <script src="agu.js"></script> -->
    </body>

</html>