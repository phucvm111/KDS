<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="/ATKD_Project/homepage/assets/image/logo2-removebg-preview.png">
        <title>>ATKD ChildCare - Update Child Profile</title>
        <link rel="stylesheet" href="/ATKD_Project/homepage/parent/css/parenthome.css">
        <link rel="stylesheet" href="/ATKD_Project/homepage/parent/css/childprofile.css">

    </head>

    <body>
        <div class="wrapper">
            <div class="home">
                <div class="left-side-menu">
                    <div class="vertical-menu">
                        <div class="user-welcome">
                            <img class="user-img" src="/ATKD_Project/homepage/parent/img/userImg/dummy-user-img.png" style="width: 80px; height: 80px;" alt="">
                            <p>${sessionScope.account.firstName} ${sessionScope.account.lastName}</p>
                        </div>
                        <div class="menu-item-container">
                            <ul class="item-lists">
                                <li class="menu-item current1">
                                    <a href="/ATKD_Project/homepage/childdetailservlet">Child Information</a>
                                </li>
                                <li class="menu-item">
                                    <a href="/ATKD_Project/homepage/parent/parentprofile.jsp">Parent Information</a>
                                </li>
                                <li class="menu-item">
                                    <a href="/ATKD_Project/homepage/parent/childregister.jsp">Child Register</a>
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
                                    <img src="/ATKD_Project/homepage/parent/img/userImg/download.png" alt="">
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
                                    <a href="childdetailcontrol?action=attendance&mainchildid=${mainchild.getKinder_id()}">Attendance</a>
                                </div>
                                <div class="profile option-item current">
                                    <a href="childdetailcontrol?action=childprofile&mainchildid=${mainchild.getKinder_id()}">Profile</a>
                                </div>
                            </div>
                                <form action="/ATKD_Project/homepage/childupdateservlet"  method="POST">                 
                            <div class="kid-profile_content">
                                <div class="class content-item wrap-input100 validate-input">
                                    <div class="item-title">
                                        <strong>First Name</strong>
                                    </div>
                                    <label for="exampleFormControlInput1" class="form-label"></label>
                                    <input type="text" class="class content-item" name="childfirstname" id="childfirstname" value="${mainchild.first_name}" pattern="[a-zA-Z]+" title="Alphabetical letters only" required="">
                                    <span class="focus-input100"></span>
                                </div>
                                <span class="invalid" id="fnameinvalid1" style="display: none;margin-left: 20%" >Name cannot contain
                                    numeric characters !</span>
                                <span class="invalid" id="fnameinvalid2" style="display: none;margin-left: 20%">Name cannot contain
                                    special characters !</span>  

                                <div class="class content-item">
                                    <div class="item-title">
                                        <strong>Last Name</strong>
                                    </div>
                                    <label for="exampleFormControlInput1" class="form-label"></label>
                                    <input type="text" class="class content-item" name="childlastname" id="childlastname" value="${mainchild.last_name}" pattern="[a-zA-Z]+" title="Alphabetical letters only" required="">
                                    <span class="focus-input100"></span>
                                </div>
                                <span class="invalid" id="fnameinvalid1" style="display: none;margin-left: 20%" >Name cannot contain
                                    numeric characters !</span>
                                <span class="invalid" id="fnameinvalid2" style="display: none;margin-left: 20%">Name cannot contain
                                    special characters !</span>
                                 
                            <div class="class content-item">
                                <div class="item-title">
                                    <strong>Date of birth</strong>
                                </div>
                                <input type="date"  class="class content-item" name="childdob" required="" value="${mainchild.dob}">
                            </div>
                            <div class="content-item">
                                <div class="item-title">
                                    <strong>Gender</strong>
                                </div>
                                <select style="width:570px; " class="class content-item" name ="childgender" id="childgender" value="
                                        <c:if test="${mainchild.gender == true }">
                                            <h1>Male</h1>
                                        </c:if>
                                        <c:if test="${mainchild.gender == false }">
                                            <h1>Female</h1>
                                        </c:if>"
                                        >
                                    <optgroup>
                                        <option value="male"><h1>Male</h1></option>
                                    <option value="female"><h1>Female</h1></option>
                                    </optgroup>
                                </select>    
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
                        
                        <div class="mb-6">
                            <input style="margin-left: 30%;margin-bottom: 20px" type="button" class="cancel_button"  onclick="window.location.replace('/ATKD_Project/homepage/parent/childprofile.jsp')" value="Cancel"/>
                            <input style="margin-left: 150px;margin-bottom: 20px" type="button" class="confirm_button" onclick="openPopup()" value="Confirm Update"/>
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
    <!--</form>-->
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
    var fname = document.getElementById("childfirstname");
    var lname = document.getElementById("childlastname");


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




</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</html>

