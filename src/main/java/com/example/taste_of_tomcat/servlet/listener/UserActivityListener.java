package com.example.taste_of_tomcat.servlet.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserActivityListener implements HttpSessionListener {

    private static final Logger logger = LogManager.getLogger(UserActivityListener.class.getName());

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String session_email = se.getSession().getAttribute("email").toString();
        logger.info("A session is destroyed for user with email: " + session_email);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("A new session created");
    }
}