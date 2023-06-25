package org.example.mongodbDatabase;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.example.model.Todo;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Denna klassen hanterar interaktionen med databasen för att spara, hämta, uppdatera och ta bort USer-objekt
 */
public class UserDB {
    /**
     * Denna instansvariabel används för att hantera samlingen av dokument i databasen som representerar users.
     */
   private MongoCollection<Document> collection;

    /**
     * Konstruktor för klassen som skapar en asnlutning till databasen genom att hämta instans av MongoDatabase från
     * MongoDBConnection. Sedan hämtas samlingen emd namnet users från databasen och tilldelas till
     * instansvariabeln collection.
     */
    public UserDB(){
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        collection = database.getCollection("users");
    }

    /**
     * Metoden används för att spara en user i databasen. Den tar emot en instans av user som argument och skapar
     * dokument baserat på user-objektets egenskaper. Egenskapen todos innehåller en konverterad lista av
     * todo-objekt till dokument. Dokumentet läggs sedan till genom att använda insertOne metoden
     * @param user
     */
    public void save(User user){
        Document userDocument = new Document("id", user.getId())
                .append("name", user.getName())
                .append("age", user.getAge())
                .append("todos", convertToTodoDocuments(user.getTodos()));
        collection.insertOne(userDocument);
    }

    /**
     * Metoden används för att hitta user i databasen baserat på dess id. En fråga skapas för att matcha dokumentet med
     * det angivna idet. Om ett matchande dokument hittas, extraheras egenskaperna name, age och todos från dokumentet.
     * Egenskapen todos konverteras till en lista av todo-objekt genom att använda ConvertToTodos. En ny instans av user
     * skapas med de extraherade egenskaperna och returneras. om ingen matchande USer hittas returneras null.
     * @param id
     * @return
     */
    public User findById(String id){
        Document query = new Document("id", id);
        Document userDocument = collection.find(query).first();
        if (userDocument != null){
            String name = userDocument.getString("name");
            int age = userDocument.getInteger("age");
            List<Todo> todos = convertToTodos(userDocument.getList("todos", Document.class));
            return new User(id, name, age, todos);
        }
        return null;
    }

    /**
     * Metoden används för att hämta alla users från databasen. En tom lista av user objekt skapas. Sedan itereras över
     * varje dokument i samlingen och egenskaperna id, name, age och todos extraheras från varje dokument. Egenskapen
     * todos konverteras till en lista av todo-objekt genom att använda ConvertToTodos. En ny instans av user skapas med
     * de extraherade egenskaperna och läggs till i listan. Till slut returneras listan med alla user-objekt.
     * @return
     */
    public List<User> findAll(){
        List<User> users = new ArrayList<>();

        for (Document userDocument : collection.find()){
            String id = userDocument.getString("id");
            String name = userDocument.getString("name");
            int age = userDocument.getInteger("age");
            List<Todo> todos = convertToTodos(userDocument.getList("todos", Document.class));

            User user = new User(id, name, age, todos);
            users.add(user);
        }
        return users;
    }

    /**
     * Metoden används för att uppdatera en befintlig user i databasen. En fråga skapas för att matcha dokumentet med
     * det angivna idet. Sedan skapas ett uppdateringsdokument med hjälp av $set-operatören för att specificera de nya
     * värdena för egenskaperna name, age och todos. Metoden används för att utföra uppdateringen på det matchande
     * dokumentet.
     * @param user
     */
    public void update(User user){
        Document query = new Document("id", user.getId());
        Document update = new Document("$set", new Document("name", user.getName())
                .append("age", user.getAge())
                .append("todos", convertToTodoDocuments(user.getTodos())));
        collection.updateOne(query, update);
    }

    /**
     * Metoden används för att ta bort en user från databasen baserat på dess id. En fråga skapas för att matcha
     * dokumentet med det angivna idet. Metoden används för att ta bort det matchande dokumentet. om ingen user hittas
     * med det angivna idet kastas ett undantag. Annars skriv ett meddelande ut att användaren har raderats.
     * @param id
     */
    public void delete(String id){
        Document query = new Document("id", id);
        DeleteResult result = collection.deleteOne(query);
        if (result.getDeletedCount() == 0){
            throw new IllegalArgumentException("Ingen användare hittades med det angivna id:t.");
        }else {
            System.out.println("Användaren raderad! ");
        }
    }

    /**
     * Detta är en hjälpmetod för att konvertera en lista av todo-objekt till en lista av dokument. En tom lista av
     * dokument skapas sendan itereras över varje todo i listan och egenskaperna id, text, done och assignedTo för varje
     * todo läggs till i ett nytt dokument. Detta dokumentet läggs sedan till i listan. Till slut returneras listan med
     * dokument.
     * @param todos
     * @return
     */
    private List<Document> convertToTodoDocuments(List<Todo> todos){
        List<Document> todoDocuments = new ArrayList<>();

        for (Todo todo : todos){
            Document todoDocument = new Document("id", todo.getId())
                    .append("text", todo.getText())
                    .append("done", todo.isDone())
                    .append("assignedTo", todo.getAssignedTo());
            todoDocuments.add(todoDocument);
        }
        return todoDocuments;
    }

    /**
     * Detta är en hjälpmetod som används för att konvertera en lista av dokument till en lista av todo-objekt. En tom
     * lista av todo-objekt skapas. Sedan itereras över varje dokument i listan av dokument och egenskaperna id, text,
     * done och assignedTo för varje dokument extraheras. En ny instans av Todo skapas med de extraherade egenskaperna
     * och läggs till i listan av todo-objekt. Till slut returneras listan med todo-objekt
     * @param todoDocuments
     * @return
     */
    private List<Todo> convertToTodos(List<Document> todoDocuments){
        List<Todo> todos = new ArrayList<>();
        for (Document todoDocument : todoDocuments){
            String id = todoDocument.getString("id");
            String text = todoDocument.getString("text");
            boolean done = todoDocument.getBoolean("done");
            String assignedTo = todoDocument.getString("assignedTo");

            Todo todo = new Todo(id, text, done, assignedTo);
            todos.add(todo);
        }
        return todos;
    }
}
