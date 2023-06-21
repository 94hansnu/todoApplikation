package org.example.mongodbFacade;

import org.example.model.Todo;
import org.example.mongodbDatabase.TodoDB;

import java.util.List;

public class TodoFacade {
    TodoDB todoDB;

    public TodoFacade(TodoDB todoDB){
        this.todoDB= todoDB;
    }

    public void createTodo(Todo todo){
        todoDB.save(todo);
    }

    public Todo getTodoById(String id){
        return todoDB.findById(id);
    }
    public List<Todo> getAllTodos(){
        return todoDB.findAll();
    }
    public void updateTodoText(String id, String newText){
        Todo todo = todoDB.findById(id);
        if (todo != null){
            todo.setText(newText);
            todoDB.update(todo);
        }
    }
    public void updateTodoStatus(String id, boolean done){
        Todo todo = todoDB.findById(id);
        if (todo != null){
            todo.setDone(done);
            todoDB.update(todo);
        }
    }
    public void deleteTodo(String id){
        todoDB.delete(id);
    }
}
