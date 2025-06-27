<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare - Update Profile</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/parentprofile.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/styleparent.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/parent/css/parentupdateprofile.css">
        <style>
            /* CSS cho thông báo lỗi */
            .form-group span.text-danger {
                font-size: 0.875rem;
                color: red;
                display: block; /* Giúp thông báo lỗi luôn ở dưới ô input */
                min-height: 1.2em; /* Giữ khoảng trống cố định dù có lỗi hay không */
            }
            .message-box {
                margin-bottom: 15px; 
                padding: 10px; 
                border: 1px solid; 
                border-radius: 5px;
                text-align: center;
            }
            .success-message {
                color: green;
                border-color: green;
                background-color: #f0fff0;
            }
            .error-message {
                color: red;
                border-color: red;
                background-color: #fff0f0;
            }
        </style>
    </head>

    <body>
        <div class="wrapper">
            <%-- Include the sidebar here --%>
            <jsp:include page="/view/sidebarParent.jsp" />    

            <div class="right-side">
                <h1>Update Profile</h1>

                <%-- Hiển thị thông báo thành công (từ session) hoặc lỗi (từ request) --%>
                <c:if test="${not empty sessionScope.successMessage}">
                    <div class="message-box success-message">
                        ${sessionScope.successMessage}
                    </div>
                    <%-- Xóa message khỏi session để không hiển thị lại khi tải lại trang --%>
                    <c:remove var="successMessage" scope="session" />
                </c:if>

                <c:if test="${not empty requestScope.errorMessage}">
                    <div class="message-box error-message">
                        ${requestScope.errorMessage}
                    </div>
                </c:if>

                <div class="profile-card">
                    <form id="updateProfileForm" action="${pageContext.request.contextPath}/parentupdateprofile" method="POST" novalidate>
                        
                        <%-- 
                            Logic quan trọng:
                            - Tạo biến "displayAccount".
                            - Nếu có lỗi validation từ Servlet (tồn tại "submittedData"), biến này sẽ lấy dữ liệu người dùng vừa nhập.
                            - Nếu không (lần đầu vào trang), nó sẽ lấy dữ liệu từ session.
                        --%>
                        <c:set var="displayAccount" value="${not empty submittedData ? submittedData : sessionScope.account}" />

                        <div class="profile-content">
                            <div class="update-form-grid">
                                
                                <div class="form-group">
                                    <label for="firstName">First name</label>
                                    <input type="text" id="firstName" name="firstName" value="<c:out value='${displayAccount.firstName}'/>" maxlength="25" required>
                                    <span id="firstNameError" class="text-danger">${errors.firstNameError}</span>
                                </div>

                                <div class="form-group">
                                    <label for="lastName">Last name</label>
                                    <input type="text" id="lastName" name="lastName" value="<c:out value='${displayAccount.lastName}'/>" maxlength="25" required>
                                    <span id="lastNameError" class="text-danger">${errors.lastNameError}</span>
                                </div>

                                <div class="form-group">
                                    <label for="gender">Gender</label>
                                    <select id="gender" name="gender">
                                        <option value="true" ${displayAccount.gender == true ? 'selected' : ''}>Male</option>
                                        <option value="false" ${displayAccount.gender == false ? 'selected' : ''}>Female</option>
                                    </select>
                                    <span class="text-danger"></span> <%-- Thêm span trống để giữ layout --%>
                                </div>

                                <div class="form-group">
                                    <label for="phone">Phone Number</label>
                                    <input type="tel" id="phone" name="phone" value="<c:out value='${displayAccount.phoneNumber}'/>" maxlength="10" required>
                                    <span id="phoneError" class="text-danger">${errors.phoneError}</span>
                                </div>

                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" id="email" name="email" value="<c:out value='${displayAccount.email}'/>" required>
                                    <span id="emailError" class="text-danger">${errors.emailError}</span>
                                </div>

                                <div class="form-group">
                                    <label for="dob">Date of Birth</label>
                                    <input type="date" id="dob" name="dob" value="<c:out value='${displayAccount.dob}'/>" required>
                                    <span id="dobError" class="text-danger">${errors.dobError}</span>
                                </div>

                                <div class="form-group full-width">
                                    <label for="address">Address</label>
                                    <input type="text" id="address" name="address" value="<c:out value='${displayAccount.address}'/>" maxlength="255" required>
                                    <span id="addressError" class="text-danger">${errors.addressError}</span>
                                </div>
                            </div>
                            
                            <div class="form-actions">
                                <button type="submit" class="update-button">Update</button>
                                <a href="${pageContext.request.contextPath}/parent/parentprofile.jsp" class="cancel-button">Cancel</a>
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
                    document.getElementById('firstNameError').textContent = 'First name is invalid or too long (max 25 characters).';
                }
                if (!nameRegex.test(lastName.value)) {
                    isValid = false;
                    document.getElementById('lastNameError').textContent = 'Last name is invalid or too long (max 25 characters).';
                }

                // Validation for Address
                if (address.value === '') {
                    isValid = false;
                    document.getElementById('addressError').textContent = 'Please enter your address.';
                } else if (address.value.length > 255) {
                    isValid = false;
                    document.getElementById('addressError').textContent = 'Address is too long (max 255 characters).';
                }

                // Validation for Phone Number
                const phoneRegex = /^(03|05|07|08|09)[0-9]{8}$/;
                if (!phoneRegex.test(phone.value)) {
                    isValid = false;
                    document.getElementById('phoneError').textContent = 'Invalid phone number (must start with 03, 05, 07, 08, 09 and have 10 digits).';
                }

                // Validation for Email
                const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (email.value === '') {
                    isValid = false;
                    document.getElementById('emailError').textContent = 'Please enter your email address.';
                } else if (!emailRegex.test(email.value)) {
                    isValid = false;
                    document.getElementById('emailError').textContent = 'Invalid email address.';
                }

                // Validation for Date of Birth
                const year = new Date(dob.value).getFullYear();
                const currentYear = new Date().getFullYear();
                if (dob.value === '') {
                    isValid = false;
                    document.getElementById('dobError').textContent = 'Please enter your date of birth.';
                } else if (isNaN(year) || year < 1951 || year >= currentYear) {
                    isValid = false;
                    document.getElementById('dobError').textContent = 'Year of birth must be from 1951 to present.';
                }

                if (!isValid) {
                    e.preventDefault();
                }
            });
        </script>
    </body>
</html>