package com.example.firsttomcat.servlets;

import com.example.firsttomcat.servlets.model.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;

public class DatabaseUtil {
    private static final Logger logger = LogManager.getLogger(DatabaseUtil.class);
    private static MongoClient connection = MongoDBConnection.getInstance();

    public void saveUser(String username, String password, String email) {
        MongoDatabase database = connection.getDatabase("data_base");

        MongoCollection<Document> collection = database.getCollection("users");

        Document user = new Document("username", username).append("password", password).append("email", email);

        collection.insertOne(user);
    }
    public FindIterable<Document> getUsers() {
        MongoDatabase database = connection.getDatabase("data_base");

        MongoCollection<Document> collection = database.getCollection("users");

        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
        return collection.find();
    }

    public List<User> getUsersList(){
        List<User> users = null;

        for (Document doc : this.getUsers()) {
            users.add(new User(doc.getString("username"), doc.getString("password"), doc.getString("email")));
        }

        if (users == null || users.isEmpty()) {
            logger.error("No users found");
        }
        return users;

    }

    public void saveUser(Document document) {
        MongoDatabase database = connection.getDatabase("data_base");

        MongoCollection<Document> collection = database.getCollection("users");

        collection.insertOne(document);
    }
}