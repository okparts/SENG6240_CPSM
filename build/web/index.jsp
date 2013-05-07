<%-- 
    Document   : index
    Created on : Mar 16, 2013, 10:24:55 PM
    Author     : Matt
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page
    import="java.util.TreeMap"
    import="java.util.Map"
    import="java.util.Enumeration"
    import="javax.servlet.http.HttpSession"
    import="ecu.SENG6240.utils.DebugTable"
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>CPSM - Login</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css" />
    <link rel="stylesheet" type="text/css" href="css/general.css" />
</head>
<body>
<%
String loggedIn = "false";
String loginError = "false";
int regError = 0;
if (session.getAttribute("regError") != null) {
    String registryError = session.getAttribute("regError").toString().trim();
    regError = Integer.parseInt(registryError);
}
loggedIn = (String) session.getAttribute("loggedIn");
loginError = (String) session.getAttribute("loginError");
String username = (String) session.getAttribute("username");
String password = (String) session.getAttribute("password");
%>
    <div id="mainContainer" class="mainContainer">
        <jsp:include page="includes/header.jsp"></jsp:include>
        <div id="formContainer" class="formContainer">
            <div id="titlePage" class="titlePage">
                <p>ACADEMIC CONFERENCE PAPER SUBMISSION</p><br /><br />
                <p>by</p>
            </div>
            <div id="loginFormContainer" class="loginFormContainer">
                <form class="loginForm" id="loginForm" method="post" action="ctrl">
                    <div id="loginFields" class="loginFields">
                        Username: <input id="loginUsername" type="text" name="username" size="30" value="<% if (username != null) out.println(username); %>" autofocus="autofocus" /><br />
                        Password: <input id="loginPassword" type="password" name="password" size="30" value="<% if (password != null) out.println(password); %>" /><br />
                        <input id="login" class="loginBtn submitBtn" type="submit" name="login" value="Login" />
                    </div>
                </form>
            </div>
            <div id="registrationForm" class="registrationForm hidden">
                <form class="regForm" id="regForm" method="post" action="ctrl">
                    <div id="regFields" class="regFields">
                        First Name: <input id="fname" type="text" name="fname" size="30" autofocus="autofocus" /><br />
                        Last Name: <input id="lname" type="text" name="lname" size="30" /><br />
                        Username: <input id="regUsername" type="text" name="username" size="30" /><br />
                        Password: <input id="regPassword" type="password" name="password" size="30" /><br />
                        Confirm Password: <input id="confPassword" type="password" name="confPassword" size="30" /><br />
                        Email Address: <input id="email" type="text" name="email" size="30" /><br />
                        College/Organization: <input id="school" type="text" name="school" size="30" /><br />
                        <input id="register" class="registerBtn submitBtn" type="submit" name="register" value="Register" />
                    </div>
                </form>
            </div>
            <div id="recoverForm" class="recoverForm hidden">
                <form id="recForm" class="recForm" method="post" action="ctrl">
                    <div id="recFields" class="recFields">
                        Email Address: <input id="email" type="text" name="email" size="30" /><br />
                        <input id="recover" class="recoverBtn submitBtn" type="submit" name="recover" value="Recover" />
                    </div>
                </form>
            </div>
        </div>
        <div id="errMsg" class="errMsg">
            <%
            if (loginError == "true") {
                out.println("The information you have entered does not match our records.<br />");
            }
            if (regError == 1) {
                out.println("A user has already registered with that email address. Use the Recover option above.<br />");
            }
            if (regError == 2) {
                out.println("There is a problem creating your account.  Try again later.<br />");
            }
            session.setAttribute("regError", null);
            session.setAttribute("loginError", null);
            %>
        </div>
        <jsp:include page="includes/footer.jsp"></jsp:include>
    </div>

    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/jquery-ui-1.10.1.min.js"></script>
    <script src="js/login.js"></script>
    <script src="js/headerFooter.js"></script>
</body>
</html>