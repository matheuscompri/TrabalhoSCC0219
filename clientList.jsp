<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Client List</title>
	</head>

	<body>
	<%-- recuperando listaClientes da sessao --%>
	<jsp:useBean id="clientList" class="java.util.ArrayList" scope="session"/>

	<h2>Client List</h2>

	Search for an user by name:
	<form action="/Projeto/hotel/RegisterController" method="GET">
		<input type="text" name="name">
		<input type="submit" value="Search">
		<input type="hidden" name="action" value="search">
		<input type="hidden" name="method" value="name">
	</form>

	Search for an user by date:
	<form action="/Projeto/hotel/RegisterController" method="GET">
		<label>After </label>
		<input type="text" name="after"><br />
		<label>Before </label>
		<input type="text" name="before"><br />
		<input type="submit" value="Search">
		<input type="hidden" name="action" value="search">
		<input type="hidden" name="method" value="date">
	</form>

	<%-- percorrendo lista de clientes com EL (Expression Language) --%>
	<form action="/Projeto/hotel/RegisterController" method="GET">
		<table border=2>
			<tr>
				<td><b>Selected</b></td>
				<td><b>ID</b></td>
				<td><b>Name</b></td>
				<td><b>CPF</b></td>
				<td><b>Birth date</b></td>
				<td><b>Gender</b></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach var="cliente" items="${clientList}" varStatus="status">
				<tr>
					<td><input type="checkbox" name="mdel${status.index}"</td>
					<td>${status.index}</td>
					<td>${cliente.name}</td>
					<td>${cliente.cpf}</td>
					<td>${cliente.dateOfBirth}</td>
					<td>${cliente.gender}</td>
					<td><a href="/Projeto/hotel/RegisterController?action=get&next=view&id=${status.index}">Details</a></td>
					<td><a href="/Projeto/hotel/RegisterController?action=get&next=edit&id=${status.index}">Edit</a></td>
					<td><a href="/Projeto/hotel/RegisterController?action=del&id=${status.index}">Remove</a></td>
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" name="action" value="mdel">
		<input type="submit" value="Delete Selected">
	</form>
	<a href="/Projeto/index.html">Novo Cliente</a>

	</body>
</html>


