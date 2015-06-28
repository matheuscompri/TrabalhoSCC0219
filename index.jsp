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
    <title>Lithium Hotel</title>
    <%-- Setting the base url based on context --%>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
    
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/index.css">
    <script src="js/jquery-2.1.3.js"></script>
    <script src="js/register.js"></script>
</head>

<body>

    <%-- including the headers --%>
    <%@include file="header.jsp" %>

    <section class="logo">
        <div class="ligther">
                <h1>Lithium Hotel</h1>
        </div>
    </section>

    <section class="slogan">
        <img width="8%" src="images/5stars.png" alt="Five Stars"/>
    </section>

    <footer>
        <p>Web page Designed by: Matheus Compri and Thais Emanuele</p>
    </footer>
</body>

</html>