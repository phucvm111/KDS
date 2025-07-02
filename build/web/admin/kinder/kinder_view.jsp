<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>📋 Danh sách học sinh</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/kinder/boot/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">

            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2>📋 Danh sách học sinh</h2>
                <a href="${pageContext.request.contextPath}/listkinder" class="btn btn-outline-secondary">
                    ⬅️ Quay lại
                </a>
            </div>

            <!-- filter -->
            <form method="get" action="${pageContext.request.contextPath}/viewKinderList" class="row mb-3">
                <div class="col-md-3">
                    <select name="classId" class="form-select">
                        <option value="-1">-- Tất cả lớp --</option>
                        <c:forEach var="cl" items="${classList}">
                            <option value="${cl.class_id}" ${selectedClassId == cl.class_id ? 'selected' : ''}>
                                ${cl.class_name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <select name="status" class="form-select">
                        <option value="">-- Tất cả --</option>
                        <option value="studying" ${status == 'studying' ? 'selected' : ''}>Đang học</option>
                        <option value="graduated" ${status == 'graduated' ? 'selected' : ''}>Đã tốt nghiệp</option>
                        <option value="dropped" ${status == 'dropped' ? 'selected' : ''}>Đã thôi học</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <input type="text" name="keyword" value="${keyword}" class="form-control" placeholder="Nhập tên học sinh">
                </div>
                <div class="col-md-2">
                    <button class="btn btn-primary w-100">🔍 Lọc</button>
                </div>
            </form>

            <!-- table -->
            <div class="table-responsive">
                <table class="table table-bordered align-middle">
                    <thead class="table-light">
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
                                <td>${sr.kinder.address != null ? sr.kinder.address : "-"}</td>
                                <td>
                                    <c:if test="${sr.kinder.parentAccount != null}">
                                        ${sr.kinder.parentAccount.firstName} ${sr.kinder.parentAccount.lastName}
                                    </c:if>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${sr.kinder.parentAccount != null}">
                                            ${sr.kinder.parentAccount.phoneNumber}
                                        </c:when>
                                        <c:otherwise>
                                            ${sr.kinder.parentPhone}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${sr.graduated}">🎓 Tốt nghiệp</c:when>
                                        <c:when test="${sr.droppedOut}">⛔ Thôi học</c:when>
                                        <c:otherwise>📚 Đang học</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:if test="${!sr.graduated && !sr.droppedOut}">
                                        <a href="${pageContext.request.contextPath}/editKinder?kinderId=${sr.kinder.kinder_id}"
                                           class="btn btn-warning btn-sm mb-1">
                                            ✏️ Sửa
                                        </a>
                                        <form method="post" action="${pageContext.request.contextPath}/graduate" style="display:inline;">
                                            <input type="hidden" name="kinder_id" value="${sr.kinder.kinder_id}">
                                            <input type="hidden" name="study_year" value="${sr.studyYear}">
                                            <button type="submit" class="btn btn-success btn-sm mb-1"
                                                    onclick="return confirm('Xác nhận tốt nghiệp học sinh này?');">🎓
                                                Tốt nghiệp
                                            </button>
                                        </form>
                                        <form method="post" action="${pageContext.request.contextPath}/dropout" style="display:inline;">
                                            <input type="hidden" name="kinder_id" value="${sr.kinder.kinder_id}">
                                            <input type="hidden" name="study_year" value="${sr.studyYear}">
                                            <button type="submit" class="btn btn-danger btn-sm"
                                                    onclick="return confirm('Xác nhận thôi học học sinh này?');">⛔
                                                Thôi học
                                            </button>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty studentList}">
                            <tr>
                                <td colspan="10" class="text-center">Không có dữ liệu phù hợp.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>

            <!-- pagination -->
            <c:if test="${totalPages > 1}">
                <nav>
                    <ul class="pagination justify-content-center">
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link"
                                   href="?page=${i}&classId=${selectedClassId}&status=${status}&keyword=${keyword}">
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
