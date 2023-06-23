package org.example.mongodbFacade;

import org.example.model.Todo;
import org.example.mongodbDatabase.TodoDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class TodoFacadeTest {
    @Mock
    private TodoDB todoDB;
    private TodoFacade todoFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todoFacade = new TodoFacade(todoDB);
    }

    @Test
    void createTodo() {
        Todo todo = new Todo("1", "Bada", false, "Hanadi");

        doNothing().when(todoDB).save(todo);

        todoFacade.createTodo(todo);

        Mockito.verify(todoDB,times(1)).save(todo);
    }

    @Test
    void getTodoById_exist() {
        String todoId = "1";
        Todo expectedTodo = new Todo(todoId, "Bada", false, "Hanadi");

        when(todoDB.findById(todoId)).thenReturn(expectedTodo);

        Todo resultTodo = todoFacade.getTodoById(todoId);

        Mockito.verify(todoDB, times(1)).findById(todoId);

        assertEquals(expectedTodo, resultTodo);
    }

    @Test
    void getTodoById_notExist(){
        String todoId = "1";

        when(todoDB.findById(todoId)).thenReturn(null);

        Todo resultTodo = todoFacade.getTodoById(todoId);

        Mockito.verify(todoDB, times(1)).findById(todoId);

        assertNull(resultTodo);
    }

    @Test
    void getAllTodos() {
        List<Todo> expectedTodos = new ArrayList<>();
        expectedTodos.add(new Todo("1", "Bada", false, "Hanadi"));
        expectedTodos.add(new Todo("2", "Bada", true, "Kelly"));

        when(todoDB.findAll()).thenReturn(expectedTodos);

        List<Todo> resultTodo = todoFacade.getAllTodos();

        Mockito.verify(todoDB, times(1)).findAll();

        assertEquals(expectedTodos, resultTodo);
    }

    @Test
    void updateTodoText() {
        String todoId = "1";
        String newText = "Sola";
        Todo todo = new Todo(todoId, "Bada", false, "Hanadi");

        when(todoDB.findById(todoId)).thenReturn(todo);

        todoFacade.updateTodoText(todoId, newText);

        Mockito.verify(todoDB, times(1)).findById(todoId);

        assertEquals(newText, todo.getText());

        Mockito.verify(todoDB, times(1)).update(todo);
    }

    @Test
    void updateTodoStatus() {
        String todoId = "1";
        boolean newStatus = true;
        Todo todo = new Todo(todoId, "Bada", false, "Hanadi");

        when(todoDB.findById(todoId)).thenReturn(todo);

        todoFacade.updateTodoStatus(todoId, newStatus);

        Mockito.verify(todoDB, times(1)).findById(todoId);

        assertEquals(newStatus, todo.isDone());

        Mockito.verify(todoDB, times(1)).update(todo);
    }

    @Test
    void deleteTodo() {
        String todoId = "1";

        doNothing().when(todoDB).delete(todoId);

        todoFacade.deleteTodo(todoId);

        Mockito.verify(todoDB, times(1)).delete(todoId);
    }
}