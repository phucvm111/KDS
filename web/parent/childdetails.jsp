<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare - Child Attendance</title>
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
                                <li class="menu-item current1">
                                    <a href="${pageContext.request.contextPath}/childdetailservlet">Child Information</a>
                                </li>
                                <li class="menu-item">
                                    <a href="${pageContext.request.contextPath}/parent/parentprofile.jsp">Parent Information</a>
                                </li>
                                <li class="menu-item">
                                    <a href="${pageContext.request.contextPath}/childregister">Child Register</a>
                                </li>
                                <li class="menu-item">
                                    <a href="${pageContext.request.contextPath}/changepassword">Change Password</a>
                                    
                                </li>
                            </ul>
                        </div>
                        <div style="border-top: 3px solid gray;"></div>
                        <div style="position: absolute;margin-top: 1vh; margin-left: 40px">
                            <input type="button" class="log-out_button" onclick="window.location.replace('${pageContext.request.contextPath}/logout')" value="Log out"/>
                        </div>
                    </div>
                </div>
                <c:if test="${kidlist.isEmpty()}">
                    <div class="nochild">
                        <h1>You haven't register any child yet !</h1>
                        <br><!-- comment -->
                        <h1><a href="${pageContext.request.contextPath}/childregister">Click here</a> to register your child</h1> 
                    </div>
                </c:if>
                <c:if test="${!kidlist.isEmpty()}">        
                    <div class="right-side">
                        <div class="page-content">
                            <div class="kid-profile">
                                <div class="kid-profile_header">
                                    <div class="img-section">
                                        <img src="${pageContext.request.contextPath}/parent/img/userImg/download.png" alt="">
                                    </div>

                                    <div class="personel-section">
                                        <c:set var="mainchild" value="${sessionScope.mainchild}" />   
                                        <div class="personel-section">
                                            <h1>${mainchild.getFullName()}</h1>
                                        </div>
                                        <form action="${pageContext.request.contextPath}/childdetailservlet" method="GET" style="margin-top: 10px">

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
                                    <div class="option-item current">
                                        <a href="${pageContext.request.contextPath}/childdetailcontrol?action=attendance&mainchildid=${mainchild.getKinder_id()}">Attendence</a>
                                    </div>
                                    <div class="option-item">
                                        <a href="${pageContext.request.contextPath}/childdetailcontrol?action=childprofile&mainchildid=${mainchild.getKinder_id()}">Profile</a>
                                    </div>
                                </div>

                                <div class="attendence-section">
                                    <div class="body-container">
                                        <c:set var="childalist" value="${sessionScope.childalist}"/>
                                        <div class="list-students-ver2">
                                            <%
                                            int count = 0;
                                            %>
                                            <c:forEach var="c" items="${childalist}">
                                                <input type="hidden" name="action" value="check_in"/>
                                                <div class="student-infor">
                                                    <%
                                                        count++;
                                                    %>
                                                    <p><%=count%></p>
                                                    <div class="img-section">
                                                        <img src="${pageContext.request.contextPath}/parent/img/userImg/download.png" alt="">
                                                    </div>
                                                    <p>Date: ${c.getCheck_date()}</p>
                                                    <p style="padding-left: 200px">Attendance status: </p>
                                                    <c:if test="${c.getStatus() == 0}">
                                                        <p style="color:red">Absent</p>
                                                    </c:if>
                                                    <c:if test="${c.getStatus() == 1}">
                                                        <p style="color:#ff9933">Checked in</p>
                                                    </c:if>
                                                    <c:if test="${c.getStatus() == 2}">
                                                        <p style="color:green">Checked out</p>
                                                    </c:if>    


                                                </div>
                                            </c:forEach>


                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
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