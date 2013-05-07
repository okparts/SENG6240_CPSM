package ecu.SENG6240.data;

/**
 *
 * @author Matt
 */
public class User {
    
    private String username;
    private String school;
    private String email;
    private String password;
    
    /**
     *
     * @param username
     * @param school
     * @param email
     */
    public User(String username, String school, String email) {
        this.username = username;
        this.school = school;
        this.email = email;
        this.password = "";
    }
    
    /**
     *
     * @param username
     * @param password
     */
    public User(String username, String password) {
         this.username = username;
        this.school = "";
        this.email = "";
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getSchool() {
        return school;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
}