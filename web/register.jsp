<%-- 
    Document   : login
    Created on : May 30, 2022, 9:12:30 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
              integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css"
              integrity="sha512-Oy+sz5W86PK0ZIkawrG0iv7XwWhYecM3exvUtMKNJMekGFJtVAhibhRPTpmyTj8+lJCkmWfnpxKgT2OopquBHA=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            * {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0px;
                padding: 0px;
                box-sizing: border-box;
            }

            body,
            html {
                height: 100%;
            }

            /*---------------------------------------------*/
            a {
                text-decoration: none;
                font-size: 14px;
                line-height: 1.7;
                color: #666666;
                margin: 0px;
                transition: all 0.4s;
                -webkit-transition: all 0.4s;
                -o-transition: all 0.4s;
                -moz-transition: all 0.4s;
            }

            a:focus {
                outline: none !important;
            }

            a:hover {
                text-decoration: none;
                color: #c80000;
            }

            /*---------------------------------------------*/
            h1,
            h2,
            h3,
            h4,
            h5,
            h6 {
                margin: 0px;
            }

            p {
                font-size: 14px;
                line-height: 1.7;
                color: #666666;
                margin: 0px;
            }

            ul,
            li {
                margin: 0px;
                list-style-type: none;
            }

            /*---------------------------------------------*/
            input {
                outline: none;
                border: none;
            }

            textarea {
                outline: none;
                border: none;
            }

            textarea:focus,
            input:focus {
                border-color: transparent !important;
            }

            input:focus::-webkit-input-placeholder {
                color: transparent;
            }

            input:focus:-moz-placeholder {
                color: transparent;
            }

            input:focus::-moz-placeholder {
                color: transparent;
            }

            input:focus:-ms-input-placeholder {
                color: transparent;
            }

            textarea:focus::-webkit-input-placeholder {
                color: transparent;
            }

            textarea:focus:-moz-placeholder {
                color: transparent;
            }

            textarea:focus::-moz-placeholder {
                color: transparent;
            }

            textarea:focus:-ms-input-placeholder {
                color: transparent;
            }

            input::-webkit-input-placeholder {
                color: #999999;
            }

            input:-moz-placeholder {
                color: #999999;
            }

            input::-moz-placeholder {
                color: #999999;
            }

            input:-ms-input-placeholder {
                color: #999999;
            }

            textarea::-webkit-input-placeholder {
                color: #999999;
            }

            textarea:-moz-placeholder {
                color: #999999;
            }

            textarea::-moz-placeholder {
                color: #999999;
            }

            textarea:-ms-input-placeholder {
                color: #999999;
            }

            /*---------------------------------------------*/
            button {
                outline: none !important;
                border: none;
                background: transparent;
            }

            button:hover {
                cursor: pointer;
            }

            iframe {
                border: none !important;
            }

            /*//////////////////////////////////////////////////////////////////
                [ Utility ]*/
            .txt1 {
                font-size: 13px;
                line-height: 1.5;
                color: #999999;
            }

            .txt2 {
                font-size: 13px;
                line-height: 1.5;
                color: #666666;
            }

            /*//////////////////////////////////////////////////////////////////
                [ login ]*/

            .limiter {
                width: 100%;
                margin: 0 auto;
            }

            .container-login100 {
                /*background: url(../img/2.jpg);*/
                width: 100%;
                min-height: 100vh;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                align-items: center;
                padding: 15px;
                /*                background: url('./assests/img/cover.jpg');*/
                background-color: red;
                background-size: cover;
                background-repeat: no-repeat;
                background-position: top;
                box-shadow: inset 0 0 0 2000px rgb(0 0 0 / 50%);
                background: url("assets/image/register.jpg");
            }

            .symbol-input100 {
                font-size: 15px;

                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                align-items: center;
                position: absolute;
                border-radius: 25px;
                bottom: 0;
                left: 0;
                width: 100%;
                height: 100%;
                padding-left: 35px;
                padding-top: 3px;
                pointer-events: none;
                color: #666666;

                -webkit-transition: all 0.4s;
                -o-transition: all 0.4s;
                -moz-transition: all 0.4s;
                transition: all 0.4s;
            }

            .wrap-login100 {
                width: 960px;
                background: #fff;
                border-radius: 10px;
                overflow: hidden;

                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                flex-wrap: wrap;
                align-items: center;
                justify-content: space-evenly;
                padding: 64px 0px;
            }

            /*------------------------------------------------------------------
            /*------------------------------------------------------------------
                [  ]*/
            .login100-form {
                width: 290px;
            }

            .login100-form-title {
                font-size: 30px;
                color: #333333;
                line-height: 1.2;
                text-align: center;

                /* margin-top: -60px; */
                width: 100%;
                display: block;
                padding-bottom: 54px;
            }

            /*---------------------------------------------*/
            .wrap-input100 {
                position: relative;
                width: 100%;
                z-index: 1;
                margin-bottom: 10px;
            }

            .input100 {
                font-size: 15px;
                line-height: 1.5;
                color: #666666;

                display: block;
                width: 100%;
                background: #e6e6e6;
                height: 50px;
                border-radius: 25px;
                padding: 0 0px 0 62px;
            }

            .wrap-input101 {
                display: flex;
                position: relative;
                width: 100%;
                z-index: 1;
                margin-bottom: 10px;
            }

            select {
                font-size: 15px;
                line-height: 1.5;
                display: block;
                width: 100%;
                background: #e6e6e6;
                height: 50px;
                border-radius: 25px;
                padding: 0 0px 0 62px;
                /*for firefox*/
                -moz-appearance: none;
                /*for chrome*/
                -webkit-appearance: none;
                appearance: none;
                border: none;
                color: #808080cc;
            }

            .radio-toolbar {
                margin: 10px;
            }

            .radio-toolbar input[type="radio"] {
                opacity: 0;
                position: fixed;
                width: 0;
            }

            .radio-toolbar label {
                display: inline-block;
                background-color: #ddd;
                padding: 10px 20px;
                font-family: sans-serif, Arial;
                font-size: 16px;
                border: 2px solid #444;
                border-radius: 4px;
                margin-left: 22px;
            }

            .radio-toolbar label:hover {
                background-color: #dfd;
            }

            .radio-toolbar input[type="radio"]:focus+label {
                border: 2px dashed #444;
            }

            .radio-toolbar input[type="radio"]:checked+label {
                background-color: #bfb;
                border-color: #4c4;
            }

            input[type="date"]::-webkit-inner-spin-button,
            input[type="date"]::-webkit-calendar-picker-indicator {
                display: none;
                -webkit-appearance: none;
            }

            /*------------------------------------------------------------------
                [ Focus ]*/
            .focus-input100 {
                display: block;
                position: absolute;
                border-radius: 25px;
                bottom: 0;
                left: 0;
                z-index: -1;
                width: 100%;
                height: 100%;
                box-shadow: 0px 0px 0px 0px;
                color: #c80000;
            }

            .input100:focus+.focus-input100 {
                -webkit-animation: anim-shadow 0.5s ease-in-out forwards;
                animation: anim-shadow 0.5s ease-in-out forwards;
            }

            /*------------------------------------------------------------------
                [ Button ]*/
            .container-login100-form-btn {
                width: 100%;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                padding-top: 20px;
            }

            .login100-form-btn {
                font-size: 15px;
                line-height: 1.5;
                color: #fff;
                text-transform: uppercase;

                width: 100%;
                height: 50px;
                border-radius: 25px;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 0 25px;

                -webkit-transition: all 0.4s;
                -o-transition: all 0.4s;
                -moz-transition: all 0.4s;
                transition: all 0.4s;
            }

            .login100-form-btn:hover {
                background: #333333;
            }

            /*------------------------------------------------------------------
                [ Responsive ]*/

            @media (max-width: 992px) {
                .wrap-login100 {
                    padding: 177px 90px 33px 85px;
                }

                .login100-pic {
                    width: 35%;

                }

                .login100-form {
                    width: 50%;
                }
            }

            @media (max-width: 768px) {
                .wrap-login100 {
                    padding: 100px 80px 33px 80px;
                }

                .login100-pic {
                    display: none;
                }

                .login100-form {
                    width: 100%;
                }
            }

            @media (max-width: 576px) {
                .wrap-login100 {
                    padding: 100px 15px 33px 15px;
                }
            }

            /*------------------------------------------------------------------
                [ Alert validate ]*/

            .validate-input {
                position: relative;
            }

            .alert-validate::before {
                content: attr(data-validate);
                position: absolute;
                max-width: 70%;
                background-color: white;
                border: 1px solid #c80000;
                border-radius: 13px;
                padding: 4px 25px 4px 10px;
                top: 50%;
                -webkit-transform: translateY(-50%);
                -moz-transform: translateY(-50%);
                -ms-transform: translateY(-50%);
                -o-transform: translateY(-50%);
                transform: translateY(-50%);
                right: 8px;
                pointer-events: none;

                color: #c80000;
                size: 13px;
                line-height: 1.4;
                text-align: left;

                visibility: hidden;
                opacity: 0;

                -webkit-transition: opacity 0.4s;
                -o-transition: opacity 0.4s;
                -moz-transition: opacity 0.4s;
                transition: opacity 0.4s;
            }

            .alert-validate::after {
                content: '\f06a';
                font-family: FontAwesome;
                display: block;
                position: absolute;
                color: #c80000;
                font-size: 15px;
                top: 50%;
                -webkit-transform: translateY(-50%);
                -moz-transform: translateY(-50%);
                -ms-transform: translateY(-50%);
                -o-transform: translateY(-50%);
                transform: translateY(-50%);
                right: 13px;
            }

            .alert-validate:hover:before {
                visibility: visible;
                opacity: 1;
            }

            @media (max-width: 992px) {
                .alert-validate::before {
                    visibility: visible;
                    opacity: 1;
                }
            }

            #create-account {
                margin-top: 64px;
            }

            #forgot {
                margin-top: 16px;
            }

            #err {
                font-size: 16px;
                margin-bottom: 28px;
                margin-top: -28px;
                color: #dc3545;
            }

            .name {
                display: flex;
            }

            /* Chat Bubbles */
            .chat-bubbles {
                margin-left: -130px;
                margin-bottom: 315px;
            }

            .message p {
                padding: 10px;
                margin-bottom: 15px;
            }

            .message span {
                padding: 10px;
                margin-bottom: 15px;
                font-size: 14px;
                line-height: 1.7;
                color: #666666;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            #phoneinvalid {
                margin-bottom: 10px;
            }

            .bubbles {
                visibility: hidden;
                position: relative;
                background: rgb(207, 199, 199);
                color: #222;
                border-radius: 3px;
                margin-left: 20px;
            }

            .bubbles::after {
                content: "";
                display: block;
                position: absolute;
                left: -15px;
                top: 15px;
                width: 0;
                height: 0;
                border-top: 8px solid transparent;
                border-bottom: 8px solid transparent;
                border-right: 15px solid rgb(207, 199, 199);
            }

            #phoneinvalid.message.bubbles::after {
                top: 40px;
            }
        </style>

    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <form class="login100-form validate-form" method="post" action="register">
                        <span class="login100-form-title"> Member Register </span>
                        <p class="text-center danger" id="err">
                            ${error1}
                            ${error4}
                        </p>
                        <!-- Name -->
                        <div class="wrap-input100 validate-input">
                            <input class="input100" type="text" pattern="[a-zA-Z]+" title="Alphabetical letters only"
                                   name="fname" id="fname" placeholder="First Name" required />
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa-solid fa-circle-user"></i>
                            </span>
                        </div>

                        <div class="wrap-input100 validate-input">
                            <input class="input100" type="text" pattern="[a-zA-Z]+" title="Alphabetical letters only"
                                   name="lname" id="lname" placeholder="Last Name" required />
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa-solid fa-circle-user"></i>
                            </span>
                        </div>

                        <!-- Gender -->
                        <div class="wrap-input100 validate-input">
                            <select name="gender">
                                <!--<option selected disabled>Gender</option>-->
                                <option selected value="male">Male</option>
                                <option value="female">Female</option>
                            </select>
                        </div>
                        <!-- Address -->
                        <div class="wrap-input100 validate-input">
                            <input class="input100" type="text" name="address" placeholder="Address" required />
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa-solid fa-house"></i>
                            </span>
                        </div>
                        <!-- Phone -->
                        <div class="wrap-input100 validate-input">
                            <input class="input100" type="text" pattern="(0)(3|5|7|8|9)+([0-9]{8})" id="phone"
                                   title="Must start with 09/03/07/08/05 and contain 10 characters" name="phone"
                                   placeholder="Phone" required />
                            <span class="focus-input100"></span>
                            <span class="symbol-input100"><i class="fa-solid fa-mobile-button"></i>
                            </span>
                        </div>

                        <!-- Email -->
                        <div class="wrap-input100 validate-input">
                            <input class="input100" type="text" name="email" id="email" placeholder="Email"
                                   title="Must contain @" required />
                            <span class="focus-input100"></span>
                            <span class="symbol-input100"><i class="fa-solid fa-envelope"></i>
                            </span>

                        </div>
                        <!-- DOB -->
                        <div class="wrap-input100 validate-input">
                            <input class="input100" type="date" name="dob" id="dob" required />
                            <span class="focus-input100"></span>
                            <span class="symbol-input100"><i class="fa-regular fa-calendar"></i>
                            </span>
                        </div>
                        <!-- Password -->
                        <div class="wrap-input100 validate-input">
                            <input class="input100" type="password" name="password" id="password" placeholder="Password"
                                   required />
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-lock" aria-hidden="true"></i>
                            </span>
                        </div>
                        <!-- Re-password -->
                        <div class="wrap-input100 validate-input">
                            <input class="input100" type="password" name="password2" id="password2"
                                   placeholder="Re-enter password" required />
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-lock" aria-hidden="true"></i>
                            </span>
                        </div>
                        <div class="container-login100-form-btn">
                            <button type="submit" class="btn btn-danger login100-form-btn">Create Account</button>
                        </div>
                        <hr />
                        <div class="row">
                            <div class="col-md-3">
                                <a class="btn btn-outline-dark" href="http://localhost:5000/auth/google" role="button"
                                   style="text-transform:none; width: 290px">
                                    <img width="20px" style="margin-bottom:3px; margin-right:5px" alt="Google sign-in"
                                         src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/512px-Google_%22G%22_Logo.svg.png" />
                                    Sign up with Google
                                </a>
                            </div>
                        </div>

                        <div class="text-center p-t-136" id="create-account">
                            <a class="txt2" href="./login">
                                <i class="fa fa-long-arrow-left m-l-5" aria-hidden="true"></i>
                                Already have account? Login
                            </a>
                        </div>
                    </form>
                    <div class="chat-bubbles">
                        <div class="message bubbles" id="fnameinvalid">
                            <p>Names cannot contain numeric or special characters</p>
                        </div>
                        <div class="message bubbles" id="lnameinvalid">
                            <p>Names cannot contain numeric or special characters</p>
                        </div>
                        <div class="message bubbles">
                            <br /><br /><br /><br />
                        </div>
                        <div class="message bubbles" id="phoneinvalid">
                            <span>Phone numbers cannot contain alphabetical characters, </span><br />
                            <span> special characters, must start with 09/03/07/08/05 and</span><br />
                            <span> contain exactly 10 characters</span>
                        </div>
                        <div class="message bubbles" id="emailinvalid">
                            <p>Emails must contain '@'</p>
                        </div>

                        <div class="message bubbles" id="dobinvalid">
                            <p>Year must be between 1900 and current year</p>
                        </div>
                        <div class="message bubbles" id="passwordinvalid">
                            <p id="password-length">Mật khẩu phải có ít nhất 8 ký tự.</p>
                            <p id="password-uppercase">Mật khẩu phải có ít nhất 1 chữ cái viết hoa.</p>
                            <p id="password-number">Mật khẩu phải có ít nhất 1 chữ số.</p>
                            <p id="password-special">Mật khẩu phải có ít nhất 1 ký tự đặc biệt.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JS-->
        <script>
            var fname = document.getElementById("fname");
            var lname = document.getElementById("lname");
            var phone = document.getElementById("phone");
            var email = document.getElementById("email");
            var password = document.getElementById("password");
            var DOB = document.getElementById("dob");
            var password = document.getElementById("password");
            var password2 = document.getElementById("password2")

            fname.onkeyup = function () {
                var numbers = /[0-9]/g;
                var specs = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
                if (fname.value.match(numbers) || fname.value.match(specs)) {
                    document.getElementById("fnameinvalid").style.visibility = "visible";
                } else {
                    document.getElementById("fnameinvalid").style.visibility = "hidden";
                }

            }

            lname.onkeyup = function () {
                var numbers = /[0-9]/g;
                var specs = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
                if (lname.value.match(numbers) || lname.value.match(specs)) {
                    document.getElementById("lnameinvalid").style.visibility = "visible";
                } else {
                    document.getElementById("lnameinvalid").style.visibility = "hidden";
                }
            }

            phone.onkeyup = function () {
                var letters = /[a-zA-Z]/g;
                var specs = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;

                if (phone.value.startsWith("03") || phone.value.startsWith("05") || phone.value.startsWith("07") || phone.value.startsWith("08") || phone.value.startsWith("09")) {
                    if (phone.value.match(letters) || phone.value.match(specs) || phone.value.length !== 10) {
                        document.getElementById("phoneinvalid").style.visibility = "visible";
                    } else {
                        document.getElementById("phoneinvalid").style.visibility = "hidden";
                    }
                } else {
                    document.getElementById("phoneinvalid").style.visibility = "visible";
                }

                // if (phone.value.match(letters)) {
                //     document.getElementById("phoneinvalid1").style.display = "block";
                // } else {
                //     document.getElementById("phoneinvalid1").style.display = "none";
                // }
                // var specs = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
                // if (phone.value.match(specs)) {
                //     document.getElementById("phoneinvalid2").style.display = "block";
                // } else {
                //     document.getElementById("phoneinvalid2").style.display = "none";
                // }
                // if (phone.value.length !== 10) {
                //     document.getElementById("phoneinvalid3").style.display = "block";
                // } else {
                //     document.getElementById("phoneinvalid3").style.display = "none";
                // }
                // if (phone.value.startsWith("03") || phone.value.startsWith("05") || phone.value.startsWith("07") || phone.value.startsWith("08") || phone.value.startsWith("09")) {
                //     document.getElementById("phoneinvalid4").style.display = "none";
                // } else {
                //     document.getElementById("phoneinvalid4").style.display = "block";
                // }
            }

            email.onkeyup = function () {
                if (email.value.includes('@') === true) {
                    document.getElementById("emailinvalid").style.visibility = "hidden";
                } else {
                    document.getElementById("emailinvalid").style.visibility = "visible";
                }
            }

            // Trong phần JavaScript, sửa lại hàm kiểm tra DOB
            dob.onchange = function () {
                var dobDate = new Date(dob.value);
                var currentDate = new Date();
                var minDate = new Date('1900-01-01');

                // Kiểm tra năm hợp lệ (1900 <= năm <= năm hiện tại)
                if (dobDate < minDate || dobDate > currentDate || dob.value.substring(0, 4) === '0000') {
                    document.getElementById("dobinvalid").style.visibility = "visible";
                } else {
                    document.getElementById("dobinvalid").style.visibility = "hidden";
                }
            }
             password.onkeyup = function() {
                var lowerCaseLetters = /[a-z]/g;
                var upperCaseLetters = /[A-Z]/g;
                var numbers = /[0-9]/g;
                var specialCharacters = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/g;

                // Validate length
                if (password.value.length >= 8) {
                    document.getElementById("password-length").style.visibility = "hidden";
                } else {
                    document.getElementById("password-length").style.visibility = "visible";
                }

                // Validate uppercase letters
                if (password.value.match(upperCaseLetters)) {
                    document.getElementById("password-uppercase").style.visibility = "hidden";
                } else {
                    document.getElementById("password-uppercase").style.visibility = "visible";
                }

                // Validate numbers
                if (password.value.match(numbers)) {
                    document.getElementById("password-number").style.visibility = "hidden";
                } else {
                    document.getElementById("password-number").style.visibility = "visible";
                }

                // Validate special characters
                if (password.value.match(specialCharacters)) {
                    document.getElementById("password-special").style.visibility = "hidden";
                } else {
                    document.getElementById("password-special").style.visibility = "visible";
                }
            };

            // You might also want to add validation for password2 (re-enter password)
            // For example, to check if password and password2 match.
            password2.onkeyup = function() {
                if (password.value === password2.value) {
                    // Hide a "passwords do not match" message if you implement one
                    // document.getElementById("password-match").style.visibility = "hidden";
                } else {
                    // Show a "passwords do not match" message
                    // document.getElementById("password-match").style.visibility = "visible";
                }
            };


        </script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>
