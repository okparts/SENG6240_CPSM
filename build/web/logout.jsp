<%-- 
    Document   : logout
    Created on : Mar 16, 2013, 10:27:35 PM
    Author     : Matt
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CPSM - Logout</title>
</head>
<body>
	<%
	session.invalidate();
	request.logout();
	System.out.println("LOGGED OUT!!!");
	response.sendRedirect("/cpsm/login");
	%>
</body>
</html>