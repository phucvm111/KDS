<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Sửa cuộc họp phụ huynh</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background: #f8f9fa;
                margin: 0;
                padding: 20px;
            }
            .container {
                max-width: 650px;
                margin: auto;
                background: white;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.15);
                padding: 30px;
            }
            h2 {
                color: #4e73df;
                margin-bottom: 20px;
                border-bottom: 2px solid #4e73df;
                padding-bottom: 8px;
            }
            label {
                display: block;
                margin-top: 15px;
                font-weight: bold;
            }
            label span {
                color: red;
                margin-left: 5px;
            }
            input[type="text"], textarea, select, input[type="datetime-local"] {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 6px;
            }
            button {
                background-color: #4e73df;
                color: white;
                padding: 10px 20px;
                border: none;
                margin-top: 20px;
                border-radius: 6px;
                cursor: pointer;
            }
            button:hover {
                background-color: #375ab6;
            }
            .back-link {
                display: inline-block;
                margin-top: 15px;
                color: #4e73df;
                text-decoration: none;
            }
            .back-link:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2><i class="fa-solid fa-edit"></i> Sửa cuộc họp phụ huynh</h2>
            <form method="post" action="">
                <input type="hidden" name="id" value="${meeting.meetingId}"/>

                <label>Chủ đề <span>*</span></label>
                <input type="text" name="topic" value="${fn:trim(meeting.topic)}" required />

                <label>Lớp <span>*</span></label>
                <select name="class_id" required>
                    <c:forEach var="c" items="${classes}">
                        <option value="${c.class_id}"
                                ${c.class_id == meeting.classId ? 'selected' : ''}>
                            ${c.class_name}
                        </option>
                    </c:forEach>
                </select>

                <label>Ngày giờ họp <span>*</span></label>
                <input type="datetime-local" name="meeting_date" 
                       value="${fn:replace(meeting.meetingDate,' ','T')}"
                       min="<%=java.time.LocalDateTime.now().toString().substring(0,16)%>"
                       required />

                <label>Trạng thái <span>*</span></label>
                <select name="status" required>
                    <option value="Scheduled" ${meeting.status eq 'Scheduled' ? 'selected' : ''}>Scheduled</option>
                    <option value="Completed" ${meeting.status eq 'Completed' ? 'selected' : ''}>Completed</option>
                    <option value="Cancelled" ${meeting.status eq 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                </select>

                <label>Ghi chú</label>
                <textarea name="notes" rows="3">${fn:trim(meeting.notes)}</textarea>

                <button type="submit">Cập nhật</button>
            </form>
            <a class="back-link" href="${pageContext.request.contextPath}/teacher/parentmeetings">
                <i class="fa-solid fa-arrow-left"></i> Quay lại danh sách
            </a>
        </div>
    </body>
</html>
