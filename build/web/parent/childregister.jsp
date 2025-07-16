<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png" />
        <title>ATKD ChildCare - Đăng ký trẻ</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css" />
        <style>
            /* === Biến màu chung === */
            :root {
                --primary-color: #6a5af9;
                --secondary-color: #00bcd4;
                --text-color: #333;
                --text-secondary: #6c757d;
                --background-color: #f8f9fa;
                --white-color: #fff;
                --border-color: #e0e0e0;
                --box-shadow: 0 4px 15px rgba(0, 0, 0, 0.07);
            }

            /* Right Side Content */
            .right-side {
                flex-grow: 1;
                padding: 40px;
                overflow-y: auto;
            }

            /* Profile Card Container */
            .profile-card {
                background-color: var(--white-color);
                border-radius: 16px;
                box-shadow: var(--box-shadow);
                overflow: hidden;
            }

            /* Header: Title + Log out */
            .profile-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 30px;
                background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
                color: var(--white-color);
            }

            .profile-header h1 {
                font-size: 2em;
                margin: 0;
            }

            .log-out_button {
                padding: 8px 20px;
                background-color: #f44336;
                color: var(--white-color);
                border: none;
                border-radius: 6px;
                cursor: pointer;
                font-weight: 600;
                transition: background-color 0.3s;
            }

            .log-out_button:hover {
                background-color: #d32f2f;
            }

            /* Form Content */
            .profile-content {
                padding: 30px;
                display: grid;
                grid-template-columns: repeat(2, 1fr);
                gap: 25px;
            }

            .content-item {
                display: flex;
                flex-direction: column;
            }

            .item-title {
                font-size: 0.9em;
                font-weight: 600;
                color: var(--text-secondary);
                margin-bottom: 8px;
            }

            .content-item input[type="text"],
            .content-item input[type="date"],
            .content-item input[type="file"],
            .content-item select {
                padding: 12px;
                border: 1px solid var(--border-color);
                border-radius: 8px;
                font-size: 1em;
                background-color: var(--background-color);
            }

            .content-item label {
                font-size: 1em;
                margin-right: 10px;
            }

            .invalid {
                font-size: 0.85em;
                color: red;
                margin-top: 5px;
            }

            /* Full-width for Confirm Button */
            .profile-actions {
                grid-column: 1 / -1;
                display: flex;
                justify-content: center;
            }

            .button {
                padding: 12px 30px;
                font-size: 1em;
                font-weight: 600;
                color: var(--white-color);
                background-color: var(--primary-color);
                border: none;
                border-radius: 8px;
                cursor: pointer;
                transition: background-color 0.3s, transform 0.2s;
            }

            .button:hover {
                background-color: #5548d9;
                transform: translateY(-2px);
            }

            /* Success & No Class Message */
            .success-message,
            .nochild {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
                padding: 20px;
                border-radius: 8px;
                margin-bottom: 30px;
            }

            .nochild {
                background-color: #fff3cd;
                color: #856404;
                border-color: #ffeeba;
            }

            /* Popup Confirm */
            .popup {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%) scale(0);
                background: var(--white-color);
                padding: 40px;
                border-radius: 16px;
                box-shadow: var(--box-shadow);
                text-align: center;
                z-index: 1000;
                transition: transform 0.3s ease-in-out;
            }

            .popup.open-popup {
                transform: translate(-50%, -50%) scale(1);
            }

            .popup img {
                width: 60px;
                margin-bottom: 20px;
            }

            .popup h2 {
                font-size: 1.5em;
                margin-bottom: 20px;
            }

            .confirm_button,
            .cancel_button {
                padding: 10px 25px;
                border: none;
                border-radius: 6px;
                font-weight: 600;
                cursor: pointer;
                margin: 5px;
            }

            .confirm_button {
                background-color: #28a745;
                color: var(--white-color);
            }

            .cancel_button {
                background-color: #dc3545;
                color: var(--white-color);
            }

            .confirm_button:hover {
                background-color: #218838;
            }

            .cancel_button:hover {
                background-color: #c82333;
            }

        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/view/sidebarParent.jsp" />

            <div class="right-side">

                <!-- Success message -->
                <c:if test="${not empty success}">
                    <div class="success-message">
                        <p>${success}</p>
                        <c:if test="${not empty registeredChild}">
                            <p>Đã đăng ký thành công cho trẻ: ${registeredChild.first_name} ${registeredChild.last_name}</p>
                        </c:if>
                    </div>
                </c:if>

                <!-- No class message -->
                <c:if test="${classlist.isEmpty()}">
                    <div class="nochild">
                        <h1>Hiện tại không có lớp học nào!</h1>
                        <h1>Vui lòng đợi đến khi có lớp học mới đăng ký</h1>
                    </div>
                </c:if>

                <!-- Register form -->
                <c:if test="${!classlist.isEmpty()}">
                    <div class="profile-card">
                        <div class="profile-header">
                            <h1>Đăng Ký Thông Tin Trẻ</h1>
                        </div>

                        <form action="${pageContext.request.contextPath}/childregister" method="POST" enctype="multipart/form-data">
                            <div class="profile-content">

                                <!-- Child First Name -->
                                <div class="content-item">
                                    <div class="item-title">Họ</div>
                                    <input type="text" id="ChildFirstName" name="ChildFirstName" pattern="[a-zA-Z]+" required title="Alphabetical letters only"/>
                                    <span class="invalid" id="fnameinvalid1" style="display:none;">Tên không được chứa số!</span>
                                    <span class="invalid" id="fnameinvalid2" style="display:none;">Tên không được chứa ký tự đặc biệt!</span>
                                </div>

                                <!-- Child Last Name -->
                                <div class="content-item">
                                    <div class="item-title">Tên</div>
                                    <input type="text" id="ChildLastName" name="ChildLastName" pattern="[a-zA-Z]+" required title="Alphabetical letters only"/>
                                    <span class="invalid" id="lnameinvalid1" style="display:none;">Tên không được chứa số!</span>
                                    <span class="invalid" id="lnameinvalid2" style="display:none;">Tên không được chứa ký tự đặc biệt!</span>
                                </div>

                                <!-- DOB -->
                                <div class="content-item">
                                    <div class="item-title">Ngày sinh</div>
                                    <input type="date" name="DOB" required />
                                </div>

                                <!-- Gender -->
                                <div class="content-item">
                                    <div class="item-title">Giới tính</div>
                                    <label>
                                        <input type="radio" name="flexRadioDefault" value="male" checked /> Nam
                                    </label>
                                    <label style="margin-left:20px;">
                                        <input type="radio" name="flexRadioDefault" value="female" /> Nữ
                                    </label>
                                </div>

                                <!-- Profile Image -->
                                <div class="content-item">
                                    <div class="item-title">Ảnh hồ sơ</div>
                                    <input type="file" name="profileImage" accept="image/*" />
                                </div>

                                <!-- Class Selection -->
                                <div class="content-item">
                                    <div class="item-title">Chọn lớp</div>
                                    <select name="register_classid">
                                        <c:forEach items="${requestScope.classlist}" var="c">
                                            <option value="${c.class_id}">${c.class_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Confirm Button -->
                                <div class="profile-actions">
                                    <input type="button" class="button" onclick="openPopup()" value="Xác nhận" />
                                </div>

                                <!-- Popup -->
                                <div class="popup" id="popup">
                                    <img src="${pageContext.request.contextPath}/parent/img/icon/tick.png" alt="tick"/>
                                    <h2>Bạn chắc chắn muốn đăng ký?</h2>
                                    <input type="button" class="cancel_button" onclick="closePopup()" value="Hủy"/>
                                    <input type="submit" class="confirm_button" value="Xác nhận"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>

        <script>
            let popup = document.getElementById("popup");
            function openPopup() {
                popup.classList.add("open-popup");
            }
            function closePopup() {
                popup.classList.remove("open-popup");
            }

            const fname = document.getElementById("ChildFirstName");
            const lname = document.getElementById("ChildLastName");

            fname.addEventListener("keyup", () => {
                document.getElementById("fnameinvalid1").style.display = /\d/.test(fname.value) ? "block" : "none";
                document.getElementById("fnameinvalid2").style.display = /[^a-zA-Z]/.test(fname.value.replace(/\s+/g, '')) && !/\d/.test(fname.value) ? "block" : "none";
            });

            lname.addEventListener("keyup", () => {
                document.getElementById("lnameinvalid1").style.display = /\d/.test(lname.value) ? "block" : "none";
                document.getElementById("lnameinvalid2").style.display = /[^a-zA-Z]/.test(lname.value.replace(/\s+/g, '')) && !/\d/.test(lname.value) ? "block" : "none";
            });
        </script>
    </body>
</html>
