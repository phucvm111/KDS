<%-- 
    Document   : adminDashboard
    Created on : Jun 11, 2025, 2:40:03 PM
    Author     : Vu Tuan Hai <HE176383>
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                color: white;
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
                        <li><a href="changepassword"><i class="uil uil-lock-alt"></i><span class="link-name">Change Password</span></a></li>
                        <li><a href="event"><i class="uil uil-calendar-alt"></i><span class="link-name">Event</span></a></li>
                    </ul>
                    <ul class="logout-mode">
                        <li><a href="logout"><i class="uil uil-signout"></i>Logout</a></li>
                    </ul>
                </div>
            </nav>
            <h2>Dashboard</h2>
            <div class="dashboard">
                <div class="card card1">
                    <div class="count">${accountNum}</div>
                    <div class="label">Account</div>
                    <a href="listaccount"> View all <i class="uil uil-arrow-right"></i> </a>
                </div>
                <div class="card card2">
                    <div class="count">${parentNum}</div>
                    <div class="label">Parent</div>
                    <a href="listaccount"> View all<i class="uil uil-arrow-right"></i> </a>
                </div>
                <div class="card card2">
                    <div class="count">${teacherNum}</div>
                    <div class="label">Teacher</div>
                    <a href="listaccount"> View all <i class="uil uil-arrow-right"></i></a>
                </div>
                <div class="card card3">
                    <div class="count">${classNum}</div>
                    <div class="label">Class</div>
                    <a href="listaccount"> View all <i class="uil uil-arrow-right"></i></a>
                </div>
                <div class="card card4">
                    <div class="count">${kindergartnerNum}</div>
                    <div class="label">Kindergartner</div>
                    <a href="listkinder"> View all <i class="uil uil-arrow-right"></i></a>
                </div>
                <div class="card card5">
                    <div class="count">${eventNum}</div>
                    <div class="label">Event</div>
                    <a href="event"> View all <i class="uil uil-arrow-right"></i></a>
                </div>
            </div>
        </form>
    </body>
</html>

