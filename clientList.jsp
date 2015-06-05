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
		<title>Client List</title>
		<%-- Setting the base url based on context --%>
	    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
	    
	    <link rel="stylesheet" href="css/layout.css">
	    <link rel="stylesheet" href="css/index.css">
	</head>

	<body>
	<%-- Redirecting not allowed users --%>
    <c:if test="${currentUser.administrator != true }">
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
                        <li><a href="reservation.jsp">Reservations</a></li>
                        
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

			<h1>Search by name:</h1>
			<form action="/Projeto/hotel/RegisterController" method="GET">
				<input type="text" name="name" placeholder="Type any part of the name">
				<input type="submit" class="submit_button" value="Search">
				<input type="reset" class="reset_button" value="Clear">
				<input type="hidden" name="action" value="search">
				<input type="hidden" name="method" value="name">
			</form>
			<div class="clear"></div>

			<h1>Search by date:</h1>
			<form action="/Projeto/hotel/RegisterController" method="GET">
				<label>After </label>
				<input type="text" name="after" placeholder="dd/mm/yyyy"><br />
				<label>Before </label>
				<input type="text" name="before" placeholder="dd/mm/yyyy"><br />
				<input type="submit" class="submit_button" value="Search">
				<input type="reset" class="reset_button" value="Clear">
				<input type="hidden" name="action" value="search">
				<input type="hidden" name="method" value="date">
			</form>
			<div class="clear"></div>

			<h1> Full Client List</h1>

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
					<c:forEach var="cliente" items="${clientList}" varStatus="status">
						<tr>
							<td><input type="checkbox" name="mdel${status.index}"</td>
							<td>${cliente.name}</td>
							<td><a href="/Projeto/hotel/RegisterController?action=get&next=view&id=${status.index}">Details</a></td>
							<td><a href="/Projeto/hotel/RegisterController?action=get&next=edit&id=${status.index}">Edit</a></td>
							<td><a href="/Projeto/hotel/RegisterController?action=del&id=${status.index}">Remove</a></td>
						</tr>
					</c:forEach>
				</table>
				<input type="hidden" name="action" value="mdel">
				<input type="button" class="reset_button" value="Add Client" onclick="location.href='/Projeto/register.jsp'">
				<input type="submit" class="submit_button" value="Delete Selected">
				<div class="clear"></div>
			</form>
		</section>
	</main>
	</body>
</html>


