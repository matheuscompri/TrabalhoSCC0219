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
	<title>View Clientes</title>
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

			<h1>User details</h1>
			<jsp:useBean id="get" class="hotel.User" scope="session" />

			Name: ${get.name}<br/>
			CPF: ${get.cpf}<br/>
			Birthdate: ${get.dateOfBirth}<br/>
			Gender: ${get.gender}<br/>
			Marital Status: ${get.maritalStatus}<br/>
			City: ${get.city}<br/>
			State: ${get.state}<br/>
			Postal Code: ${get.postalCode}<br/>
            Email: ${get.email}<br/>
			Password: ${get.password}<br/>
			Administrator: ${get.administrator}<br/><br />
			<a href="/Projeto/clientList.jsp">return to list</a>
		</section>
	</div>
</body>
</html>
