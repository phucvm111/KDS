<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminSidebar.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarTeacher.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/sidebarParent.css">
        <!----======== CSS ======== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <link rel="stylesheet" href="admin/schedule/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <link rel="icon" href="./assets/image/logo2-removebg-preview.png">
        <title>ATKD ChildCare</title>

        <!--script-->
        <script src="https://kit.fontawesome.com/28a1dc3e10.js" crossorigin="anonymous"></script>
        <!--<title>Admin Dashboard Panel</title>-->
    </head>

    <body>
        <c:choose>
            <c:when test="${sessionScope.userid == 1}">
                <jsp:include page="/view/adminSidebar.jsp" />
            </c:when>
            <c:when test="${sessionScope.userid == 2}">
                <jsp:include page="/view/sidebarTeacher.jsp" />
            </c:when>
            <c:when test="${sessionScope.userid == 3}">
                <jsp:include page="/view/sidebarParent.jsp" />
            </c:when>
            <c:otherwise>
                <jsp:include page="/view/defaultSidebar.jsp" />
            </c:otherwise>
        </c:choose>

 
        <div class="dashboard">
            <div class="dash-lefttop">
                <img src="https://i.pinimg.com/originals/72/45/fb/7245fb0ca786bb4a98fb8465e437c5bb.jpg" alt="">
                <a href="#">${sessionScope.account.firstName} </a>
            </div>
            <div class="test">
                Hello
                ${sessionScope.cid}
                =
                firstMonday ${requestScope.firstMonday}
                = 
                recentMonday ${requestScope.recentMonday}
            </div>
            <form id="f" action="listschedule" method="post">
                <div class="select-menu">
                    <!--class-->
                    <div class="class-select">
                        <c:set value="${requestScope.cid_raw}" var="cid_raw"/>

                        <select class="form-select form-select-sm" aria-label=".form-select-sm example" name="cid"
                                style="height: 36px;">
                            <!--<option selected>Select Class</option>-->
                            <c:forEach items="${requestScope.classes}" var="cl">
                                <option <c:if test="${cl.class_id eq cid_raw}">selected</c:if> 
                                                                               value="${cl.class_id}">${cl.class_name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <c:set value="${requestScope.firstMonday}" var="fM"/>
                    <c:set value="${requestScope.recentMonday}" var="rM"/>

                    <select name="datee" id="ddlViewBy">
                        <c:forEach items="${requestScope.weeks}" var="w">
                            <c:set value="${w.key}" var="wk"></c:set>
                            <option <c:if test="${wk eq fM || wk eq rM}">selected</c:if>
                                                                         value="${wk}">${w.value}</option>
                        </c:forEach>
                    </select>

                    <button class="btn btn-outline-success" type="submit"
                            onclick="changeDate()">Search</button>
            </form>
        </div>

        <div class="dash-bottomtable">
            <table class="table" style="margin-bottom: 0">
                <thead style="bgcolor: red">
                    <c:set var="sde" value="${requestScope.scheduleDetails}">
                    </c:set>

                    <tr>
                        <th></th>
                            <c:forEach items="${sde.scheduleMap}" var="map">
                            <th scope="col">
                                <fmt:formatDate pattern = "EEE" value = "${map.key}" />
                            </th>
                        </c:forEach>
                    </tr> 

                    <tr>
                        <th></th>
                            <c:forEach items="${sde.scheduleMap}" var="map">
                            <th scope="col">
                                <fmt:formatDate pattern = "dd/MM" value = "${map.key}" />
                            </th>
                        </c:forEach>
                    </tr> 
                </thead>

                <c:forEach begin="0" end="6" varStatus="loop">
                    <tr>
                        <td style="text-align: center; vertical-align: middle; font-weight: bold">
                            Slot ${loop.index + 1}<br/>
                            <c:out value="${slots[loop.index].start_hour}" /> - 
                            <c:out value="${slots[loop.index].end_hour}" />
                        </td>
                        <c:forEach items="${sde.scheduleMap}" var="map">  

                            <!--if slot not null-->
                            <c:if test="${map.value[loop.index] != null}">
                                <td style="min-height: 1px">
                                    <div style="display: flex">
                                        <fmt:formatDate pattern = 'yyyy-MM-dd' value = "${map.key}" var="date_picked"/>
                                        <c:set var="date_picked_converted" value="${date_picked}"/>
                                        <c:set var="schedule_update_index" value="${date_picked_converted}:${loop.index + 1}"/>
                                        <c:set var="schedule_update_old" value="${date_picked_converted}:${loop.index + 1}:old"/>

                                        <div class="schedule-list" style="width: 80px;" id="${schedule_update_old}">
                                            ${map.value[loop.index].activity.act_name}
                                            <!--${map.value[loop.index].schedule_id}-->
                                        </div>
                                        <c:if test="${sessionScope.userid == 1}">
                                            <div id="${schedule_update_index}" style="display: none">

                                                <form action="updateschedule" method="post">
                                                    <!--<h1>${map.value[loop.index].schedule_id}</h1>-->
                                                    <input name="schedule_id" value="${map.value[loop.index].schedule_id}" type="hidden"/>
                                                    <input name="schedule-update-index" value="${schedule_update_index}" type="hidden"/>
                                                    <input name="date_picked_converted" value="${date_picked_converted}" type="hidden"/>
                                                    <input name="cid_raw" value="${cid_raw}" type="hidden"/>
                                                    <!--<input name="cid_raw" value="${cid_raw}" type="hidden"/>-->
                                                    <input name="slot_chosen" value="${loop.index + 1}" type="hidden"/>

                                                    <select name="select_activity" style="max-width: 100%; margin-bottom: 4px;">
                                                        <c:forEach items="${requestScope.activity}" var="act">
                                                            <option value="${act.activity_id}"
                                                                    <c:if test="${map.value[loop.index].activity.activity_id eq act.activity_id}">selected</c:if>>
                                                                ${act.act_name}</option>
                                                            </c:forEach>
                                                    </select>


                                                    <!--<div>-->

                                                    <a class="update_confirm" >
                                                        <button type="submit">
                                                            Update <i class="fa-solid fa-pen"></i> 
                                                        </button>
                                                    </a>



                                                    <a class="update_cancel" 
                                                       onclick="cancelUpdate('${schedule_update_index}', '${schedule_update_old}')">
                                                        Cancel <i class="fa-solid fa-ban"></i> 
                                                    </a>

                                                    <!--</div>-->
                                                </form>

                                            </div>

                                            <div class="dropdown" style="flex-grow: 1;">
                                                <button 
                                                    class="btn-sm btn-option btn-secondary dropdown-toggle" type="button" 
                                                    id="dropdownMenuButton" 
                                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                </button>

                                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                    <a class="dropdown-item" href="#" 
                                                       onclick="doDelete('${map.value[loop.index].schedule_id}')">
                                                        <i class="fa-delete fa-solid fa-trash fa-fw fa-align-center"></i> Delete</a>
                                                    <a class="dropdown-item" href="#" 
                                                       onclick="doUpdate('${schedule_update_index}', '${schedule_update_old}')">
                                                        <i class="fa fa-edit"></i> Update</a>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </c:if>
                            </c:if>  

                            <!--if slot null-->

                            <c:if test="${map.value[loop.index] == null}">
                                <td style="text-align: center;vertical-align: middle;" class="empty-cell"> 
                                    <form action="addschedule" method="post">
                                        <fmt:formatDate pattern = 'yyyy-MM-dd' value = "${map.key}" var="date_picked"/>
                                        <c:set var="date_picked_converted" value="${date_picked}"/>
                                        <input name="date_picked_converted" value="${date_picked_converted}" type="hidden"/>
                                        <input name="cid_raw" value="${cid_raw}" type="hidden"/>
                                        <input name="slot_chosen" value="${loop.index + 1}" type="hidden"/>
                                        <c:if test="${sessionScope.userid == 1}">
                                            <div class="add-button">
                                                <select name="select_activity" style="max-width: 100px">
                                                    <c:forEach items="${requestScope.activity}" var="act">
                                                        <option value="${act.activity_id}">${act.act_name}</option>
                                                    </c:forEach>
                                                </select>

                                                <button  type="submit">
                                                    <i class="fa-solid fa-2x fa-circle-plus fa-fw" style="color: #12ef73"></i>
                                                </button>
                                            </div>
                                        </c:if>
                                    </form>
                                </td>
                            </c:if> 
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>



    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="admin/schedule/js/schedule.js"></script> 
</body>

</html>