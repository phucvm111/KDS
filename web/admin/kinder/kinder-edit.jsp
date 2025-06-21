<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>✏️ Chỉnh sửa học sinh</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/kinder/boot/bootstrap.min.css">
        <style>
            body {
                background-color: #f4f6f9;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            h2 {
                color: #0d6efd;
                font-weight: 600;
                margin-bottom: 30px;
            }

            .form-label {
                font-weight: 500;
            }

            .form-check-label {
                margin-right: 15px;
            }

            .form-container {
                background-color: #ffffff;
                padding: 30px;
                border-radius: 0.75rem;
                box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            }

            .btn-primary {
                font-weight: 500;
            }

            .btn-secondary {
                font-weight: 500;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2>✏️ Chỉnh sửa thông tin học sinh</h2>

            <div class="form-container">
                <form method="post" action="${pageContext.request.contextPath}/editKinder">
                    <input type="hidden" name="kinderId" value="${kinder.kinder_id}"/>

                    <div class="mb-3">
                        <label class="form-label">Họ</label>
                        <input type="text" name="first_name" value="${kinder.first_name}" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Tên</label>
                        <input type="text" name="last_name" value="${kinder.last_name}" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Ngày sinh</label>
                        <input type="date" name="dob" value="${kinder.dob}" class="form-control" required>
                    </div>

                    <div class="mb-4">
                        <label class="form-label d-block mb-2">Giới tính</label>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" value="male" ${kinder.gender ? 'checked' : ''}>
                            <label class="form-check-label">Nam</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" value="female" ${!kinder.gender ? 'checked' : ''}>
                            <label class="form-check-label">Nữ</label>
                        </div>
                    </div>

                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">💾 Lưu thay đổi</button>
                        <a href="${pageContext.request.contextPath}/viewKinderList" class="btn btn-secondary">❌ Hủy</a>
                    </div>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                </form>
            </div>
        </div>
    </body>
</html>
