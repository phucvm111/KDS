<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="admin/kinder/css/style.css">
        <link rel="stylesheet" href="admin/kinder/boot/bootstrap.min.css">
        <link rel="stylesheet" href="admin/kinder/boot/bootstrap.css">

        <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare</title>
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <style>
            body {
                background-color: #f0f4f8;
            }
            .selection-wrapper {
                margin-left: 250px;
                padding: 4rem 2rem;
                display: flex;
                justify-content: center;
            }
            .selection-section {
                width: 100%;
                max-width: 800px;
                display: flex;
                flex-direction: column;
                align-items: center;
                gap: 1.2rem;
            }
            .selection-section h2 {
                font-weight: bold;
                font-size: 2.2rem;
                margin-bottom: 1rem;
            }
            .selection-section a {
                width: 100%;
                padding: 1.2rem;
                font-size: 1.1rem;
                font-weight: 600;
                text-align: center;
                transition: transform 0.2s ease-in-out;
            }
            .selection-section a:hover {
                transform: scale(1.02);
            }
            .info-box {
                margin-top: 2rem;
                text-align: center;
                font-size: 1rem;
                color: #555;
                background-color: #ffffff;
                padding: 1.5rem;
                border-radius: 1rem;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.05);
            }
        </style>
    </head>

    <body>
        <nav>
            <div class="logo-name">
                <div class="logo-image">
                    <img src="images/logo.jpg" alt="">
                </div>
                <span class="logo_name"><a href="listaccount" style="text-decoration: none;color: black">Admin Page</a></span>
            </div>

            <div class="menu-items">
                <ul class="nav-links">
                    <li><a href="dashboard"><i class="uil uil-dashboard"></i>Dashboard</a></li>
                    <li><a href="listaccount"><i class="uil uil-estate"></i>Account</a></li>
                    <li><a href="listkinder"><i class="uil uil-chart"></i>Kindergartner</a></li>
                    <li><a href="listclass"><i class="uil uil-thumbs-up"></i>Class</a></li>
                    <li><a href="listschedule"><i class="uil uil-comments"></i>Schedule</a></li>
                    <li><a href="changepassword"><i class="uil uil-lock-alt"></i>Change Password</a></li>
                    <li><a href="event"><i class="uil uil-calendar-alt"></i>Event</a></li>
                    <li><a href="day_class"><i class="uil uil-utensils-alt"></i>Nutrition</a></li>
                </ul>
                <ul class="logout-mode">
                    <li><a href="login"><i class="uil uil-signout"></i>Logout</a></li>
                </ul>
            </div>
        </nav>

        <div class="selection-wrapper">
            <div class="selection-section">
                <h2>📋 Chọn danh mục học sinh</h2>
                <a href="${pageContext.request.contextPath}/students?status=studying" class="btn btn-lg btn-primary rounded-4 shadow-sm">
                    📚 Học sinh đang theo học
                </a>

                <a href="${pageContext.request.contextPath}/students?status=graduated" class="btn btn-lg btn-success rounded-4 shadow-sm">
                    🎓 Học sinh đã tốt nghiệp
                </a>
                <a href="${pageContext.request.contextPath}/students?status=dropped" class="btn btn-lg btn-danger rounded-4 shadow-sm">
                    ⛔ Học sinh đã thôi học
                </a>
                <a href="${pageContext.request.contextPath}/viewKinderList" class="btn btn-lg btn-warning rounded-4 shadow-sm">
                    ✏️ Chỉnh sửa thông tin học sinh
                </a>





                <div class="info-box mt-4">
                    <p><strong>Lưu ý:</strong> Bạn có thể chọn từng danh mục để xem và quản lý danh sách học sinh theo trạng thái học tập. Các danh sách này cho phép lọc theo lớp, tìm theo tên, và chỉnh sửa thông tin học sinh nếu cần.</p>
                </div>

            </div>
        </div>
    </body>
</html>
