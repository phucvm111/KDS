<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lịch sử gửi đơn</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 40px;
            background-color: #f8f9fa;
        }

        h2 {
            color: #343a40;
            margin-bottom: 25px;
        }

        .search-box {
            margin-bottom: 20px;
        }

        .search-box input[type="text"] {
            padding: 8px 12px;
            width: 250px;
            border-radius: 6px;
            border: 1px solid #ccc;
        }

        .search-box input[type="submit"] {
            padding: 8px 16px;
            background-color: #007bff;
            border: none;
            color: white;
            font-weight: bold;
            border-radius: 6px;
            cursor: pointer;
        }

        .search-box input[type="submit"]:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 10px rgba(0,0,0,0.05);
        }

        th, td {
            padding: 14px 12px;
            border-bottom: 1px solid #dee2e6;
            text-align: left;
        }

        th {
            background-color: #343a40;
            color: white;
            text-transform: uppercase;
            font-size: 13px;
        }

        td {
            color: #495057;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .actions a {
            padding: 6px 12px;
            background-color: #17a2b8;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-size: 13px;
        }

        .actions a:hover {
            background-color: #138496;
        }

        .status {
            font-weight: bold;
            padding: 6px 10px;
            border-radius: 4px;
            display: inline-block;
            text-align: center;
            width: 100px;
        }

        .status.pending {
            background-color: #ffc107;
            color: #212529;
        }

        .status.reviewed {
            background-color: #17a2b8;
            color: white;
        }

        .status.completed {
            background-color: #28a745;
            color: white;
        }
    </style>
</head>
<body>

<h2>📋 Lịch sử gửi đơn</h2>

<!-- 🔍 TÌM KIẾM -->
<form method="get" action="historyform" class="search-box">
    <input type="hidden" name="action" value="historyform" />
    <input type="text" name="search" placeholder="🔍 Tìm theo tiêu đề..." value="${param.search}" />
    <input type="submit" value="Tìm kiếm" />
</form>

<!-- 📝 DANH SÁCH -->
<table>
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
                        ${form.status == 'pending' ? 'Chờ xử lý' :
                          form.status == 'reviewed' ? 'Đã xem' :
                          form.status == 'completed' ? 'Hoàn thành' : form.status}
                    </span>
                </td>
                <td class="actions">
                    <a href="viewForm?id=${form.form_id}">Xem</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
