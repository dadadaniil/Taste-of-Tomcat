package com.example.firsttomcat.servlet.service;

import com.example.firsttomcat.servlet.model.DatabaseUtil;
import com.example.firsttomcat.servlet.model.User;
import jakarta.mail.MessagingException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;

public class UserService {
    private DatabaseUtil databaseUtil = new DatabaseUtil();

    public User authenticate(String email, String password) {
        User user = databaseUtil.findUserByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean userExists(String email) {
        User user = databaseUtil.findUserByEmail(email);
        return user != null;
    }


    public void register(String username, String password, String email) throws MessagingException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(username, hashedPassword, email);
        databaseUtil.saveUser(user.toDocument());

        String verificationCode = generateVerificationCode();
        databaseUtil.saveVerificationCode(email, verificationCode);
        EmailUtility.sendConfirmationEmail(email, verificationCode);
    }

    private String generateVerificationCode() {
        // Generate a random 6-digit code
        return String.format("%06d", new Random().nextInt(999999));
    }

}