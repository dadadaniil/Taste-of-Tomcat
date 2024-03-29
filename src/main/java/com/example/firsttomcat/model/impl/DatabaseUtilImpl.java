package com.example.firsttomcat.model.impl;

import com.example.firsttomcat.model.DatabaseUtil;
import com.example.firsttomcat.model.MongoDBConnection;
import com.example.firsttomcat.model.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtilImpl implements DatabaseUtil {
    private static final String DATABASE_NAME = "data_base";

    private static final String USERS_COLLECTION_NAME = "users";
    private static final String VERIFICATION_CODES_COLLECTION_NAME = "verification_codes";
    private static final String LETTERS_COLLECTION_NAME = "letters";

    private static final Logger logger = LogManager.getLogger(DatabaseUtilImpl.class);
    private static final MongoClient connection = MongoDBConnection.getInstance();

    @Override
    public void saveUser(String username, String password, String email) {
        MongoDatabase database = connection.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION_NAME);
        Document user = new Document("username", username).append("password", password).append("email", email);
        collection.insertOne(user);
    }

    @Override
    public FindIterable<Document> receiveUsers() {
        MongoDatabase database = connection.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION_NAME);
        return collection.find();
    }

    @Override
    public List<User> receiveUsersList() {
        List<User> users = new ArrayList<>();
        for (Document doc : this.receiveUsers()) {
            users.add(new User(doc.getString("username"), doc.getString("password"), doc.getString("email")));
        }
        if (users.isEmpty()) {
            logger.error("No users found");
        }
        return users;
    }


    @Override
    public void saveUser(Document document) {
        MongoDatabase database = connection.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION_NAME);
        collection.insertOne(document);
    }

    @Override
    public User findUserByEmail(String email) {
        MongoDatabase database = connection.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION_NAME);
        Document query = new Document("email", email);
        Document user = collection.find(query).first();

        if (user != null) {
            return new User(user.getString("username"), user.getString("password"), user.getString("email"));
        } else {
            logger.error("User with email " + email + " not found");
            return null;
        }
    }

    @Override
    public void deleteUserByEmail(String email) {
        MongoDatabase database = connection.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION_NAME);
        Document query = new Document("email", email);
        collection.deleteOne(query);
    }


    @Override
    public void saveVerificationCode(String email, String verificationCode) {
        MongoDatabase database = connection.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(VERIFICATION_CODES_COLLECTION_NAME);
        Document code = new Document("email", email).append("code", verificationCode);
        collection.insertOne(code);
    }

    @Override
    public boolean checkVerificationCode(String email, String verificationCode) {
        MongoDatabase database = connection.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(VERIFICATION_CODES_COLLECTION_NAME);
        Document query = new Document("email", email).append("code", verificationCode);
        Document code = collection.find(query).first();
        return code != null;
    }

    @Override
    public void updateUsername(String email, String newUsername) {
        MongoDatabase database = connection.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION_NAME);
        Document query = new Document("email", email);
        Document update = new Document("$set", new Document("username", newUsername));
        collection.updateOne(query, update);
    }

    @Override
    public void saveLetterToJoBiden(String email, String letterText) {
        MongoDatabase database = connection.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(LETTERS_COLLECTION_NAME);
        Document letter = new Document("email", email)
                .append("heading", "Dear Mr. President")
                .append("letter", letterText);
        logger.info("Letter from user with email " + email + " was sent to Joe Biden");
        collection.insertOne(letter);
    }
}