<%-- 
    Document   : main
    Created on : Mar 16, 2013, 10:29:16 PM
    Author     : Matt
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page 
    import="javax.servlet.http.HttpSession"
    import="java.io.*"
    import="java.util.*"
    import="ecu.SENG6240.utils.DebugTable"
%>
<%
boolean loggedIn = false;
if (session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn").toString().trim() == "true") {
    loggedIn = true;
} else {
    System.out.println("Oh man!");
}
if (!loggedIn) {
    response.sendRedirect("/cpsm/");
    System.out.println("Oh yeah!");
}
System.out.println(session.getAttribute("loggedIn").toString());
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>CPSM - Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css" />
    <link rel="stylesheet" type="text/css" href="css/general.css" />
</head>
<body>
<%
String error = "false";
String firstname = (String) session.getAttribute("firstname");
boolean isAdmin = false;
if (session.getAttribute("isAdmin") != null && session.getAttribute("isAdmin").toString() == "true") {
    isAdmin = true;
}
error = (String) session.getAttribute("error");
%>
    <div id="mainContainer" class="mainContainer">
        <jsp:include page="includes/header.jsp"></jsp:include>
        <div id="content" class="content">
            <div id="userProfile" class="userProfile mainDisplay">
                <div id="userDataHeader" class="userDataHeader dashboardDisplayHeader">
                    User Profile
                </div>
                <div id="tabMenu" class="tabMenu">
                    <div id="dataTab" class="tab tabActive" data-tab="1">
                        User Info
                    </div>
                    <div id="dataEditTab" class="tab" data-tab="2">
                        Edit User Info
                    </div>
                    <div id="changePasswordTab" class="tab" data-tab="3">
                        Change Password
                    </div>
                </div>
                <div id="userDataDisplay" class="userDataDisplay dashDisplay">
                    <div id="userDataLabels" class="userDataLabels">
                        <p>Name:</p>
                        <p>Username:</p>
                        <p>School:</p>
                        <p>Email:</p>
                    </div>
                    <div id="userDataValues" class="userDataValues">
                        <p><%= session.getAttribute("fullname") %></p>
                        <p><%= session.getAttribute("username") %></p>
                        <p id="schoolDisplay"><%= session.getAttribute("school") %></p>
                        <p id="emailDisplay"><%= session.getAttribute("email") %></p>
                    </div>
                </div>
                <div id="userDataEdit" class="userDataEdit dashDisplay hidden">
                    <form id="userEditForm" class="userEditForm" action="ctrl" method="post">
                        <div id="userDataLabels" class="userDataLabels">
                            <p>Name:</p>
                            <p>Username:</p>
                            <p class="fieldLabel">School:</p>
                            <p class="fieldLabel">Email:</p>
                        </div>
                        <div id="userDataValues" class="userDataValues">
                            <p><%= session.getAttribute("fullname") %></p>
                            <p id="usernameEdit"><%= session.getAttribute("username") %></p>
                            <p><input type="text" id="school" class="school" name="school" placeholder="<%= session.getAttribute("school") %>" /></p>
                            <p><input type="text" id="email" class="email" name="email" placeholder="<%= session.getAttribute("email") %>" /></p>
                            <input id="userEditBtn" class="userEditBtn submitBtn" type="submit" name="update" value="Update" />
                        </div>
                    </form>
                </div>
                <div id="userChangePassword" class="userChangePassword dashDisplay hidden">
                    <form id="changePasswordForm" class="changePasswordForm" action="ctrl" method="post">
                        <div id="userDataLabels" class="userDataLabels">
                            <p class="passwordFieldLabelOne">Password:</p>
                            <p class="passwordFieldLabel">New Password:</p>
                            <p class="passwordFieldLabel">Confirm New Password:</p>
                        </div>
                        <div id="userDataValues" class="userDataValues">
                            <p><input type="password" id="oldPassword" class="oldPassword" name="oldPassword" /></p>
                            <p><input type="password" id="newChangePassword" class="newChangePassword" name="newChangePassword" /></p>
                            <p><input type="password" id="confirmChangePassword" class="confirmChangePassword" name="confirmChangePassword" /></p>
                            <input id="changePasswordBtn" class="changePasswordBtn submitBtn" type="submit" name="changePassword" value="Change Password" />
                        </div>
                    </form>
                </div>
            </div>
            <div id="userPapers" class="userPapers mainDisplay hidden">
                <div id="userDataHeader" class="userDataHeader dashboardDisplayHeader">
                    Paper Submissions
                </div>
                <div id="tabMenu" class="tabMenu">
                    <div id="paperDisplayTab" class="tab tabActive" data-tab="1">
                        My Papers
                    </div>
                    <div id="paperReviewTab" class="tab" data-tab="2">
                        My Reviews
                    </div>
                    <div id="paperHistoryTab" class="tab" data-tab="3">
                        Submit New Paper
                    </div>
                </div>
                <div id="userPaperDisplay" class="userPaperDisplay dashDisplay">
                    <div id="currentPapers" class="currentPapers">
                        <div id="currentPapersHdr" class="currentPapersHdr">
                            Current Submissions
                        </div>
                        <hr />
                        <div id="currentPapersList" class="currentPapersList">
                            <jsp:include page="includes/currentPapersList.jsp"></jsp:include>
                        </div>
                    </div>
                </div>
                <div id="userReviewDisplay" class="userReviewDisplay dashDisplay hidden">
                    <div id="currentPapersHdr" class="currentPapersHdr">
                        Current Reviews
                    </div>
                    <hr />
                    <div id="currentReviewList" class="currentPapersList">
                        <jsp:include page="includes/currentReviewList.jsp"></jsp:include>
                    </div>
                </div>
                <div id="submitPaperDisplay" class="userHistoryDisplay dashDisplay hidden">
                    <div id="currentPapersHdr" class="currentPapersHdr">
                        Submit a New Paper
                    </div>
                    <div id="uploadPaper" class="uploadPaperContainer">
                         <form id="uploadPaper" class="uploadPaper" action="ctrl" method="post" enctype="multipart/form-data">
                            <p><jsp:include page="includes/currentConferenceSelection.jsp"></jsp:include></p>
                            <p><input id="selectPaper" class="selectPaperBtn" type="file" name="submitPaper" /></p>
                            <p><input id="submitPaper" class="submitPaperBtn submitBtn" name="submitPaper" type="submit" value="Submit Paper" /></p>
                        </form>
                    </div>
                </div>
            </div>
            <div id="userConferences" class="userConferences mainDisplay hidden">
                <div id="userDataHeader" class="userDataHeader dashboardDisplayHeader">
                    Conferences
                </div>
                <div id="tabMenu" class="tabMenu">
                    <div id="conferenceTab" class="tab tabActive" data-tab="1">
                        All Conferences
                    </div>
                    <div id="myConferenceTab" class="tab" data-tab="2">
                        My Conferences
                    </div>
                    <div id="createConferenceTab" class="tab" data-tab="3">
                        Create Conference
                    </div>
                </div>
                <div id="conferenceDisplay" class="userDataDisplay dashDisplay">
                    <div id="allConferences" class="currentPapersHdr">
                        All Conferences
                    </div>
                    <div id="allConferencesList" class="currentPapersList">
                        <jsp:include page="includes/allConferencesList.jsp"></jsp:include>
                    </div>
                </div>
                <div id="myConferenceDisplay" class="userDataEdit dashDisplay hidden">
                    <div id="allConferences" class="currentPapersHdr">
                        My Conferences
                    </div>
                    <div id="allConferencesList" class="currentPapersList">
                        <jsp:include page="includes/myConferencesList.jsp"></jsp:include>
                    </div>
                </div>
                <div id="createConferenceDisplay" class="userChangePassword dashDisplay hidden">
                    <form id="changePasswordForm" class="changePasswordForm" action="ctrl" method="post">
                        <div id="userDataLabels" class="userDataLabels">
                            <p class="passwordFieldLabelOne">Conference Name:</p>
                            <p class="passwordFieldLabel">School:</p>
                            <p class="passwordFieldLabel">Conference Date:</p>
                            <p class="passwordFieldLabel">Conference Deadline:</p>
                            <p class="passwordFieldLabel">Select up to Five Reviewers:</p>
                        </div>
                        <div id="userDataValues" class="userDataValues">
                            <p><input type="text" id="conferenceName" class="conferenceName" name="conferenceName" /></p>
                            <p><input type="text" id="school" class="school" name="school" /></p>
                            <p><input type="date" id="date" class="date" name="date" /></p>
                            <p><input type="date" id="deadline" class="deadline" name="deadline" /></p>
                            <p><input type="text" id="reviewerOne" class="reviewerOne" name="reviewerOne" placeholder="Reviewer 1" /></p>
                            <p><input type="text" id="reviewerTwo" class="reviewerTwo" name="reviewerTwo" placeholder="Reviewer 2" /></p>
                            <p><input type="text" id="reviewerThree" class="reviewerThree" name="reviewerThree" placeholder="Reviewer 3" /></p>
                            <p><input type="text" id="reviewerFour" class="reviewerFour" name="reviewerFour" placeholder="Reviewer 4" /></p>
                            <p><input type="text" id="reviewerFive" class="reviewerFive" name="reviewerFive" placeholder="Reviewer 5" /></p>
                            <br />
                            <input id="createConferenceBtn" class="createConferenceBtn submitBtn" type="submit" name="createConference" value="Create Conference" />
                        </div>
                    </form>
                </div>
            </div>
            <div id="admin" class="admin mainDisplay hidden">

            </div>
        </div>
            <div id="errMsg" class="errMsg">
                <%
                    if (error != "false" && !error.isEmpty() && error != null) {
                        out.print(error);
                    }
                    session.setAttribute("error", "");
                %>
            </div>
            <jsp:include page="includes/footer.jsp"></jsp:include>
	</div>
	
	<!--        DEBUG DATA         -->
	<div id="debug" class="debug">
<%--
  The debug output below and the DebugData class are modified code of the following copyright:

  Copyright (c) 2002 by Phil Hanna
  All rights reserved.
  
  You may study, use, modify, and distribute this
  software for any purpose provided that this
  copyright notice appears in all copies.
  
  This software is provided without warranty
  either expressed or implied.
--%>
<%
if (isAdmin) {

    /* Header data output for debug */
    Enumeration reqHeader;
    Map keyValueHeaders = new TreeMap();

    reqHeader = request.getHeaderNames();
    while (reqHeader.hasMoreElements()) {
        String name = (String) reqHeader.nextElement();
        String value = request.getHeader(name);
        keyValueHeaders.put(name, value);
    }

    DebugTable dtHeaders = new DebugTable();
    out.println(dtHeaders.createTable(keyValueHeaders, "Request Headers"));

    /* Session data output for debug */
    Enumeration sessionAttrs;
    Map keyValueSession = new TreeMap();

    sessionAttrs = session.getAttributeNames();
    while (sessionAttrs.hasMoreElements()) {
        String name = (String) sessionAttrs.nextElement();
        String value = " " + session.getAttribute(name);
        keyValueSession.put(name, value);
    }

    DebugTable dtSess = new DebugTable();
    out.println(dtSess.createTable(keyValueSession, "Session Attributes"));
}
%>
	</div>

    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/jquery-ui-1.10.1.min.js"></script>
    <script src="js/login.js"></script>
    <script src="js/headerFooter.js"></script>
    <script src="js/tabNavigation.js"></script>
    <script src="js/dashboard.js"></script>
</body>
</html>