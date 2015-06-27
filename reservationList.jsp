<%@page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- Fixing the context for dispatcher calls --%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url" >${req.requestURL}</c:set>

<!DOCTYPE html>
<html>

	<head>
		<title>Reservation List</title>
	</head>
    <%-- Setting the base url based on context --%>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
    <script type="text/javascript" src="js/validation.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
</head>

<body id="imh-2" class="full">
    <%-- Redirecting not allowed users --%>
    <c:if test="${currentUser.name == null }">
        <c:redirect url="permissionDenied.jsp" />
    </c:if>

    <header>
        <div class="navbarLogo">Lithium Hotel</div>
        <nav class="navbar">
            <ul>
                <%-- Changing the nav bar according to the user --%>
                <c:choose>

                    <%-- Guest user (not logged in) --%>
                    <c:when test="${currentUser.name == null}">
                        <li><spam class="username">Guest</spam></li>
                        <li><a href="index.jsp">Hotel</a></li>                        
                        <li><a href="register.jsp">Register</a></li>
                        <li><a href="login.jsp">Login</a></li>
                        <li><a href="contact.jsp">Contact</a></li>
                    </c:when>
                    
                    <%-- Authenticated user--%>
                    <c:otherwise>
                        <li><spam class="username">${currentUser.name}</spam></li>
                        <li><a href="/Projeto/hotel/LoginController?action=logout">Logout</a></li>
                        <li><a href="reservationList.jsp">Reservations</a></li>
                        
                        <c:choose>
                            <%-- Admin --%>
                            <c:when test="${currentUser.administrator}">
                                <li><a href="clientList.jsp">Accounts</a></li>
                                <li><a href="messageList.jsp">Messages</a></li>
                            </c:when>

                            <%-- Client --%>
                            <c:otherwise>
                                <li><a href="index.jsp">Account</a></li>
                                <li><a href="contact.jsp">Contact</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </header>

    <div class="main">
    <section class="registerSection">
	<%-- recuperando listaClientes da sessao --%>
	<jsp:useBean id="clientList" class="java.util.ArrayList" scope="session"/>
	<jsp:useBean id="reservationList" class="java.util.ArrayList" scope="session"/>
	
	<h1>Reservation List</h1>

	Reservations for all clients:
	
	<%-- percorrendo lista de clientes com EL (Expression Language) --%>
	<form action="/Projeto/hotel/ReservationController" method="GET">
		<table border=2>
			<tr>
				<td><b>Selected</b></td>
				<td><b>Client Name</b></td>
				<td><b>Arrival</b></td>
				<td><b>Departure</b></td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach var="reservation" items="${reservationList}" varStatus="status">
				<c:if test = "${currentUser.name == reservation.client.name || currentUser.administrator == true }">
					<tr>
						<td><input type="checkbox" name="mdel${status.index}"</td>
						<td>${reservation.client.name}</td>
						<td>${reservation.arrival.time}</td>
						<td>${reservation.departure.time}</td>
						<td><a href="/Projeto/hotel/ReservationController?action=get&id=${status.index}">Details</a></td>
						<td><a href="/Projeto/hotel/ReservationController?action=del&id=${status.index}">Remove</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<input type="hidden" name="action" value="mdel">
		<input type="submit" class="submit_button" value="Delete Selected">
		<input type="button" class="reset_button" value="Add Reservation" onClick="location.href = '/Projeto/reservation.jsp'" >
	</form>
	<div class="clear"></div>
    </section>
    </div>
	</body>
</html>


