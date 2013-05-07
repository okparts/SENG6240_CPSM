/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecu.SENG6240.utils;

import ecu.SENG6240.data.Paper;
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
public class GetReviewPapers {
    
    private Paper[] paperArray;
    private String username;
    
    public GetReviewPapers(String username) {
        
        this.username = username;
        
    }
    
    public Paper[] printPapers() throws SQLException {
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        int length = 0;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "select * from paperreviews, conferences, papers where reviewer = '" + username + "' and paperreviews.conferenceID = conferences.conferenceID and paperreviews.paperID = papers.paperID order by paperreviews.conferenceID;";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsm", "root", "");
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String author = this.username;
                String paperName = rs.getString("paperName");
                int paperID = rs.getInt("paperID");
                int conferenceID = rs.getInt("conferenceID");
                String conferenceName = rs.getString("conferenceName");
                Date deadline = rs.getDate("deadline");
                Date date = rs.getDate("date");
                Paper paper = new Paper(paperName, author, paperID, conferenceID, conferenceName, deadline, date);
                if (paperArray != null) {
                    length = paperArray.length;
                    Paper[] tmp = Arrays.copyOf(paperArray, length + 1);
                    tmp[length] = paper;
                    paperArray = tmp;
                } else {
                    paperArray = new Paper[] {paper};
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
        
        return this.paperArray;
    }
    
}
