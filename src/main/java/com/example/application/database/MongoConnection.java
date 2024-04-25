package com.example.application.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoConnection{
    private static MongoConnection instance = null;

    private final MongoClient mongoClient;
    private final MongoDatabase database;

    private MongoConnection(String dbName){
        try{
            mongoClient = MongoClients.create("mongodb://localhost:27017/");
            database = mongoClient.getDatabase(dbName);
            System.out.println("=> MongoDB Connection Successful");
        } catch (Exception e){
            throw new RuntimeException("Error Connecting to Mongo: " + e.getMessage());
        }
    }

    public static synchronized MongoConnection getInstance(String dbName){
        if(instance == null){
            instance = new MongoConnection(dbName);
        }
        return instance;
    }

    public MongoCollection<Document> getCollection(String collectionName){
        return database.getCollection(collectionName);
    }
}
