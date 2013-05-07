package ecu.SENG6240.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matt
 */
public class CreateSession {
	
	public void setSession(String username, String password, HttpSession session) {
		
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
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstname = rs.getString("fname");
				String lastname = rs.getString("lname");
				String fullname = firstname + " " + lastname;
				String uname = rs.getString("username");
				String email = rs.getString("email");
				String school = rs.getString("school");
				boolean isAdmin = rs.getBoolean("isAdmin");
				
				session.setAttribute("id", id);
				session.setAttribute("firstname", firstname);
				session.setAttribute("lastname", lastname);
				session.setAttribute("fullname", fullname);
				session.setAttribute("username", uname);
				session.setAttribute("email", email);
				session.setAttribute("school", school);
				session.setAttribute("isAdmin", isAdmin);
				session.setAttribute("loggedIn", "true");
				session.setAttribute("error", "false");
			}
			conn.close();
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}

