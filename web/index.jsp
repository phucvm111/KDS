<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/node_modules/fullpage.js/dist/fullpage.min.css">
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Caveat+Brush&family=Hi+Melody&family=Patrick+Hand&display=swap"
              rel="stylesheet">
        <title>KDS</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style_2.css">
       
        <style>
            /* CSS cho body và các section của fullpage.js */
            body {
                margin: 0;
                font-family: 'Patrick Hand', cursive;
                padding-top: 70px; /* Đảm bảo nội dung không bị header cố định che */
                box-sizing: border-box;
            }

            /* --- CSS CHO HEADER CHÍNH --- */
            .main-header {
                width: 100%;
                display: flex; /* Sử dụng Flexbox để căn chỉnh các phần tử */
                justify-content: space-between; /* Đẩy logo sang trái, menu ra giữa, avatar sang phải */
                align-items: center;

                padding: 10px 20px;
                background-color: white; /* Đảm bảo có màu nền */
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                position: fixed; /* Cố định header khi cuộn */
                top: 0;
                left: 0;
                z-index: 1000; /* Đảm bảo header nằm trên các phần tử khác */
            }

            /* CSS cho phần Logo trong Header */
            .main-header .logo-main {
                font-size: 24px;
                font-weight: bold;
                color: #333; /* Màu chữ cho tên logo */
                text-decoration: none;
                display: flex;
                align-items: center;
                flex-shrink: 0; /* Ngăn logo bị co lại */
            }

            .main-header .logo-main img {
                height: 40px; /* Kích thước logo */
                margin-right: 10px;
            }

            /* CSS cho Navigation Menu */
            .main-header nav {
                flex-grow: 1; /* Cho phép nav chiếm không gian còn lại để căn giữa */
                text-align: center; /* Căn giữa nội dung của nav */
            }

            .main-header nav ul {
                list-style: none;
                margin: 0;
                padding: 0;
                display: flex; /* Sử dụng Flexbox cho các mục menu */
                justify-content: center; /* Căn giữa các mục menu */
                gap: 20px; /* Khoảng cách giữa các mục menu */
            }

            .main-header nav ul li a {
                text-decoration: none;
                color: #555; /* Màu chữ mặc định của menu */
                font-weight: bold;
                padding: 5px 10px;
                transition: color 0.3s ease;
                white-space: nowrap; /* Ngăn chữ bị ngắt dòng */
            }

            .main-header nav ul li a:hover {
                color: #7d44c8; /* Màu hover của menu */
            }

            /* CSS cho Avatar và Dropdown Menu */
            .avatar-container {
                position: relative;
                cursor: pointer;
                margin-left: 20px; /* Khoảng cách giữa menu và avatar */
                flex-shrink: 0; /* Ngăn avatar bị co lại */
            }

            .avatar-container img {
                border-radius: 50%;
                width: 40px;
                height: 40px;
                object-fit: cover;
            }

            .dropdown-menu {
                position: absolute;
                right: 0;
                top: 50px; /* Đặt dưới avatar một chút */
                background-color: white;
                color: black;
                border: 1px solid #ccc;
                border-radius: 8px;
                width: 180px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                display: none; /* Mặc định ẩn */
                z-index: 1001; /* Đảm bảo dropdown nằm trên header */
            }

            .dropdown-menu a {
                display: block;
                padding: 10px;
                text-decoration: none;
                color: black;
                text-align: left;
            }

            .dropdown-menu a:hover {
                background-color: #f0f0f0;
            }

            /* Đảm bảo FullPage.js navigation không bị che bởi header */
            #fp-nav {
                z-index: 999; /* Đặt thấp hơn header nhưng cao hơn nội dung */
            }

            /* Các section của FullPage.js */
            .section {
                /* FullPage.js tự động xử lý vị trí.
                   padding-top body đã giải quyết việc không bị header che. */
            }

            /* --- CSS CHO Events Section (MỚI) --- */
            .events-container {
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                gap: 20px; /* Khoảng cách giữa các card sự kiện */
                padding: 20px;
                max-width: 1200px;
                margin: 0 auto; /* Căn giữa container */
            }

            .event-card {
                background-color: #f9f9f9;
                border: 1px solid #e0e0e0;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                padding: 25px;
                width: 400px; /* Chiều rộng cố định cho mỗi card */
                text-align: left;
                transition: transform 0.3s ease;
            }

            .event-card:hover {
                transform: translateY(-5px); /* Hiệu ứng khi hover */
            }

            .event-card h3 {
                color: #7d44c8;
                font-size: 1.5em;
                margin-top: 0;
                margin-bottom: 10px;
                font-family: 'Caveat Brush', cursive; /* Hoặc font khác bạn thích */
            }

            .event-card .event-date {
                font-weight: bold;
                color: #555;
                margin-bottom: 5px;
            }

            .event-card .event-location {
                color: #777;
                font-style: italic;
                margin-bottom: 15px;
            }

            .event-card .event-description {
                font-size: 0.95em;
                line-height: 1.6;
                color: #333;
            }
        </style>
    </head>
    <body>
        <header class="main-header">
            <a href="#welcome" class="logo-main">
                <img src="${pageContext.request.contextPath}/assets/image/logo.png" alt="" />
            </a>
            <nav>
                <ul>
                    <li><a href="#events-section" class="navigation-links">Events</a></li>
                    <li><a href="#about-us" class="navigation-links">About us</a></li>
                    <li><a href="#section-2" class="navigation-links">Where we are</a></li>
                    <li><a href="#section-3" class="navigation-links">Vision & Mission</a></li>
                    <li><a href="#section-4" class="navigation-links">What we do</a></li>

                    <li><a href="#feedback" class="navigation-links">Parent's feedback</a></li>
                </ul>
            </nav>
            <div class="avatar-container" onclick="toggleMenu()">
                <img src="https://i.pravatar.cc/40" alt="Avatar" />
                <div class="dropdown-menu" id="dropdown">
                    <a href="${pageContext.request.contextPath}/parent/parentprofile.jsp">⚙ Information</a>
                    <a href="${pageContext.request.contextPath}/index.html">🚪 Logout</a>
                </div>
            </div>
        </header>

        <div id="fullpage">

            <%-- Section Events (Đã thay thế và cấu trúc lại từ Parent Meetings) --%>
            <div class="section section-custom" id="events-section">
                <h2 style="text-align: center; margin-bottom: 40px;">🗓️ Upcoming <span class="highlight">Events</span></h2>
                <div class="events-container">
                    <c:choose>
                        <c:when test="${not empty events}"> <%-- Sử dụng 'events' thay vì 'meetingsList' --%>
                            <c:forEach var="event" items="${events}" varStatus="loop"> <%-- Vòng lặp cho Event object --%>
                                <div class="event-card">
                                    <h3 class="event-title"><c:out value="Sự kiện sắp tới: ${event.eventName}" /></h3>
                                    <p class="event-info"><strong>Thời gian:</strong>
                                        <fmt:formatDate value="${event.eventDate}" pattern="dd/MM/yyyy" />
                                    </p>
                                    <p class="event-info"><strong>Địa chỉ:</strong>
                                        <c:out value="${event.location}" />
                                    </p>
                                    <p class="event-info"><strong>Mô tả:</strong>
                                        <c:out value="${event.eventDescription}" />
                                    </p>
                                </div>

                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p style="text-align: center;">Hiện chưa có sự kiện nào được lên lịch.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <%-- Kết thúc Section Events --%>

            <div class="section section-custom" id="welcome">
                <div class="title-container">
                    <h1>We embrace<br /><span class="highlight">the magic</span> of childhood</h1>
                    <p style="color: #dad4e3">We aim to care for your child <br />as you would at home in a safe, fun and happy way...</p>
                </div>
            </div>

            <div class="section section-custom" id="about-us">
                <h2>A little bit <span class="highlight">about us</span></h2>
                <div class="sub-text">
                    <p>After more than <span class="highlight">10 years</span> of dedication to early childhood education, our
                        Kindergarten Management System is trusted by both parents and <span class="highlight">educational experts</span> for helping
                        schools apply modern management methods effectively. The system supports personalized learning and care,
                        aiming to unlock the full potential of each child.</p>
                    <p>With a deep understanding of the needs of children aged <span class="highlight">0-6 years old</span>, the system assists teachers
                        through detailed observations, attendance tracking, and health monitoring. A personal <span class="highlight">roadmap for each
                            child</span> is developed to ensure timely support, nutritious meals, safe environments, and quality rest for every child.</p>
                </div>
            </div>

            <div class="section section-custom section-text-img" id="section-2">
                <img src="${pageContext.request.contextPath}/assets/image/section-2.jpeg" alt="">
                <div class="text-container">
                    <h2><span>Vietnam's leading solution</span><br />for kindergarten management</h2>
                    <p>Launched in May 2025, the KMS (Kindergarten Management System) is designed to streamline and modernize how kindergartens operate
                        , supporting the implementation of the <span class="highlight">Educational
                            experts</span> educational approach through technology and data-driven tools.</p>
                    <p>Currently, KMS is being used in multiple locations including <span class="highlight">Hanoi</span>, <span class="highlight">Ho Chi Minh City</span>,
                        and <span class="highlight">Quang Ninh</span>, helping schools manage student information, attendance, health tracking, parent
                        communication, and activity scheduling in an efficient and secure way. The platform supports kindergartens in providing personalized
                        care and education for every child.</p>
                </div>
            </div>

            <div class="section section-custom section-small-blocks" id="section-3">
                <div class="small-block small-block-3">
                    <h2>Our <span>mission</span></h2>
                    <p>KMS is committed to providing the community with a sustainable educational
                        environment, inspiring creativity.
                        <br /><br />
                        Here at KMS, we feel privileged to share your child's early years and
                        we are committed to making sure that this is the right place for your child and for you. We
                        fully recognise the great trust that you have placed in us when deciding to leave your child here
                        and
                        we will work hard to ensure that we repay that trust.
                    </p>
                </div>
                <div class="small-block small-block-3">
                    <h2>Our <span>goals</span></h2>
                    <p>Our carers provide genuine love and concern for the children in their care, thus providing them
                        with
                        a warm, friendly and inviting atmosphere where your child will feel secure and happy in their
                        new
                        environment.
                        <br /><br />
                        We want every part of our service to not only meet, but to exceed
                        your needs and those of
                        your child. This requires partnership, so at ATKD ChildCare we believe that families and
                        educators
                        must cooperate and work together, sharing information and building relationships and a sense of
                        community and belonging.
                    </p>
                </div>
                <div class="small-block small-block-3">
                    <h2>Things <span>to do</span></h2>
                    <p>We will:<br />
                        <br />
                        - Create a learning environment which will encourage children to interact, take on roles and
                        develop
                        relationships with others through play<br />
                        <br />
                        - Encourage children to be respectful, sensitive and thoughtful towards their natural
                        environment<br />
                        <br />
                        - Provide a nutritious and varied menu with the emphasis on organic, natural foods and a nut
                        free
                        environment.
                    </p>
                </div>
            </div>

            <div class="section section-custom section-text-img" id="section-4">
                <div class="text-container">
                    <h2><span>Teaching, nurturing, and building </span>confidence is our specialty</h2>
                    <p>To become a leading institution in the field of education and training throughout from
                        Kindergarten
                        to Pre-University with the goal of combining Eastern values with international education to
                        train
                        global citizens. bridge.</p>
                    <p>
                        Provide high-quality educational programs in an innovative and challenging learning environment.
                        Applying innovative and advanced methods in teaching and learning keeps students interested and
                        confident about lifelong learning.</p>
                </div>
                <img src="${pageContext.request.contextPath}/assets/image/section-4.jpeg" alt="">
            </div>

            <div class="section section-custom section-small-blocks" id="feedback">
                <div class="small-block small-block-2">
                    <h1>"</h1>
                    <p>Mình có một bé 3,5 tuổi đã theo học tại trường ngay từ thời gian đầu mới thành lập. Quan điểm của
                        mình và gia đình là đối với việc giáo dục con trẻ thì môi trường và phương pháp là hai yếu tố
                        rất
                        quan trọng, ảnh hưởng lớn đến trẻ. Cả hai yếu tố này nhà trường đều đáp ứng được rất tốt…</p>
                    <h5><span>Mr. Phạm Thanh Tùng - Phụ huynh bé Nancy
                        </span></h5>
                </div>
                <div class="small-block small-block-2">
                    <h1>"</h1>
                    <p>Điều tôi ấn tượng nhất ở nhà trường chính là các hoạt động ngoại khóa của trường. Mỗi sự kiện của
                        trường được tổ chức quy mô, hướng tới những thông điệp ý nghĩa mà thông qua đó các con được tự
                        do
                        sáng tạo, trau dồi kỹ năng và hình thành những đức tính tốt.
                    </p>
                    <h5><span>Mrs. Ngô Thanh Thúy - Phụ huynh bé Bin, lớp Orchid</span></h5>
                </div>
            </div>

            <footer class="section section-custom" id="footer">Copyright © 2022. All Rights Reserved | Designed by:
                @trananhhh | </footer>
        </div>

        <script>
            function toggleMenu() {
                const menu = document.getElementById("dropdown");
                menu.style.display = menu.style.display === "block" ? "none" : "block";
            }

            document.addEventListener("click", function (event) {
                const avatar = document.querySelector(".avatar-container");
                const menu = document.getElementById("dropdown");
                // Đảm bảo rằng click không phải bên trong avatar hoặc dropdown menu
                if (!avatar.contains(event.target) && !menu.contains(event.target)) {
                    menu.style.display = "none";
                }
            });
        </script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/4.0.9/fullpage.min.js"
                integrity="sha512-JSVRnP8UFs0ieN/cvP9v4vmW1CotIaEKKN7W+4JaKNrllZolTv2aJfVGn4BFdfZ1jRZxgTAAaXWdlZbEm9iwFA=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    </body>
</html>