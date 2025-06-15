<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Kindergarten Management System</title>
        <!-- AOS animate on scroll -->
        <link href="https://cdn.jsdelivr.net/npm/aos@2.3.4/dist/aos.css" rel="stylesheet" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Caveat+Brush&family=Hi+Melody&family=Patrick+Hand&display=swap" rel="stylesheet" />

        <link rel="icon" href="./assets/image/logo2-removebg-preview.png" />
        <link rel="stylesheet" href="/./node_modules/fullpage.js/dist/fullpage.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./assets/css/style_2.css" />


    </head>
    <style>
        .vision-section {
            display: flex;
            gap: 30px;
            padding: 80px 60px;
            background-color: #e6dff1;
            justify-content: center;
            flex-wrap: wrap;

        }

        .mission-card {
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
            width: 300px;
            max-width: 100%;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .mission-card:hover {
            transform: translateY(-10px) scale(1.03);
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.15);
        }

        .mission-card h3 {
            font-size: 1.8em;
            margin-bottom: 15px;
        }

        .mission-card span {
            color: #8e44ad;
        }

        body {
            margin: 0;
            font-family: 'Patrick Hand', sans-serif;
            overflow-x: hidden;
        }
        header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px 40px;
            background-color: white;
            position: fixed;
            width: 100%;
            top: 0;
            z-index: 999;
        }

        .header-logo {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: flex-start;
        }

        .header-logo img {
            height: 40px;
        }

        .header-nav {
            flex: 2;
            display: flex;
            justify-content: center;
            gap: 25px;
        }

        .navigation-links {
            text-decoration: none;
            color: #8e44ad;
            font-weight: bold;
            transition: 0.2s;
        }

        .navigation-links:hover {
            text-decoration: underline;
        }

        .right-side {
            flex: 1;
            display: flex;
            justify-content: flex-end;
            gap: 12px;
        }




        .navigation-links {
            text-decoration: none;
            color: #8e44ad;
            font-weight: bold;
            transition: 0.2s;
        }

        .navigation-links:hover {
            text-decoration: underline;
        }

        .right-side {
            display: flex;
            gap: 12px;
        }

        .login-btns {
            padding: 8px 16px;
            border: 1px solid #8e44ad;
            border-radius: 20px;
            text-decoration: none;
            color: #8e44ad;
            font-weight: bold;
        }

        .btn-highlight {
            background-color: #8e44ad;
            color: white;
        }

        .section {
            padding-top: 80px;
        }

        .section-text-img {
            display: flex;
            flex-direction: row;
            gap: 30px;
            align-items: center;
            justify-content: center;
            padding: 60px 80px;
            min-height: calc(100vh - 80px);
        }

        .section-text-img > .swiper.mySwiper {
            width: 100% !important;
            height: auto;


            max-width: 100%;
            align-self: stretch;
            display: block;
        }

        .swiper-wrapper {
            width: 100%;
            height: 100%;
        }

        .swiper-slide {
            width: 100%;
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .swiper-slide img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-radius: 12px;
        }

        .swiper-button-next,
        .swiper-button-prev {
            color: #8e44ad;
            background: rgba(255, 255, 255, 0.9);
            width: 40px;
            height: 40px;
            border-radius: 50%;
            top: 50%;
            transform: translateY(-50%);
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
            margin: 0 20px;
        }

        .swiper-button-next:hover,
        .swiper-button-prev:hover {
            background: rgba(142, 68, 173, 0.1);
            transform: translateY(-50%) scale(1.1);
        }

        .swiper-button-next::after,
        .swiper-button-prev::after {
            font-size: 16px;
            font-weight: bold;
        }

        .swiper-button-prev {
            left: 0px;
        }

        .swiper-button-next {
            right: 0px;
        }

        .text-container {

            flex: 0 0 50%;

        }

        .text-container h2 {
            font-size: 2.5em;
            line-height: 1.2;
            margin-bottom: 25px;
            color: #333;
            text-align: left;
        }

        .text-container p {
            font-size: 1.2em;
            line-height: 1.6;
            margin-bottom: 20px;
            color: #666;
            text-align: left;
        }

        .highlight {
            background: #d1c4e9;
            padding: 2px 8px;
            border-radius: 5px;
            font-weight: bold;
        }
        .swiper.mySwiper {
            flex: 0 0 60%;               /* tăng chiều ngang */
            height: auto;
            aspect-ratio: 5 / 3;         /* giảm chiều cao tương đối */
            border-radius: 12px;
            overflow: hidden;
        }

        @media (max-width: 768px) {
            header {
                flex-direction: column;
                align-items: flex-start;
                gap: 10px;
            }

            .header-left {
                flex-direction: column;
                gap: 10px;
            }

            .header-nav {
                flex-wrap: wrap;
                justify-content: center;
            }

            .section-text-img {
                grid-template-columns: 1fr;
                padding: 30px 20px;
                text-align: center;
                gap: 30px;
            }
            .swiper.mySwiper {
                height: 300px;
                flex: 0 0 70%;
                aspect-ratio: 5 / 3;
                border-radius: 12px;
                overflow: hidden;
            }



            .swiper-button-next,
            .swiper-button-prev {
                width: 35px;
                height: 35px;
            }

            .swiper-button-prev {
                left: 15px;
            }

            .swiper-button-next {
                right: 15px;
            }
        }
    </style>


    <body>
        <header>
            <div class="header-logo">
                <a href="#welcome">
                    <img src="./assets/image/logo.png" alt="logo" />
                </a>
            </div>

            <nav class="header-nav">
                <a href="#about-us" class="navigation-links">About us</a>
                <a href="#section-2" class="navigation-links">Where we are</a>
                <a href="#section-3" class="navigation-links">Vision & Mission</a>
                <a href="#section-4" class="navigation-links">What we do</a>
                <a href="#feedback" class="navigation-links">Parents's feedback</a>
            </nav>

            <div class="right-side">
                <a href="get_otp_register" class="login-btns btn-highlight">Register</a>
                <a href="login" class="login-btns">Login</a>
            </div>
        </header>





        <div class="section section-custom" id="welcome">
            <div class="title-container">
                <h1>We embrace<br /><span class="highlight">the magic</span> of childhood</h1>
                <p style="color: #dad4e3">
                    We aim to care for your child <br />as you would at home in a safe, fun and happy way...
                </p>
            </div>
        </div>

        <div class="section section-custom" id="about-us">
            <h2>A little bit <span class="highlight">about us</span></h2>
            <div class="sub-text">
                <p>
                    After more than <span class="highlight">10 years</span> of dedication to early childhood education, our Kindergarten Management System is trusted by both parents and
                    <span class="highlight">educational experts</span> for helping schools apply modern management methods effectively.
                </p>
                <p>
                    With a deep understanding of the needs of children aged <span class="highlight">0-6 years old</span>, the system assists teachers through detailed observations, attendance tracking, and health monitoring. A personal
                    <span class="highlight">roadmap for each child</span> is developed to ensure support and care.
                </p>
            </div>
        </div>

        <div class="section section-custom section-text-img" id="section-2">
            <div class="swiper mySwiper">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <img src="./assets/image/section-2.jpeg" alt="Image 1" />
                    </div>
                    <div class="swiper-slide">
                        <img src="./assets/image/image2.jpg" alt="Image 2" />
                    </div>
                    <div class="swiper-slide">
                        <img src="./assets/image/image3.jpg" alt="Image 3" />
                    </div>
                </div>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>

            <div class="text-container">
                <h2><span>Vietnam's leading solution</span><br />for kindergarten management</h2>
                <p>
                    Launched in May 2025, the KMS is designed to streamline and modernize how kindergartens operate, supporting <span class="highlight">Educational experts</span> through technology.
                </p>
                <p>
                    KMS is being used in <span class="highlight">Hanoi</span>, <span class="highlight">Ho Chi Minh City</span>, and <span class="highlight">Quang Ninh</span>, helping schools manage attendance, health, communication, and scheduling.
                </p>
            </div>
        </div>
        <div class="section section-custom vision-section" id="section-3">
            <div class="mission-card" data-aos="fade-up" data-aos-delay="100">
                <h3>Our <span>mission</span></h3>
                <p>
                    KMS is committed to providing the community with a sustainable educational environment, inspiring creativity.
                </p>
                <p>
                    We feel privileged to share your child’s early years and are committed to making sure this is the right place for your child and your family.
                </p>
            </div>

            <div class="mission-card" data-aos="fade-up" data-aos-delay="200">
                <h3>Our <span>goals</span></h3>
                <p>
                    Our carers provide love and care in a warm, friendly environment where your child will feel safe and happy.
                </p>
                <p>
                    We believe that families and educators must work together – sharing information and building lasting partnerships.
                </p>
            </div>

            <div class="mission-card" data-aos="fade-up" data-aos-delay="300">
                <h3>Things <span>to do</span></h3>
                <ul>
                    <li>Create an engaging environment through role play</li>
                    <li>Encourage respect and awareness of nature</li>
                    <li>Provide healthy meals with natural ingredients</li>
                </ul>
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
                    passionate about lifelong learning.</p>
                <!-- <button class="button-9" role="button">GET IN TOUCH</button> -->
            </div>
            <img src="./assets/image/section-4.jpeg" alt="">
        </div>
        <div class="section section-custom section-small-blocks" id="feedback">
            <div class="small-block small-block-2">
                <h1>"</h1>
                <p>Mình có một bé 3,5 tuổi đã theo học tại trường ngay từ thời gian đầu mới thành lập. Quan điểm của
                    mình và gia đình là đối với việc giáo dục con trẻ thì môi trường và phương pháp là hai yếu tố
                    rất
                    quan trọng, ảnh hưởng lớn đến trẻ. Cả hai yếu tố này nhà trường đều đáp ứng được rất tốt…</p>
                <h5><span>Mr. Phạm Thanh Tùng - Ph Bé Nancy
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




        <footer class="section section-custom" id="footer">
            Copyright © 2025. All Rights Reserved | Designed by: @phucvm |
        </footer>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/4.0.9/fullpage.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

    <script>
        const swiper = new Swiper('.mySwiper', {
            loop: true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            autoplay: {
                delay: 3000,
                disableOnInteraction: false,
            },
        });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/aos@2.3.4/dist/aos.js"></script>
    <script>
        AOS.init({
            duration: 800,
            once: true
        });
    </script>

</body>
</html>
