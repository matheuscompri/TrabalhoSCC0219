<%@page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- Fixing the context for dispatcher calls --%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url" >${req.requestURL}</c:set>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <title>Reservation</title>
    <%-- Setting the base url based on context --%>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

    <script src="js/jquery-2.1.3.js"></script>
    <script src="js/validation.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/reservation.js"></script>
    <link rel="stylesheet" href="css/jquery-ui.min.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/reservation.css">
</head>

<body id="imh-1" class="full">
    <%-- Redirecting not allowed users --%>
    <c:if test="${currentUser.name == null }">
        <c:redirect url="permissionDenied.jsp" />
    </c:if>

   
    <%-- including the headers --%>
    <%@include file="header.jsp" %>
    
    <div class="main">
        <section class="registerSection">
            <h1>Reservation</h1>
            <form id="reservationForm" action="/Projeto/hotel/ReservationController" method="POST">
                <label id="arrivingLabel" for="fname">Arriving:</label>
                <input type="text" id="arriving" name="arr" />
                <label id="departingLabel" for="sur">Departing:</label>
                <input id="departing" name="dep" />
                <label for="adults">Adults:</label>
                <select id="adults" name="adults">
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                    <option value="4">Four</option>
                </select>
                <label for="babies">Children( 0y-3y ):</label>
                <select id="babies" name="babies">
                    <option value="0">None</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
                <label for="kids">Children( 4y-12y ):</label>
                <select id="children" name="kids">
                    <option value="0">None</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                    <option value="4">Four</option>
                </select>

                <input type="hidden" name="action" value="reservation" />

                <input class="submit_button" type="submit" value="Register" />
                <input class="reset_button" type="reset" value="Clear" />
                <div class="clear"></div>
            </form>
        </section>
    </div>
    <footer>
    </footer>
</body>

</html>