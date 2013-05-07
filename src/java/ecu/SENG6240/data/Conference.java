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
public class Conference {
    
    private String conferenceName, creator, school, one, two, three, four, five;
    private int conferenceID;
    private Date deadline, date;
    
    public Conference() {
        
    }
    
    public Conference(String name, String creator, String school, String one, String two, String three, String four, String five, int id, Date deadline, Date date) {
        this.conferenceName = name;
        this.creator = creator;
        this.school = school;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.conferenceID = id;
        this.deadline = deadline;
        this.date = date;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public String getCreator() {
        return creator;
    }

    public String getSchool() {
        return school;
    }

    public String getOne() {
        return one;
    }

    public String getTwo() {
        return two;
    }

    public String getThree() {
        return three;
    }

    public String getFour() {
        return four;
    }

    public String getFive() {
        return five;
    }

    public int getConferenceID() {
        return conferenceID;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Date getDate() {
        return date;
    }
    
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public void setConferenceID(int conferenceID) {
        this.conferenceID = conferenceID;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
