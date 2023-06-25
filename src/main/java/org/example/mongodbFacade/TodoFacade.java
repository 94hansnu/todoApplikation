package org.example.mongodbFacade;

import org.example.model.Todo;
import org.example.mongodbDatabase.TodoDB;

import java.util.List;

/**
 * Klassen fungerar som ett gränssnitt mellan applikationens logik och databaslagret todoDB. Den har referens till en
 * instans av TodoDB-klassen och används för att utföra operationer relaterade till todo-objekt
 */
public class TodoFacade {
    TodoDB todoDB;

    public TodoFacade(TodoDB todoDB){
        this.todoDB= todoDB;
    }

    /**
     * Metoden används för att skapa en ny Todo. Den tar emot ett Todo-objekt och använder TodoDB-objektet för att spara
     * det i databasen genom att anropa save-metoden i TodoDB.
     * @param todo
     */
    public void createTodo(Todo todo){
        todoDB.save(todo);
    }

    /**
     * Metoden används för att hämta en Todo från databasen baserat på dess ID. Den tar emot ett ID som parameter och
     * använder TodoDB-objektet för att söka efter den matchande Todo genom att anropa findById-metoden i TodoDB.
     * Returnerar den hittade Todo.
     * @param id
     * @return
     */
    public Todo getTodoById(String id){
        return todoDB.findById(id);
    }

    /**
     * Metoden används för att hämta alla Todo från databasen. Den använder TodoDB-objektet för att hämta en lista av
     * alla tillgängliga Todo genom att anropa findAll-metoden i TodoDB. Returnerar en lista av Todo-objekt.
     * @return
     */
    public List<Todo> getAllTodos(){
        return todoDB.findAll();
    }

    /**
     * Metoden används för att uppdatera texten för en befintlig Todo. Den tar emot ett ID och en ny text som parameter.
     * Den använder TodoDB-objektet för att hämta den befintliga Todo baserat på ID genom att anropa findById-metoden i
     * TodoDB. Om Todo hittas, uppdateras texten med den nya texten och TodoDB-objektet används för att utföra
     * uppdateringen genom att anropa update-metoden i TodoDB.
     * @param id
     * @param newText
     */
    public void updateTodoText(String id, String newText){
        Todo todo = todoDB.findById(id);
        if (todo != null){
            todo.setText(newText);
            todoDB.update(todo);
        }
    }

    /**
     * Metoden används för att uppdatera statusen (klar/ouppfylld) för en befintlig Todo. Den tar emot ett ID och en ny
     * status som parameter. Den använder TodoDB-objektet för att hämta den befintliga Todo baserat på ID genom att
     * anropa findById-metoden i TodoDB. Om Todo hittas, uppdateras statusen med den nya statusen och TodoDB-objektet
     * används för att utföra uppdateringen genom att anropa update-metoden i TodoDB.
     * @param id
     * @param done
     */
    public void updateTodoStatus(String id, boolean done){
        Todo todo = todoDB.findById(id);
        if (todo != null){
            todo.setDone(done);
            todoDB.update(todo);
        }
    }

    /**
     * Metoden används för att ta bort en Todo från databasen baserat på dess ID. Den tar emot ett ID som parameter och
     * använder TodoDB-objektet för att radera den matchande Todo genom att anropa delete-metoden i TodoDB.
     * @param id
     */
    public void deleteTodo(String id){
        todoDB.delete(id);
    }
}
