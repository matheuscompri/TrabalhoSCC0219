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
    <title>Log in</title>
    <%-- Setting the base url based on context --%>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
    <script type="text/javascript" src="js/validation.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
</head>

<body id="imh-2" class="full">
    
    <%-- including the headers --%>
    <%@include file="header.jsp" %>
    
    <div class="main">
        <section class="registerSection">
            <h1>Log in</h1>
            <form id="loginForm" action="/Projeto/hotel/LoginController" method="POST">
                <label id="usernameLabel" for="username">Username</label>
                <input type="text" id="username" class="box-extend" name="username" />

                <label id="passwordLabel" for="password">Password</label>
                <input type="password" id="password" class="box-extend" name="password" />

                <input type="submit" class="submit_button" value="Log in" />
                <input type="button" class="reset_button" value="Don't have an account?" onClick="location.href = 'register.jsp'" />
            </form>
            <div class="clear"></div>
        </section>
    </div>
    <footer>
    </footer>
</body>

</html>