package com.example.taste_of_tomcat.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "localization-servlet", value = "/localization-servlet")
public class LocalizationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = request.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("localization.messages", locale);

        request.setAttribute("bundle", bundle);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}