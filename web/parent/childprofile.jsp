<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="${pageContext.request.contextPath}/homepage/assets/image/logo2-removebg-preview.png">
        <title>KDS ChildCare - Child Profile</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage/parent/css/parenthome.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage/parent/css/childprofile.css">
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

                <div class="right-side">
                    <div class="page-content">
                        <div class="kid-profile">
                            <div class="kid-profile_header">

                                <div class="img-section">
                                    <img src="${pageContext.request.contextPath}/homepage/parent/img/userImg/download.png" alt="">
                                </div>

                                <div class="personel-section">
                                    <c:set var="mainchild" value="${sessionScope.mainchild}" />   
                                    <div class="personel-section">
                                        <h1>${mainchild.getFullName()}</h1>
                                    </div>
                                    <form action="childdetailservlet" method="GET" style="margin-top: 10px">

                                        <select name="mainchildid" id="mainchildid" class="item-list">
                                            <c:forEach items="${kidlist}" var="k">
                                                <option value="${k.getKinder_id()}">
                                                <h1>${k.getFullName()}</h1>
                                                </option>
                                            </c:forEach>
                                        </select>  
                                        <input type="submit" value="Change Kid" class="button">
                                    </form>    


                                </div>
                            </div>

                            <div class="list-option" id="options">
                                <div class="attendence option-item">
                                    <a href="${pageContext.request.contextPath}/homepage/childdetailcontrol?action=attendance&mainchildid=${mainchild.getKinder_id()}">Attendance</a>
                                </div>
                                <div class="profile option-item current">
                                    <a href="${pageContext.request.contextPath}/homepage/childdetailcontrol?action=childprofile&mainchildid=${mainchild.getKinder_id()}">Profile</a>
                                </div>
                            </div>

                            <div class="kid-profile_content">
                                <div class="class content-item">
                                    <div class="item-title">
                                        <strong>Date of birth</strong>
                                    </div>
                                    <p>${mainchild.dob}</p>
                                </div>
                                <div class="content-item description">
                                    <div class="item-title">
                                        <strong>Gender</strong>
                                    </div>
                                    <c:if test="${mainchild.gender == true }">
                                        <p class="child-gender">Male</p>
                                    </c:if>
                                    <c:if test="${mainchild.gender == false }">
                                        <p class="child-gender">Female</p>
                                    </c:if> 
                                </div>
                                <div class="class content-item">
                                    <div class="item-title">
                                        <strong>Class</strong>
                                    </div>
                                    <p>${sessionScope.kinder_class.class_name}</p>
                                </div>
                                <div class="content-item phone">
                                    <div class="item-title">
                                        <strong>Parent's phone</strong>
                                    </div>
                                    <c:set var="parent" value="${requestScope.account}"/>
                                    <p>${account.phoneNumber}</p>
                                </div>
                                <div class="content-item address">
                                    <div class="item-title">
                                        <strong>Address</strong>
                                    </div>
                                    <p>${sessionScope.account.address}</p>
                                </div>
                                <div class="content-item description">
                                    <div class="item-title">
                                        <strong>Parent</strong>
                                    </div>
                                    <p>${sessionScope.account.firstName} ${sessionScope.account.lastName}</p>
                                </div>
                            </div>
                            <div class="mb-6">
                                <a href="${pageContext.request.contextPath}/homepage/parent/childupdateprofile.jsp">
                                    <input type="submit" class="child-update" value="Update profile"/>
                                </a>
                            </div>    
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </body>
    <script>//
//        window.onload = function () {
//            var patharr = location.pathname.split("/");
//            var fileName = patharr[3];
//            var options = document.getElementById('options');
//            var links = options.getElementsByTagName("a");
//            // alert(links.length);
//            for (i = 1; i < links.length; i++) {
//                if (links[i].getAttribute("href").indexOf(fileName) > -1) {
//                    links[i].parentNode.className = 'current';
//                }
//            }
//        };
//    </script>

</html>
