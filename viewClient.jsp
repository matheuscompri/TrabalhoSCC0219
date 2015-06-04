<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>View Clientes</title>
</head>
<body>

	<p>View Client</p>
	<jsp:useBean id="get" class="hotel.User" scope="session" />

	Nome: ${get.name}<br/>
	CPF: ${get.dateOfBirth}<br/>
	Gender: ${get.gender}<br/>
	Email: ${get.email}<br/>
	
	<a href="/Projeto/clientList.jsp">back</a>
</body>
</html>
