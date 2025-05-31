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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/parenthome.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/childprofile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/childfb.css">
    </head>

    <body>
        <div class="wrapper">
            <div class="home">
                <div class="left-side-menu">
                    <div class="vertical-menu">
                        <div class="user-welcome">
                            <img class="user-img" src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" style="width: 80px; height: 80px;" alt="">
                            <p>${sessionScope.account.first_name} ${sessionScope.account.last_name}</p>
                        </div>
                        <div class="menu-item-container">
                            <ul class="item-lists">
                                <li class="menu-item current1">
                                    <a href="${pageContext.request.contextPath}/parent/childdetails.jsp">Child Information</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Parent Information</a>
                                </li>
                                <li class="menu-item">
                                    <a href="#">Child Register</a>
                                </li>
                            </ul>
                        </div>
                        <div style="position: absolute;bottom: 0;">
                            <input type="button" class="button" onclick="window.location.replace('/ATKD_Project/logout')" value="Log out"/>
                        </div>
                    </div>
                </div>

                <div class="right-side">
                    <div class="page-content">
                        <div class="kid-profile">
                            <div class="kid-profile_header">
                                <div class="img-section">
                                    <img src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" alt="">
                                </div>
                                <c:set var="mainchild" value="${sessionScope.mainchild}" />
                                <div class="personel-section">
                                    <c:set var="mainchild" value="${sessionScope.mainchild}" />   
                                    <div class="personel-section">
                                        <h1>${mainchild.getFullName()}</h1>
                                    </div>
                                    <form action="childdetailservlet" method="GET">
                                        <label for="mainchildid">Choose kinderdergartner</label>
                                        <select name="mainchildid" id="mainchildid">
                                            <c:forEach items="${kidlist}" var="k">
                                                <option value="${k.getKinder_id()}">
                                                <h1>${k.getFullName()}</h1>
                                                </option>
                                            </c:forEach>
                                        </select>  
                                        <input type="submit" value="Change">
                                    </form>    

                                    
                                </div>
                            </div>

                            <div class="list-option" id="options">
                                <div class="attendence option-item">
                                    <a href="childdetailcontrol?action=attendance&mainchildid=${mainchild.getKinder_id()}">Attendance</a>
                                </div>
                                <div class="feedback option-item current">
                                    <a href="childdetailcontrol?action=feedback&mainchildid=${mainchild.getKinder_id()}">Feedback</a>
                                </div>
                                <div class="profile option-item">
                                    <a href="childdetailcontrol?action=childprofile&mainchildid=${mainchild.getKinder_id()}">Profile</a>
                                </div>
                            </div>
                            <form action="childfb" method="POST">
                                <c:set var="mainchildid" value="${requestScope.mainchild.getKinder_id()}"/>
                                <input type="hidden" name="mainchildid" value="${requestScope.mainchild.getKinder_id()}" readonly />
                                <div class="feedback-content">
                                    <textarea name="fb-content" id="" cols="30" rows="10"></textarea>
                                </div>
                                <div class="feedback-section">

                                    <div class="feedback-section-content">
                                        <div id="rating">
                                            <input type="radio" id="star5" name="rating" value="5" />
                                            <label class="full" for="star5" title="Awesome - 5 stars"></label>

                                            <input type="radio" id="star4half" name="rating" value="4.5" />
                                            <label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>

                                            <input type="radio" id="star4" name="rating" value="4" />
                                            <label class="full" for="star4" title="Pretty good - 4 stars"></label>

                                            <input type="radio" id="star3half" name="rating" value="3.5" />
                                            <label class="half" for="star3half" title="Meh - 3.5 stars"></label>

                                            <input type="radio" id="star3" name="rating" value="3" />
                                            <label class="full" for="star3" title="Meh - 3 stars"></label>

                                            <input type="radio" id="star2half" name="rating" value="2.5" />
                                            <label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>

                                            <input type="radio" id="star2" name="rating" value="2" />
                                            <label class="full" for="star2" title="Kinda bad - 2 stars"></label>

                                            <input type="radio" id="star1half" name="rating" value="1 and a half" />
                                            <label class="half" for="star1half" title="Meh - 1.5 stars"></label>

                                            <input type="radio" id="star1" name="rating" value="1" />
                                            <label class="full" for="star1" title="Sucks big time - 1 star"></label>

                                            <input type="radio" id="starhalf" name="rating" value="1.5" />
                                            <label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
                                        </div>

                                    </div>
                                    
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </body>
    <script src="js/kidprofile.js"></script>
    <script>
        window.onload = function () {
            var patharr = location.pathname.split("/");
            var fileName = patharr[3];
            var options = document.getElementById('options');
            var links = options.getElementsByTagName("a");
            // alert(links.length);
            for (i = 1; i < links.length; i++) {
                if (links[i].getAttribute("href").indexOf(fileName) > -1) {
                    links[i].parentNode.className = 'current';
                }
            }
        };
    </script>

</html>
