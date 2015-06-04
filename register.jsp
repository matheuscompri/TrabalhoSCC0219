<%@page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- Fixing the context for dispatcher calls --%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url" >${req.requestURL}</c:set>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <title>Register</title>
    <%-- Setting the base url based on context --%>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

    <script src="js/jquery-2.1.3.js"></script>
    <script src="js/validation.js"></script>
    <script src="js/register.js"></script>
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/register.css">
</head>

<body id="imh-3" class="full">
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
            <h1>Please fill out the following form</h1>
            <form id="registerForm" action="/Projeto/hotel/RegisterController" method="POST">
                <label id="nameLabel" for="name">Name:</label>
                <input id="name" type="text" name="name" />

                <label id="cpfLabel" for="cpf">CPF:</label>
                <input id="cpf" type="text" name="cpf" placeholder="Without Dots or Dashes" />

                <label id="birthLabel" for="dateOfBirth">Date of Birth:</label>
                <input id="birth" type="text" name="dateOfBirth" placeholder="DD/MM/YYYY" />

                <label for="gender">Gender:</label>
                <select id="gender" name="gender">
                    <option value="none">Prefer not to state</option>
                    <option value="male">Male</option>
                    <option value="fem">Female</option>
                </select>

                <label for="maritalStatus">Marital Status:</label>
                <select id="mstatus" name="maritalStatus">
                    <option value="none">Single</option>
                    <option value="male">Married</option>
                    <option value="fem">Other</option>
                </select>

                <label id="cityLabel" for="city">City:</label>
                <input id="city" type="text" name="city" />

                <label for="state">State:</label>
                <select id="state" name="state">
                    <option value="AC">Acre</option>
                    <option value="AL">Alagoas</option>
                    <option value="AP">Amapá</option>
                    <option value="AM">Amazonas</option>
                    <option value="BA">Bahia</option>
                    <option value="CE">Ceará</option>
                    <option value="DF">Distrito Federal</option>
                    <option value="ES">Espirito Santo</option>
                    <option value="GO">Goiás</option>
                    <option value="MA">Maranhão</option>
                    <option value="MS">Mato Grosso do Sul</option>
                    <option value="MT">Mato Grosso</option>
                    <option value="MG">Minas Gerais</option>
                    <option value="PA">Pará</option>
                    <option value="PB">Paraíba</option>
                    <option value="PR">Paraná</option>
                    <option value="PE">Pernambuco</option>
                    <option value="PI">Piauí</option>
                    <option value="RJ">Rio de Janeiro</option>
                    <option value="RN">Rio Grande do Norte</option>
                    <option value="RS">Rio Grande do Sul</option>
                    <option value="RO">Rondônia</option>
                    <option value="RR">Roraima</option>
                    <option value="SC">Santa Catarina</option>
                    <option value="SP">São Paulo</option>
                    <option value="SE">Sergipe</option>
                    <option value="TO">Tocantins</option>
                </select>

                <label id="cepLabel" for="postalCode">Post Code:</label>
                <input id="cep" type="text" name="postalCode" placeholder="XXXXXXXX" />

                <label id="emailLabel" for="email">E-mail:</label>
                <input id="email" type="text" name="email" />

                <label id="passwordLabel" for="password">Password:</label>
                <input id="password" type="password" name="password" />

                <label id="password_confirmationLabel" for="passwordConfirmations">Confirm Password:</label>
                <input id="password_confirmation" onpaste="return false" type="password" name="passwordConfirmations" />

                <input type="hidden" name="action" value="register" />

                <input class="submit_button" type="submit" value="Register" />
                <input class="reset_button" type="reset" value="Clear" />
            </form>
            <div class="clear"></div>

        </section>

    </div>
</body>

</html>