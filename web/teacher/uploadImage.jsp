<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Tải ảnh hoạt động</title>
    <link rel="stylesheet" href="../teacher/css/teacherhome.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f8fc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .upload-container {
            background: #ffffff;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 400px;
        }

        .upload-container h2 {
            margin-bottom: 20px;
            color: #333;
        }

        input[type="file"] {
            display: block;
            margin: 20px auto;
            padding: 8px;
        }

        input[type="submit"] {
            background-color: #6b48ff;
            color: white;
            border: none;
            padding: 12px 25px;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #593ae0;
        }
    </style>
</head>
<body>
    <div class="upload-container">
        <h2>📤 Tải ảnh hoạt động lớp</h2>
        <form action="${pageContext.request.contextPath}/teacher/uploadImage" method="post" enctype="multipart/form-data">
            <input type="file" name="imageFile" accept="image/*" required />
            <input type="submit" value="Tải lên" />
        </form>
    </div>
</body>
</html>
