package com.example.firsttomcat.servlet.servlet;

import com.example.firsttomcat.servlet.model.DatabaseUtil;
import com.example.firsttomcat.servlet.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "usersServlet", value = "/users-servlet")
public class UsersServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MultiplyServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseUtil dbUtil = new DatabaseUtil();
        List<User> users = dbUtil.getUsersList();

        logger.info("Fetched " + users.size() + " users from the database");

        request.getSession().setAttribute("usersList", users);
        response.sendRedirect(request.getContextPath() + "/pages/users.jsp");
    }
}