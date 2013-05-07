/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecu.SENG6240.utils;

import ecu.SENG6240.data.Conference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matthew.strapko
 */
public class CreateConference {
    
    Conference conference = new Conference();
    java.util.Date conferenceDate;
    java.util.Date conferenceDeadline;
    java.sql.Date sqlDate;
    java.sql.Date sqlDeadline;
    
    public boolean newConference(String conferenceName, String username, String school, String deadline, String date, String reviewerOne, String reviewerTwo, String reviewerThree, String reviewerFour, String reviewerFive) {
        try {
            conferenceDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(date);
            conferenceDeadline = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(deadline);
        } catch (ParseException ex) {
            Logger.getLogger(CreateConference.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlDate = new java.sql.Date(conferenceDate.getTime());
        sqlDeadline = new java.sql.Date(conferenceDeadline.getTime());
        
        conference.setConferenceName(conferenceName);
        conference.setCreator(username);
        conference.setSchool(school);
        conference.setOne(reviewerOne);
        conference.setTwo(reviewerTwo);
        conference.setThree(reviewerThree);
        conference.setFour(reviewerFour);
        conference.setFive(reviewerFive);
        conference.setDeadline(sqlDeadline);
        conference.setDate(sqlDate);
        
        try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "insert into conferences (conferenceName, creator, school, deadline, date, selectorOne, selectorTwo, selectorThree, selectorFour, selectorFive) values ('" + conference.getConferenceName() + "', '" + conference.getCreator() + "', '" + conference.getSchool() + "', '" + conference.getDeadline() + "', '" + conference.getDate() + "', '" + conference.getOne() + "', '" + conference.getTwo() + "', '" + conference.getThree() + "', '" + conference.getFour() + "', '" + conference.getFive() + "');";
		
		try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsm", "root", "");
                    Statement st = conn.createStatement();
                    int result = st.executeUpdate(sql);

                    if (result > 0) {
                        conn.close();
                        st.close();
                        return true;
                    } else {
                        conn.close();
                        st.close();
                        return false;
                    }
		} catch (SQLException e) {
                    e.printStackTrace();
		}
        
        return false;
        
    }
    
}
