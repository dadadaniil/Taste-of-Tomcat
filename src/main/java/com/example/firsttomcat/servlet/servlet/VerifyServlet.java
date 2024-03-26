package com.example.firsttomcat.servlet.servlet;

import com.example.firsttomcat.servlet.model.DatabaseUtil;
import com.example.firsttomcat.servlet.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "verifyServlet", value = "/verify-servlet")
public class VerifyServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();
    private DatabaseUtil databaseUtil = new DatabaseUtil();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, IOException {
        logger.info("VerifyServlet doPost");

        response.setContentType("text/html");

        String email = request.getParameter("email");
        String code = request.getParameter("code");

        if (databaseUtil.checkVerificationCode(email, code)) {
            logger.info("User with email " + email + " verified");
            response.sendRedirect("pages/success.jsp");
        } else {
            logger.info("Verification failed for user with email " + email);
            request.setAttribute("errorMessage", "Verification failed");
            request.getRequestDispatcher("pages/error.jsp").forward(request, response);
        }
    }
}