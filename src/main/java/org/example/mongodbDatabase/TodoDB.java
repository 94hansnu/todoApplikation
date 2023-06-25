package org.example.mongodbDatabase;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.example.model.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Denna klassen TodoDB hanterar interaktionen med databasen för att spara, hämta, uppdatera och ta bort todo-objekt.
 */
public class TodoDB {

   /**
    * En instansvariabel för att hantera samlingen av dokument i databasen som representerar todos.
    */
   private MongoCollection<Document> collection;

   /**
    * Konstruktor för klassen och skapaer en anslutning till databasen genom att hämta en instans av MongoDatabase
    * från MongoDBConnection. Sedan hämtas samlingen med namnet todos från databasen och tilldelas till
    * instansvariabeln collection.
    */
   public TodoDB(){
      MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
      collection = database.getCollection("todos");
   }

   /**
    * Metod för att spara en todo-uppgift i databasen. Den tar emot en instans av Todo som argument och skapar ett
    * dokument baserat på todo objektets egenskaper. Dokumentet läggs i samligen genom att använda insertOne-metoden
    * @param todo
    */
   public void save (Todo todo){
      Document todoDocument = new Document("id", todo.getId())
              .append("text", todo.getText())
              .append("done", todo.isDone())
              .append("assignedTo", todo.getAssignedTo());
      collection.insertOne(todoDocument);
   }

   /**
    * Metod för att hitta en todo-uppgift i databasen baserat på dess id. En fråga skapas för att matcha dokumentet med
    * det angivna idet. Om ett matchande dokument hittas, extraheras egenskaperna text, done och assignedTo från
    * dokumentet och en ny instans av todo skapas och returneras. Annars returneras null.
    * @param id
    * @return
    */
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

   /**
    * Metod för att hämta alla todo-uppgifterna från databasen. En tom lista av Todo-objekt skapas. Sedan itereras över
    * varje dokument i samlingen och egneksaperna id, text, done, assignedTo extraheras från varje dokument och används
    * för att skapa en ny instans av todo som läggs till i listan. Till slut returneras listan med alla Todo-objekt.
    * @return
    */
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

   /**
    * Metod används för att uppdatera en befintlig todo-uppgift i databasen. En fråga skapas för att matcha dokumentet
    * med det angivna idet. sedan skapas ett uppdateringsdokument med hjälp av $set-operatören för att specifiera de
    * nya värdena för egenskaperna text, done och assignedTo. Metoden används för att utföra matchande uppdateringar.
    * @param todo
    */
   public void update(Todo todo){
      Document query = new Document("id", todo.getId());
      Document update = new Document("$set", new Document("text", todo.getText())
              .append("done", todo.isDone())
              .append("assignedTo", todo.getAssignedTo()));
      collection.updateOne(query, update);
   }

   /**
    * Metoden används för att ta bort en todo-uppgift från databasen baserat på dess id. En fråga skapas för att matcha
    * dokument med det angivna idet. Metoden används för att ta bort matchande dokumentet. Om ingen todo hittas kastas
    * ett undantag. Annars skrivs ett meddelande ut att Todo har raderats.
    * @param id
    */
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
