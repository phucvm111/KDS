<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Menu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminSidebar.css">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-3 col-lg-2 p-0">
            <jsp:include page="/view/adminSidebar.jsp" />
        </div>

        <!-- Main content -->
        <div class="col-md-9 col-lg-10 mt-4">
            <div class="container bg-white shadow rounded p-4 mb-5">
                <h2 class="mb-4">📝 Edit Menu Item</h2>

                <form action="update" method="post" class="row g-3">
                    <!-- Hidden inputs giữ thông tin cần thiết -->
                    <input type="hidden" name="menuId" value="${menu.menu_id}" />
                    <input type="hidden" name="menudate" value="${menu.menudate}" />
                    <input type="hidden" name="classId" value="${menu.calssid.class_id}" />

                    <!-- Meal Type -->
                    <div class="col-md-3">
                        <label class="form-label">Meal Type</label>
                        <select name="mealType" class="form-select" required>
                            <option value="Breakfast" ${menu.menutype == 'Breakfast' ? 'selected' : ''}>Breakfast</option>
                            <option value="Lunch" ${menu.menutype == 'Lunch' ? 'selected' : ''}>Lunch</option>
                            <option value="Dinner" ${menu.menutype == 'Dinner' ? 'selected' : ''}>Dinner</option>
                        </select>
                    </div>

                    <!-- Dish -->
                    <div class="col-md-3">
                        <label class="form-label">Dish Name</label>
                        <input type="text" name="dish" class="form-control" value="${menu.dish}" required />
                    </div>

                    <!-- Calories -->
                    <div class="col-md-2">
                        <label class="form-label">Calories</label>
                        <input type="number" name="calories" class="form-control" value="${menu.calories}" step="any" required />
                    </div>

                    <!-- Notes -->
                    <div class="col-md-4">
                        <label class="form-label">Notes</label>
                        <input type="text" name="notes" class="form-control" value="${menu.notes}" />
                    </div>

                    <!-- Buttons -->
                    <div class="col-md-12 text-end">
                        <button type="submit" class="btn btn-primary">Update</button>
                        <a href="day_class?classId=${menu.calssid.class_id}&menudate=${menu.menudate}" class="btn btn-secondary">Cancel</a>
                    </div>

                    <!-- Error display -->
                    <c:if test="${not empty error}">
                        <div class="col-md-12">
                            <p style="color: red">${error}</p>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
