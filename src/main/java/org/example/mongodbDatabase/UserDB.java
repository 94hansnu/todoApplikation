package org.example.mongodbDatabase;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.example.model.Todo;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
   private MongoCollection<Document> collection;

    public UserDB(){
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        collection = database.getCollection("users");
    }
    public void save(User user){
        Document userDocument = new Document("id", user.getId())
                .append("name", user.getName())
                .append("age", user.getAge())
                .append("todos", convertToTodoDocuments(user.getTodos()));
        collection.insertOne(userDocument);
    }
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
    public void update(User user){
        Document query = new Document("id", user.getId());
        Document update = new Document("$set", new Document("name", user.getName())
                .append("age", user.getAge())
                .append("todos", convertToTodoDocuments(user.getTodos())));
        collection.updateOne(query, update);
    }
    public void delete(String id){
        Document query = new Document("id", id);
        DeleteResult result = collection.deleteOne(query);
        if (result.getDeletedCount() == 0){
            throw new IllegalArgumentException("Ingen användare hittades med det angivna id:t.");
        }else {
            System.out.println("Användaren raderad! ");
        }
    }
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
