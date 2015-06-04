<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<DOCTYPE html>
<html>
	<head>
		<title>Search Results for Users</title>
	</head>

	<body>
	<%-- recuperando listaClientes da sessao --%>
	<jsp:useBean id="searchResults" class="java.util.ArrayList" scope="session"/>

	<h2>Search Results for Users</h2>

	<%-- percorrendo lista de clientes com EL (Expression Language) --%>
	<form action="/Projeto/hotel/RegisterController" method="GET">
		<table>
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
			<c:forEach var="cliente" items="${searchResults}" varStatus="status">
				<tr>
					<td><input type="checkbox" name="mdel${status.index}"</td>
					<td>${status.index}</td>
					<td>${cliente.name}</td>
					<td>${cliente.cpf}</td>
					<td>${cliente.dateOfBirth}</td>
					<td>${cliente.gender}</td>
					<td><a href="/Projeto/hotel/RegisterController?action=get&next=view&id=${status.index}">Details</a></td>
					<td><a href="/Projeto/hotel/RegisterController?action=get&next=edit&id=${status.index}">Editar</a></td>
					<td><a href="/Projeto/hotel/RegisterController?action=del&id=${status.index}">Remover</a></td>
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" name="action" value="mdel">
		<input type="submit" value="Delete Selected">
	</form>
	<a href="/Projeto/clientList.jsp">Back</a>

	</body>
</html>


