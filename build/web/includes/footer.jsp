<%-- 
    Document   : footer
    Created on : Mar 16, 2013, 10:47:46 PM
    Author     : Matt
--%>

<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div id="mainFooter" class="mainFooter">
	<div id="copyright" class="copyright">
		&copy; <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %> | Matt Strapko | ECU SENG 6240
	</div>
</div>
