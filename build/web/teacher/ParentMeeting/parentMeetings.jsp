<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.lang.Math"%> <%-- Required for Math.max and Math.min in JSP --%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cuộc họp phụ huynh</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css">
        <!-- Google Fonts: Inter for a modern look -->
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
        <!-- Font Awesome for icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            /* Global styles */
            :root {
                --primary-blue: #4e73df;
                --hover-blue: #375ab6;
                --light-blue: #e0f2f7;
                --text-dark: #2f3542;
                --text-light: #6c757d;
                --bg-light: #f8f9fa;
                --border-light: #dee2e6;
                --shadow-light: rgba(0, 0, 0, 0.08);
                --shadow-medium: rgba(0, 0, 0, 0.15);
            }

            body {
                font-family: 'Inter', sans-serif;
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                background-color: var(--bg-light);
            }

            .wrapper { /* Changed from main-wrapper to wrapper */
                display: flex;
                background-color: var(--bg-light);
                min-height: 100vh;
            }
            .right-side { /* Changed from content-wrapper to right-side */
                flex: 1;
                padding: 30px; /* Increased padding */
                max-width: 1200px; /* Increased max-width for more space */
                margin: 0 auto;
                box-sizing: border-box; /* Ensure padding is included in width */
            }
            .right-side h2 { /* Changed from content-wrapper h2 to right-side h2 */
                color: var(--primary-blue); /* Changed to primary blue */
                margin-bottom: 30px; /* Increased margin */
                display: flex;
                align-items: center;
                gap: 12px; /* Increased gap for icon */
                font-size: 1.8rem; /* Larger font size */
                font-weight: 700; /* Bolder */
                padding-bottom: 10px;
                border-bottom: 3px solid var(--primary-blue); /* Thicker border */
            }
            .right-side h2 i { /* Changed from content-wrapper h2 i to right-side h2 i */
                color: var(--primary-blue);
                font-size: 2rem;
            }

            /* Add New Meeting Button */
            .add-meeting-btn {
                background-color: #28a745; /* Green for add */
                color: white;
                padding: 10px 20px; /* Larger padding */
                border-radius: 8px; /* More rounded */
                text-decoration: none;
                font-weight: 600;
                display: inline-flex; /* Align icon and text */
                align-items: center;
                gap: 8px;
                transition: background-color 0.3s ease, transform 0.2s ease;
                box-shadow: 0 4px 8px rgba(40, 167, 69, 0.2); /* Subtle shadow */
            }
            .add-meeting-btn:hover {
                background-color: #218838; /* Darker green on hover */
                transform: translateY(-2px); /* Slight lift effect */
            }

            /* Filter Form */
            .filter-form {
                display: flex;
                flex-wrap: wrap;
                gap: 15px; /* Increased gap */
                margin-top: 25px; /* Space from button */
                margin-bottom: 30px; /* Space before table */
                align-items: center;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 8px var(--shadow-light);
            }
            .filter-form input,
            .filter-form select {
                padding: 8px 12px; /* Increased padding */
                border-radius: 6px; /* More rounded */
                border: 1px solid var(--border-light);
                font-size: 1rem;
                color: var(--text-dark);
                transition: border-color 0.3s ease, box-shadow 0.3s ease;
            }
            .filter-form input:focus,
            .filter-form select:focus {
                border-color: var(--primary-blue);
                box-shadow: 0 0 0 3px var(--light-blue); /* Focus ring */
                outline: none;
            }
            .filter-form button {
                background-color: var(--primary-blue);
                color: white;
                border: none;
                padding: 8px 15px; /* Increased padding */
                border-radius: 6px;
                font-weight: 600;
                cursor: pointer;
                transition: background-color 0.3s ease, transform 0.2s ease;
                display: inline-flex;
                align-items: center;
                gap: 5px;
            }
            .filter-form button:hover {
                background-color: var(--hover-blue);
                transform: translateY(-1px);
            }

            /* Table Styles */
            table {
                width: 100%;
                border-collapse: separate; /* Use separate for border-radius on cells */
                border-spacing: 0; /* Remove space between cells */
                background: white;
                border-radius: 10px; /* More rounded table corners */
                overflow: hidden; /* Ensures rounded corners are visible */
                box-shadow: 0 4px 15px var(--shadow-medium); /* Stronger shadow */
            }
            th, td {
                padding: 15px; /* Increased padding */
                border-bottom: 1px solid var(--border-light); /* Only bottom border */
                text-align: center;
            }
            th {
                background-color: var(--primary-blue);
                color: white;
                font-weight: 600;
                text-transform: uppercase;
                font-size: 0.9rem;
            }
            /* Remove border from last row cells */
            tbody tr:last-child td {
                border-bottom: none;
            }
            /* Hover effect for table rows */
            tbody tr:hover {
                background-color: var(--light-blue);
                transition: background-color 0.2s ease;
            }

            /* Status Badges */
            .status {
                padding: 6px 12px; /* Larger padding for badges */
                border-radius: 20px; /* Pill shape */
                color: white;
                display: inline-block;
                font-weight: 600;
                font-size: 0.85rem;
                min-width: 90px; /* Ensure consistent width */
            }
            .status-scheduled {
                background-color: #007bff; /* Bootstrap primary blue */
            }
            .status-completed {
                background-color: #28a745; /* Bootstrap success green */
            }
            .status-cancelled {
                background-color: #dc3545; /* Bootstrap danger red */
            }

            /* Action Links */
            .action-link {
                text-decoration: none;
                color: var(--primary-blue);
                font-weight: 500;
                transition: color 0.2s ease, transform 0.2s ease;
                display: inline-flex;
                align-items: center;
                gap: 5px;
            }
            .action-link:hover {
                color: var(--hover-blue);
                transform: translateY(-1px);
            }
            /* Style for the "Sửa" button */
            .btn.btn-primary {
                background-color: var(--primary-blue);
                color: white;
                padding: 8px 15px;
                border-radius: 6px;
                text-decoration: none;
                font-weight: 600;
                display: inline-flex;
                align-items: center;
                gap: 5px;
                transition: background-color 0.3s ease, transform 0.2s ease;
            }
            .btn.btn-primary:hover {
                background-color: var(--hover-blue);
                transform: translateY(-1px);
            }


            /* No Data Message */
            .no-data {
                font-style: italic;
                color: var(--text-light);
                text-align: center;
                padding: 25px;
                font-size: 1.1rem;
            }

            /* Pagination */
            .pagination {
                text-align: center;
                margin-top: 30px; /* Increased margin */
                display: flex;
                justify-content: center;
                gap: 8px; /* Space between pages */
            }
            .pagination a {
                padding: 8px 16px; /* Larger padding */
                text-decoration: none;
                background-color: #f0f2f5; /* Lighter background */
                color: var(--text-dark);
                border-radius: 6px;
                font-weight: 500;
                transition: background-color 0.3s ease, color 0.3s ease, transform 0.2s ease;
            }
            .pagination a:hover {
                background-color: var(--primary-blue);
                color: white;
                transform: translateY(-1px);
            }
            .pagination a.active {
                background-color: var(--primary-blue);
                color: white;
                font-weight: 700;
                box-shadow: 0 2px 8px var(--shadow-medium);
            }
            .pagination span { /* Style for ellipsis */
                padding: 8px 4px; /* Smaller padding for visual alignment */
                color: var(--text-light);
                font-weight: 500;
            }
            .pagination a.pagination-prev,
            .pagination a.pagination-next {
                background-color: var(--primary-blue);
                color: white;
                font-weight: 600;
                display: inline-flex;
                align-items: center;
                gap: 5px;
            }
            .pagination a.pagination-prev:hover,
            .pagination a.pagination-next:hover {
                background-color: var(--hover-blue);
                transform: translateY(-1px);
            }

            /* Responsive adjustments */
            @media (max-width: 768px) {
                .right-side { /* Changed from content-wrapper to right-side */
                    padding: 20px 15px;
                }
                .filter-form {
                    flex-direction: column;
                    align-items: stretch;
                }
                .filter-form input,
                .filter-form select,
                .filter-form button {
                    width: 100%;
                }
                table, thead, tbody, th, td, tr {
                    display: block;
                }
                thead tr {
                    position: absolute;
                    top: -9999px;
                    left: -9999px;
                }
                tr {
                    margin-bottom: 15px;
                    border: 1px solid var(--border-light);
                    border-radius: 10px;
                    overflow: hidden;
                    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                }
                td {
                    border: none;
                    border-bottom: 1px solid var(--border-light);
                    position: relative;
                    padding-left: 50%;
                    text-align: left;
                    white-space: normal; /* Allow text to wrap */
                }
                td:last-child {
                    border-bottom: none;
                }
                td:before {
                    content: attr(data-label);
                    position: absolute;
                    left: 10px;
                    width: 45%;
                    padding-right: 10px;
                    white-space: nowrap;
                    font-weight: 600;
                    color: var(--primary-blue);
                }
            }
            @media (max-width: 480px) {
                .right-side h2 { /* Changed from content-wrapper h2 to right-side h2 */
                    font-size: 1.5rem;
                }
                .right-side h2 i { /* Changed from content-wrapper h2 i to right-side h2 i */
                    font-size: 1.8rem;
                }
                .add-meeting-btn {
                    width: 100%;
                    justify-content: center;
                }
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <%-- SIDEBAR --%>
            <jsp:include page="/view/sidebarTeacher.jsp"/>

            <div class="right-side">
                <div class="container"> <%-- This container might need review based on sidebarTeacher.css --%>
                    <h2><i class="fa-solid fa-people-group"></i> Danh sách cuộc họp phụ huynh</h2>

                    <div style="margin-bottom: 20px;">
                        <a href="${pageContext.request.contextPath}/teacher/addparentmeeting"
                           class="add-meeting-btn">
                            <i class="fa-solid fa-plus"></i> Thêm cuộc họp mới
                        </a>
                    </div>

                    <form method="get" class="filter-form" action="${pageContext.request.contextPath}/teacher/parentmeetings">
                        <input type="text" name="search" placeholder="Tìm theo chủ đề" value="${search}">
                        <select name="status">
                            <option value="">-- Tất cả trạng thái --</option>
                            <option value="Scheduled" ${status == 'Scheduled' ? 'selected' : ''}>Scheduled</option>
                            <option value="Completed" ${status == 'Completed' ? 'selected' : ''}>Completed</option>
                            <option value="Cancelled" ${status == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                        </select>
                        <button type="submit"><i class="fa-solid fa-filter"></i> Lọc</button>
                    </form>

                    <c:choose>
                        <c:when test="${not empty meetingList}">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Chủ đề</th>
                                        <th>Lớp</th>
                                        <th>Ngày họp</th>
                                        <th>Trạng thái</th>
                                        <th>Ghi chú</th>
                                        <th>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="m" items="${meetingList}">
                                        <tr>
                                            <td data-label="Chủ đề">${m.topic}</td>
                                            <td data-label="Lớp">${m.className}</td>
                                            <td data-label="Ngày họp">${m.meetingDate}</td>
                                            <td data-label="Trạng thái">
                                                <span class="status
                                                      ${m.status eq 'Scheduled' ? 'status-scheduled' :
                                                        m.status eq 'Completed' ? 'status-completed' :
                                                        m.status eq 'Cancelled' ? 'status-cancelled' : ''}">
                                                          ${m.status}
                                                      </span>
                                                </td>
                                                <td data-label="Ghi chú">${m.notes}</td>
                                                <td data-label="Hành động">
                                                    <a href="${pageContext.request.contextPath}/teacher/editparentmeeting?id=${m.meetingId}"
                                                       class="btn btn-primary"><i class="fa fa-edit"></i> Sửa</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <p class="no-data">Không có cuộc họp nào.</p>
                            </c:otherwise>
                        </c:choose>

                        <!-- Pagination -->
                        <!-- Pagination -->
                        <c:if test="${totalPages > 1}">
                            <div class="pagination">
                                <c:if test="${currentPage > 1}">
                                    <a href="?page=${currentPage - 1}&search=${search}&status=${status}" class="pagination-prev">
                                        <i class="fa-solid fa-angle-left"></i> Prev
                                    </a>
                                </c:if>

                                <%-- Page Numbers --%>
                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <a href="?page=${i}&search=${search}&status=${status}" class="${i == currentPage ? 'active' : ''}">
                                        ${i}
                                    </a>
                                </c:forEach>

                                <c:if test="${currentPage < totalPages}">
                                    <a href="?page=${currentPage + 1}&search=${search}&status=${status}" class="pagination-next">
                                        Next <i class="fa-solid fa-angle-right"></i>
                                    </a>
                                </c:if>
                            </div>
                        </c:if>


                    </div>
                </div>
        </body>
    </html>