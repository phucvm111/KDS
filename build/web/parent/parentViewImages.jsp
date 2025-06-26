<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ảnh hoạt động của lớp</title>
        <style>
            body {
                font-family: 'Segoe UI', sans-serif;
                background-color: #f4f6f9;
                margin: 0;
                padding: 20px;
            }

            h1 {
                text-align: center;
                color: #2f3542;
                margin-bottom: 20px;
            }

            .controls {
                display: flex;
                justify-content: center;
                margin: 20px 0;
            }

            .controls form {
                display: flex;
                gap: 10px;
                align-items: center;
            }

            input[type="date"], input[type="submit"] {
                padding: 8px;
                border-radius: 6px;
                border: 1px solid #ccc;
                font-size: 14px;
            }

            input[type="submit"] {
                background-color: #4e73df;
                color: white;
                border: none;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #375ab6;
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
                text-align: center;
            }

            .image-card img {
                width: 100%;
                height: 200px;
                object-fit: contain;
                border-radius: 8px;
                background-color: #f9f9f9;
                padding: 6px;
            }

            .no-images {
                text-align: center;
                color: gray;
                font-style: italic;
                margin-top: 40px;
            }
            .back-button {
                text-decoration: none;
                background-color: #6c757d;
                color: white;
                padding: 8px 16px;
                border-radius: 6px;
                font-size: 14px;
                display: inline-block;
            }

            .back-button:hover {
                background-color: #5a6268;
            }

        </style>
    </head>
    <body>

        <h1>📸 Ảnh hoạt động của lớp</h1>

        <div class="controls">
            <form method="get" action="viewImages" style="display: flex; gap: 10px; align-items: center;">
                <label for="date">Xem ảnh theo ngày:</label>
                <input type="date" id="date" name="date" value="${selectedDate}">
                <input type="submit" value="Xem">
                <a href="${pageContext.request.contextPath}/parent/parentprofile.jsp" class="back-button">⬅ Quay lại</a>
            </form>
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
                                    <img src="${pageContext.request.contextPath}/${img}" alt="Ảnh lớp" class="zoomable">

                                    <!-- Không có nút xoá -->
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                </c:forEach>
                <c:if test="${totalPages > 1}">
                    <div style="text-align:center; margin-top: 30px;">
                        <c:if test="${currentPage > 1}">
                            <a href="?page=${currentPage - 1}&date=${selectedDate}">« Trang trước</a>
                        </c:if>
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <a href="?page=${i}&date=${selectedDate}" style="margin: 0 5px; ${i == currentPage ? 'font-weight:bold;' : ''}">
                                ${i}
                            </a>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <a href="?page=${currentPage + 1}&date=${selectedDate}">Trang sau »</a>
                        </c:if>
                    </div>
                </c:if>

            </c:otherwise>
        </c:choose>
        <!-- Lightbox phóng to ảnh -->
        <div id="lightbox" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%;
             background:rgba(0,0,0,0.85); justify-content:center; align-items:center; z-index:1000; flex-direction: column;">
            <div style="width:100%; text-align:right; padding:10px 20px;">
                <span id="close-lightbox" style="font-size: 28px; color: white; cursor: pointer;">&times;</span>
            </div>
            <img id="lightbox-img" src="" style="max-width:90%; max-height:90%; border:5px solid white; border-radius:10px;">
        </div>




    </body>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const lightbox = document.getElementById("lightbox");
            const lightboxImg = document.getElementById("lightbox-img");
            const closeBtn = document.getElementById("close-lightbox");

            document.querySelectorAll(".zoomable").forEach(img => {
                img.style.cursor = "zoom-in";
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
</html>
