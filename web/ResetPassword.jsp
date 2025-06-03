<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Đặt lại mật khẩu</title>
        <style>
            body {
                margin: 0;
                padding: 0;
                font-family: 'Segoe UI', sans-serif;
                background: linear-gradient(to right, #74ebd5, #acb6e5);
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .container {
                background-color: #ffffff;
                border-radius: 20px;
                padding: 40px 30px;
                box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
                width: 400px;
                text-align: center;
                animation: fadeIn 1s ease;
            }

            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(-20px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }

            h2 {
                color: #333333;
                margin-bottom: 25px;
            }

            input[type="password"] {
                width: 100%;
                padding: 12px 15px;
                margin: 12px 0;
                border: 1px solid #ddd;
                border-radius: 8px;
                font-size: 15px;
            }

            input[type="submit"] {
                width: 100%;
                padding: 12px;
                background: linear-gradient(to right, #00c6ff, #0072ff);
                border: none;
                color: white;
                font-size: 16px;
                border-radius: 8px;
                cursor: pointer;
                margin-top: 15px;
                transition: background 0.3s ease;
            }

            input[type="submit"]:hover {
                background: linear-gradient(to right, #0072ff, #00c6ff);
            }

            .password-requirements {
                text-align: left;
                background-color: #f3f8ff;
                border: 1px solid #cce0ff;
                border-radius: 10px;
                padding: 15px;
                margin-top: 10px;
                font-size: 14px;
                color: #333;
            }

            .password-requirements h4 {
                margin-top: 0;
                color: #0066cc;
            }

            .password-requirements ul {
                padding-left: 20px;
            }

            .footer {
                margin-top: 20px;
                font-size: 13px;
                color: #666666;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2>🔒 Reset Password</h2>
            <p style="color: red">${error}</p>
            <form action="resetpassword" method="post">
                <input type="password" name="newPassword" placeholder="new password" required />

                <div class="password-requirements">
                    <h4>Requirement Password:</h4>
                    <ul>
                        <li>At least 8 characters</li>
                        <li>Contains at least 1 uppercase letter</li>
                        <li>Contains at least 1 lowercase letter</li>
                        <li>Contains at least 1 number</li>
                        <li>Contains at least 1 special character</li>
                    </ul>
                </div>

                <input type="password" name="confirmPassword" placeholder="confirm password" required />
                <input type="submit" value="Xác nhận" />
            </form>
            <div class="footer">
                © 2025 Safety and Security
            </div>
        </div>

    </body>
</html>
