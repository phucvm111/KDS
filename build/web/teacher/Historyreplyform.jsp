<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lịch sử phản hồi</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                padding: 30px;
                background-color: #f9f9f9;
            }

            h1 {
                color: #333;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #fff;
            }

            th, td {
                padding: 12px;
                border: 1px solid #ccc;
                text-align: left;
            }

            th {
                background-color: #007bff;
                color: white;
            }

            tr:hover {
                background-color: #f1f1f1;
            }

            .back-button {
                margin-top: 20px;
            }

            .back-button button {
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .back-button button:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>

        <h1>Lịch sử phản hồi các đơn</h1>
        <!-- 🔍 Thanh tìm kiếm -->
        <form action="historyreply" method="get" style="margin-bottom: 20px;">
            <input type="hidden" name="action" value="search">
            <input type="text" name="keyword" value="${param.keyword}" placeholder="🔍 Tìm kiếm theo người gửi, trẻ hoặc nội dung..."
                   style="padding: 10px; width: 300px; border: 1px solid #ccc; border-radius: 5px; font-size: 14px;">
            <button type="submit" style="padding: 10px 16px; border: none; background-color: #007bff; color: white; border-radius: 5px; margin-left: 5px;">Tìm</button>
        </form>

        <table>
            <thead>
                <tr>          
                    <th>Thể loại đơn</th>
                    <th>Người gửi</th>
                    <th>Trẻ</th>
                    <th>Nội dung</th>
                    <th>Ngày gửi</th>
                    <th>Trạng thái</th>
                    <th>Phản hồi</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="form" items="${repliedForms}">
                    <tr>
                        <td>${form.getFormstyle().type_name}</td>
                        <td>${form.getAccount().firstName} ${form.getAccount().lastName}</td>
                        <td>${form.getKindergartner().first_name} ${form.getKindergartner().last_name}</td>
                        <td>${form.content}</td>
                        <td>${form.date_submitted}</td>
                        <td>${form.status}</td>
                        <td>${form.reply}</td>
                        <td>
                            <form action="editreply" method="get" style="display:inline;">
                                <input type="hidden" name="form_id" value="${form.form_id}" />
                                <button type="submit" style="
                                        padding: 6px 12px;
                                        background-color: #ffc107;
                                        color: black;
                                        border: none;
                                        border-radius: 4px;
                                        cursor: pointer;
                                        ">Sửa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p style="color: greenyellow">${success}</p>
        <p style="color: red">${error}</p>
        <div class="back-button">
            <button onclick="history.back()">← Quay lại</button>
        </div>

    </body>
</html>
