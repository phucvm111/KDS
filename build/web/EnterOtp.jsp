<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter OTP</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
            width: 350px;
        }
        input[type="text"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        input[type="submit"] {
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #1e7e34;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Enter OTP</h2>
    <form action="enterotp" method="post">
        <label>Enter the OTP sent to your email:</label>
        <input type="text" name="otpinput" required placeholder="Enter OTP">
        <p style="color: red">${optfalse}</p>
        <input type="submit" value="Verify OTP">
    </form>
</div>
</body>
</html>
