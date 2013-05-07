package ecu.SENG6240.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Matt
 */
public class ValidateUser {
	
	public boolean isValidUser(String username, String password) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "select * from users where username = '" + username + "' and password = '" + password + "';";
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsm", "root", "");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if (rs.next()) {
				conn.close();
				st.close();
				rs.close();
				return true;
			} else {
				conn.close();
				st.close();
				rs.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
