<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register here</title>
</head>
<body>
This is Registration Page...

<form action="/create_user" method="post">

<p>User ID: <input type="text" name="userID"></input></p>
<p>Password: <input type="password" name="password"></input></p>
<p>contact: <input type="text" name="contact"></input></p>
<p>Mobile No.: <input type="text" name="mobile"></input></p>
<p><input type="submit" value="Register"></input></p>

</form>
</body>
</html>