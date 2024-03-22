package com.example.firsttomcat.servlets.service;

import com.example.firsttomcat.servlets.DatabaseUtil;
import com.example.firsttomcat.servlets.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private DatabaseUtil databaseUtil = new DatabaseUtil();

    public User authenticate(String email, String password) {
        User user = databaseUtil.getUserByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public void register(String username, String password, String email) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(username, hashedPassword, email);
        databaseUtil.saveUser(user.toDocument());
    }

    public boolean userExists(String email) {
        User user = databaseUtil.getUserByEmail(email);
        return user != null;
    }

}