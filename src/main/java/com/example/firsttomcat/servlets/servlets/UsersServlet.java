package com.example.firsttomcat.servlets.servlets;

import com.example.firsttomcat.servlets.DatabaseUtil;
import com.example.firsttomcat.servlets.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "usersServlet", value = "/users-servlet")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseUtil dbUtil = new DatabaseUtil();
        List<User> users = dbUtil.getUsersList();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/users.jsp");
        dispatcher.forward(request, response);
    }
}
