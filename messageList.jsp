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
		<title>Message List</title>
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
			<jsp:useBean id="messageList" class="java.util.ArrayList" scope="session" />

			<h1>Message List</h1>

			<form action="/Projeto/hotel/MessageController" method="GET">
				<table>
					<tr>
						<td><b></b></td>
						<td><b>Read</b></td>
						<td><b>Name</b></td>
						<td><b>Email</b></td>
						<td></td>
						<td></td>
					</tr>
					<c:forEach var="message" items="${messageList}" varStatus="status">
						<tr>
							<td><input type="checkbox" name="mdel${status.index}" /></td>
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
							<td><a href="/Projeto/hotel/MessageController?action=get&id=${status.index}">Details</a></td>
							<td><a href="/Projeto/hotel/MessageController?action=del&id=${status.index}">Remove</a></td>
						</tr>
					</c:forEach>
				</table>
				<input type="hidden" name="action" value="mdel">
				<input type="button" class="reset_button" value="New Message" onclick="location.href='/Projeto/contact.jsp'">
				<input type="submit" class="submit_button" value="Delete Selected">
				<div class="clear">		
				</div>			
			</form>
		</section>
	</main>
	</body>
</html>


