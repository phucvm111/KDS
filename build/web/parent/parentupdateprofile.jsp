<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="/ATKD_Project/homepage/assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare - Update Parent Profile</title>
        <link rel="stylesheet" href="/ATKD_Project/homepage/parent/css/parenthome.css">
        <link rel="stylesheet" href="/ATKD_Project/homepage/parent/css/childprofile.css">
        <link rel="stylesheet" href="/ATKD_Project/homepage/parent/css/childregister.css">
    </head>

    <body>
        <div class="wrapper">
            <div class="home">
                <div class="left-side-menu">
                    <div class="vertical-menu" style="position: relative;">
                        <div class="user-welcome">
                            <img class="user-img" src="/ATKD_Project/homepage/parent/img/userImg/dummy-user-img.png" style="width: 80px; height: 80px;" alt="">
                            <p>${sessionScope.account.firstName} ${sessionScope.account.lastName}</p>
                        </div>
                        <div class="menu-item-container">
                            <ul class="item-lists">
                                <li class="menu-item ">
                                    <a href="/ATKD_Project/homepage/childdetailservlet">Child Information</a>
                                </li>
                                <li class="menu-item current1">
                                    <a href="/ATKD_Project/homepage/parent/parentprofile.jsp">Parent Information</a>
                                </li>
                                <li class="menu-item">
                                    <a href="/ATKD_Project/homepage/childregister">Child Register</a>
                                </li>
                            </ul>
                        </div>
                        <div style="border-top: 3px solid gray;"></div>
                        <div style="position: absolute;margin-top: 1vh; margin-left: 40px">
                            <input type="button" class="log-out_button" onclick="window.location.replace('/ATKD_Project/homepage/logout')" value="Log out"/>
                        </div>
                    </div>
                </div>

                <div class="right-side">
                    <div class="page-content">
                        <div class="kid-profile">
                            <div class="kid-profile_header">

                                <div class="img-section">
                                    <img src="/ATKD_Project/homepage/parent/img/userImg/dummy-user-img.png" alt="">
                                </div>

                                <div class="personel-section">
                                    <h1>${sessionScope.account.firstName} ${sessionScope.account.lastName}</h1>
                                    <p>${sessionScope.account.dob}</p>
                                </div>
                            </div>


                            <form action="/ATKD_Project/homepage/parentupdate" method="POST">      
                                <div class="kid-profile_content">
                                    <div class="class content-item wrap-input100 validate-input">
                                        <div class="item-title">
                                            <strong>First Name</strong>
                                        </div>
                                        <c:set var="parent" value="${requestScope.account}"/>
                                        <label for="exampleFormControlInput1" class="form-label"></label>
                                        <input type="text" class="class content-item" name="parentfirstname" id="parentfirstname" value="${account.firstName}" pattern="[a-zA-Z]+" title="Alphabetical letters only" required="">
                                        <span class="focus-input100"></span>
                                    </div>
                                    <span class="invalid" id="fnameinvalid1" style="display: none;margin-left: 20%" >Name cannot contain
                                        numeric characters !</span>
                                    <span class="invalid" id="fnameinvalid2" style="display: none;margin-left: 20%">Name cannot contain
                                        special characters !</span>    

                                    <div class="content-item wrap-input100 validate-input">
                                        <div class="item-title">
                                            <strong>Last name</strong>
                                        </div>
                                        <c:set var="parent" value="${requestScope.account}"/>
                                        <input type="text" class="class content-item" id="parentlastname" name="parentlastname" pattern="[a-zA-Z]+" title="Alphabetical letters only" required="" value="${account.lastName}" >
                                        <label for="exampleFormControlInput1" class="form-label"></label>
                                        <span class="focus-input100"></span>
                                    </div>
                                    <span class="invalid" id="lnameinvalid1" style="display: none;margin-left: 20%" >Name cannot contain
                                        numeric characters !</span>
                                    <span class="invalid" id="lnameinvalid2" style="display: none;margin-left: 20%">Name cannot contain
                                        special characters !</span>

                                    <div class="content-item">
                                        <div class="item-title">
                                            <strong>Date of birth</strong>
                                        </div>
                                        <c:set var="parent" value="${requestScope.account}"/>
                                        <input type="date"  class="class content-item" name="parentdob" required="" value="${account.dob}">
                                    </div>
                                    <div class="content-item">
                                        <div class="item-title">
                                            <strong>Gender</strong>
                                        </div>
                                        <select style="width:570px; " class="class content-item" name ="parentgender" id="parentgender" value="
                                                <c:if test="${account.gender == true }">
                                                    <h1>Male</h1>
                                                </c:if>
                                                <c:if test="${account.gender == false }">
                                                    <h1>Female</h1>
                                                </c:if>"
                                                >
                                            <optgroup>
                                                <option value="male"><h1>Male</h1></option>
                                            <option value="female"><h1>Female</h1></option>
                                            </optgroup>
                                        </select>    
                                    </div>    

                                    <div class="content-item wrap-input100 validate-input">
                                        <div class="item-title">
                                            <strong>Phone</strong>
                                        </div>
                                        <c:set var="parent" value="${requestScope.account}"/>
                                        <input type="number" class="class content-item" pattern="(0)(3|5|7|8|9)+([0-9]{8})" name="parentphonenumber" id="parentphonenumber" title="Must start with 09/03/07/08/05 and contain 10 characters" value="${account.phoneNumber}" required="">
                                        <span class="focus-input100"></span>
                                    </div>
                                    <span class="invalid" id="phoneinvalid1" style="display: none;margin-left: 20%">Phone numbers cannot contain
                                        alphabetical characters</span>
                                    <span class="invalid" id="phoneinvalid2" style="display: none;margin-left: 20%">Phone numbers cannot contain
                                        special characters</span>
                                    <span class="invalid" id="phoneinvalid3" style="display: none;margin-left: 20%">Phone numbers must contain 10 characters</span>
                                    <span class="invalid" id="phoneinvalid4" style="display: none;margin-left: 20%">Phone numbers must start with
                                        09/03/07/08/05</span>

                                    <div class="content-item description">
                                        <div class="item-title">
                                            <strong>Address</strong>

                                        </div>
                                        <c:set var="parent" value="${requestScope.account}"/>
                                        <input type="text" class="class content-item" name="parentaddress" required="" value="${account.address}">


                                    </div>
                                    <div class="content-item wrap-input100 validate-input ">
                                        <div class="item-title">
                                            <strong>Email</strong>
                                        </div>
                                        <c:set var="parent" value="${requestScope.account}"/>
                                        <input type="text" class="class content-item" name="parentemail" id="parentemail" title="Must contain @" required value="${account.email}">
                                        <span class="focus-input100"></span>
                                        <span class="invalid" id="emailinvalid" style="display: none;">Email must contain
                                            @</span>
                                        </div>
                                    <!--                                    <div class="content-item description">
                                                                        <div class="item-title">
                                                                            <strong>Avatar</strong>
                                                                            
                                                                        </div>
                                    <c:set var="parent" value="${requestScope.account}"/>
                                    <input type="text" class="class content-item" name="parentavatar" value="${account.img}">
                                    
                                    
                                </div>-->
                                    <div class="mb-6" style="margin-top: 30px; margin-bottom: 30px; padding-left: 250px ">

                                        <input type="button" class="cancel_button"  onclick="window.location.replace('/ATKD_Project/homepage/parent/parentprofile.jsp')" value="Cancel"/>

                                        <input style="margin-left: 130px;"  type="button" class="confirm_button" onclick="openPopup()" value="Confirm Update"/>
                                        <div class="popup" id="popup">
                                            <img src="/ATKD_Project/homepage/parent/img/icon/tick.png">
                                            <h2>Confirm Update ?</h2>
                                            <input type="button" class="cancel_button" onclick="closePopup()" value="Cancel">
                                            <input style="margin-left: 60px;" type="submit" class="confirm_button"  value="Confirm">
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
    <script>
        let popup = document.getElementById("popup");

        function openPopup() {
            popup.classList.add("open-popup");
        }
        function closePopup() {
            popup.classList.remove("open-popup");
        }
    </script>
    <script>
        var fname = document.getElementById("parentfirstname");
        var lname = document.getElementById("parentlastname");
        var phone = document.getElementById("parentphonenumber");
        var email = document.getElementById("parentemail");

        fname.onkeyup = function () {
            var numbers = /[0-9]/g;
            if (fname.value.match(numbers)) {
                document.getElementById("fnameinvalid1").style.display = "block";
            } else {
                document.getElementById("fnameinvalid1").style.display = "none";
            }
            var specs = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
            if (fname.value.match(specs)) {
                document.getElementById("fnameinvalid2").style.display = "block";
            } else {
                document.getElementById("fnameinvalid2").style.display = "none";
            }
        }

        lname.onkeyup = function () {
            var numbers = /[0-9]/g;
            if (lname.value.match(numbers)) {
                document.getElementById("lnameinvalid1").style.display = "block";
            } else {
                document.getElementById("lnameinvalid1").style.display = "none";
            }
            var specs = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
            if (lname.value.match(specs)) {
                document.getElementById("lnameinvalid2").style.display = "block";
            } else {
                document.getElementById("lnameinvalid2").style.display = "none";
            }
        }

        phone.onkeyup = function () {
            var letters = /[a-zA-Z]/g;
            if (phone.value.match(letters)) {
                document.getElementById("phoneinvalid1").style.display = "block";
            } else {
                document.getElementById("phoneinvalid1").style.display = "none";
            }
            var specs = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
            if (phone.value.match(specs)) {
                document.getElementById("phoneinvalid2").style.display = "block";
            } else {
                document.getElementById("phoneinvalid2").style.display = "none";
            }
            if (phone.value.length !== 10) {
                document.getElementById("phoneinvalid3").style.display = "block";
            } else {
                document.getElementById("phoneinvalid3").style.display = "none";
            }
            if (phone.value.startsWith("03") || phone.value.startsWith("05") || phone.value.startsWith("07") || phone.value.startsWith("08") || phone.value.startsWith("09")) {
                document.getElementById("phoneinvalid4").style.display = "none";
            } else {
                document.getElementById("phoneinvalid4").style.display = "block";
            }
        }
        
        email.onkeyup = function () {
                if (email.value.includes('@') === true) {
                    document.getElementById("emailinvalid").style.display = "none";
                } else {
                    document.getElementById("emailinvalid").style.display = "block";
                }
            }


    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</html>

