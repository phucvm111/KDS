<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Báo Cáo Điểm Danh</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&family=Open+Sans:wght@400;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <style>
            /* Reset và Typography cơ bản */
            body {
                font-family: 'Open Sans', sans-serif; /* Sử dụng Open Sans cho body */
                background-color: #f4f7f6; /* Nền nhẹ nhàng, dễ chịu */
                color: #333;
                margin: 0;
                padding: 0;
                line-height: 1.6;
            }

            /* Container chính của báo cáo */
            .container {
                max-width: 1100px; /* Tăng chiều rộng tối đa */
                margin: 50px auto; /* Căn giữa và tạo khoảng cách trên/dưới */
                background: #ffffff;
                padding: 40px; /* Tăng padding */
                border-radius: 15px; /* Bo tròn góc nhiều hơn, hiện đại */
                box-shadow: 0 8px 25px rgba(0,0,0,0.1); /* Bóng đổ mềm mại, sâu hơn */
                border: 1px solid #e0e0e0; /* Viền nhẹ */
            }

            /* Tiêu đề trang */
            h1 {
                font-family: 'Roboto', sans-serif; /* Sử dụng Roboto cho tiêu đề */
                text-align: center;
                color: #2c3e50; /* Màu sắc thanh lịch hơn */
                margin-bottom: 35px;
                font-weight: 700; /* In đậm hơn */
                font-size: 2.5em; /* Kích thước lớn hơn */
                position: relative;
                padding-bottom: 15px;
            }
            h1::after { /* Đường kẻ dưới tiêu đề */
                content: '';
                position: absolute;
                left: 50%;
                bottom: 0;
                transform: translateX(-50%);
                width: 100px;
                height: 5px;
                background-color: #007bff; /* Màu xanh chủ đạo */
                border-radius: 3px;
            }

            /* Thông báo tổng quan về lớp */
            .class-overview-heading {
                text-align: center;
                color: #34495e;
                margin-top: 30px;
                margin-bottom: 25px;
                font-size: 1.8em;
                font-weight: 600;
            }

            /* Biểu đồ */
            .chart-container {
                max-width: 450px; /* Đặt chiều rộng tối đa */
                height: 350px; /* Điều chỉnh chiều cao để cân đối hơn */
                margin: 40px auto; /* Căn giữa và tạo khoảng cách */
                display: block; /* Để margin auto hoạt động */
                padding: 20px; /* Padding cho biểu đồ */
                background-color: #fcfcfc; /* Nền nhẹ cho biểu đồ */
                border-radius: 10px;
                box-shadow: 0 4px 15px rgba(0,0,0,0.05);
            }
            .chart-container canvas {
                width: 100% !important; /* Đảm bảo canvas lấp đầy container */
                height: 100% !important; /* Đảm bảo canvas lấp đầy container */
            }

            /* Bảng */
            table {
                width: 100%;
                border-collapse: separate; /* Dùng separate để có border-radius */
                border-spacing: 0; /* Loại bỏ khoảng cách giữa các ô */
                margin-top: 40px;
                border-radius: 12px; /* Bo tròn góc bảng */
                overflow: hidden; /* Quan trọng để border-radius hoạt động */
                box-shadow: 0 4px 20px rgba(0,0,0,0.08); /* Bóng đổ cho bảng */
            }
            th, td {
                border: 1px solid #e0e0e0; /* Màu border nhạt hơn */
                padding: 14px 18px; /* Tăng padding */
                text-align: left; /* Căn trái cho nội dung bảng */
            }
            th {
                background-color: #007bff; /* Màu xanh chính */
                color: #fff;
                font-weight: 600;
                text-transform: uppercase; /* Chữ hoa */
                letter-spacing: 0.8px;
                font-size: 0.95em;
            }
            tr:nth-child(even){
                background-color: #f8f9fa; /* Màu xen kẽ nhẹ nhàng */
            }
            tbody tr:hover {
                background-color: #e2f0fb; /* Màu khi hover */
                transition: background-color 0.3s ease-in-out;
            }
            td {
                font-size: 0.9em;
                color: #444;
            }

            /* Định dạng cột tỷ lệ chuyên cần và các cột căn giữa */
            td:last-child, /* Tỷ lệ chuyên cần */
            td:nth-child(1), /* STT */
            td:nth-child(2) { /* Mã học sinh */
                text-align: center;
            }
            /* Class cho màu sắc tỷ lệ chuyên cần */
            .attendance-percentage.good {
                font-weight: 600;
                color: #28a745; /* Xanh lá cây cho tỷ lệ tốt */
            }
            .attendance-percentage.poor {
                font-weight: 600;
                color: #dc3545; /* Đỏ cho tỷ lệ kém */
            }

            /* Nút quay lại */
            .back-button {
                display: block; /* Đặt thành block để căn giữa */
                width: fit-content; /* Chiều rộng vừa với nội dung */
                margin: 40px auto 0; /* Căn giữa và tạo khoảng cách */
                background: #6c757d;
                color: white;
                padding: 12px 30px; /* Tăng padding */
                border-radius: 8px; /* Bo tròn nhiều hơn */
                text-decoration: none;
                font-weight: 500;
                font-size: 1.05em;
                transition: background-color 0.3s ease, transform 0.2s ease;
                box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            }
            .back-button:hover {
                background: #5a6268;
                transform: translateY(-3px); /* Hiệu ứng nhấc nhẹ */
                box-shadow: 0 6px 15px rgba(0,0,0,0.15);
            }

            /* Thông báo lỗi (nếu có) */
            .error-message {
                color: #dc3545;
                background-color: #f8d7da;
                border: 1px solid #f5c6cb;
                padding: 15px;
                border-radius: 8px;
                margin-bottom: 25px;
                text-align: center;
                font-size: 1.1em;
                font-weight: 500;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Báo Cáo Điểm Danh</h1>

            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
            </c:if>

            <h2 class="class-overview-heading">Lớp: ${className}</h2>

            <div class="chart-container">
                <canvas id="classChart"></canvas>
            </div>

            <script>
                const ctx = document.getElementById("classChart").getContext("2d");
                const classChart = new Chart(ctx, {
                    type: "doughnut", // Biểu đồ doughnut trông hiện đại hơn pie
                    data: {
                        labels: ["Có mặt", "Vắng mặt"],
                        datasets: [{
                                data: [
                ${empty avgPresentForChart ? 0 : avgPresentForChart},
                ${empty avgAbsentForChart ? 0 : avgAbsentForChart}
                                ],
                                backgroundColor: ["#28a745", "#dc3545"], /* Xanh lá cho Có mặt, Đỏ cho Vắng mặt */
                                borderColor: '#ffffff', /* Viền trắng giữa các lát cắt */
                                borderWidth: 2,
                                hoverOffset: 8 /* Hiệu ứng phồng khi di chuột */
                            }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        animation: {
                            duration: 0 // Tắt animation khi load để tránh giật giật
                        },
                        plugins: {
                            legend: {
                                position: 'bottom', /* Đặt chú thích ở dưới */
                                labels: {
                                    font: {
                                        size: 14,
                                        family: 'Open Sans'
                                    },
                                    color: '#555'
                                }
                            },
                            title: {
                                display: true,
                                text: 'Tỷ lệ chuyên cần trung bình cả lớp',
                                font: {
                                    size: 18,
                                    weight: 'bold',
                                    family: 'Roboto'
                                },
                                color: '#34495e',
                                padding: {
                                    top: 10,
                                    bottom: 20
                                }
                            },
                            tooltip: {
                                backgroundColor: 'rgba(0,0,0,0.7)',
                                bodyFont: {
                                    size: 14,
                                    family: 'Open Sans'
                                },
                                titleFont: {
                                    size: 14,
                                    family: 'Roboto',
                                    weight: 'bold'
                                },
                                callbacks: {
                                    label: function (context) {
                                        let label = context.label || '';
                                        if (label) {
                                            label += ': ';
                                        }
                                        const value = context.parsed;
                                        const percentage = value.toFixed(2);
                                        return label + percentage + '%';
                                    }
                                }
                            }
                        }
                    }
                });
            </script>

            <table>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Mã học sinh</th>
                        <th>Tên học sinh</th>
                        <th>Tỷ lệ chuyên cần</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="kid" items="${kids}" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${kid.kinder_id}</td>
                            <td>${kid.first_name} ${kid.last_name}</td>
                            <td>
                                <%-- Lấy giá trị tỷ lệ chuyên cần của học sinh hiện tại --%>
                                <c:set var="attendancePercent" value="${attendancePercentages[kid.kinder_id]}" />

                                <c:choose>
                                    <c:when test="${not empty attendancePercent and attendancePercent < 50.0}">
                                        <span class="attendance-percentage poor">
                                            <fmt:formatNumber value="${attendancePercent}" pattern="0.00" />%
                                        </span>
                                    </c:when>
                                    <c:when test="${not empty attendancePercent}">
                                        <span class="attendance-percentage good">
                                            <fmt:formatNumber value="${attendancePercent}" pattern="0.00" />%
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        N/A
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="${pageContext.request.contextPath}/attendance" class="back-button">Quay lại điểm danh</a>
        </div>
    </body>
</html>