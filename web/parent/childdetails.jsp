<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
        <title>KDS - Child Attendance</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/parenthome.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/childprofile.css">
        <script src="https://kit.fontawesome.com/67b5c45612.js" crossorigin="anonymous"></script>
        <script src="js/childdetails.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/childdetails.css">
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
                                <li class="menu-item ">
                                    <a href="${pageContext.request.contextPath}/childdetailservlet">Child Information</a>
                                </li>
                                <li class="menu-item current1">
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
                                <li class="menu-item">
                                    <a href="${pageContext.request.contextPath}/listschedule">Schedule</a>
                                </li>
                                <li class="menu-item">
                                    <a href="${pageContext.request.contextPath}/sendform">SendForm</a>
                                </li>
                                <li class="menu-item">
                                    <a href="${pageContext.request.contextPath}/day_class">View Nutribution</a>
                                </li>
                                <li class="menu-item ">
                                    <a href="${pageContext.request.contextPath}/parent/viewImages" >Xem ảnh hằng ngày </a>
                                </li>

                            </ul>

                        </div>
                        <div style="border-top: 3px solid gray;"></div>
                        <div style="position: absolute;margin-top: 1vh; margin-left: 40px">
                            <input type="button" class="log-out_button" onclick="window.location.replace('${pageContext.request.contextPath}/events')" value="Log out"/>
                        </div>
                    </div>
                </div>
                <c:if test="${empty kidlist}">
                    <div class="nochild">
                        <h1>You haven't registered any child yet!</h1>
                        <h2><a href="${pageContext.request.contextPath}/childregister">Click here</a> to register your child</h2>
                    </div>
                </c:if>

                <c:if test="${not empty kidlist}">
                    <div style="margin: 20px;">
                        <h2>Danh sách trẻ của bạn</h2>
                        <div style="display:flex; flex-wrap:wrap; gap: 20px;">
                            <c:forEach var="k" items="${kidlist}">
                                <div class="card" style="width: 250px; border: 1px solid #ccc; border-radius: 10px; padding: 15px; text-align: center;">
                                    <img src="${k.img}" alt="Avatar" style="width: 100%; height: 200px; object-fit: cover; border-radius: 10px;">
                                    <h3>${k.fullName}</h3>
                                    <p>Ngày sinh: ${k.dob}</p>
                                    <p>Giới tính: <c:choose>
                                            <c:when test="${k.gender}">Nam</c:when>
                                            <c:otherwise>Nữ</c:otherwise>
                                        </c:choose></p>
                                    <form method="get" action="${pageContext.request.contextPath}/childdetailservlet">
                                        <input type="hidden" name="mainchildid" value="${k.kinder_id}">
                                        <button class="btn" type="submit">Xem / Cập nhật</button>
                                    </form>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty sessionScope.mainchild}">
                    <div style="margin: 30px;">
                        <h2>Thông tin chi tiết: ${mainchild.fullName}</h2>
                        <form method="post" action="${pageContext.request.contextPath}/update-child">
                            <input type="hidden" name="kidId" value="${mainchild.kinder_id}">
                            Tên: <input type="text" name="firstName" value="${mainchild.first_name}"><br>
                            Họ: <input type="text" name="lastName" value="${mainchild.last_name}"><br>
                            Ngày sinh: <input type="date" name="dob" value="${mainchild.dob}"><br>
                            Giới tính:
                            <select name="gender">
                                <option value="true" ${mainchild.gender ? 'selected' : ''}>Nam</option>
                                <option value="false" ${!mainchild.gender ? 'selected' : ''}>Nữ</option>
                            </select><br><br>
                            <button class="btn" type="submit">Cập nhật</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
    <!--    <script>
            window.onload = function () {
                var patharr = location.pathname.split("/");
                var fileName = patharr[1];
                var options = document.getElementById('options');
                var links = options.getElementsByTagName("a");
                // alert(links.length);
                for (i = 1; i < links.length; i++) {
                    if (links[i].getAttribute("href").indexOf(fileName) > -1) {
                        links[i].parentNode.className = 'current';
                    }
                }
            }
        </script>-->


</html>