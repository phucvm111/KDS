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
        <title>Admin Dashboard</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f6f9;
                margin: 0;
                padding: 20px;
            }
            .dashboard {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
            }
            .card {
                flex: 1 1 200px;
                padding: 20px;
                border-radius: 12px;
                color: black;
                font-weight: bold;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                box-shadow: 0 2px 6px rgba(0,0,0,0.1);
                height: 150px;
            }
            .icon {
                font-size: 36px;
                margin-bottom: 10px;
            }
            .card1 {
                background: linear-gradient(to right, #ff416c, #ff4b2b);
            }
            .card2 {
                background: linear-gradient(to right, #11998e, #38ef7d);
            }
            .card3 {
                background: linear-gradient(to right, #36d1dc, #5b86e5);
            }
            .card4 {
                background: linear-gradient(to right, #fc4a1a, #f7b733);
            }
            .card5 {
                background: linear-gradient(to right, #9cecfb, #65c7f7, #0052d4);
            }
            .count {
                font-size: 32px;
            }
            .label {
                margin-top: 10px;
                color: black;
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
                        <a href="listaccount">Admin Page</a>
                    </span>
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
                        <li>
                            <a href="day_class">
                                <i class="uil uil-utensils-alt"></i>
                                <span class="link-name">Nutrition</span>
                            </a>
                        </li>

                    </ul>
                    <ul class="logout-mode">
                        <li><a href="login"><i class="uil uil-signout"></i>Logout</a></li>
                    </ul>
                </div>
            </nav>
            <h2>Dashboard</h2>
            <div class="dashboard">
                <div class="card ">
                    <div class="count">${accountNum}</div>
                    <div class="label">Account</div>
                    <a href="listaccount"> View all <i class="uil uil-arrow-right"></i> </a>
                </div>
                <div class="card ">
                    <div class="count">${parentNum}</div>
                    <div class="label">Parent</div>
                    <a href="listaccount"> View all<i class="uil uil-arrow-right"></i> </a>
                </div>
                <div class="card ">
                    <div class="count">${teacherNum}</div>
                    <div class="label">Teacher</div>
                    <a href="listaccount"> View all <i class="uil uil-arrow-right"></i></a>
                </div>
                <div class="card ">
                    <div class="count">${classNum}</div>
                    <div class="label">Class</div>
                    <a href="listaccount"> View all <i class="uil uil-arrow-right"></i></a>
                </div>
                <div class="card ">
                    <div class="count">${kindergartnerNum}</div>
                    <div class="label">Kindergartner</div>
                    <a href="listkinder"> View all <i class="uil uil-arrow-right"></i></a>
                </div>
                <div class="card ">
                    <div class="count">${eventNum}</div>
                    <div class="label">Event</div>
                    <a href="event"> View all <i class="uil uil-arrow-right"></i></a>
                </div>
                <h2>Chart of number of accounts</h2>
                <div class="container">
                    <canvas id="myChart"></canvas>
                </div>
                <h2>List of Classes</h2>
                <c:choose>
                    <c:when test="${not empty classList}">
                        <div class="dashboard">
                            <c:forEach var="c" items="${classList}">
                                <div class="card ">
                                    <div class="count">${c.class_name}</div>
                                    <div class="label">Grade: ${c.grade}</div>
                                    <div class="label">Description: ${c.class_description}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>No classes found.</p>
                    </c:otherwise>
                </c:choose>

                <h2>List of Events</h2>
                <c:choose>
                    <c:when test="${not empty event}">
                        <div class="dashboard">
                            <c:forEach var="e" items="${event}">
                                <div class="card ">
                                    <div class="count">${e.eventName}</div>
                                    <div class="label">Date: ${e.eventDate}</div>
                                    <div class="label">Description: ${e.eventDescription}</div>
                                    <div class="label">Location: ${e.location}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>No events found.</p>
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
                    labels: ['Teacher', 'Parent'],
                    datasets: [{
                            label: 'Population',
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
                        text: 'Chart of number of accounts',
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
                    }
                }
            });
        </script>
    </body>
</html>

