/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecu.SENG6240.utils;

import ecu.SENG6240.data.Conference;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 *
 * @author matthew.strapko
 */
public class GetConferences {
    
    private Conference[] conferenceArray;
    
    public Conference[] printConferences() throws SQLException {
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        int length = 0;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "select * from conferences;";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsm", "root", "");
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int conferenceID = rs.getInt("conferenceID");
                String conferenceName = rs.getString("conferenceName");
                String creator = rs.getString("creator");
                String school = rs.getString("school");
                String one = rs.getString("selectorOne");
                String two = rs.getString("selectorTwo");
                String three = rs.getString("selectorThree");
                String four = rs.getString("selectorFour");
                String five = rs.getString("selectorFive");
                Date deadline = rs.getDate("deadline");
                Date date = rs.getDate("date");
                Conference conference = new Conference(conferenceName, creator, school, one, two, three, four, five, conferenceID, deadline, date);
                if (conferenceArray != null) {
                    length = conferenceArray.length;
                    Conference[] tmp = Arrays.copyOf(conferenceArray, length + 1);
                    tmp[length] = conference;
                    conferenceArray = tmp;
                } else {
                    conferenceArray = new Conference[] {conference};
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        
        return this.conferenceArray;
    }
    
    public Conference[] printMyConferences(String username) throws SQLException {
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        int length = 0;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "select * from conferences where creator = '" + username + "';";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsm", "root", "");
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int conferenceID = rs.getInt("conferenceID");
                String conferenceName = rs.getString("conferenceName");
                String creator = rs.getString("creator");
                String school = rs.getString("school");
                String one = rs.getString("selectorOne");
                String two = rs.getString("selectorTwo");
                String three = rs.getString("selectorThree");
                String four = rs.getString("selectorFour");
                String five = rs.getString("selectorFive");
                Date deadline = rs.getDate("deadline");
                Date date = rs.getDate("date");
                Conference conference = new Conference(conferenceName, creator, school, one, two, three, four, five, conferenceID, deadline, date);
                if (conferenceArray != null) {
                    length = conferenceArray.length;
                    Conference[] tmp = Arrays.copyOf(conferenceArray, length + 1);
                    tmp[length] = conference;
                    conferenceArray = tmp;
                } else {
                    conferenceArray = new Conference[] {conference};
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        
        return this.conferenceArray;
    }
    
}
