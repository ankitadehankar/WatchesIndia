<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Shopping Cart Web Site</title>
</head>
<body>

	<center>
		<h1>Shopping Cart</h1>
	</center>
	<center>${msg}</center>
	<center>${successMessage}</center>
	<center>${errorMessage}</center>
	${loginMessage}
	<hr>

	<c:if test="${empty loginMessage}">
		<a href="login">Login</a>
	</c:if>

	<a href="register"> Register</a>
	<a href="contact"> Contact Us</a>
	<a href="aboutUs"> About Us</a>
	<br>

	<c:if test="${not empty loginMessage}">
		<a href="mycart">My Cart</a>
		<a href="logout">Logout</a>
	</c:if>

	<c:if test="${isUserClickedLogin==true}">
		<jsp:include page="/WEB-INF/views/Menu/login.jsp"></jsp:include>
	</c:if>
	
	<c:if test="${not empty errorMessage}">
	    <jsp:include page="/WEB-INF/views/Menu/login.jsp"></jsp:include>
	</c:if>

	<c:if test="${isUserClickedRegister==true}">
		<jsp:include page="/WEB-INF/views/Menu/register.jsp"></jsp:include>
	</c:if>

	<c:if test="${isUserClickedAboutUs==true}">
		<jsp:include page="/WEB-INF/views/Menu/aboutUs.jsp"></jsp:include>
	</c:if>

	<c:if test="${isUserClickedContactUs==true}">
		<jsp:include page="/WEB-INF/views/Menu/contact.jsp"></jsp:include>
	</c:if>

</body>
</html>