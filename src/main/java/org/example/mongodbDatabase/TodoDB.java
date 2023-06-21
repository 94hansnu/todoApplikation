package org.example.mongodbDatabase;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoDB {
   MongoClient mongoClient;
   MongoDatabase database;
   MongoCollection<Document> collection;

   public TodoDB(){
      mongoClient = MongoClients.create();
      database = mongoClient.getDatabase("mongodb://localhost:27017");
      collection = database.getCollection("todos");
   }

   public void save (Todo todo){
      Document todoDocument = new Document("id", todo.getId())
              .append("text", todo.getText())
              .append("done", todo.isDone())
              .append("assignedTo", todo.getAssignedTo());
      collection.insertOne(todoDocument);
   }

   public Todo findById(String id){
      Document query = new Document("id", id);
      Document todoDocument = collection.find(query).first();

      if (todoDocument !=null){
         String text = todoDocument.getString("text");
         boolean done = todoDocument.getBoolean("done");
         String assignedTo = todoDocument.getString("assignedTo");
         return new Todo(id, text, done, assignedTo);
      }
      return null;
   }

   public List<Todo> findAll(){
      List<Todo> todos = new ArrayList<>();
      for (Document todoDocument: collection.find()){
         String id = todoDocument.getString("id");
         String text = todoDocument.getString("text");
         boolean done = todoDocument.getBoolean("done");
         String assignedTo = todoDocument.getString("assignedTo");

         Todo todo = new Todo(id, text, done, assignedTo);
         todos.add(todo);
      }
      return todos;
   }

   public void update(Todo todo){
      Document query = new Document("id", todo.getId());
      Document update = new Document("$set", new Document("text", todo.getText())
              .append("done", todo.isDone())
              .append("assignedTo", todo.getAssignedTo()));
      collection.updateOne(query, update);
   }

   public void delete(String id){
      Document query = new Document("id", id);
      collection.deleteOne(query);
   }
}
