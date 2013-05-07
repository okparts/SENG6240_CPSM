<%-- 
    Document   : header
    Created on : Mar 16, 2013, 10:48:06 PM
    Author     : Matt
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
String loggedIn = "false";
boolean isAdmin = false;
loggedIn = (String) session.getAttribute("loggedIn");
if (session.getAttribute("isAdmin") != null && session.getAttribute("isAdmin").toString() == "true") {
	isAdmin = true;
}
%>

<div id="mainHeader" class="mainHeader">
	<div id="mainTitle" class="mainTitle">
		Conference Paper Submission Manager
	</div>
	<div id="login" class="login headerBtns">
		<% if (loggedIn == "true") {
			out.println("Logout");
		} else {
			out.println("Login");
		} %>
	</div>
	<%
	if (loggedIn == "false" || loggedIn == null) {
		out.println("<div id=\"register\" class=\"register headerBtns\">");
		out.println("Register");
		out.println("</div>");
		out.println("<div id=\"recover\" class=\"recover headerBtns\">");
		out.println("Recover");
		out.println("</div>");
	} else {
		out.println("<div id=\"myAccount\" class=\"myProfile headerBtns\">");
		out.println("Profile");
		out.println("</div>");
		out.println("<div id=\"conferences\" class=\"conferences headerBtns\">");
		out.println("Conferences");
		out.println("</div>");
		out.println("<div id=\"myPapers\" class=\"myPapers headerBtns\">");
		out.println("Papers");
		out.println("</div>");
//		if (isAdmin) {
//			out.println("<div id=\"admin\" class=\"admin headerBtns\">");
//			out.println("Admin");
//			out.println("</div>");
//		}
	}
	%>
</div>