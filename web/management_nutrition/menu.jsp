<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Menu Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="container mt-5 mb-5 p-4 shadow bg-white rounded">
            <h2 class="text-center mb-4">🍽️ Kindergarten Menu Management</h2>

            <!-- 🔍 Filter menu by class and date -->
            <form action="day_class" method="post">
                <div class="col-md-4">
                    <label class="form-label">Select class</label>
                    <select class="form-select" name="classId">
                        <c:forEach var="menu" items="${classList}">
                            <option value="${menu.class_id}" 
                                    <c:if test="${menu.class_id == selectedClassId}">selected</c:if>>
                                ${menu.class_name}
                            </option>
                        </c:forEach>

                    </select>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Select date</label>
                    <input type="date" class="form-control" name="menudate"
                           value="${selectedDate != null ? selectedDate : ''}">
                </div>
                <div class="col-md-4 d-flex align-items-end">
                    <button type="submit" class="btn btn-primary w-100">Filter Menu</button>
                </div>
            </form>

            <!-- ➕ Add new dish -->
            <h5 class="mb-3">➕ Add New Dish</h5>
            <c:if test="${not empty selectedClassId && not empty selectedDate}">
            <form class="row g-3 mb-4" action="insert_menu" method="post">

                <input type="hidden" name="classId" value="${selectedClassId}" />
                <input type="hidden" name="menudate" value="${selectedDate}" />

                <div class="col-md-2">
                    <select class="form-select" name="mealType">
                        <option value="Breakfast">Breakfast</option>
                        <option value="Lunch">Lunch</option>
                        <option value="Dinner">Dinner</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" placeholder="Dish name" name="dish" required="">
                </div>
                <div class="col-md-2">
                    <input type="number" class="form-control" placeholder="Calories" name="calories" step="any"required="">
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" placeholder="Notes" name="notes">
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-success w-100">Add</button>
                </div>
                <p style="color: red">${error}</p>
            </form>
            </c:if>
            <!-- 📋 Menu list -->
            <h5 class="mb-3">📅 Menu for <strong>${selectedDate}</strong> - Class: <strong>${selectedClass.class_name}</strong></h5>
            <table class="table table-bordered">
                <thead class="table-secondary">
                    <tr>
                        <th>Meal</th>
                        <th>Dish</th>
                        <th>Calories</th>
                        <th>Notes</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="menu" items="${menuListFiltered}">
                        <tr>
                            <td>${menu.menutype}</td>
                            <td>${menu.dish}</td>
                            <td>${menu.calories}</td>
                            <td>${menu.notes}</td>
                            <td>
                                <!-- Form Delete -->
                                <form action="deleteMenu" method="post">
                                    <input type="hidden" name="menuId" value="${menu.menu_id}">
                                    <input type="hidden" name="classId" value="${selectedClassId}">
                                    <input type="hidden" name="menudate" value="${selectedDate}">
                                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                </form>


                                <!-- Form Edit (redirect) -->
                                <form action="editmenu" method="get" style="display:inline;">
                                    <input type="hidden" name="menuId" value="${menu.menu_id}" />
                                    <!--<button type="submit" class="btn btn-warning btn-sm">Edit</button>-->
                                </form>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
