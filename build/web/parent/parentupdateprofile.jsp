<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
    <title>ATKD ChildCare - Cập nhật Hồ sơ</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/parentprofile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/parentupdateprofile.css">
</head>

<body>
    <div class="wrapper">
        <jsp:include page="/view/sidebarParent.jsp" />    

        <div class="right-side">
            <h1>Cập nhật Hồ sơ</h1>

            <%-- Hiển thị thông báo thành công (từ session) --%>
            <c:if test="${not empty sessionScope.successMessage}">
                <div class="message-box success-message">
                    <p>${sessionScope.successMessage}</p>
                </div>
                <c:remove var="successMessage" scope="session" />
            </c:if>

            <%-- Hiển thị thông báo lỗi chung (từ request) --%>
            <c:if test="${not empty requestScope.errorMessage}">
                <div class="message-box error-message">
                    <p>${requestScope.errorMessage}</p>
                </div>
            </c:if>

            <div class="profile-card">
                <form id="updateProfileForm" action="${pageContext.request.contextPath}/parentupdateprofile" method="POST" novalidate>
                    <c:set var="displayAccount" value="${not empty submittedData ? submittedData : sessionScope.account}" />

                    <div class="profile-content">
                        <div class="update-form-grid">
                            
                            <div class="form-group">
                                <label for="firstName">Tên</label>
                                <input type="text" id="firstName" name="firstName" value="<c:out value='${displayAccount.firstName}'/>" maxlength="25" required>
                                <span id="firstNameError" class="text-danger">${errors.firstNameError}</span>
                            </div>

                            <div class="form-group">
                                <label for="lastName">Họ</label>
                                <input type="text" id="lastName" name="lastName" value="<c:out value='${displayAccount.lastName}'/>" maxlength="25" required>
                                <span id="lastNameError" class="text-danger">${errors.lastNameError}</span>
                            </div>

                            <div class="form-group">
                                <label for="gender">Giới tính</label>
                                <select id="gender" name="gender">
                                    <option value="true" ${displayAccount.gender == true ? 'selected' : ''}>Nam</option>
                                    <option value="false" ${displayAccount.gender == false ? 'selected' : ''}>Nữ</option>
                                </select>
                                <span class="text-danger"></span> <%-- Giữ span trống để giữ layout --%>
                            </div>

                            <div class="form-group">
                                <label for="phone">Số điện thoại</label>
                                <input type="tel" id="phone" name="phone" value="<c:out value='${displayAccount.phoneNumber}'/>" maxlength="10" required>
                                <span id="phoneError" class="text-danger">${errors.phoneError}</span>
                            </div>

                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email" value="<c:out value='${displayAccount.email}'/>" required>
                                <span id="emailError" class="text-danger">${errors.emailError}</span>
                            </div>

                            <div class="form-group">
                                <label for="dob">Ngày sinh</label>
                                <input type="date" id="dob" name="dob" value="<c:out value='${displayAccount.dob}'/>" required>
                                <span id="dobError" class="text-danger">${errors.dobError}</span>
                            </div>

                            <div class="form-group full-width">
                                <label for="address">Địa chỉ</label>
                                <input type="text" id="address" name="address" value="<c:out value='${displayAccount.address}'/>" maxlength="255" required>
                                <span id="addressError" class="text-danger">${errors.addressError}</span>
                            </div>
                        </div>
                        
                        <div class="form-actions">
                            <button type="submit" class="update-button">Cập nhật</button>
                            <a href="${pageContext.request.contextPath}/parentprofile" class="cancel-button">Hủy</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        // Phần JavaScript validation phía client vẫn được giữ lại để tăng trải nghiệm người dùng
        const form = document.getElementById('updateProfileForm');
        form.addEventListener('submit', function (e) {
            let isValid = true;

            const firstName = document.getElementById('firstName');
            const lastName = document.getElementById('lastName');
            const address = document.getElementById('address');
            const phone = document.getElementById('phone');
            const email = document.getElementById('email');
            const dob = document.getElementById('dob');

            // Trim whitespace
            firstName.value = firstName.value.trim();
            lastName.value = lastName.value.trim();
            address.value = address.value.trim();
            phone.value = phone.value.trim();
            email.value = email.value.trim();

            // Reset all error messages
            document.querySelectorAll('span.text-danger').forEach(el => el.textContent = '');

            // Validation for Name
            const nameRegex = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸỳỵỷỹ\s]{1,25}$/;
            if (!nameRegex.test(firstName.value)) {
                isValid = false;
                document.getElementById('firstNameError').textContent = 'Tên không hợp lệ hoặc quá dài (tối đa 25 ký tự).';
            }
            if (!nameRegex.test(lastName.value)) {
                isValid = false;
                document.getElementById('lastNameError').textContent = 'Họ không hợp lệ hoặc quá dài (tối đa 25 ký tự).';
            }

            // Validation for Address
            if (address.value === '') {
                isValid = false;
                document.getElementById('addressError').textContent = 'Vui lòng nhập địa chỉ của bạn.';
            } else if (address.value.length > 255) {
                isValid = false;
                document.getElementById('addressError').textContent = 'Địa chỉ quá dài (tối đa 255 ký tự).';
            }

            // Validation for Phone Number
            const phoneRegex = /^(03|05|07|08|09)[0-9]{8}$/;
            if (!phoneRegex.test(phone.value)) {
                isValid = false;
                document.getElementById('phoneError').textContent = 'Số điện thoại không hợp lệ (phải bắt đầu bằng 03, 05, 07, 08, 09 và có 10 chữ số).';
            }

            // Validation for Email
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (email.value === '') {
                isValid = false;
                document.getElementById('emailError').textContent = 'Vui lòng nhập địa chỉ email của bạn.';
            } else if (!emailRegex.test(email.value)) {
                isValid = false;
                document.getElementById('emailError').textContent = 'Địa chỉ email không hợp lệ.';
            }

            // Validation for Date of Birth
            const dobDate = new Date(dob.value);
            const currentYear = new Date().getFullYear();
            if (dob.value === '') {
                isValid = false;
                document.getElementById('dobError').textContent = 'Vui lòng nhập ngày sinh của bạn.';
            } else if (isNaN(dobDate.getFullYear()) || dobDate.getFullYear() < 1951 || dobDate.getFullYear() >= currentYear) {
                isValid = false;
                document.getElementById('dobError').textContent = 'Năm sinh phải từ 1951 đến hiện tại.';
            }

            if (!isValid) {
                e.preventDefault();
            }
        });
    </script>
</body>
</html>