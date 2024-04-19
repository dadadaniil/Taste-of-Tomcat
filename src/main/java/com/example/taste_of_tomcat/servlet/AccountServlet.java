package com.example.taste_of_tomcat.servlet;

import com.example.taste_of_tomcat.model.User;
import com.example.taste_of_tomcat.model.impl.DatabaseUtilImpl;
import com.example.taste_of_tomcat.service.impl.UserServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

@WebServlet(name = "accountServlet", value = "/account-servlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AccountServlet extends HttpServlet {

    static final String PARAM_ACTION = "action";
    static final String PARAM_NEW_USERNAME = "newUsername";
    static final String PARAM_LETTER_TO_JO_BIDEN = "letterToJoBiden";
    static final String ATTR_EMAIL = "email";
    static final String ATTR_SUCCESS_MESSAGE = "successMessage";
    static final String ACTION_CHANGE_USERNAME = "Change Username";
    static final String ACTION_SEND_LETTER = "Send letter";
    static final String ACTION_LOGOUT = "Logout";
    static final String ACTION_DELETE_ACCOUNT = "Delete Account";
    static final String ACTION_UPLOAD_AVATAR = "Upload Avatar";

    private final UserServiceImpl userServiceImpl = new UserServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String action = request.getParameter(PARAM_ACTION);
        String newUsername = request.getParameter(PARAM_NEW_USERNAME);
        String letterText = request.getParameter(PARAM_LETTER_TO_JO_BIDEN);

        String email = (String) request.getSession().getAttribute(ATTR_EMAIL);

        switch (action) {
            case ACTION_CHANGE_USERNAME:
                userServiceImpl.changeUsername(email, newUsername);
                request.getSession().setAttribute(ATTR_SUCCESS_MESSAGE, "Username has been successfully updated.");
                response.sendRedirect(request.getContextPath() + "/account-servlet");
                return;
            case ACTION_SEND_LETTER:
                userServiceImpl.sendLetter(email, letterText);
                request.setAttribute(ATTR_SUCCESS_MESSAGE, "Letter has been successfully sent.");
                response.sendRedirect(request.getContextPath() + "/account-servlet");
//                request.getRequestDispatcher("/pages/account.jsp").forward(request, response);
                return;
            case ACTION_LOGOUT:
                request.getSession().invalidate();
                request.setAttribute(ATTR_SUCCESS_MESSAGE, "You have been successfully logged out.");
                request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
                return;
            case ACTION_DELETE_ACCOUNT:
                userServiceImpl.deleteAccount(email);
                request.setAttribute(ATTR_SUCCESS_MESSAGE, "Your account has been successfully deleted, you can create a new one");
                request.getSession().invalidate();
                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
                return;
            case ACTION_UPLOAD_AVATAR:
                Part filePart = request.getPart("avatar");
                userServiceImpl.uploadAvatar(filePart.getInputStream(), email);
                request.getSession().setAttribute(ATTR_SUCCESS_MESSAGE, "Avatar has been successfully uploaded.");
                response.sendRedirect(request.getContextPath() + "/account-servlet");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String userEmail = (String) request.getSession().getAttribute(ATTR_EMAIL);
        DatabaseUtilImpl databaseUtil = new DatabaseUtilImpl();
        User user = databaseUtil.findUserByEmail(userEmail);
        request.setAttribute("user", user);

        Optional<InputStream> optionalImageStream = databaseUtil.downloadFile(userEmail);

        if (optionalImageStream.isPresent()) {
            InputStream imageStream = optionalImageStream.get();
            byte[] imageBytes = IOUtils.toByteArray(imageStream);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            request.setAttribute("userImage", base64Image);
        } else {
            ServletContext context = getServletContext();
            String fullPath = context.getRealPath("/images/avatar.jpg");
            Path path = Paths.get(fullPath);
            byte[] imageBytes = Files.readAllBytes(path);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            request.setAttribute("userImage", base64Image);
        }

        request.getRequestDispatcher("/pages/account.jsp").forward(request, response);
        request.getSession().removeAttribute(ATTR_SUCCESS_MESSAGE);
    }
}