<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<DOCTYPE html>
<html>
	<head>
		<title>Message List</title>
	</head>

	<body>
	<jsp:useBean id="messageList" class="java.util.ArrayList" scope="session"/>

	<h2>Message List</h2>

	<form action="/Projeto/hotel/MessageController" method="GET">
		<table border=2>
			<tr>
				<td><b>Select</b></td>
				<td><b>ID</b></td>
				<td><b>Read</b></td>
				<td><b>Name</b></td>
				<td><b>Email</b></td>
				<td><b>Phone date</b></td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach var="message" items="${messageList}" varStatus="status">
				<tr>
					<td><input type="checkbox" name="mdel${status.index}" /></td>
					<td>${status.index}</td>
					<td>
						<c:choose>
							<c:when test="${message.read}">
								<input type="checkbox" disabled checked />
							</c:when>
							<c:otherwise>
								<input type="checkbox" disabled />
							</c:otherwise>
						</c:choose>
					</td>
					<td>${message.name}</td>
					<td>${message.email}</td>
					<td>${message.phone}</td>
					<td><a href="/Projeto/hotel/MessageController?action=get&id=${status.index}">Details</a></td>
					<td><a href="/Projeto/hotel/MessageController?action=del&id=${status.index}">Remove</a></td>
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" name="action" value="mdel">
		<input type="submit" value="Delete Selected">
	</form>
	<a href="/Projeto/index.html">New Message</a>

	</body>
</html>


