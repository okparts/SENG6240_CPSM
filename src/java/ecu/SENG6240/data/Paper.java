/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecu.SENG6240.data;

import java.sql.Date;

/**
 *
 * @author matthew.strapko
 */
public class Paper {
    
    private String paperName, author, conferenceName, deadline, date = "";
    private int paperID, conferenceID = 0;
    
    public Paper(String paperName, String author, int paperID, int conferenceID, String conferenceName, Date deadline, Date date) {
        
        this.paperName = paperName;
        this.author = author;
        this.paperID = paperID;
        this.conferenceID = conferenceID;
        this.conferenceName = conferenceName;
        this.deadline = deadline.toString();
        this.date = date.toString();
        
    }
    
    public Paper(String paperName, String author, int conferenceID) {
        
        this.paperName = paperName;
        this.author = author;
        this.conferenceID = conferenceID;
        
    }

    public String getPaperName() {
        return paperName;
    }

    public String getAuthor() {
        return author;
    }

    public int getPaperID() {
        return paperID;
    }

    public int getConferenceID() {
        return conferenceID;
    }
    
    public String getConferenceName() {
        return conferenceName;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDate() {
        return date;
    }
    
}
