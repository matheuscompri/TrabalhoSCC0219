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
		<title>Internal Error</title>
		<%-- Setting the base url based on context --%>
	    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
	    
	    <link rel="stylesheet" href="css/layout.css">
	    <link rel="stylesheet" href="css/index.css">
	</head>

	<body>
	
    <%-- including the headers --%>
    <%@include file="header.jsp" %>
    
     <div class="main">
        <section class="registerSection">
			<h1>Invalid Reservation Error</h1>
			This date is not available for reservation.
		</section>
	</main>
	</body>
</html>


