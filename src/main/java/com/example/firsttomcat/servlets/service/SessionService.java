package com.example.firsttomcat.servlets.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionService {
    public void createSession(HttpServletRequest request, String email) {
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
    }
}