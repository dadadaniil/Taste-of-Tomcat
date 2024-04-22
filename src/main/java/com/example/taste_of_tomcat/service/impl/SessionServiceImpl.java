package com.example.taste_of_tomcat.service.impl;

import com.example.taste_of_tomcat.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Locale;

public class SessionServiceImpl implements SessionService {
    @Override
    public void createSession(HttpServletRequest request, String email) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute("locale");
        
        session.setAttribute("locale", locale.getLanguage());
        session.setAttribute("email", email);
    }
}