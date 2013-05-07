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
public class RegisterUser {

	public int createUser(String firstname, String lastname, String username, String password, String email, String school) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String checkSql = "select * from users where email = '" + email + "';";
		String sql = "insert into users (fname, lname, username, password, email, school) values ('" + firstname + "', '" + lastname + "', '" + username + "', '" + password + "', '" + email + "', '" + school + "');";
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpsm", "root", "");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(checkSql);
			
			if (rs.next()) {
				conn.close();
				st.close();
				rs.close();
				return 1;
			} else {
				int result = st.executeUpdate(sql);
				
				if (result > 0) {
					conn.close();
					st.close();
					rs.close();
					return 0;
				} else {
					conn.close();
					st.close();
					rs.close();
					return 2;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 2;
	}
}

