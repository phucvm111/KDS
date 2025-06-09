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
            <h3 class="text-center mb-4">Tạo tài khoản</h3>
            <form id="registerForm" action="register" method="post" novalidate>
                <div class="mb-3">
                    <label for="fname" class="form-label">Họ</label>
                    <input type="text" class="form-control" id="fname" name="fname" maxlength="25" required>
                    <span id="fnameError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="lname" class="form-label">Tên</label>
                    <input type="text" class="form-control" id="lname" name="lname" maxlength="25" required>
                    <span id="lnameError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="gender" class="form-label">Giới tính</label>
                    <select class="form-select" name="gender" id="gender">
                        <option value="male">Nam</option>
                        <option value="female">Nữ</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">Địa chỉ</label>
                    <textarea class="form-control" name="address" id="address" rows="3" maxlength="255" required></textarea>
                    <span id="addressError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Số điện thoại</label>
                    <input type="text" class="form-control" id="phone" name="phone" maxlength="10" required>
                    <span id="phoneError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="dob" class="form-label">Ngày sinh</label>
                    <input type="date" class="form-control" id="dob" name="dob" required>
                    <span id="dobError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Mật khẩu</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <span id="passwordError" class="text-danger"></span>
                </div>
                <div class="mb-3">
                    <label for="password2" class="form-label">Xác nhận mật khẩu</label>
                    <input type="password" class="form-control" id="password2" name="password2" required>
                    <span id="password2Error" class="text-danger"></span>
                </div>
                <button type="submit" class="btn btn-primary w-100">Đăng ký</button>
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
                    document.getElementById('fnameError').textContent = 'Họ không hợp lệ hoặc quá dài (tối đa 25 ký tự).';
                }

                if (!nameRegex.test(lname.value)) {
                    isValid = false;
                    document.getElementById('lnameError').textContent = 'Tên không hợp lệ hoặc quá dài (tối đa 25 ký tự).';
                }

                if (address.value === '') {
                    isValid = false;
                    document.getElementById('addressError').textContent = 'Vui lòng nhập địa chỉ.';
                }

                const phoneRegex = /^(03|05|07|08|09)[0-9]{8}$/;
                if (!phoneRegex.test(phone.value)) {
                    isValid = false;
                    document.getElementById('phoneError').textContent = 'Số điện thoại không hợp lệ.';
                }

                const year = new Date(dob.value).getFullYear();
                const currentYear = new Date().getFullYear();
                if (isNaN(year) || year < 1951 || year >= currentYear) {
                    isValid = false;
                    document.getElementById('dobError').textContent = 'Năm sinh phải từ 1951 đến hiện tại.';
                }

                const passwordRegex = /^(?=.*[A-Z])(?=.*\d).{8,}$/;
                if (!passwordRegex.test(password.value)) {
                    isValid = false;
                    document.getElementById('passwordError').textContent = 'Mật khẩu phải có ít nhất 8 ký tự, gồm 1 chữ hoa và 1 số.';
                }

                if (password.value !== password2.value) {
                    isValid = false;
                    document.getElementById('password2Error').textContent = 'Mật khẩu xác nhận không khớp.';
                }

                if (!isValid)
                    e.preventDefault();
            });
        </script>
    </body>
</html>
