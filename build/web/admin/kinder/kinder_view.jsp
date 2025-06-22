<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>📋 Danh sách học sinh</title>
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

            .card-filter {
                background-color: #ffffff;
                border: 1px solid #dee2e6;
                border-radius: 0.75rem;
                padding: 20px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.03);
                margin-bottom: 30px;
            }

            .table thead {
                background-color: #e3f2fd;
            }

            .table thead th {
                color: #0d47a1;
                font-weight: 600;
            }

            .table-hover tbody tr:hover {
                background-color: #f0f8ff;
            }

            .btn-sm {
                font-weight: 500;
            }

            .alert-warning {
                background-color: #fff3cd;
                color: #664d03;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2>📋 Danh sách học sinh</h2>

            <!-- Bộ lọc -->
            <div class="card-filter">
                <form method="get" action="${pageContext.request.contextPath}/viewKinderList" class="row g-3">
                    <div class="col-md-4">
                        <label class="form-label">Lớp học</label>
                        <select class="form-select" name="classId">
                            <option value="-1">-- Tất cả lớp --</option>
                            <c:forEach var="cl" items="${classList}">
                                <option value="${cl.class_id}" ${param.classId == cl.class_id ? 'selected' : ''}>${cl.class_name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Tìm theo tên</label>
                        <input type="text" class="form-control" name="keyword" value="${param.keyword}" placeholder="Nhập họ tên học sinh">
                    </div>
                    <div class="col-md-4 d-flex align-items-end">
                        <button type="submit" class="btn btn-primary w-100">🔍 Lọc</button>
                    </div>
                </form>
            </div>

            <!-- Bảng học sinh -->
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle">
                    <thead>
                        <tr>
                            <th>Họ tên</th>
                            <th>Ngày sinh</th>
                            <th>Giới tính</th>
                            <th>Lớp</th>
                            <th>Địa chỉ</th>
                            <th>Phụ huynh</th>
                            <th>SĐT</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sr" items="${studentList}">
                            <tr>
                                <td>${sr.kinder.fullName}</td>
                                <td>${sr.kinder.dob}</td>
                                <td>${sr.kinder.gender ? 'Nam' : 'Nữ'}</td>
                                <td>${sr.classID.class_name}</td>
                                <td>${sr.kinder.address}</td>
                                <td>${sr.kinder.parentAccount.firstName} ${sr.kinder.parentAccount.lastName}</td>
                                <td>${sr.kinder.parentAccount.phoneNumber}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/editKinder?kinderId=${sr.kinder.kinder_id}" class="btn btn-warning btn-sm">✏️ Sửa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Thông báo khi không có học sinh -->
            <c:if test="${empty studentList}">
                <div class="alert alert-warning text-center mt-4">Không tìm thấy học sinh nào phù hợp.</div>
            </c:if>

            <a href="${pageContext.request.contextPath}/listkinder" class="btn btn-outline-secondary mt-4">⬅️ Quay lại chọn danh mục</a>
        </div>
    </body>
</html>
