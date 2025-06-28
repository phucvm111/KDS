<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Ảnh hoạt động của lớp</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css">
        <style>
            /* CSS gốc viewImages */
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f4f6f9;
                margin: 0;
            }
            .page-content {
                padding: 20px;
            }
            h1 {
                text-align: center;
                color: #2f3542;
                margin-bottom: 10px;
            }
            .controls {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin: 20px auto;
                max-width: 900px;
                flex-wrap: wrap;
            }
            .date-picker form {
                display: flex;
                align-items: center;
                gap: 10px;
            }
            .date-picker input[type="date"] {
                padding: 8px;
                font-size: 14px;
                border: 1px solid #ccc;
                border-radius: 6px;
            }
            .date-picker input[type="submit"] {
                padding: 8px 14px;
                background-color: #4e73df;
                color: white;
                border: none;
                border-radius: 6px;
                cursor: pointer;
            }
            .date-picker input[type="submit"]:hover {
                background-color: #375ab6;
            }
            .upload-form {
                margin-top: 10px;
            }
            .upload-form input[type="file"] {
                margin-right: 10px;
            }
            .upload-form input[type="submit"] {
                background-color: #28a745;
                color: white;
                border: none;
                padding: 8px 16px;
                border-radius: 6px;
                cursor: pointer;
            }
            .upload-form input[type="submit"]:hover {
                background-color: #218838;
            }
            .back-button a {
                text-decoration: none;
                background-color: #6c757d;
                color: white;
                padding: 8px 16px;
                border-radius: 6px;
                font-size: 14px;
            }
            .back-button a:hover {
                background-color: #5a6268;
            }
            .image-group {
                margin: 30px auto;
                max-width: 1100px;
            }
            .image-group h2 {
                font-size: 18px;
                color: #3742fa;
                margin-bottom: 15px;
            }
            .image-list {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                gap: 20px;
            }
            .image-card {
                background: #fff;
                padding: 12px;
                border-radius: 12px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.05);
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                align-items: center;
                transition: transform 0.2s ease-in-out;
            }
            .image-card:hover {
                transform: translateY(-4px);
            }
            .image-card img {
                width: 100%;
                height: 200px;
                object-fit: contain;
                border-radius: 8px;
                background-color: #f4f4f4;
                padding: 6px;
                cursor: zoom-in;
            }
            .image-card button {
                background-color: #e74c3c;
                color: white;
                border: none;
                padding: 6px 12px;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 10px;
            }
            .image-card button:hover {
                background-color: #c0392b;
            }
            .no-images {
                text-align: center;
                color: gray;
                font-style: italic;
                margin-top: 40px;
            }
            .pagination a {
                margin: 0 5px;
                text-decoration: none;
                color: #007bff;
            }
            .pagination a:hover {
                text-decoration: underline;
            }
            /* tránh xung đột với sidebar css */
            .wrapper {
                display: flex;
                width: 100%;
            }
            .right-side {
                flex: 1;
                background-color: #f4f6f9;
                overflow-x: hidden;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <%-- SIDEBAR --%>
            <jsp:include page="/view/sidebarTeacher.jsp" />

            <div class="right-side">
                <div class="page-content">
                    <h1>📸 Ảnh hoạt động của lớp</h1>

                    <div class="controls">
                        <div class="date-picker">
                            <form method="get" action="viewImages">
                                <label for="date">Xem ảnh theo ngày:</label>
                                <input type="date" name="date" value="${selectedDate}">
                                <input type="submit" value="Xem">
                            </form>
                        </div>
                        <div class="upload-form">
                            <form action="${pageContext.request.contextPath}/teacher/uploadImage" method="post" enctype="multipart/form-data">
                                <input type="file" name="imageFile" accept="image/*" required />
                                <input type="submit" value="Tải ảnh lên" />
                            </form>
                        </div>
<!--                        <div class="back-button">
                            <a href="${pageContext.request.contextPath}/teacher/teacherprofile.jsp">⬅ Quay lại</a>
                        </div>-->
                    </div>

                    <c:choose>
                        <c:when test="${empty imageMap}">
                            <p class="no-images">Không có ảnh nào cho ngày này.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="entry" items="${imageMap}">
                                <div class="image-group">
                                    <h2>📅 Ngày: ${entry.key}</h2>
                                    <div class="image-list">
                                        <c:forEach var="img" items="${entry.value}">
                                            <div class="image-card">
                                                <img src="${pageContext.request.contextPath}/${img}" alt="Ảnh lớp">
                                                <form method="post" action="viewImages">
                                                    <input type="hidden" name="path" value="${img}">
                                                    <input type="hidden" name="date" value="${selectedDate}">
                                                    <input type="hidden" name="page" value="${currentPage}">
                                                    <button type="submit">Xoá</button>
                                                </form>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <c:if test="${totalPages > 1}">
                                        <div class="pagination" style="text-align:center; margin-top: 30px;">
                                            <c:if test="${currentPage > 1}">
                                                <a href="?page=${currentPage - 1}&date=${selectedDate}">« Trang trước</a>
                                            </c:if>
                                            <c:forEach begin="1" end="${totalPages}" var="i">
                                                <a href="?page=${i}&date=${selectedDate}" style="${i == currentPage ? 'font-weight:bold;' : ''}">${i}</a>
                                            </c:forEach>
                                            <c:if test="${currentPage < totalPages}">
                                                <a href="?page=${currentPage + 1}&date=${selectedDate}">Trang sau »</a>
                                            </c:if>
                                        </div>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                    <div id="lightbox" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%;
                         background:rgba(0,0,0,0.85); justify-content:center; align-items:center; z-index:1000; flex-direction:column;">
                        <div style="width: 100%; text-align: right; padding: 10px 20px;">
                            <span id="close-lightbox" style="font-size: 28px; color: white; cursor: pointer;">&times;</span>
                        </div>
                        <img id="lightbox-img" src="" style="max-width:90%; max-height:90%; border: 5px solid white; border-radius: 10px;">
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const lightbox = document.getElementById("lightbox");
                const lightboxImg = document.getElementById("lightbox-img");
                const closeBtn = document.getElementById("close-lightbox");

                document.querySelectorAll(".image-card img").forEach(img => {
                    img.addEventListener("click", function () {
                        lightboxImg.src = this.src;
                        lightbox.style.display = "flex";
                    });
                });
                closeBtn.addEventListener("click", function () {
                    lightbox.style.display = "none";
                    lightboxImg.src = "";
                });
                lightbox.addEventListener("click", function (e) {
                    if (e.target === lightbox) {
                        lightbox.style.display = "none";
                        lightboxImg.src = "";
                    }
                });
            });
        </script>
    </body>
</html>
