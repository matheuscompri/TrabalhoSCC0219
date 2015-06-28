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
	<title>View Reservation</title>
	<%-- Setting the base url based on context --%>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/contact.css">
</head>
<body>
	
    <%-- including the headers --%>
    <%@include file="header.jsp" %>

    <div class="main">
        <section class="registerSection">

			<h1>Reservation details</h1>
			<jsp:useBean id="get" class="hotel.Reservation" scope="session" />

            <%-- Redirecting not allowed users --%>
            <c:if test="${currentUser.name == null}">
                <c:redirect url="permissionDenied.jsp" />
            </c:if>
            <c:if test="${ currentUser.name != get.client.name && currentUser.administrator != true}">
                <c:redirect url="permissionDenied.jsp" />
            </c:if> 

            Name: ${get.client.name}<br/>
            Arrival Date: ${get.arrival}<br/>
			Departure Date: ${get.departure}<br/>
            Number of adults: ${get.adults}<br/>
            Number of children: ${get.children}<br/>
            Number of babies: ${get.babies}<br/><br />
			<a href="/Projeto/reservationList.jsp">return to list</a>
		</section>
	</div>
</body>
</html>
