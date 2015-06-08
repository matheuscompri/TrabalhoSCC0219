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
	<title>View Clientes</title>
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

			<h1>User details</h1>
			<jsp:useBean id="get" class="hotel.User" scope="session" />

			Name: ${get.name}<br/>
			CPF: ${get.cpf}<br/>
			Birthdate: ${get.dateOfBirth}<br/>
			Gender: ${get.gender}<br/>
			Marital Status: ${get.maritalStatus}<br/>
			City: ${get.city}<br/>
			State: ${get.state}<br/>
			Postal Code: ${get.postalCode}<br/>
            Email: ${get.email}<br/>
			Password: ${get.password}<br/>
			Administrator: ${get.administrator}<br/><br />
			<a href="/Projeto/clientList.jsp">return to list</a>
		</section>
	</div>
</body>
</html>
