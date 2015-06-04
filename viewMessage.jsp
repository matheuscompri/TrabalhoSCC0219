<%@page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>View Message</title>
</head>
<body>

	<p>View Message</p>
	<jsp:useBean id="get" class="hotel.Message" scope="session" />

	Name: ${get.name}<br/>
	Email: ${get.email}<br/>
	Phone: ${get.phone}<br/>
	Message: ${get.message}<br/>
	Date: ${get.datestring}<br/>

	How did you hear about us?<br />
	<label>Newspaper</label>
	<c:choose>
		<c:when test="${get.newspaper}">
			<input type="checkbox" disabled checked />
		</c:when>
		<c:otherwise>
			<input type="checkbox" disabled />
		</c:otherwise>
	</c:choose>

	<label>Recomendation</label>

	<c:choose>
		<c:when test="${get.recomendation}">
			<input type="checkbox" disabled checked />
		</c:when>
		<c:otherwise>
			<input type="checkbox" disabled />
		</c:otherwise>
	</c:choose>

	<label>Social Networks</label>
	<c:choose>
		<c:when test="${get.socialNetworks}">
			<input type="checkbox" disabled checked />
		</c:when>
		<c:otherwise>
			<input type="checkbox" disabled />
		</c:otherwise>
	</c:choose>

	<label>Google Search</label>
	<c:choose>
		<c:when test="${get.googleSearch}">
			<input type="checkbox" disabled checked />
		</c:when>
		<c:otherwise>
			<input type="checkbox" disabled />
		</c:otherwise>
	</c:choose>

	<label>Magazines</label>
	<c:choose>
		<c:when test="${get.magazines}">
			<input type="checkbox" disabled checked />
		</c:when>
		<c:otherwise>
			<input type="checkbox" disabled />
		</c:otherwise>
	</c:choose>

	<label>Other</label>	
	<c:choose>
		<c:when test="${get.other}">
			<input type="checkbox" disabled checked />
		</c:when>
		<c:otherwise>
			<input type="checkbox" disabled />
		</c:otherwise>
	</c:choose>
	<br/>
	<a href="/Projeto/messageList.jsp">back</a>
</body>
</html>
