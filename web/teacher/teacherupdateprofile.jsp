<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="icon" href="${pageContext.request.contextPath}/assets/image/logo2-removebg-preview.png" />
        <title>Update Teacher Profile</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css" />

        <style>
            :root {
                --primary-color: #6a5af9;
                --secondary-color: #00bcd4;
                --text-color: #333;
                --text-secondary: #6c757d;
                --background-color: #f8f9fa;
                --white-color: #fff;
                --border-color: #e0e0e0;
                --box-shadow: 0 4px 15px rgba(0, 0, 0, 0.07);
            }

            body {
                font-family: 'Poppins', sans-serif;
                margin: 0;
                background: var(--background-color);
            }

            .wrapper {
                display: flex;
                min-height: 100vh;
            }

            .left-side-menu {
                width: 250px;
                background: #fff;
                box-shadow: var(--box-shadow);
                padding: 20px;
            }

            .user-welcome {
                text-align: center;
                margin-bottom: 30px;
            }

            .user-welcome img {
                border-radius: 50%;
            }

            .user-welcome p {
                margin-top: 10px;
                font-weight: 600;
            }

            .item-lists {
                list-style: none;
                padding: 0;
            }

            .item-lists li {
                margin: 15px 0;
            }

            .item-lists li a {
                text-decoration: none;
                color: var(--text-color);
                font-weight: 500;
            }

            .item-lists li a:hover {
                color: var(--primary-color);
            }

            .log-out_button {
                padding: 10px 20px;
                background: #f44336;
                color: #fff;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                font-weight: 600;
                width: 100%;
                margin-top: 20px;
            }

            .form-container {
                flex-grow: 1;
                padding: 40px;
                max-width: 900px;
                margin: 0 auto;
                background: var(--white-color);
                border-radius: 16px;
                box-shadow: var(--box-shadow);
            }

            .form-container h2 {
                background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
                color: var(--white-color);
                padding: 20px;
                border-radius: 8px 8px 0 0;
                margin: -40px -40px 30px -40px;
            }

            form {
                display: grid;
                grid-template-columns: repeat(2, 1fr);
                gap: 25px;
            }

            .form-group {
                display: flex;
                flex-direction: column;
            }

            .form-group label {
                font-size: 0.9em;
                font-weight: 600;
                color: var(--text-secondary);
                margin-bottom: 8px;
            }

            .form-group input,
            .form-group select {
                padding: 12px;
                border: 1px solid var(--border-color);
                border-radius: 8px;
                font-size: 1em;
                background: var(--background-color);
            }

            .submit-btn {
                grid-column: 1 / -1;
                padding: 12px 30px;
                font-size: 1em;
                font-weight: 600;
                color: var(--white-color);
                background-color: var(--primary-color);
                border: none;
                border-radius: 8px;
                cursor: pointer;
                transition: background-color 0.3s, transform 0.2s;
                justify-self: center;
                width: fit-content;
            }

            .submit-btn:hover {
                background-color: #5548d9;
                transform: translateY(-2px);
            }

            @media (max-width: 768px) {
                form {
                    grid-template-columns: 1fr;
                }
            }
        </style>
    </head>

    <body>
        <c:set var="teacher" value="${requestScope.account}" />

        <div class="wrapper">
            <jsp:include page="/view/sidebarTeacher.jsp" />

            <div class="form-container">
                <h2>Update Profile</h2>
                <form method="post" action="updateTeacher">
                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" name="firstName" value="${teacher.firstName}" required>
                    </div>

                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" name="lastName" value="${teacher.lastName}" required>
                    </div>

                    <div class="form-group">
                        <label>Date of Birth</label>
                        <input type="date" name="dob" value="${teacher.dob}" required>
                    </div>

                    <div class="form-group">
                        <label>Gender</label>
                        <select name="gender" required>
                            <option value="true" ${teacher.gender == true ? 'selected' : ''}>Male</option>
                            <option value="false" ${teacher.gender == false ? 'selected' : ''}>Female</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" name="phone" value="${teacher.phoneNumber}" required>
                    </div>

                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" name="address" value="${teacher.address}" required>
                    </div>

                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="email" value="${teacher.email}" required>
                    </div>

                    <button type="submit" class="submit-btn">Save Changes</button>
                </form>
            </div>
        </div>
    </body>
</html>
