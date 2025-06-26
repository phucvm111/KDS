<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List of Forms</title>
        <style>
            body {
                font-family: Arial;
                margin: 40px;
            }

            .form-container {
                max-width: 800px;
                margin-bottom: 30px;
                border: 1px solid #ccc;
                padding: 20px;
                border-radius: 8px;
            }

            .form-group {
                margin-bottom: 10px;
            }

            label {
                font-weight: bold;
            }

            textarea, select, input[type="submit"] {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
            }
        </style>
    </head>
    <body>

        <h2>Danh sách các đơn gửi đến giáo viên</h2>

        <c:forEach var="form" items="${formList}">
            <div class="form-container">
                <div class="form-group">
                    <label>Title:</label>
                    <p>${form.title}</p>
                </div>

                <div class="form-group">
                    <label>Content:</label>
                    <p>${form.content}</p>
                </div>

                <div class="form-group">
                    <label>Sender :</label>
                    <p>${form.getAccount().firstName} ${form.getAccount().lastName}</p>
                </div>

                <div class="form-group">
                    <label>Date Submitted:</label>
                    <p>${form.date_submitted}</p>
                </div>

                <!-- Form phản hồi tương ứng -->
                <form action="respondform" method="post">
                    <input type="hidden" name="form_id" value="${form.form_id}" />

                    <div class="form-group">
                        <label for="reply">Reply:</label>
                        <textarea name="reply" rows="4" required></textarea>
                    </div>

                    <div class="form-group">
                        <label for="status">Status:</label>
                        <select name="status">
                            <option value="pending">Pending</option>
                            <option value="reviewed">Reviewed</option>
                            <option value="completed">Completed</option>
                        </select>
                    </div>

                    <input type="submit" value="Submit Reply" />

                </form>

            </div>
        </c:forEach>
        <p style="color: greenyellow">${success}</p>
        <p style="color: greenyellow">${error}</p>

    </body>
</html>
