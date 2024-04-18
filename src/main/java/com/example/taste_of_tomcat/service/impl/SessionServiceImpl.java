package com.example.taste_of_tomcat.service.impl;

import com.example.taste_of_tomcat.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionServiceImpl implements SessionService {
    @Override
    public void createSession(HttpServletRequest request, String email) {
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
    }
}