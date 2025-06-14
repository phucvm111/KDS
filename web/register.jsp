<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng ký thành viên</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background: linear-gradient(to right, #667eea, #764ba2);
                min-height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .form-container {
                background-color: white;
                padding: 30px;
                border-radius: 12px;
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 500px;
            }
            .form-group span.text-danger {
                font-size: 0.875rem;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h3 class="text-center mb-4">Create Account</h3>
            <form id="registerForm" action="register" method="post" novalidate>
                <div class="mb-3">
                    <label for="fname" class="form-label">First Name <span style="color: red">(*)</span></label>
                    <input type="text" class="form-control" id="fname" name="fname" maxlength="25" required>
                    <span id="fnameError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="lname" class="form-label">Last Name <span style="color: red">(*)</span></label>
                    <input type="text" class="form-control" id="lname" name="lname" maxlength="25" required>
                    <span id="lnameError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="gender" class="form-label">Sex <span style="color: red">(*)</span></label>
                    <select class="form-select" name="gender" id="gender">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">Address <span style="color: red">(*)</span></label>
                    <textarea class="form-control" name="address" id="address" rows="3" maxlength="255" required></textarea>
                    <span id="addressError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone Number <span style="color: red">(*)</span></label>
                    <input type="text" class="form-control" id="phone" name="phone" maxlength="10" required>
                    <span id="phoneError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="dob" class="form-label">Date of Birth <span style="color: red">(*)</span></label>
                    <input type="date" class="form-control" id="dob" name="dob" required>
                    <span id="dobError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password <span style="color: red">(*)</span></label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <span id="passwordError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="password2" class="form-label">Confirm Password <span style="color: red">(*)</span></label>
                    <input type="password" class="form-control" id="password2" name="password2" required>
                    <span id="password2Error" class="text-danger"></span>
                </div>
                <button type="submit" class="btn btn-primary w-100">Register</button>
            </form>
        </div>

        <script>
            const form = document.getElementById('registerForm');
            form.addEventListener('submit', function (e) {
                let isValid = true;

                // Trim and get values
                const fname = document.getElementById('fname');
                const lname = document.getElementById('lname');
                const address = document.getElementById('address');
                const phone = document.getElementById('phone');
                const dob = document.getElementById('dob');
                const password = document.getElementById('password');
                const password2 = document.getElementById('password2');

                fname.value = fname.value.trim();
                lname.value = lname.value.trim();
                address.value = address.value.trim();
                phone.value = phone.value.trim();

                // Reset error messages
                document.querySelectorAll('span.text-danger').forEach(el => el.textContent = '');

                const nameRegex = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸỳỵỷỹ\s]{1,25}$/;
                if (!nameRegex.test(fname.value)) {
                    isValid = false;
                    document.getElementById('fnameError').textContent = 'First name is invalid or too long';
                }

                if (!nameRegex.test(lname.value)) {
                    isValid = false;
                    document.getElementById('lnameError').textContent = 'Last name is invalid or too long.';
                }

                if (address.value === '') {
                    isValid = false;
                    document.getElementById('addressError').textContent = 'Please enter your address.';
                }

                const phoneRegex = /^(03|05|07|08|09)[0-9]{8}$/;
                if (!phoneRegex.test(phone.value)) {
                    isValid = false;
                    document.getElementById('phoneError').textContent = 'Invalid phone number.';
                }

                const year = new Date(dob.value).getFullYear();
                const currentYear = new Date().getFullYear();
                if (isNaN(year) || year < 1951 || year >= currentYear) {
                    isValid = false;
                    document.getElementById('dobError').textContent = 'Year of birth must be from 1951 to present.';
                }

                const passwordRegex = /^(?=.*[A-Z])(?=.*\d).{8,}$/;
                if (!passwordRegex.test(password.value)) {
                    isValid = false;
                    document.getElementById('passwordError').textContent = 'Password must have at least 8 characters, include 1 uppercase letter and 1 number.';
                }

                if (password.value !== password2.value) {
                    isValid = false;
                    document.getElementById('password2Error').textContent = 'Confirmation password does not match.';
                }

                if (!isValid)
                    e.preventDefault();
            });
        </script>
    </body>
</html>
