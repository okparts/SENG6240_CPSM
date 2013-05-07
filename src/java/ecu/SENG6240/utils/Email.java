/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecu.SENG6240.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author Matt
 */
public class Email {
    
    private String senderId;
    private String username;
    private String password;
    private MultiPartEmail email = new MultiPartEmail();
    
    public boolean sendRecoverEmail(String emailTo) {
        
        String myEmailId = "ECU.SENG6240@gmail.com";
        String myPassword = "1234567890password";
        
        try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
                e.printStackTrace();
        }
        
        String sql = "select * from users where email = '" + emailTo + "';";

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsm", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                password = rs.getString("password");
                username = rs.getString("username");
                conn.close();
                st.close();
                rs.close();
            } else {
                conn.close();
                st.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(myEmailId, myPassword));
            email.setDebug(true);
            email.setHostName("smtp.gmail.com");
            email.setFrom(myEmailId);
            email.setSubject("CPSM Password Recovery");
            email.setMsg("Hello " + username + ",\n\nYour password is: " + password + ".\n\n-CPSM Admin");
            email.addTo(emailTo);
            email.setTLS(true);

//            EmailAttachment attachment = new EmailAttachment();
//            attachment.setPath("/Users/fahadparkar/Desktop/Fahim/tables.xlsx");
//            attachment.setDisposition(EmailAttachment.ATTACHMENT);
//            attachment.setDescription("Excel");
//            attachment.setName("tables.xlsx");
//            email.attach(attachment);

            email.send();
            System.out.println("Mail sent!");
            return true;
        } catch (Exception e) {
            System.out.println("Exception :: " + e);
        }
        return false;
    }
    
    public void sendReviewEmail(String paper) {
        
        String myEmailId = "ECU.SENG6240@gmail.com";
        String myPassword = "1234567890password";
        
        try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
                e.printStackTrace();
        }
        
        String sql = "select * from papers where paperName = '" + paper + "';";

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsm", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                username = rs.getString("author");
                conn.close();
                st.close();
                rs.close();
            } else {
                conn.close();
                st.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        sql = "select * from users where username = '" + username + "';";

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsm", "root", "");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                senderId = rs.getString("email");
                System.out.println("DAMN: " + rs.getString("email"));
                conn.close();
                st.close();
                rs.close();
            } else {
                conn.close();
                st.close();
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(myEmailId, myPassword));
            email.setDebug(true);
            email.setHostName("smtp.gmail.com");
            email.setFrom(myEmailId);
            email.setSubject("CPSM Review Available");
            email.setMsg("Hello " + username + ",\n\nA paper has been reviewed and is available in your list of Papers.\n\n-CPSM Admin");
            email.addTo(senderId);
            email.setTLS(true);

//            EmailAttachment attachment = new EmailAttachment();
//            attachment.setPath("/Users/fahadparkar/Desktop/Fahim/tables.xlsx");
//            attachment.setDisposition(EmailAttachment.ATTACHMENT);
//            attachment.setDescription("Excel");
//            attachment.setName("tables.xlsx");
//            email.attach(attachment);

            email.send();
            System.out.println("Mail sent!");
        } catch (Exception e) {
            System.out.println("Exception :: " + e);
        }
    }
}