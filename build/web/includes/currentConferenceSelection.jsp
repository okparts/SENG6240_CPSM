<%-- 
    Document   : currentConferenceSelection
    Created on : Apr 15, 2013, 3:00:01 PM
    Author     : matthew.strapko
--%>

<%@page import="ecu.SENG6240.data.Conference"
        import="ecu.SENG6240.utils.GetConferences" 
        import="java.util.Calendar" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%
    if (session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn").toString().trim() == "true") {
        Calendar cal = Calendar.getInstance();
        java.util.Date utilDate = cal.getTime();
        java.sql.Date now = new java.sql.Date(utilDate.getTime());
        GetConferences gc = new GetConferences();
        Conference[] conferences = gc.printConferences();
        if (conferences != null && conferences.length > 0) {
            out.println("<select name=\"conferenceSelection\">");
            out.println("<option value=\"default\">Select a Conference...</option>");
            for (int i = 0; i < conferences.length; i++) {
                if (conferences[i].getDeadline().after(now)) {
                    out.println("<option value=\"" + conferences[i].getConferenceID() + "\">" + conferences[i].getConferenceName() + "</option>");
                }
            }
            out.println("</select>");
        } else {
            out.println("No conferences are scheduled at this time.");
        }
    }
%>