<%-- 
    Document   : currentPapersList
    Created on : Apr 10, 2013, 3:42:19 PM
    Author     : matthew.strapko
--%>

<%@page import="ecu.SENG6240.data.Paper"
        import="ecu.SENG6240.utils.GetPapers" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%
    String username = session.getAttribute("username").toString();
    String path = "/cpsm/papers/";
    GetPapers gp = new GetPapers(username);
    Paper[] papers = gp.printPapers();
    int oldConference = 0;
    int newConference = 0;
    if (papers != null && papers.length > 0) {
        for (int i = 0; i < papers.length; i++) {
            oldConference = papers[i].getConferenceID();
            if (oldConference != newConference && newConference != 0) {
                out.println("</ul>");
                out.println("<hr />");
                out.println("<div id=\"conf-" + papers[i].getConferenceID() + "\" class=\"conferenceTitle\">" + papers[i].getConferenceName() + "</div>");
                out.println("<ul id=\"conf-" + papers[i].getConferenceID() + "\" class=\"papersList\">");
            } else if (oldConference != newConference) {
                out.println("<div id=\"conf-" + papers[i].getConferenceID() + "\" class=\"conferenceTitle\">" + papers[i].getConferenceName() + "</div>");
                out.println("<ul id=\"conf-" + papers[i].getConferenceID() + "\" class=\"papersList\">");
            }
            out.println("<li id=\"paper-" + papers[i].getPaperID() + "\" class=\"papers\"><a href=\"" + path + papers[i].getPaperName() + "\" target=\"_blank\">" + papers[i].getPaperName() + "</a></li>");
            newConference = oldConference;
        }
        out.println("</ul>");
    } else {
        out.println("<li class=\"papers\">No papers have been submitted!</li>");
    }
%>
