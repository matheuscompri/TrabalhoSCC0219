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
    <title>Contact</title>
    <%-- Setting the base url based on context --%>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/contact.css">
    <script src="js/jquery-2.1.3.js"></script>
    <script type="text/javascript" src="js/validation.js"></script>
    <script type="text/javascript" src="js/contact.js"></script>
</head>

<body id="imh-4" class="full">
   
    <%-- including the headers --%>
    <%@include file="header.jsp" %>

    <div class="main">
        <section class="registerSection">
            <h1>Contact</h1>
            <form id="contactForm" action="/Projeto/hotel/MessageController" method="POST">
                <label id="nameLabel" for="name">Name:</label>
                <input id="name" type="text" name="name" class="regbox-extend" />

                <label id="emailLabel" for="email">E-mail:</label>
                <input id="email" type="text" name="email" class="regbox-extend" />

                <label id="phoneLabel" for="phone">Mobile phone:</label>
                <input id="phone" type="text" name="phone" class="" placeholder="(XX)XXXXX-XX-XX" />

                <label id="checkboxLabel" for="">How did you hear about us?</label>
                <div id="aligning">
                    <div class="checkboxGroup">
                        <input type="checkbox" name="newspaper" id="newspapers">Newspapers<br>
                        <input type="checkbox" name="recomendation" id="recommendation">Recommendation<br>
                                        
                        <input type="checkbox" name="socialNetworks" id="socialNetwork">Social Networks
                    </div>
                    <div class="checkboxGroup">
                        <input type="checkbox" name="googleSearch" id="googleSearch">Google Search<br>
                        <input type="checkbox" name="magazines" id="magazines">Magazines<br>
                        <input type="checkbox" name="other" id="other">Other
                    </div>
                </div>

                <label id="leaveMessageLabel" for="textarea">Leave your message:</label>
                <textarea id="leaveMessage" class="contact-message" cols="60" rows="5" name="message"></textarea>
                <input class="submit_button" type="submit" value="Send" />
                <input class="reset_button" type="reset" value="Clear" />
            </form>
            <div class="clear"></div>
        </section>
    </div>
    <footer>
    </footer>
</body>

</html>