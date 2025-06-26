<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${pageTitle}</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/kinder/boot/bootstrap.min.css">
        <style>
            body {
                background-color: #f4f6f9;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            h2 {
                color: #0d6efd;
                font-weight: 600;
            }

            .btn-outline-secondary {
                font-weight: 500;
                background-color: white;
            }

            .card-filter {
                background-color: #ffffff;
                border: 1px solid #dee2e6;
                border-radius: 0.75rem;
                padding: 20px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.03);
            }

            .table {
                background-color: white;
                border-radius: 0.5rem;
                overflow: hidden;
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

            .btn-success.btn-sm {
                background-color: #198754;
                border-color: #157347;
                font-weight: 500;
            }

            .btn-success.btn-sm:hover {
                background-color: #146c43;
                border-color: #115c38;
            }

            .btn-primary {
                background-color: #0d6efd;
                border-color: #0a58ca;
                font-weight: 500;
            }

            .btn-primary:hover {
                background-color: #0b5ed7;
                border-color: #0a53be;
            }

            .alert-warning {
                background-color: #fff3cd;
                border-color: #ffecb5;
                color: #664d03;
            }

            .form-label {
                font-weight: 500;
            }
        </style>

    </head>
    <body>
        <div class="container mt-5">
            <a href="${pageContext.request.contextPath}/listkinder" class="btn btn-outline-secondary mb-4">
                ⬅️ Quay lại chọn danh mục
            </a>

            <h2>${pageTitle}</h2>

            <!-- Bộ lọc -->
            <div class="card-filter">
                <form method="get" action="${pageContext.request.contextPath}/students" class="row g-3">
                    <input type="hidden" name="status" value="${isGraduated ? 'graduated' : 'studying'}" />
                    <div class="col-md-3">
                        <label class="form-label">Năm học</label>
                        <input type="number" class="form-control" name="year" value="${param.year}" placeholder="VD: 2024">
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Lớp học</label>
                        <select class="form-select" name="classId">
                            <option value="-1">-- Tất cả lớp --</option>
                            <c:forEach var="cl" items="${classList}">
                                <option value="${cl.class_id}" ${param.classId == cl.class_id ? 'selected' : ''}>
                                    ${cl.class_name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Tìm theo tên</label>
                        <input type="text" class="form-control" name="name" placeholder="Nhập tên học sinh" value="${param.name}">
                    </div>
                    <div class="col-md-2 d-flex align-items-end">
                        <button type="submit" class="btn btn-primary w-100">🔍 Lọc</button>
                    </div>
                </form>
            </div>

            <!-- Bảng kết quả -->
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
                                <c:if test="${status == 'studying'}">
                                <th>Thao tác</th>
                                </c:if>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sr" items="${studentList}">
                            <tr>
                                <td>${sr.kinder.fullName}</td>
                                <td>${sr.kinder.dob}</td> 

                                <td>
                                    <c:choose>
                                        <c:when test="${sr.kinder.gender}">Nam</c:when>
                                        <c:otherwise>Nữ</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${sr.classID.class_name}</td>
                                <td>${sr.kinder.address}</td>
                                <td>${sr.kinder.parentAccount.firstName} ${sr.kinder.parentAccount.lastName}</td>
                                <td>${sr.kinder.parentAccount.phoneNumber}</td>
                                <td>
                                    <c:if test="${status == 'studying'}">
                                        <!-- Nút Tốt nghiệp -->
                                        <form method="post" action="${pageContext.request.contextPath}/graduate" class="d-inline">
                                            <input type="hidden" name="kinder_id" value="${sr.kinder.kinder_id}" />
                                            <input type="hidden" name="study_year" value="${sr.studyYear}" />
                                            <button class="btn btn-success"
                                                    onclick="return confirm('Bạn có chắc chắn muốn đánh dấu học sinh này là đã tốt nghiệp không?');">
                                                🎓 Tốt nghiệp
                                            </button>

                                        </form>

                                        <!-- Nút Thôi học -->
                                        <form method="post" action="${pageContext.request.contextPath}/dropout" class="d-inline ms-1">
                                            <input type="hidden" name="kinder_id" value="${sr.kinder.kinder_id}" />
                                            <input type="hidden" name="study_year" value="${sr.studyYear}" />
                                            <button class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn cho học sinh thôi học?');">
                                                ⛔ Thôi học
                                            </button>
                                        </form>
                                    </c:if>
                                </td>


                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Thông báo khi không có dữ liệu -->
            <c:if test="${empty studentList}">
                <div class="alert alert-warning text-center mt-4">
                    Không tìm thấy học sinh với điều kiện lọc.
                </div>
            </c:if>
            <!-- Phân trang -->
            <c:if test="${totalPages > 1}">
                <nav class="mt-4">
                    <ul class="pagination justify-content-center">
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/students?status=${param.status}&year=${year}&classId=${classId}&name=${name}&page=${i}">
                                    ${i}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>

        </div>
    </body>
</html>
