<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%-- Add this line for date formatting --%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="${pageContext.request.contextPath}//assets/image/logo2-removebg-preview.png">
        <title>KDS - Parent Profile</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/parenthome.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/childprofile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/childregister.css">
    </head>
    <style>
        .right-side {
            background-color: #f3f3f3;
            padding: 30px;
            border-radius: 10px;
            margin-left: 250px;
            width: calc(100% - 270px);
            box-sizing: border-box;
        }

        .page-content {
            background-color: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0px 3px 10px rgba(0,0,0,0.1);
        }

        h2 {
            color: #333;
            margin-bottom: 25px;
            text-align: center;
            font-size: 1.8em;
            padding-bottom: 10px;
            border-bottom: 2px solid #007bff;
            display: inline-block; /* To make the border-bottom only under the text */
            margin-left: auto;
            margin-right: auto;
            display: block; /* To center the block element */
        }

        .meeting-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden; /* Ensures rounded corners are applied */
        }

        .meeting-table th,
        .meeting-table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .meeting-table th {
            background-color: #8A2BE2;
            color: white;
            font-weight: bold;
            text-transform: uppercase;
            font-size: 0.9em;
        }

        .meeting-table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .meeting-table tbody tr:hover {
            background-color: #e9e9e9;
            cursor: pointer;
        }

        .no-meeting {
            text-align: center;
            color: #666;
            margin-top: 30px;
            font-style: italic;
            padding: 20px;
            background-color: #ffeeba;
            border: 1px solid #ffcc00;
            border-radius: 5px;
        }
    </style>

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

                            </ul>
                        </div>
                        <div style="border-top: 3px solid gray;"></div>
                        <div style="position: absolute;margin-top: 1vh; margin-left: 40px">
                            <input type="button" class="log-out_button" onclick="window.location.replace('${pageContext.request.contextPath}/logout')" value="Log out"/>
                        </div>
                    </div>
                </div>
                <div class="right-side">
                    <div class="page-content">
                        <h2>Your Parent Meeting Schedule</h2>

                        <c:if test="${empty meetings}">
                            <p class="no-meeting">No meetings scheduled yet.</p>
                        </c:if>

                        <c:if test="${not empty meetings}">
                            <table class="meeting-table">
                                <thead>
                                    <tr>
                                        <th>Class</th>
                                        <th>Teacher</th>
                                        <th>Date & Time</th>
                                        <th>Topic</th>
                                        <th>Notes</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="m" items="${meetings}">
                                        <tr>
                                            <td>${m.className}</td>
                                            <td>${m.teacherName}</td>
                                            <td><fmt:formatDate value="${m.meetingDate}" pattern="dd/MM/yyyy HH:mm"/></td>
                                            <td>${m.topic}</td>
                                            <td>${m.notes}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        window.onload = function () {
            var path = location.pathname.split("/").pop();
            var items = document.querySelectorAll('.menu-item a');
            items.forEach(item => {
                if (item.getAttribute('href').includes(path)) {
                    item.parentElement.classList.add('current1');
                } else {
                    item.parentElement.classList.remove('current1');
                }
            });
        };

    </script>

</html>