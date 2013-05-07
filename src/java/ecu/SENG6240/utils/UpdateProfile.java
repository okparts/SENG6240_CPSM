package ecu.SENG6240.utils;

import ecu.SENG6240.data.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Matt
 */
public class UpdateProfile {

	public boolean updateUserInfo(String username, String school, String email) {
            
            User userData = new User(username, school, email);
					
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "update users set school = '" + userData.getSchool() + "', email = '" + userData.getEmail() + "' where username = '" + userData.getUsername() + "';";
		
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
