package com.example.firsttomcat.servlet.service;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;

public class EmailUtility {

    public static void sendConfirmationEmail(String to, String text) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // Gmail SMTP host
        mailSender.setPort(587); // Port for TLS
        mailSender.setUsername("stconfmail@gmail.com"); // Your Gmail address
        mailSender.setPassword("&(sf^2hj*jd.-1"); // Your app-specific password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Use TLS
        props.put("mail.debug", "true"); // Optional: for logging

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Platform verification code");
        message.setText(text);

        mailSender.send(message);
    }
}
