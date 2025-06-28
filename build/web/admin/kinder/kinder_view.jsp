<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>📋 Quản lý học sinh</title>
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
                background-color: white;
                font-weight: 500;
            }
            .card-filter {
                background-color: #fff;
                border: 1px solid #dee2e6;
                border-radius: 0.75rem;
                padding: 20px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.03);
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
            .status-badge {
                padding: 0.3rem 0.6rem;
                border-radius: 0.5rem;
                font-size: 0.9rem;
                color: white;
            }
            .status-studying {
                background-color: #0d6efd;
            }
            .status-graduated {
                background-color: #198754;
            }
            .status-dropped {
                background-color: #dc3545;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <a href="${pageContext.request.contextPath}/listkinder" class="btn btn-outline-secondary mb-4">
                ⬅️ Quay lại chọn danh mục
            </a>

            <h2>📋 Danh sách học sinh</h2>

            <!-- BỘ LỌC -->
            <div class="card-filter mb-4">
                <form method="get" action="${pageContext.request.contextPath}/viewKinderList" class="row g-3">
                    <div class="col-md-3">
                        <label class="form-label">Lớp học</label>
                        <select class="form-select" name="classId">
                            <option value="-1">-- Tất cả lớp --</option>
                            <c:forEach var="cl" items="${classList}">
                                <option value="${cl.class_id}"
                                        <c:if test="${selectedClassId != -1 && selectedClassId == cl.class_id}">selected</c:if>>
                                    ${cl.class_name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Trạng thái</label>
                        <select class="form-select" name="status">
                            <option value="">-- Tất cả --</option>
                            <option value="studying" ${param.status == 'studying' ? 'selected' : ''}>Đang học</option>
                            <option value="graduated" ${param.status == 'graduated' ? 'selected' : ''}>Đã tốt nghiệp</option>
                            <option value="dropped" ${param.status == 'dropped' ? 'selected' : ''}>Đã thôi học</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Tìm tên</label>
                        <input type="text" class="form-control" name="keyword" placeholder="Nhập tên" value="${param.keyword}">
                    </div>
                    <div class="col-md-2 d-flex align-items-end">
                        <button type="submit" class="btn btn-primary w-100">🔍 Lọc</button>
                    </div>
                </form>
            </div>

            <!-- BẢNG -->
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Họ tên</th>
                            <th>Ngày sinh</th>
                            <th>Giới tính</th>
                            <th>Lớp</th>
                            <th>Địa chỉ</th>
                            <th>Phụ huynh</th>
                            <th>SĐT</th>
                            <th>Trạng thái</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sr" items="${studentList}" varStatus="loop">
                            <tr>
                                <td>${(currentPage - 1) * 10 + loop.index + 1}</td>
                                <td>${sr.kinder.fullName}</td>
                                <td>${sr.kinder.dob}</td>
                                <td>${sr.kinder.gender ? "Nam" : "Nữ"}</td>
                                <td>${sr.classID.class_name}</td>
                                <td>${sr.kinder.address}</td>
                                <td>${sr.kinder.parentAccount.firstName} ${sr.kinder.parentAccount.lastName}</td>
                                <td>${sr.kinder.parentAccount.phoneNumber}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${sr.graduated}">
                                            <span class="status-badge status-graduated">🎓 Đã tốt nghiệp</span>
                                        </c:when>
                                        <c:when test="${sr.droppedOut}">
                                            <span class="status-badge status-dropped">⛔ Đã thôi học</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="status-badge status-studying">📚 Đang học</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:if test="${!sr.graduated && !sr.droppedOut}">
                                        <form method="post" action="${pageContext.request.contextPath}/graduate" class="d-inline">
                                            <input type="hidden" name="kinder_id" value="${sr.kinder.kinder_id}">
                                            <input type="hidden" name="study_year" value="${sr.studyYear}">
                                            <button class="btn btn-success btn-sm" onclick="return confirm('Xác nhận tốt nghiệp?');">
                                                🎓 Tốt nghiệp
                                            </button>
                                        </form>
                                        <form method="post" action="${pageContext.request.contextPath}/dropout" class="d-inline ms-1">
                                            <input type="hidden" name="kinder_id" value="${sr.kinder.kinder_id}">
                                            <input type="hidden" name="study_year" value="${sr.studyYear}">
                                            <button class="btn btn-danger btn-sm" onclick="return confirm('Xác nhận thôi học?');">
                                                ⛔ Thôi học
                                            </button>
                                        </form>
                                        <a href="${pageContext.request.contextPath}/editKinder?kinderId=${sr.kinder.kinder_id}"
                                           class="btn btn-warning btn-sm ms-1">✏️ Sửa</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <c:if test="${empty studentList}">
                <div class="alert alert-warning text-center mt-4">
                    Không tìm thấy học sinh phù hợp.
                </div>
            </c:if>

            <!-- PHÂN TRANG -->
            <c:if test="${totalPages > 1}">
                <nav class="mt-4">
                    <ul class="pagination justify-content-center">
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/viewKinderList?page=${i}
                                   &classId=${selectedClassId}
                                   &status=${param.status}
                                   &keyword=${param.keyword}">
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
