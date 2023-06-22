package org.example.mongodbDatabase;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static final String DATABASE_HOST = "localhost";
    private static final int DATABASE_PORT = 27017;
    private static final String DATABASE_NAME= "todoApplikation";

    private static MongoDBConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private MongoDBConnection(){
        String connectionString = String.format("mongodb://%s:%d", DATABASE_HOST, DATABASE_PORT);
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(DATABASE_NAME);
    }
    public static MongoDBConnection getInstance(){
        if (instance == null){
            instance = new MongoDBConnection();
        }
        return instance;
    }
    public MongoDatabase getDatabase(){
        return database;
    }
}
