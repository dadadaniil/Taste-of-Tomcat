package com.example.taste_of_tomcat.service;

import jakarta.servlet.http.HttpServletRequest;

public interface SessionService {
    void createSession(HttpServletRequest request, String email);
}
