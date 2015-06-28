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
	<title>Editor Clientes</title>
	<%-- Setting the base url based on context --%>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
    
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/contact.css">
</head>
<body>
    <%-- Redirecting not allowed users --%>
    <c:if test="${currentUser.administrator != true }">
        <c:redirect url="permissionDenied.jsp" />
    </c:if>

	
    <%-- including the headers --%>
    <%@include file="header.jsp" %>
    
     <div class="main">
        <section class="registerSection">
			<h1>Edit Client</h1>
			<jsp:useBean id="get" class="hotel.User" scope="session" />

			<form action="/Projeto/hotel/RegisterController" method="POST">
				Nome: <input type="text" id="name" name="name" value="${get.name}"><br/>
				CPF: <input type="text" id="cpf" name="cpf" value="${get.cpf}"><br/>
                Date of Birth: <input type="text" id="dateOfBirth" name="dateOfBirth" value="${get.dateOfBirth}"><br/>
                Gender: <input type="text" id="gender" name="gender" value="${get.gender}"><br/>
                Marital Status: <input type="text" id="maritalStatus" name="maritalStatus" value="${get.maritalStatus}"><br/>
                City: <input type="text" id="city" name="city" value="${get.city}"><br/>
                State: <input type="text" id="state" name="state" value="${get.state}"><br/>
                Postal Code: <input type="text" id="postalCode" name="postalCode" value="${get.postalCode}"><br/>
				Email: <input type="text" id="email" name="email" value="${get.email}"><br/>
				Password: <input type="password" id="password" name="password" value="${get.password}"><br/>
				<input type="hidden" name="editId" value="${get.id}" />
				<input type="hidden" name="action" value="edit" />

				<input type="submit" class="submit_button" value="Save Changes">
                <input type="button" class="reset_button" value="Discard" onClick="location.href = '/Projeto/clientList.jsp'" />
			</form>
			<div class="clear"></div>
		</section>
	</div>
</body>
</html>
