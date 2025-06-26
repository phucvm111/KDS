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
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 15px;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            .search-box {
                margin-bottom: 15px;
            }
            .actions a {
                margin-right: 10px;
                color: blue;
                text-decoration: none;
            }
            .actions a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>

        <h2>Lịch sử gửi đơn</h2>

        <!-- 🔍 FORM TÌM KIẾM -->
        <form method="get" action="historyform" class="search-box">
            <input type="text" name="search" placeholder="Tìm theo tiêu đề..." value="${param.search}" />
            <input type="submit" value="Tìm kiếm" />
        </form>

        <!-- 📋 DANH SÁCH ĐƠN -->
        <table>
            <thead>
                <tr>

                    <th>Thể Loại Đơn</th>
                    <th>Người Gửi</th>
                    <th>Trẻ</th>
                    <th>Tiêu Đề</th>
                    <th>Nội Dung</th>
                    <th>Ngày Gửi</th>
                    <th>Trạng Thái</th>
                    <th>Hành động</th>

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
                        <td>${form.status}</td>
                        <td class="actions">
                            
                            <a href="viewForm?id=${form.form_id}">Xem</a> |
                            <a href="editForm?id=${form.form_id}">Sửa</a> |
                            <a href="deleteForm?id=${form.form_id}" onclick="return confirm('Bạn có chắc muốn xóa đơn này?');">Xóa</a>
                            
                        </td>


                    </tr>

                </c:forEach>




            </tbody>
        </table>

    </body>
</html>
