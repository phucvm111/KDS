<%-- 
    Document   : login_test
    Created on : Jul 7, 2022, 8:23:55 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="assets/css/homepage.css"/>
        
        <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare</title>
        <script src="https://kit.fontawesome.com/28a1dc3e10.js" crossorigin="anonymous"></script>
        <script src="assets/js/homepage.js"></script>
    </head>
    <style>
        .btn-outline-dark:hover {
            color: #fff;
            background-color: #343a40;
            border-color: #343a40;

        }

        .btn {
            display: inline-block;
            font-weight: 400;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            border: 1px solid transparent;
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            line-height: 1.5;
            border-radius: 0.25rem;
            transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
        }
    </style>
    <body>
        <div id="bg">
            <img src="assets/image/login.jpg" alt="">
        </div>
        <div class="container" id="container">
            <div class="form-container sign-in-container">
                <form action="login" method="POST">
                    <h1>Sign in</h1>
                    <div class="social-container">
                        <a class="btn btn-outline-dark" href="http://localhost:5000/auth/google" role="button"
                           style="text-transform:none; width: 290px">
                            <img width="20px" style="margin-bottom:3px; margin-right:5px" alt="Google sign-in"
                                 src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/512px-Google_%22G%22_Logo.svg.png" />
                            Login with Google
                        </a>
                      
                    </div>
                    <span>or use your account</span>
                    <input type="text" name="email" placeholder="Email" required=""/><br />
                    <input type="password" name="password" placeholder="Password" required=""/><br />
                    <!--<br />-->
                    <!--<a href="#">Forgot your password?</a>-->
                    <!--<c:if test="${requestScope.error != null}">-->
                    <p style="color: red">${requestScope.error}</p>
                    <!--</c:if>-->

                    <!--<br/>-->
                    <button>Sign In</button>
                     <a href="ForgetPassword.jsp" style="color: blue; text-decoration: underline;">Forget Password</a>

                </form>
                   
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="ghost" id="signUp">
                            <a href="register">Sign Up</a>
                        </button>
                        
                       
                        
                    </div>
                </div>
            </div>
        </div>


    </body>
</html>
