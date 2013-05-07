<%-- 
    Document   : myConferencesList
    Created on : May 7, 2013, 11:07:12 AM
    Author     : matthew.strapko
--%>

<%@page import="ecu.SENG6240.data.Conference"
        import="ecu.SENG6240.utils.GetConferences" 
        import="java.util.Calendar" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%
    Calendar cal = Calendar.getInstance();
    java.util.Date utilDate = cal.getTime();
    java.sql.Date now = new java.sql.Date(utilDate.getTime());
    GetConferences gc = new GetConferences();
    String user = session.getAttribute("username").toString();
    Conference[] conferences = gc.printMyConferences(user);
    if (conferences != null && conferences.length > 0) {
        out.println("<br />");
        out.println("<div class=\"conferenceListTitleHdr\">");
        out.println("Conference");
        out.println("</div>");
        out.println("<div class=\"conferenceListDeadlineHdr\">");
        out.println("Submission Deadline");
        out.println("</div>");
        out.println("<div class=\"conferenceListDateHdr\">");
        out.println("Date");
        out.println("</div>");
        out.println("<br />");
        out.println("<hr />");
        for (int i = 0; i < conferences.length; i++) {
            if (conferences[i].getDeadline().after(now)) {
                out.println("<div class=\"conferenceListContainer\">");
                out.println("<div class=\"conferenceListTitle\">");
                out.println(conferences[i].getConferenceName());
                out.println("</div>");
                out.println("<div class=\"conferenceListDeadline\">");
                out.println(conferences[i].getDeadline().toString());
                out.println("</div>");
                out.println("<div class=\"conferenceListDate\">");
                out.println(conferences[i].getDate().toString());
                out.println("</div>");
                out.println("</div>");
                out.println("<br />");
            }
        }
    } else {
        out.println("No conferences are scheduled at this time.");
    }
%>
