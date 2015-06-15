<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Reservation List</title>
	</head>

	<body>
	<%-- recuperando listaClientes da sessao --%>
	<jsp:useBean id="clientList" class="java.util.ArrayList" scope="session"/>
	<jsp:useBean id="reservationList" class="java.util.ArrayList" scope="session"/>
	
	<h2>Reservation List</h2>

	

	Reservations for all clients:
	
	<%-- percorrendo lista de clientes com EL (Expression Language) --%>
	<form action="/Projeto/hotel/RegisterController" method="GET">
		<table border=2>
			<tr>
				<td><b>Selected</b></td>
				<td><b>Index</b></td>
				<td><b>Client</b></td>
				<td><b>Arrival Date</b></td>
				<td><b>Departure</b></td>
				<td><b>Number of Adults</b></td>
				<td><b>Number of Children/b></td>
				<td><b>Number of Babies/b></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach var="reservation" items="${reservationList}" varStatus="status">
				<c:if test = "${currentUser.name == reservation.client.name || currentUser.administrator == true }">
					<tr>
						<td><input type="checkbox" name="mdel${status.index}"</td>
						<td>${status.index}</td>
						<td>${reservation.client.name}</td>
						<td>${reservation.arrival.time}</td>
						<td>${reservation.departure.time}</td>
						<td>${reservation.adults}</td>
						<td>${reservation.children}</td>
						<td>${reservation.babies}</td>
						<td><a href="/Projeto/hotel/ReservationController?action=get&next=view&id=${status.index}">Details</a></td>
						<td><a href="/Projeto/hotel/ReservationController?action=get&next=edit&id=${status.index}">Edit</a></td>
						<td><a href="/Projeto/hotel/ReservationController?action=del&id=${status.index}">Remove</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<input type="hidden" name="action" value="mdel">
		<input type="submit" value="Delete Selected">
	</form>
	<a href="/Projeto/reservation.jsp">Nova reserva</a>

	</body>
</html>


