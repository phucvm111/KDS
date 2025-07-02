<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Lịch sử gửi đơn</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <style>
            body {
                font-family: 'Segoe UI', sans-serif;
                background-color: #f4f6f9;
                padding: 40px;
            }

            .card {
                border-radius: 12px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.05);
            }

            .table th {
                background-color: #343a40;
                color: #fff;
                text-transform: uppercase;
                font-size: 13px;
            }

            .table td {
                vertical-align: middle;
            }

            .status {
                font-weight: 500;
                padding: 4px 10px;
                border-radius: 20px;
                font-size: 13px;
            }

            .status.pending {
                background-color: #ffe08a;
                color: #856404;
            }

            .status.reviewed {
                background-color: #74c0fc;
                color: #084298;
            }

            .status.completed {
                background-color: #b2f2bb;
                color: #2f9e44;
            }

            .search-bar input[type="text"] {
                max-width: 300px;
            }

            .btn-view {
                background-color: #20c997;
                color: white;
            }

            .btn-view:hover {
                background-color: #0ca678;
            }
            .btn-rollback {
                background-color: #6c757d;
                color: white;
                padding: 10px 18px;
                border: none;
                border-radius: 6px;
                font-size: 14px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-rollback:hover {
                background-color: #5a6268;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2 class="mb-4 text-dark"><i class="bi bi-clock-history me-2"></i>Lịch sử gửi đơn</h2>

            <!-- TÌM KIẾM -->
            <form method="get" action="historyform" class="d-flex align-items-center gap-2 mb-4 search-bar">
                <input type="hidden" name="action" value="historyform" />
                <input type="text" name="search" class="form-control" placeholder="🔍 Tìm theo tiêu đề..." value="${param.search}" />
                <button type="submit" class="btn btn-primary"><i class="bi bi-search"></i> Tìm</button>
            </form>

            <!-- DANH SÁCH ĐƠN -->
            <div class="card">
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead>
                            <tr>
                                <th>Thể Loại</th>
                                <th>Người Gửi</th>
                                <th>Trẻ</th>
                                <th>Tiêu Đề</th>
                                <th>Nội Dung</th>
                                <th>Ngày Gửi</th>
                                <th>Trạng Thái</th>
                                <th>Hành Động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="form" items="${formList}">
                                <tr>
                                    <td>${form.getFormstyle().type_name}</td>
                                    <td>${form.getAccount().firstName} ${form.getAccount().lastName}</td>
                                    <td>${form.getKindergartner().first_name} ${form.getKindergartner().last_name}</td>
                                    <td>${form.title}</td>
                                    <td>${form.content}</td>
                                    <td>${form.date_submitted}</td>
                                    <td>
                                        <span class="status ${form.status}">
                                            <c:choose>
                                                <c:when test="${form.status == 'pending'}">Chờ xử lý</c:when>
                                                <c:when test="${form.status == 'reviewed'}">Đã xem</c:when>
                                                <c:when test="${form.status == 'completed'}">Hoàn thành</c:when>
                                                <c:otherwise>${form.status}</c:otherwise>
                                            </c:choose>
                                        </span>
                                    </td>
                                    <td>
                                        <a href="viewForm?id=${form.form_id}" class="btn btn-sm btn-view">
                                            <i class="bi bi-eye"></i> Xem
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <p style="color: green">${success}</p>
                    <p style="color: red">${error}</p>
                </div>
            </div>
            <button class="btn-rollback" onclick="history.back()">
                ⬅️ Quay lại
            </button>  
        </div>

    </body>
</html>
