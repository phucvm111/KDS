<%-- 
    Document   : studentlist
    Created on : Jun 8, 2022, 11:16:04 AM
    Author     : Windows 10 TIMT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare</title>
        <link rel="stylesheet" href="teacher/css/teacherhome.css">
        <script src="https://kit.fontawesome.com/67b5c45612.js" crossorigin="anonymous"></script>
        <script src="teacher/js/teacherhome.js"></script>

        <style>
            .current1>a{
                color: #fff;
            }
            .notification-btn {
                padding: 8px 15px;
                background-color: #6b48ff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-left: 10px;
            }
            .notification-btn:hover {
                background-color: #5a3de6;
            }
            .menu-container {
                display: flex;
                padding: 0;
                margin: 0;
                list-style: none;
            }

            .menu-container .menu-item {
                flex: 1;
                text-align: center;
            }
        </style>
    </head>

    <body>
        <c:set var="acc" value="${sessionScope.account}"/>
        <c:set var="kinderClass" value="${sessionScope.kinder_class}"/>
        <div class="wrapper">
            <div class="home">
                <div class="left-side-menu">
                    <div class="vertical-menu">
                        <div class="user-welcome">
                            <img class="user-img" src="teacher/img/userImg/dummy-user-img.png" style="width: 80px; height: 80px;" alt="">
                            <p>${acc.firstName} ${acc.lastName}</p>
                        </div>
                        <div class="menu-item-container">
                            <ul class="item-lists">
                                <li class="menu-item current1">
                                    <a href="attendance" style="color: #fff;">Home</a>
                                </li>
                                <li class="menu-item current1">
                                    <a href="changepassword" style="color: #fff;">Change Password</a>
                                </li>
                                <li class="menu-item current1">
                                    <a href="notifications" style="color: #fff;">Notification</a>
                                </li>                              
                                <li class="menu-item current1">
                                    <a href="${pageContext.request.contextPath}/teacher/teacherprofile.jsp">Teacher Information</a>
                                </li>
                                </li>                              
                                <li class="menu-item current1">
                                    <a href="${pageContext.request.contextPath}/respondform">Respond Form</a>
                                </li>
                                <li class="menu-item current1">
                                    <a href="${pageContext.request.contextPath}/day_class">View Nutribution</a>
                                </li>
                                <ul class="menu-container">
                                    <li class="menu-item current1">
                                        <a href="${pageContext.request.contextPath}/teacher/uploadImage.jsp" style="color: #fff;">📤 Tải ảnh </a>
                                    </li>
                                    <li class="menu-item current1">
                                        <a href="${pageContext.request.contextPath}/teacher/viewImages" style="color: #fff;">📸 Xem ảnh</a>
                                    </li>
                                </ul>

                            </ul>

                        </div>
                        <div class="log-out">
                            <a href="logout">Log out</a>
                        </div>
                    </div>
                </div>

                <div class="right-side">
                    <div class="page-content">
                        <div class="header-container">
                            <div class="header-intro">
                                <div class="class-section">
                                    <h1>${kinderClass.class_name}</h1>
                                </div>
                                <div class="search-section">
                                    <form action="filter" method="get">
                                        <input type="search" name="searchbar" placeholder="Search...">
                                        <input type="hidden" name="action" value="checkin"/>
                                        <button type="submit">Search</button>
                                    </form>
                                </div>
                            </div>
                            <div class="header-filter">
                                <div class="date-input">
                                    <form id="myform" action="checkattendance" method="POST">
                                        <input id="dateinput" type="date" name="checkindate" value="${requestScope.checkindate}"/>
                                        <input type="hidden" name="action" value="checkin"/>
                                        <input type = "submit" value="Submit" onclick="onSubmitClick()" hidden/>
                                    </form>
                                </div>
                                <div class="left-modes">
                                    <div class="check-section">
                                        <div class="check-in check-item current1">
                                            <a href="#" style="color: #fff;">Check in</a>
                                        </div>
                                        <div class="check-out check-item">
                                            <a href="checkout">Check out</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:set var="map" value="${requestScope.studentMap}"/>
                        <c:set var="listKinder" value="${requestScope.listKinder}"/>
                        <c:set var="presentKids" value="${requestScope.present_kids}"/>
                        <c:set var="attendanceStr" value="${requestScope.attendanceStr}"/>
                        <c:set var="isPast" value="${requestScope.isPast}"/>
                        <div class="body-container">
                            <form action="attendance" method="post"> 
                                <div class="list-students-ver2">
                                    <%
                                        int count = 0;
                                        
                                    %>
                                    <c:forEach var="s" items="${listKinder}">
                                        <div class="student-infor">
                                            <%
                                                count++;
                                            %>
                                            <p>${s.kinder.kinder_id}</p>
                                            <div class="img-section">
                                                <img src="teacher/img/userImg/download.png" alt="">
                                            </div>
                                            <a href="kidprofile?kid_id=${s.kinder.kinder_id}">${s.kinder.first_name} ${s.kinder.last_name}</a>
                                            <c:if test="${presentKids == null}">
                                                <div class="check-attendance">
                                                    <c:set var="k1" value="${s.kinder.kinder_id}:1"/>
                                                    <c:set var="k2" value="${s.kinder.kinder_id}:0"/>
                                                    <input type="radio" name="<%=count%>" id="${k1}" onclick="saveAttendance('${k1}')" value="1" 
                                                           placeholder="Attend"> Attend
                                                    <input type="radio" name="<%=count%>" id="${k2}" onclick="saveAttendance('${k2}')" value="0" 
                                                           checked placeholder="Absent"> Absent
                                                </div>
                                            </c:if>
                                            <c:if test="${presentKids != null}">
                                                <c:forEach items="${map}" var="ps">
                                                    <c:if test="${isPast == false}">
                                                        <c:if test="${ps.key == s.kinder.kinder_id}">
                                                            <div class="check-attendance">
                                                                <c:set var="k1" value="${ps.key}:1"/>
                                                                <c:set var="k2" value="${ps.key}:0"/>
                                                                <input type="radio" name="<%=count%>" id="${k1}" onclick="saveAttendance('${k1}')" value="1" 
                                                                       ${ps.value!=0?"checked":""} /> Attend
                                                                <input type="radio" name="<%=count%>" id="${k2}" onclick="saveAttendance('${k2}')" value="0" 
                                                                       ${ps.value==0?"checked":""} /> Absent
                                                            </div>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${isPast == true}">
                                                        <c:if test="${ps.key == s.kinder.kinder_id}">
                                                            <div class="check-attendance">
                                                                <c:set var="k1" value="${ps.key}:1"/>
                                                                <c:set var="k2" value="${ps.key}:0"/>
                                                                <input type="radio" name="<%=count%>" id="${k1}" onclick="saveAttendance('${k1}')" value="1" 
                                                                       ${ps.value!=0?"checked":""} disabled="true"/> Attend
                                                                <input type="radio" name="<%=count%>" id="${k2}" onclick="saveAttendance('${k2}')" value="0" 
                                                                       ${ps.value==0?"checked":""} disabled="true"/> Absent
                                                            </div>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                </div>
                                <input type="hidden" name="attendanceStatus" id="attendanceStatus" />
                                <div class="submit-btn">
                                    <input type="submit" name="Save" value="Save"/>
                                </div>
                            </form>
                        </div>
                        <c:if test="${requestScope.announcement != null || sessionScope.message != null}">
                            <div id="popup1" class="overlay">
                                <div class="popup"> 
                                    <div class="warning-icon">
                                        <i class="fa-solid fa-circle-exclamation"></i>
                                    </div>
                                    <c:if test="${requestScope.announcement != null}">
                                        <div class="content">
                                            ${requestScope.announcement}
                                        </div>
                                    </c:if>
                                    <c:if test="${sessionScope.message != null}">
                                        <div class="content">
                                            ${sessionScope.message}
                                        </div>
                                    </c:if>
                                    <div class="close-btn" id="close">
                                        <i class="fa-solid fa-xmark"></i>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        document.getElementById('dateinput').onchange = function () {
            document.getElementById("myform").submit();
        };

        if (${requestScope.announcement != null || sessionScope.message != null}) {
            window.addEventListener("load", function () {
                setTimeout(
                        function open(event) {
                            document.querySelector(".overlay").style.visibility = "visible";
                            document.querySelector(".overlay").style.opacity = "1";
                        },
                        1000
                        );
            });
            document.querySelector("#close").addEventListener("click", function () {
        <%
                    session.removeAttribute("message");
        %>
                document.querySelector(".popup").style.display = "none";
                document.querySelector(".overlay").style.visibility = "hidden";
                document.querySelector(".overlay").style.opacity = "0";
            });
        }

        var stumap = new Map();
        var a1 = '${requestScope.studentMap}'.slice(1, -1).split(", ");
        for (var i = 0; i < a1.length; i++) {
            stumap.set(a1[i].substring(0, a1[i].indexOf('=')), a1[i].substring(a1[i].indexOf('=') + 1, a1[i].length));
        }
        function saveAttendance(index) {
            var ans = index.split(':');
            stumap.set(ans[0], ans[1]);
            var output = "";
            for (const [key, value] of stumap) {
                output += key + ":" + value + ",";
            }
            console.log(output);
            document.getElementById("attendanceStatus").value = output;
        }
    </script>
</html>