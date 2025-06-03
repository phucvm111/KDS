<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đổi Mật Khẩu</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                min-height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 20px;
            }

            .container {
                background: white;
                border-radius: 20px;
                box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
                padding: 40px;
                width: 100%;
                max-width: 450px;
                position: relative;
                overflow: hidden;
            }

            .container::before {
                content: '';
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                height: 5px;
                background: linear-gradient(90deg, #667eea, #764ba2);
            }

            .header {
                text-align: center;
                margin-bottom: 30px;
            }

            .header h1 {
                color: #333;
                font-size: 28px;
                font-weight: 600;
                margin-bottom: 10px;
            }

            .header p {
                color: #666;
                font-size: 14px;
            }

            .form-group {
                margin-bottom: 20px;
                position: relative;
            }

            .form-group label {
                display: block;
                margin-bottom: 8px;
                color: #333;
                font-weight: 500;
                font-size: 14px;
            }

            .form-group input {
                width: 100%;
                padding: 15px 20px;
                border: 2px solid #e1e5e9;
                border-radius: 10px;
                font-size: 16px;
                transition: all 0.3s ease;
                background: #f8f9fa;
            }

            .form-group input:focus {
                outline: none;
                border-color: #667eea;
                background: white;
                box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
            }



            .btn {
                width: 100%;
                padding: 15px;
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                color: white;
                border: none;
                border-radius: 10px;
                font-size: 16px;
                font-weight: 600;
                cursor: pointer;
                transition: all 0.3s ease;
                margin-top: 10px;
            }

            .btn:hover {
                transform: translateY(-2px);
                box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
            }

            .btn:active {
                transform: translateY(0);
            }

            .alert {
                padding: 12px 15px;
                border-radius: 8px;
                margin-bottom: 20px;
                font-size: 14px;
            }

            .alert-success {
                background-color: #d4edda;
                border: 1px solid #c3e6cb;
                color: #155724;
            }

            .alert-error {
                background-color: #f8d7da;
                border: 1px solid #f5c6cb;
                color: #721c24;
            }

            .password-requirements {
                background: #f8f9fa;
                border-radius: 8px;
                padding: 15px;
                margin-top: 15px;
                font-size: 13px;
            }

            .password-requirements h4 {
                color: #333;
                margin-bottom: 8px;
                font-size: 14px;
            }

            .password-requirements ul {
                list-style: none;
                padding-left: 0;
            }

            .password-requirements li {
                color: #666;
                margin-bottom: 4px;
                position: relative;
                padding-left: 20px;
            }

            .password-requirements li::before {
                content: '•';
                color: #667eea;
                position: absolute;
                left: 0;
            }

            .back-link {
                text-align: center;
                margin-top: 20px;
            }

            .back-link a {
                color: #667eea;
                text-decoration: none;
                font-size: 14px;
                transition: color 0.3s ease;
            }

            .back-link a:hover {
                color: #764ba2;
                text-decoration: underline;
            }

            @media (max-width: 480px) {
                .container {
                    padding: 30px 20px;
                }

                .header h1 {
                    font-size: 24px;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <h1>Đổi Mật Khẩu</h1>
                <p>Vui lòng nhập thông tin để thay đổi mật khẩu của bạn</p>
            </div>

            <!-- Hiển thị thông báo -->


            <c:if test="${not empty changepassfalse}">
                <p style="color: red">${changepassfalse}</p>
            </c:if>

            <c:if test="${not empty changepasssucess}">
                <c:remove var="changepassfalse"/>
                <p style="color: green">${changepasssucess}</p>
            </c:if>





            <form action="changepassword" method="post" id="changePasswordForm">
                <div class="form-group">
                    <label for="currentPassword">Mật khẩu hiện tại *</label>
                    <input type="password" id="currentPassword" name="currentPassword" required>
                </div>

                <div class="form-group">
                    <label for="newPassword">Mật khẩu mới *</label>
                    <input type="password" id="newPassword" name="newPassword" required>
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Xác nhận mật khẩu mới *</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                </div>

                <div class="password-requirements">
                    <h4>Yêu cầu mật khẩu:</h4>
                    <ul>
                        <li>Ít nhất 8 ký tự</li>
                        <li>Chứa ít nhất 1 chữ hoa</li>
                        <li>Chứa ít nhất 1 chữ thường</li>
                        <li>Chứa ít nhất 1 số</li>
                        <li>Chứa ít nhất 1 ký tự đặc biệt</li>
                    </ul>
                </div>

                <button type="submit" class="btn">Đổi Mật Khẩu </button>
            </form>


            
            <c:set var="backLink" value="listschedule" />
            <c:if test="${sessionScope.account.role.roleID == 3}">
                <c:set var="backLink" value="childdetailservlet" />
            </c:if>
            <c:if test="${sessionScope.account.role.roleID == 2}">
                <c:set var="backLink" value="attendance" />
            </c:if>

            <div class="back-link">
                <a href="${backLink}">← Quay lại trang cá nhân</a>
            </div>

        </div>
    </body>
</html>
