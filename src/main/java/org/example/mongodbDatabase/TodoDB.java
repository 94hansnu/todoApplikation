package org.example.mongodbDatabase;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.example.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoDB {
   private MongoCollection<Document> collection;

   public TodoDB(){
      MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
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
   public void delete(String id) {
      Document query = new Document("id", id);
      DeleteResult result = collection.deleteOne(query);

      if (result.getDeletedCount()==0) {
         throw new IllegalArgumentException("Ingen Todo hittades med det angivna id:t.");
      } else {
         System.out.println("Todo raderad!");
      }
   }
}
