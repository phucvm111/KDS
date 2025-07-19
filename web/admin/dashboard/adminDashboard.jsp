<%-- 
    Document   : adminDashboard
    Created on : Jun 11, 2025, 2:40:03 PM
    Author     : Vu Tuan Hai <HE176383>
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!DOCTYPE html>
<link rel="icon" href="./assets/image/logo2-removebg-preview.png">
<link rel="stylesheet" href="admin/account/css/style.css">
<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
<html>
    <head>
        <meta charset="UTF-8">
        <title>Bảng điều khiển quản trị</title>
        <style>
            body {
                font-family: 'Segoe UI', sans-serif;
                background: #f9f9fb;
                margin: 0;
                padding: 2rem;
                color: #333;
            }

            .dashboard {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                justify-content: center;
            }

            .card {
                flex: 1 1 240px;
                background: linear-gradient(135deg, #ffffff, #f0f4ff);
                border-radius: 16px;
                padding: 24px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
                transition: transform 0.3s ease, box-shadow 0.3s ease;
                text-align: center;
                position: relative;
                overflow: hidden;
                border: 1px solid #e0e6f0;
            }

            .card::before {
                content: '';
                position: absolute;
                width: 100px;
                height: 100px;
                background: rgba(100, 149, 237, 0.08);
                border-radius: 50%;
                top: -30px;
                right: -30px;
                z-index: 0;
            }

            .card:hover {
                transform: translateY(-5px);
                box-shadow: 0 12px 20px rgba(0, 0, 0, 0.12);
            }

            .count {
                font-size: 36px;
                font-weight: 600;
                color: #4a4a4a;
                z-index: 1;
                position: relative;
            }

            .label {
                margin-top: 10px;
                font-size: 16px;
                color: #555;
                z-index: 1;
                position: relative;
            }

            .card a {
                margin-top: 12px;
                display: inline-block;
                color: #3a86ff;
                font-weight: 500;
                text-decoration: none;
                z-index: 1;
                position: relative;
                transition: color 0.2s ease;
            }

            .card a:hover {
                color: #265df2;
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <form>
            <nav>
                <div class="logo-name">
                    <div class="logo-image">
                        <img src="${pageContext.request.contextPath}/assets/image/logo.png" alt="Logo">
                    </div>
                    <span class="logo_name">
                        <a href="dashboard">Trang quản trị</a>
                    </span>
                </div>

                <div class="menu-items">
                    <jsp:include page="/view/adminSidebar.jsp" />
                </div>
            </nav>
            <h2>Dashboard</h2>
            <div class="dashboard">
                <div class="card ">
                    <div class="count">${accountNum}</div>
                    <div class="label">Tài khoản</div><br/>
                    <a href="listaccount"> Xem tất cả <i class="uil uil-arrow-right"></i> </a>
                </div>
                <div class="card ">
                    <div class="count">${parentNum}</div>
                    <div class="label">Phụ huynh</div><br/>
                    <a href="listaccount"> Xem tất cả<i class="uil uil-arrow-right"></i> </a>
                </div>
                <div class="card ">
                    <div class="count">${teacherNum}</div>
                    <div class="label">Giáo viên</div><br/>
                    <a href="listaccount"> Xem tất cả <i class="uil uil-arrow-right"></i></a>
                </div>
                <div class="card ">
                    <div class="count">${classNum}</div>
                    <div class="label">Lớp</div><br/>
                    <a href="listaccount"> Xem tất cả <i class="uil uil-arrow-right"></i></a>
                </div>
                <div class="card ">
                    <div class="count">${kindergartnerNum}</div>
                    <div class="label">Trẻ</div><br/>
                    <a href="listkinder"> Xem tất cả <i class="uil uil-arrow-right"></i></a>
                </div>
                <div class="card ">
                    <div class="count">${eventNum}</div>
                    <div class="label">Sự kiện</div><br/>
                    <a href="event"> Xem tất cả <i class="uil uil-arrow-right"></i></a>
                </div>
                <h2>Biểu đồ số lượng tài khoản</h2>
                <div class="container">
                    <canvas id="myChart"></canvas>
                </div>
                <h2>Danh sách lớp</h2>
                <c:choose>
                    <c:when test="${not empty classList}">
                        <div class="dashboard">
                            <c:forEach var="c" items="${classList}">
                                <div class="card ">
                                    <div class="count">${c.class_name}</div>
                                    <div class="label">Khối: ${c.grade}</div>
                                    <div class="label">Thông tin: ${c.class_description}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>Chưa có lớp nào.</p>
                    </c:otherwise>
                </c:choose>

                <h2>Danh sách sự kiện</h2>
                <c:choose>
                    <c:when test="${not empty event}">
                        <div class="dashboard">
                            <c:forEach var="e" items="${event}">
                                <div class="card ">
                                    <div class="count">${e.eventName}</div>
                                    <div class="label">Ngày tổ chức: ${e.eventDate}</div>
                                    <div class="label">Thông tin chi tiết: ${e.eventDescription}</div>
                                    <div class="label">Địa điểm tổ chức: ${e.location}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>Chưa có sự kiện nào.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
        <script>
            let myChart = document.getElementById('myChart').getContext('2d');
            // Global Options
            Chart.defaults.global.defaultFontFamily = 'Lato';
            Chart.defaults.global.defaultFontSize = 18;
            Chart.defaults.global.defaultFontColor = '#777';

            let massPopChart = new Chart(myChart, {
                type: 'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
                data: {
                    labels: ['Giáo viên', 'Phụ huynh'],
                    datasets: [{
                            label: '',
                            data: [
            ${teacherNum},
            ${parentNum}
                            ],
                            //backgroundColor:'green',
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.6)',
                                'rgba(54, 162, 235, 0.6)'
                            ],
                            borderWidth: 1,
                            borderColor: '#777',
                            hoverBorderWidth: 3,
                            hoverBorderColor: '#000'
                        }]
                },
                options: {
                    title: {
                        display: true,
                        text: 'Biểu đồ số lượng tài khoản',
                        fontSize: 25
                    },
                    legend: {
                        display: true,
                        position: 'right',
                        labels: {
                            fontColor: '#000'
                        }
                    },
                    layout: {
                        padding: {
                            left: 50,
                            right: 0,
                            bottom: 0,
                            top: 0
                        }
                    },
                    tooltips: {
                        enabled: true
                    },
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                    min: 0
                                }
                            }]
                    }
                }
            });
        </script>
    </body>
</html>

