package com.example.firsttomcat.servlets.servlets;

import com.example.firsttomcat.servlets.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register-servlet")
public class RegisterServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException {
        logger.info("RegisterServlet doPost");

        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (userService.userExists(email)) {
            logger.info("User with email " + email + " already exists");
            request.setAttribute("errorMessage", "User with email " + email + " already exists");
            request.getRequestDispatcher("pages/error.jsp").forward(request, response);
        } else {
            userService.register(username, password, email);
            logger.info("User with email " + email + " registered");
            response.sendRedirect("pages/success.jsp");
        }
    }
}