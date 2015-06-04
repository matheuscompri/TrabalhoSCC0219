<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Editor Clientes</title>
</head>
<body>

	<p>Editar Cliente</p>
	<jsp:useBean id="get" class="hotel.User" scope="session" />

	<form action="/Projeto/hotel/RegisterController" method="POST">
		Nome: <input type="text" id="name" name="name" value="${get.name}"><br/>
		CPF: <input type="text" id="cpf" name="cpf" value="${get.cpf}"><br/>
		Date of Birth: <input type="text" id="dateOfBirth" name="dateOfBirth" value="${get.dateOfBirth}"><br/>
		Gender: <input type="text" id="gender" name="gender" value="${get.gender}"><br/>
		Email: <input type="text" id="email" name="email" value="${get.email}"><br/>
		Password: <input type="password" id="password" name="password"><br/>
		<input type="hidden" name="editId" value="${get.id}" />
		<input type="hidden" name="action" value="edit" />

		<input type="submit" value="Send">
	</form>


</body>
</html>
