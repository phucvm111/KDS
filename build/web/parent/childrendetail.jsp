<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png" />
        <title>ATKD ChildCare - Chi tiết trẻ</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css" />
        <script src="https://kit.fontawesome.com/67b5c45612.js" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet" />

        <style>
            * {
                font-family: 'Poppins', sans-serif;
            }

            body {
                background-color: #f5f7fa;
                margin: 0;
                padding: 0;
            }

            .wrapper {
                display: flex;
                min-height: 100vh;
            }

            .right-side {
                flex: 1;
                padding: 30px;
            }

            .child-details-container {
                background: #fff;
                padding: 30px;
                border-radius: 15px;
                box-shadow: 0 5px 20px rgba(0, 0, 0, 0.05);
            }

            .page-title {
                text-align: center;
                margin-bottom: 30px;
                font-size: 28px;
                font-weight: 600;
                color: #333;
                position: relative;
            }

            .page-title:after {
                content: '';
                position: absolute;
                width: 60px;
                height: 4px;
                background: linear-gradient(90deg, #71B7E6, #9B59B6);
                bottom: -10px;
                left: 50%;
                transform: translateX(-50%);
                border-radius: 2px;
            }

            .alert {
                padding: 15px;
                margin-bottom: 20px;
                border-radius: 8px;
                display: flex;
                align-items: center;
            }

            .alert-success {
                background-color: #d1f7dd;
                color: #0a6b39;
                border-left: 4px solid #0a6b39;
            }

            .alert-danger {
                background-color: #ffe5e5;
                color: #cf0000;
                border-left: 4px solid #cf0000;
            }

            .alert i {
                margin-right: 10px;
                font-size: 20px;
            }

            .profile-section {
                display: flex;
                flex-direction: column;
                gap: 30px;
            }

            .child-header {
                display: flex;
                align-items: center;
                background: linear-gradient(135deg, #71B7E6, #9B59B6);
                padding: 30px;
                border-radius: 15px;
                color: white;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            }

            .child-image-wrapper {
                position: relative;
                margin-right: 30px;
            }

            .child-image-large {
                width: 150px;
                height: 150px;
                border-radius: 50%;
                object-fit: cover;
                border: 5px solid white;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            }

            .gender-badge {
                position: absolute;
                bottom: 10px;
                right: 10px;
                width: 30px;
                height: 30px;
                background: white;
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
            }

            .male-icon {
                color: #71B7E6;
            }

            .female-icon {
                color: #FF6F91;
            }

            .child-name-large {
                font-size: 32px;
                font-weight: bold;
                margin-bottom: 5px;
                text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            .child-class {
                font-size: 18px;
                opacity: 0.9;
            }

            .form-container {
                background: white;
                border-radius: 15px;
                padding: 30px;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
            }

            .form-grid {
                display: grid;
                grid-template-columns: 1fr 1fr;
                gap: 25px;
            }

            @media (max-width: 768px) {
                .form-grid {
                    grid-template-columns: 1fr;
                }
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-label {
                display: block;
                font-weight: 500;
                margin-bottom: 8px;
                color: #555;
            }

            .form-control {
                width: 100%;
                padding: 12px 15px;
                border: 1px solid #ddd;
                border-radius: 8px;
                font-size: 16px;
                transition: all 0.3s;
                box-sizing: border-box;
            }

            .form-control:focus {
                border-color: #9B59B6;
                box-shadow: 0 0 0 3px rgba(155, 89, 182, 0.2);
                outline: none;
            }

            .radio-group {
                display: flex;
                gap: 20px;
            }

            .radio-option {
                display: flex;
                align-items: center;
                cursor: pointer;
            }

            .radio-option input[type="radio"] {
                margin-right: 8px;
                cursor: pointer;
                width: 18px;
                height: 18px;
            }

            .file-upload {
                position: relative;
            }

            .file-upload-label {
                display: block;
                padding: 12px 15px;
                background: #f5f7fa;
                border: 1px dashed #ddd;
                border-radius: 8px;
                text-align: center;
                cursor: pointer;
                transition: all 0.3s;
            }

            .file-upload-label:hover {
                background: #eef1f5;
                border-color: #9B59B6;
            }

            .file-upload input[type="file"] {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                opacity: 0;
                cursor: pointer;
            }

            .file-upload-info {
                display: block;
                margin-top: 5px;
                font-size: 12px;
                color: #777;
            }

            .form-actions {
                margin-top: 20px;
            }

            .btn {
                padding: 8px 20px;
                border: 1px solid #ccc;
                background-color: white;
                color: #333;
                border-radius: 4px;
                cursor: pointer;
                margin-right: 10px;
            }

            .btn:hover {
                background-color: #f0f0f0;
            }
        </style>
    </head>

    <body>
        <div class="wrapper">
            <jsp:include page="/view/sidebarParent.jsp" />

            <div class="right-side">
                <div class="child-details-container">
                    <h1 class="page-title">Chi Tiết Hồ Sơ Trẻ</h1>

                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success">
                            <i class="fas fa-check-circle"></i>
                            ${successMessage}
                        </div>
                    </c:if>

                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">
                            <i class="fas fa-exclamation-circle"></i>
                            ${errorMessage}
                        </div>
                    </c:if>

                    <div class="profile-section">
                        <div class="child-header">
                            <div class="child-image-wrapper">
                                <c:choose>
                                    <c:when test="${not empty kindergartner.img}">
                                        <img src="${pageContext.request.contextPath}/uploads/${kindergartner.img}" alt="Child Photo" class="child-image-large">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${pageContext.request.contextPath}/parent/img/userImg/dummy-user-img.png" alt="Default Child Photo" class="child-image-large">
                                    </c:otherwise>
                                </c:choose>
                                <div class="gender-badge ${kindergartner.gender ? 'male-icon' : 'female-icon'}">
                                    <i class="fas ${kindergartner.gender ? 'fa-mars' : 'fa-venus'}"></i>
                                </div>
                            </div>
                            <div>
                                <div class="child-name-large">${kindergartner.first_name} ${kindergartner.last_name}</div>
                                <div class="child-class">Lớp: ${className}</div>
                            </div>
                        </div>

                        <div class="form-container">
                            <form action="${pageContext.request.contextPath}/childrendetail" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="kinder_id" value="${kindergartner.kinder_id}">

                                <div class="form-grid">
                                    <div class="form-group">
                                        <label class="form-label">Họ</label>
                                        <input type="text" name="first_name" class="form-control" value="${kindergartner.first_name}" required>
                                    </div>

                                    <div class="form-group">
                                        <label class="form-label">Tên</label>
                                        <input type="text" name="last_name" class="form-control" value="${kindergartner.last_name}" required>
                                    </div>

                                    <div class="form-group">
                                        <label class="form-label">Ngày sinh</label>
                                        <input type="date" name="dob" class="form-control" value="${kindergartner.dob}" required>
                                    </div>

                                    <div class="form-group">
                                        <label class="form-label">Giới tính</label>
                                        <div class="radio-group">
                                            <label class="radio-option">
                                                <input type="radio" name="gender" value="male" ${kindergartner.gender ? 'checked' : ''}> Nam
                                            </label>
                                            <label class="radio-option">
                                                <input type="radio" name="gender" value="female" ${!kindergartner.gender ? 'checked' : ''}> Nữ
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="form-label">Địa chỉ</label>
                                        <input type="text" name="address" class="form-control" value="${kindergartner.address}">
                                    </div>

                                    <div class="form-group">
                                        <label class="form-label">Số điện thoại phụ huynh</label>
                                        <input type="text" name="parentPhone" class="form-control" value="${kindergartner.parentPhone}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="form-label">Ảnh hồ sơ</label>
                                    <div class="file-upload">
                                        <label class="file-upload-label">
                                            <i class="fas fa-cloud-upload-alt"></i> Chọn ảnh mới
                                            <input type="file" name="img" accept="image/*">
                                        </label>
                                        <span class="file-upload-info">Để trống để giữ hình ảnh hiện tại</span>
                                    </div>
                                </div>

                                <div class="form-actions">
                                    <button type="submit" class="btn">Cập nhật</button>
                                    <button type="button" class="btn" onclick="window.location.href = '${pageContext.request.contextPath}/childrenlist'">Quay lại</button>
                                </div>

                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>

</html>
