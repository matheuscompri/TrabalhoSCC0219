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
	
    <%-- including the headers --%>
    <%@include file="header.jsp" %>
    
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
							<td><input type="checkbox" name="mdel${message.id}" /></td>
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
							<td><a href="/Projeto/hotel/MessageController?action=get&id=${message.id}">Details</a></td>
							<td><a href="/Projeto/hotel/MessageController?action=del&id=${message.id}">Remove</a></td>
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


