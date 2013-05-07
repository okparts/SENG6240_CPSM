package ecu.SENG6240.utils;

import ecu.SENG6240.data.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Matt
 */
public class ChangePassword {

	public String passwordChange(String username, String oldPassword, String newPassword) {
		
            User userData = new User(username, oldPassword);
            
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
                String checkSql = "select * from users where username = '" + userData.getUsername() + "' and password = '" + userData.getPassword() + "'";
		String sql = "update users set password = '" + newPassword + "' where username = '" + userData.getUsername() + "';";
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsm", "root", "");
			Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(checkSql);
			
			if (rs.next()) {
                            int result = st.executeUpdate(sql);
                            if (result > 0) {
                                    conn.close();
                                    st.close();
                                    rs.close();
                                    return "Your password was successfully updated.";
                            } else {
                                    conn.close();
                                    st.close();
                                    rs.close();
                                    return "There was a problem changing your password.";
                            }
			} else {
                            conn.close();
                            st.close();
                            rs.close();
                            return "The old password does not match the password for this account.";
                        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "There was a problem changing your password.";
	}
	
}