package com.example.firsttomcat.servlets.servlets;

import com.example.firsttomcat.servlets.DatabaseUtil;
import com.example.firsttomcat.servlets.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register-servlet")
public class RegisterServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MultiplyServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {

        response.setContentType("text/html");


        User user = new User(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));


        DatabaseUtil databaseUtil = new DatabaseUtil();
        databaseUtil.saveUser(user.toDocument());
        logger.info("User with email" + user.getEmail() + " registered");

        response.sendRedirect("pages/success.jsp");
    }
}
