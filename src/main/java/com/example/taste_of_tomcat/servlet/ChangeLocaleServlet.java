package com.example.taste_of_tomcat.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "changeLocaleServlet", value = "/change-locale-servlet")
public class ChangeLocaleServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String selectedLocale = request.getParameter("locale");
        Locale locale = new Locale(selectedLocale);
        request.getSession().setAttribute("locale", locale);
        response.sendRedirect(request.getHeader("Referer"));
    }
}