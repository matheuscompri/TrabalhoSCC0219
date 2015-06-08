<%@page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- Fixing the context for dispatcher calls --%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url" >${req.requestURL}</c:set>

<html>
<head>
	<title>Message Details</title>
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
			<h1>Message Details</h1>
			<jsp:useBean id="get" class="hotel.Message" scope="session" />

			Name: ${get.name}<br/>
			Email: ${get.email}<br/>
			Phone: ${get.phone}<br/>
			Date: ${get.datestring}<br/>

			How did you hear about us?<br />
			<div id="aligning">
				<div class="checkboxGroup">

					<c:choose>
						<c:when test="${get.newspaper}">
							<input type="checkbox" disabled checked />Newspaper<br />
						</c:when>
						<c:otherwise>
							<input type="checkbox" disabled />Newspaper<br />
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${get.recomendation}">
							<input type="checkbox" disabled checked />Recomendation<br />
						</c:when>
						<c:otherwise>
							<input type="checkbox" disabled />Recomendation<br />
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${get.socialNetworks}">
							<input type="checkbox" disabled checked />Social Networks<br />
						</c:when>
						<c:otherwise>
							<input type="checkbox" disabled />Social Networks<br />
						</c:otherwise>
					</c:choose>
				</div>
				<div class="checkboxGroup">

					<c:choose>
						<c:when test="${get.googleSearch}">
							<input type="checkbox" disabled checked />Google Search<br />
						</c:when>
						<c:otherwise>
							<input type="checkbox" disabled />Google Search<br />
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${get.magazines}">
							<input type="checkbox" disabled checked />Magazines<br />
						</c:when>
						<c:otherwise>
							<input type="checkbox" disabled />Magazines<br />
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${get.other}">
							<input type="checkbox" disabled checked />Other<br/>
						</c:when>
						<c:otherwise>
							<input type="checkbox" disabled />Other<br/>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			Message:<br />${get.message}<br/>
			<a href="/Projeto/messageList.jsp">return to list</a>
		</section>
	</div>
</body>
</html>
