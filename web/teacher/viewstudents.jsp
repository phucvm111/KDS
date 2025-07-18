<%-- 
    Document   : viewstudents.jsp
    Created on : Jul 18, 2025, 3:51:09 PM
    Author     : Vu Tuan Hai <HE176383>
--%>

<%@page import="model.Kindergartner"%>
<%@page import="model.Account"%>
<%@page import="model.Class"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Danh sách lớp và học sinh</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />

        <style>
            :root {
                --primary-color: #6a5af9;
                --secondary-color: #00bcd4;
                --text-color: #333;
                --text-secondary: #6c757d;
                --background-color: #f8f9fa;
                --white-color: #fff;
                --border-color: #e0e0e0;
                --box-shadow: 0 4px 15px rgba(0, 0, 0, 0.07);
            }

            body {
                font-family: 'Poppins', sans-serif;
                margin: 0;
                background: var(--background-color);
            }

            .wrapper {
                display: flex;
                min-height: 100vh;
            }

            .right-side {
                flex-grow: 1;
                padding: 40px;
                overflow-y: auto;
            }

            .profile-card {
                background-color: var(--white-color);
                border-radius: 16px;
                box-shadow: var(--box-shadow);
                overflow: hidden;
                max-width: 1000px;
                margin: 0 auto;
                padding: 30px;
            }

            h2, h3 {
                color: var(--primary-color);
                text-align: center;
                margin-bottom: 20px;
            }

            form {
                text-align: center;
                margin-bottom: 30px;
            }

            select {
                padding: 10px 15px;
                font-size: 1em;
                border-radius: 8px;
                border: 1px solid var(--border-color);
                outline: none;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                font-size: 0.95em;
            }

            table, th, td {
                border: 1px solid var(--border-color);
            }

            th {
                background-color: var(--primary-color);
                color: white;
                padding: 12px;
                text-align: center;
            }

            td {
                padding: 10px;
                text-align: center;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            @media (max-width: 768px) {
                .profile-card {
                    padding: 20px;
                }

                table, thead, tbody, th, td, tr {
                    display: block;
                }

                td {
                    text-align: left;
                    padding-left: 50%;
                    position: relative;
                }

                td::before {
                    position: absolute;
                    left: 10px;
                    top: 10px;
                    white-space: nowrap;
                    font-weight: bold;
                }

                td:nth-child(1)::before {
                    content: "ID";
                }
                td:nth-child(2)::before {
                    content: "Họ tên";
                }
                td:nth-child(3)::before {
                    content: "Ngày sinh";
                }
                td:nth-child(4)::before {
                    content: "Giới tính";
                }
                td:nth-child(5)::before {
                    content: "Phụ huynh";
                }
                td:nth-child(6)::before {
                    content: "SĐT";
                }
                td:nth-child(7)::before {
                    content: "Email";
                }
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="/view/sidebarTeacher.jsp" />

            <div class="right-side">
                <div class="profile-card">
                    <h2>Chọn lớp để xem học sinh</h2>
                    <form method="get" action="ViewStudentInClassServlet">
                        <label for="classId">Lớp học:</label>
                        <select name="classId" id="classId" onchange="this.form.submit()">
                            <option value="">-- Chọn lớp --</option>
                            <%
                                List<Class> classList = (List<Class>) request.getAttribute("classList");
                                Integer selectedClassId = (Integer) request.getAttribute("selectedClassId");
                                for (Class cl : classList) {
                                    boolean selected = selectedClassId != null && selectedClassId == cl.getClass_id();
                            %>
                            <option value="<%= cl.getClass_id() %>" <%= selected ? "selected" : "" %>>
                                <%= cl.getClass_name() %> (Khối <%= cl.getGrade() %>)
                            </option>
                            <% } %>
                        </select>
                    </form>

                    <%
                        List<Kindergartner> studentList = (List<Kindergartner>) request.getAttribute("studentList");
                        if (studentList != null) {
                    %>
                    <h3>Danh sách học sinh</h3>
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Họ tên</th>
                            <th>Ngày sinh</th>
                            <th>Giới tính</th>
                            <th>Phụ huynh</th>
                            <th>SĐT</th>
                            <th>Email</th>
                        </tr>
                        <% for (Kindergartner k : studentList) {
                               Account parent = k.getParentAccount();
                        %>
                        <tr>
                            <td><%= k.getKinder_id() %></td>
                            <td><%= k.getFullName() %></td>
                            <td><%= k.getDob() %></td>
                            <td><%= k.isGender() ? "Nam" : "Nữ" %></td>
                            <td><%= parent.getFirstName() + " " + parent.getLastName() %></td>
                            <td><%= parent.getPhoneNumber() %></td>
                            <td><%= parent.getEmail() %></td>
                        </tr>
                        <% } %>
                    </table>
                    <% } %>
                </div>
            </div>
        </div>
    </body>
</html>
