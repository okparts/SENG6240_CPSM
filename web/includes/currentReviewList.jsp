<%-- 
    Document   : currentReviewList
    Created on : May 6, 2013, 2:15:43 PM
    Author     : matthew.strapko
--%>

<%@page import="ecu.SENG6240.data.Paper"
        import="ecu.SENG6240.utils.GetReviewPapers"
        import="ecu.SENG6240.data.Conference"
        import="ecu.SENG6240.utils.GetConferences" 
        import="java.util.Calendar" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%
    String username = session.getAttribute("username").toString();
    String path = "/cpsm/papers/";
    GetReviewPapers gp = new GetReviewPapers(username);
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
        out.println("<hr />");
        
        Calendar cal = Calendar.getInstance();
        java.util.Date utilDate = cal.getTime();
        java.sql.Date now = new java.sql.Date(utilDate.getTime());
        GetConferences gc = new GetConferences();
        Conference[] conferences = gc.printConferences();
        if (conferences != null && conferences.length > 0) {
            out.println("<br />");
            out.println("<div id=\"submitReview\" class=\"submitReview\">");
            out.println("<div id=\"currentPapersHdr\" class=\"currentPapersHdr\">");
            out.println("Submit Paper Review");
            out.println("</div>");
            out.println("<br />");
            out.println("<div id=\"reviewSubmitForm\" class=\"reviewSubmitForm\">");
            out.println("<form id=\"uploadReview\" class=\"uploadReview\" action=\"ctrl\" method=\"post\" enctype=\"multipart/form-data\">");
            out.print("<p>"); 
            out.println("<select name=\"conferenceSelection\">");
            out.println("<option value=\"default\">Select a Conference...</option>");
            for (int i = 0; i < conferences.length; i++) {
                if (conferences[i].getDeadline().after(now)) {
                    out.println("<option value=\"" + conferences[i].getConferenceID() + "\">" + conferences[i].getConferenceName() + "</option>");
                }
            }
            out.println("</select>");
            out.print("</p>");
            out.println("<p><input id=\"selectPaper\" class=\"selectPaperBtn\" type=\"file\" name=\"reviewPaper\" /></p>");
            out.println("<p><input id=\"submitPaper\" class=\"submitPaperBtn submitBtn\" name=\"submitReview\" type=\"submit\" value=\"Submit Review\" /></p>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div>");
        } else {
            out.println("No conferences are scheduled at this time.");
        }
    } else {
        out.println("<li class=\"papers\">No papers have been submitted for review!</li>");
    }
%>