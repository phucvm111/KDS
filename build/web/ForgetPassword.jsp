<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            font-family: 'Poppins', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 1rem;
        }

        .container {
            background: #fff;
            padding: 2.5rem 2rem;
            border-radius: 1rem;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            max-width: 450px;
            width: 100%;
            text-align: center;
            animation: fadeIn 0.6s ease-in-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h2 {
            font-size: 1.8rem;
            font-weight: 600;
            color: #2c3e50;
            margin-bottom: 1.5rem;
        }

        .input-group {
            position: relative;
            margin-bottom: 1.5rem;
        }

        label {
            font-size: 0.9rem;
            color: #555;
            text-align: left;
            display: block;
            margin-bottom: 0.5rem;
        }

        input[type="email"] {
            width: 100%;
            padding: 0.75rem 1rem 0.75rem 2.5rem;
            border: 1px solid #ddd;
            border-radius: 0.5rem;
            font-size: 1rem;
            transition: border-color 0.3s, box-shadow 0.3s;
            background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="%23999" viewBox="0 0 16 16"><path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v.6l7 4.9 7-4.9V4a1 1 0 0 0-1-1H2zm13 2.3l-6.9 4.8a1 1 0 0 1-1.2 0L1 5.3V12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V5.3z"/></svg>') no-repeat 0.75rem center;
            background-size: 1rem;
        }

        input[type="email"]:focus {
            border-color: #007bff;
            box-shadow: 0 0 8px rgba(0, 123, 255, 0.2);
            outline: none;
        }

        input[type="email"]:valid {
            border-color: #28a745;
        }

        input[type="submit"] {
            width: 100%;
            padding: 0.85rem;
            background: #007bff;
            border: none;
            color: #fff;
            font-size: 1rem;
            font-weight: 600;
            border-radius: 0.5rem;
            cursor: pointer;
            transition: background 0.3s, transform 0.2s;
        }

        input[type="submit"]:hover {
            background: #0056b3;
            transform: translateY(-2px);
        }

        input[type="submit"]:disabled {
            background: #ccc;
            cursor: not-allowed;
        }

        .error-message {
            color: #e74c3c;
            font-size: 0.85rem;
            margin-bottom: 1rem;
            animation: shake 0.3s ease-in-out;
        }

        @keyframes shake {
            0%, 100% { transform: translateX(0); }
            25% { transform: translateX(-5px); }
            50% { transform: translateX(5px); }
            75% { transform: translateX(-5px); }
        }

        @media (max-width: 400px) {
            .container {
                padding: 1.5rem 1rem;
            }

            h2 {
                font-size: 1.5rem;
            }

            input[type="email"], input[type="submit"] {
                font-size: 0.9rem;
            }
        }
    </style>
</head>
<body>
<div class="container" role="main">
    <h2>🔐 Forgot Your Password?</h2>
    <form action="sendotp" method="post" id="forgot-password-form" aria-label="Forgot Password Form">
        <div class="input-group">
            <label for="email">Enter your email address</label>
            <input type="email" id="email" name="email" required placeholder="example@gmail.com" aria-describedby="email-error">
        </div>
        
        <c:if test="${not empty notsend}">
            <div class="error-message" id="email-error">${notsend}</div>
        </c:if>
        
        <input type="submit" value="Send OTP" id="submit-btn">
    </form>
</div>
<script>
    const form = document.getElementById('forgot-password-form');
    const submitBtn = document.getElementById('submit-btn');

    form.addEventListener('submit', () => {
        submitBtn.disabled = true;
        submitBtn.value = 'Sending...';
    });
</script>
</body>
</html>