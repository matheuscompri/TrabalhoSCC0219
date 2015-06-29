<%@page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
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

			<h1>Message List</h1>
			
			<%-- Opening database connection --%>
		    <sql:setDataSource var="snapshot" driver="org.postgresql.Driver" url="jdbc:postgresql://localhost:5432/postgres" user="postgres"  password="postgres"/>
			
			<%-- Creating the query --%>
		    <sql:query dataSource="${snapshot}" var="result">
				SELECT * from messages;
			</sql:query>

			<form action="/Projeto/hotel/MessageController" method="GET">
				<table>
					<tr>
						<th></th>
						<th><b>Read</b></th>
						<th><b>Name</b></th>
						<th><b>Email</b></th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach var="row" items="${result.rows}">
						<tr>
							<td><input type="checkbox" name="mdel${row.message_id}" /></td>
							<td>
								<c:choose>
									<c:when test="${row.message_readStatus == true}">
										<input type="checkbox" disabled checked />
									</c:when>
									<c:otherwise>
										<input type="checkbox" disabled />
									</c:otherwise>
								</c:choose>
							</td>
							<td>${row.message_name}</td>
							<td>${row.message_email}</td>
							<td><a href="/Projeto/hotel/MessageController?action=get&id=${row.message_id}">Details</a></td>
							<td><a href="/Projeto/hotel/MessageController?action=del&id=${row.message_id}">Remove</a></td>
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


