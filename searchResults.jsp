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
		<title>Search Results for Users</title>
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
			<%-- recuperando listaClientes da sessao --%>
			<jsp:useBean id="searchResults" class="java.util.ArrayList" scope="session"/>

			<h2>Search Results for Users</h2>

			<%-- percorrendo lista de clientes com EL (Expression Language) --%>
			<form action="/Projeto/hotel/RegisterController" method="GET">
				<table>
					<tr>
						<td><b></b></td>
						<td><b>Name</b></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<c:forEach var="cliente" items="${searchResults}" varStatus="status">
						<tr>
							<td><input type="checkbox" name="mdel${status.index}"</td>
							<td>${cliente.name}</td>
							<td><a href="/Projeto/hotel/RegisterController?action=get&next=view&id=${cliente.id}">Details</a></td>
							<td><a href="/Projeto/hotel/RegisterController?action=get&next=edit&id=${cliente.id}">Editar</a></td>
							<td><a href="/Projeto/hotel/RegisterController?action=del&id=${cliente.id}">Remover</a></td>
						</tr>
					</c:forEach>
				</table>
                <input type="hidden" name="action" value="mdel">
				<input type="hidden" name="searchResults" value="true">
                <input type="submit" class="submit_button" value="Delete Selected" />
                <input type="button" class="reset_button" value="Return to list" onClick="location.href = '/Projeto/clientList.jsp'" />
                <div class="clear"></div>
			</form>
		</section>
	</div>
	</body>
</html>


