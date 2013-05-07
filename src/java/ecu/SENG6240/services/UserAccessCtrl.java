package ecu.SENG6240.services;

import ecu.SENG6240.utils.ChangePassword;
import ecu.SENG6240.utils.CreateConference;
import ecu.SENG6240.utils.CreateSession;
import ecu.SENG6240.utils.Email;
import ecu.SENG6240.utils.RegisterUser;
import ecu.SENG6240.utils.ReviewPaper;
import ecu.SENG6240.utils.UpdateProfile;
import ecu.SENG6240.utils.UploadPaper;
import ecu.SENG6240.utils.ValidateUser;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Matt
 * Servlet implementation class UserAccessCtrl
 */
public class UserAccessCtrl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserAccessCtrl() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String register = request.getParameter("register");
        String recover = request.getParameter("recover");
        String updateUserData = request.getParameter("update");
        String changePassword = request.getParameter("changePassword");
        String submitPaper = request.getParameter("submitPaper");
        String createConference = request.getParameter("createConference");

        if (login != null) {

            /* Process Login Credentials */

            String username, password;

            username = request.getParameter("username");
            password = request.getParameter("password");

            HttpSession session = request.getSession();

            ValidateUser vu = new ValidateUser();
            boolean isValid = vu.isValidUser(username, password);

            if (isValid) {
                CreateSession cs = new CreateSession();
                cs.setSession(username, password, session);
                response.sendRedirect("/cpsm/dashboard");
                System.out.println("login - YES!!!!");
            } else {
                session.setAttribute("loggedIn", "false");
                session.setAttribute("loginError", "true");
                response.sendRedirect("/cpsm/login");
                System.out.println("login - Fail!!!!");
            }
        } else if (register != null) {

            /* Process Registration Form */

            String firstname, lastname, username, password, email, school;

            firstname = request.getParameter("fname");
            lastname = request.getParameter("lname");
            username = request.getParameter("username");
            password = request.getParameter("password");
            email = request.getParameter("email");
            school = request.getParameter("school");

            HttpSession session = request.getSession();

            RegisterUser ru = new RegisterUser();
            int isRegistered = ru.createUser(firstname, lastname, username, password, email, school);

            if (isRegistered == 0) {
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("regError", isRegistered);
                response.sendRedirect("/cpsm/login");
                System.out.println("register - YES!!!!");
            } else {
                session.setAttribute("regError", isRegistered);
                response.sendRedirect("/cpsm/login");
                System.out.println("register - OH NO!!!!");
            }
        } else if (recover != null) {
            
            String email = request.getParameter("email");
            
            HttpSession session = request.getSession();
            
            Email recoverEmail = new Email();
            boolean recovery = recoverEmail.sendRecoverEmail(email);
            
            if (!recovery) {
                session.setAttribute("error", "There was a problem retrieving your password.");
                response.sendRedirect("/cpsm/login");
            } else {
                session.setAttribute("error", "An email with your password has been sent to the address you provided.");
                response.sendRedirect("/cpsm/login");
            }
            
        } else if (updateUserData != null) {

            String username, school, email;

            HttpSession session = request.getSession();

            username = session.getAttribute("username").toString();
            school = request.getParameter("school");
            email = request.getParameter("email");

            if (school.isEmpty()) {
                school = session.getAttribute("school").toString();
            }
            if (email.isEmpty()) {
                email = session.getAttribute("email").toString();
            }	

            UpdateProfile up = new UpdateProfile();
            boolean update = up.updateUserInfo(username, school, email);

            if (update){
                session.setAttribute("email", email);
                session.setAttribute("school", school);
                session.setAttribute("error", "Your information was successfully updated.");
            } else {
                session.setAttribute("error", "There was a problem updating your profile information.");
            }

            response.sendRedirect("/cpsm/dashboard");

        } else if (changePassword != null) {

            String username, oldPassword, newPassword;

            HttpSession session = request.getSession();

            username = session.getAttribute("username").toString();
            oldPassword = request.getParameter("oldPassword");
            newPassword = request.getParameter("newChangePassword");

            ChangePassword cp = new ChangePassword();
            String change = cp.passwordChange(username, oldPassword, newPassword);

            if (!change.isEmpty() && change != null) {
                session.setAttribute("error", change);
            }

            response.sendRedirect("/cpsm/dashboard");
            
        } else if (createConference != null) {
            
            String username, conferenceName, school, date, deadline, reviewerOne, reviewerTwo, reviewerThree, reviewerFour, reviewerFive;
            
            HttpSession session = request.getSession();
            
            username = session.getAttribute("username").toString();
            conferenceName = request.getParameter("conferenceName");
            school = request.getParameter("school");
            date = request.getParameter("date") + " 08:00:00";
            deadline = request.getParameter("deadline") + " 23:59:59";
            if (request.getParameter("reviewerOne") != "" && request.getParameter("reviewerOne") != null) {
                reviewerOne = request.getParameter("reviewerOne");
            } else {
                reviewerOne = "";
            }
            if (request.getParameter("reviewerTwo") != "" && request.getParameter("reviewerTwo") != null) {
                reviewerTwo = request.getParameter("reviewerTwo");
            } else {
                reviewerTwo = "";
            }
            if (request.getParameter("reviewerThree") != "" && request.getParameter("reviewerThree") != null) {
                reviewerThree = request.getParameter("reviewerThree");
            } else {
                reviewerThree = "";
            }
            if (request.getParameter("reviewerFour") != "" && request.getParameter("reviewerFour") != null) {
                reviewerFour = request.getParameter("reviewerFour");
            } else {
                reviewerFour = "";
            }
            if (request.getParameter("reviewerFive") != "" && request.getParameter("reviewerFive") != null) {
                reviewerFive = request.getParameter("reviewerFive");
            } else {
                reviewerFive = "";
            }
            
            CreateConference cc = new CreateConference();
            boolean created = cc.newConference(conferenceName, username, school, deadline, date, reviewerOne, reviewerTwo, reviewerThree, reviewerFour, reviewerFive);
            
            if (created) {
                session.setAttribute("error", "Conference created successfully!");
            } else {
                session.setAttribute("error", "There was a problem creating the conference.");
            }
            
            response.sendRedirect("/cpsm/dashboard");
            
        } else if (request.getContentType().startsWith("multipart/form-data")) {
            
            System.out.println("Here!");
            
            HttpSession session = request.getSession();
            String filename, filenameReview = "";
            String path, pathReview = "";
            File file, fileReview = null;
            String author = session.getAttribute("username").toString();
            int conferenceID = 0;
            String submit = null;
            String change = "";

            //submitPaper
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (isMultiPart) {
          
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletContext servletContext = this.getServletConfig().getServletContext();
                File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                factory.setRepository(repository);
                ServletFileUpload upload = new ServletFileUpload(factory);

                try {

                    List<FileItem> items = upload.parseRequest(request);
                    Iterator<FileItem> iter = items.iterator();

                    while (iter.hasNext()) {
                        FileItem item = iter.next();

                        if (item.isFormField()) {
                            String fieldName = item.getFieldName();
                            System.out.println(fieldName);
                            if (fieldName.contains("conferenceSelection")) {
                                conferenceID = Integer.parseInt(item.getString());
                                System.out.println(conferenceID);
                            }
                        } else {
                            String originalName = item.getName();
                            submit = item.getFieldName();
                            filename = author + "-" + originalName;
                            System.out.println("File Submit: " + submit);
                            try {
                                if (submit.contains("reviewPaper")) {
                                    System.out.println("REVIEW TIME!");
                                    filenameReview = "REVIEWED-" + originalName;
                                    pathReview = getServletContext().getRealPath("/")+File.separator+"papers/"+filenameReview;
                                    fileReview = new File(pathReview);
                                    item.write(fileReview);
                                    ReviewPaper up = new ReviewPaper();
                                    change = up.paperReview(originalName, filenameReview, author, conferenceID);
                                    if (change.isEmpty()) {
                                        Email email = new Email();
                                        email.sendReviewEmail(filenameReview);
                                    }
                                } else if (submit.contains("submitPaper")) {
                                    System.out.println("UPLOAD TIME!");
                                    path = getServletContext().getRealPath("/")+File.separator+"papers/"+filename;
                                    file = new File(path);
                                    item.write(file);
                                    UploadPaper up = new UploadPaper();
                                    change = up.paperUpload(filename, author, conferenceID);
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(UserAccessCtrl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (!change.isEmpty() && change != null) {
                            session.setAttribute("error", change);
                        }
                    }

                } catch (FileUploadException ex) {
                    Logger.getLogger(UserAccessCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (!change.isEmpty() && change != null) {
                    session.setAttribute("error", change);
                } else {
                    session.setAttribute("error", "Your paper was successfully submitted.");
                    response.sendRedirect("/cpsm/dashboard");
                }
            }

        } else {
            System.out.println("Wrong Input");
        }
    }
}
