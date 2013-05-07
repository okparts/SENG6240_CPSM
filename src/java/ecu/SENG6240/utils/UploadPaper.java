/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecu.SENG6240.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author matthew.strapko
 */
public class UploadPaper {
    
    public String paperUpload(String paper, String author, int conferenceID) {
        
        try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
                e.printStackTrace();
        }
        
        String sql = "insert into papers (paperName, author, conferenceID) values ('" + paper + "', '" + author + "', " + conferenceID + ");";

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsm", "root", "");
            Statement st = conn.createStatement();
            int result = st.executeUpdate(sql);

            if (result > 0) {
                conn.close();
                st.close();
                return "";
            } else {
                conn.close();
                st.close();
                return "There was an error with the database.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "There was an error with the database.";
    }
}
