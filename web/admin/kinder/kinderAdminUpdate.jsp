<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : kinderAdminUpdate
    Created on : Jun 26, 2022, 2:19:00 PM
    Author     : win
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : kinderAdminAdd
    Created on : Jun 26, 2022, 2:17:40 AM
    Author     : win
--%>


<!DOCTYPE html>
<!--=== Coding by CodingLab | www.codinglabweb.com === -->
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!----======== CSS ======== -->
        <link rel="stylesheet" href="admin/kinder/css/style.css">
        <link rel="stylesheet" href="admin/kinder/boot/bootstrap.min.css">
        <link rel="stylesheet" href="admin/kinder/boot/bootstrap.css">
        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <!--<title>Admin Dashboard Panel</title>-->
    </head>

    <body>
        <form action="updatekinder?kingId=${sid}" method="POST">
            <nav>
                <div class="logo-name">
                    <div class="logo-image">
                        <img src="images/logo.jpg" alt="">
                    </div>

                    <span class="logo_name"><a href="listaccount" style="text-decoration: none;color: black">Admin Page</a></span>
                </div>

                <div class="menu-items">
                    <ul class="nav-links">
                        <li><a href="listaccount">
                                <i class="uil uil-estate"></i>
                                <span class="link-name">Account</span>
                            </a></li>

                        <li><a href="listkinder">
                                <i class="uil uil-chart"></i>
                                <span class="link-name">Kindergartner</span>
                            </a></li>
                        <li><a href="#">
                                <i class="uil uil-thumbs-up"></i>
                                <span class="link-name">Class</span>
                            </a></li>
                        <li><a href="#">
                                <i class="uil uil-comments"></i>
                                <span class="link-name">Schedule</span>
                            </a></li>
                        <!--                        <li><a href="#">
                                                        <i class="uil uil-share"></i>
                                                        <span class="link-name">Attendance</span>
                                                    </a></li>-->
                        <li><a href="adminNotifyTeachers">
                                <i class="uil uil-bell"></i>
                                <span class="link-name">Send Notification</span>
                            </a></li>

                    </ul>

                    <ul class="logout-mode">
                        <li><a href="#">
                                <i class="uil uil-signout"></i>
                                <span class="link-name">Logout</span>
                            </a></li>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="dashboard">

                <div class="dash-lefttop">
                    <img src="https://i.pinimg.com/originals/72/45/fb/7245fb0ca786bb4a98fb8465e437c5bb.jpg" alt="">
                    <a href="#">User</a>
                </div>

                <div class="form-title" style="text-align: center;font-size: 50px;">Update Kinder</div>
                <div class="form-content" style="width: 80%;height: auto; margin-left: 10%; padding-top: 0;padding-bottom: 0;">
                    <c:set value="${requestScope.kinder}" var="k"></c:set>
                    <input type="text" value="${k.kinder_id}" name="kid" hidden=""/>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">ParentName</label>
                        <input value="${k.parentAccount.accountID}" name="txtPrId" type="hidden"/>
                        <input value="${k.parentAccount.firstName} ${k.parentAccount.lastName}" type="text" readonly="">
                        <!--<select class="form-select" name="txtPrId" aria-label="Default select example" >-->
                        <%--<c:forEach items="${requestScope.listK}" var="ltnameac">--%>                 
                                           <!--<option  value="${ltnameac.parentAccount.accountID}">${ltnameac.parentAccount.lastName}</option>-->   
                        <%--</c:forEach>--%>
                        <!--</select>--> 
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">FirstName</label>
                        <input type="text" class="form-control" id="exampleFormControlInput1" value="${k.first_name}"  name="txtFsName">
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">LastName</label>
                        <input type="text" class="form-control" value="${k.last_name}" id="exampleFormControlInput1" name="txtLsName">
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">DOB</label>
                        <input type="date" class="form-control" value="${k.dob}" id="exampleFormControlInput1" name="dob">
                    </div>
                    <div class="rdCheck" style="display: flex">
                        <div class="form-check">
                            <input class="form-check-input" type="radio"
                                   <c:if test="${requestScope.kinder.gender}">
                                       selected
                                   </c:if>
                                   value="male" name="flexRadioDefault" id="flexRadioDefault1">
                            <label class="form-check-label" for="flexRadioDefault1">
                                Male
                            </label>
                        </div>
                        <div class="form-check" style="padding-left: 40px">
                            <input class="form-check-input" type="radio" 
                                   <c:if test="${!requestScope.kinder.gender}">
                                       selected
                                   </c:if>
                                   value="female" name="flexRadioDefault" id="flexRadioDefault2" checked>
                            <label class="form-check-label" for="flexRadioDefault2">
                                FeMale
                            </label>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Image</label>
                        <input type="text" class="form-control" value="${requestScope.kinder.img}"  id="exampleFormControlInput1" name="txtImg">
                    </div>


                    <div class="d-grid gap-2 d-md-block" style="margin-top: 30px; margin-bottom: 30px; ;">
                        <input type="submit" value="Save"/>

                    </div>
                </div>



            </div>



        </div>

        <!-- <script src="agu.js"></script> -->
</body>
</form>
</html>
