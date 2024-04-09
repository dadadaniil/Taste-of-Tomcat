package com.example.firsttomcat.servlet;

import com.example.firsttomcat.model.User;
import com.example.firsttomcat.model.impl.DatabaseUtilImpl;
import com.example.firsttomcat.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Optional;

@WebServlet(name = "accountServlet", value = "/account-servlet")
public class AccountServlet extends HttpServlet {
    private final UserServiceImpl userServiceImpl = new UserServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        String newUsername = request.getParameter("newUsername");
        String letterText = request.getParameter("letterToJoBiden");

        String email = (String) request.getSession().getAttribute("email");

        switch (action) {
            case "Change Username":
                userServiceImpl.changeUsername(email, newUsername);
                request.setAttribute("successMessage", "Username has been successfully updated.");
                request.getRequestDispatcher("/pages/account.jsp").forward(request, response);
                break;
            case "Send letter":
                userServiceImpl.sendLetter(email, letterText);
                request.setAttribute("successMessage", "Letter has been successfully sent.");
                request.getRequestDispatcher("/pages/account.jsp").forward(request, response);
                break;
            case "Logout":
                request.getSession().invalidate();
                request.setAttribute("successMessage", "You have been successfully logged out.");
                request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
                return;
            case "Delete Account":
                userServiceImpl.deleteAccount(email);
                request.setAttribute("successMessage", "Your account has been successfully deleted, you can create a new one");
                request.getSession().invalidate();
                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String userEmail = (String) request.getSession().getAttribute("email");
        DatabaseUtilImpl databaseUtil = new DatabaseUtilImpl();
        User user = databaseUtil.findUserByEmail(userEmail);
        request.setAttribute("user", user);

        Optional<InputStream> optionalImageStream = databaseUtil.downloadFile(userEmail);

        if (optionalImageStream.isPresent()) {
            InputStream imageStream = optionalImageStream.get();
            byte[] imageBytes = IOUtils.toByteArray(imageStream);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            request.setAttribute("userImage", base64Image);
        }

        request.getRequestDispatcher("/pages/account.jsp").forward(request, response);
    }
}