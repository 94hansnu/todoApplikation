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
    /**
     * @Mock används för att skapa ett mock-objekt för TodoDB. Detta gör det möjligt att simulera beteendet hos TodoDB
     * under testningen.
     */
    @Mock
    private TodoDB todoDB;
    private TodoFacade todoFacade;

    /**
     * metoden körs före varje enskild testmetod och skapar en instans av TodoFacade-klassen med mock-objektet todoDB.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todoFacade = new TodoFacade(todoDB);
    }

    /**
     * I createTodo()-metoden skapas ett Todo-objekt och används som argument för createTodo()-metoden i todoFacade.
     * Mock-objektet todoDB förväntas att save()-metoden anropas exakt en gång med todo som argument.
     */
    @Test
    void createTodo() {
        Todo todo = new Todo("1", "Bada", false, "Hanadi");

        doNothing().when(todoDB).save(todo);

        todoFacade.createTodo(todo);

        Mockito.verify(todoDB,times(1)).save(todo);
    }

    /**
     * I getTodoById_exist()-metoden testas scenariot när ett Todo-objekt finns med det givna todoId. Mock-objektet
     * todoDB förväntas att findById()-metoden anropas exakt en gång med todoId som argument och returnerar expectedTodo.
     * Det kontrolleras också om getTodoById()-metoden i todoFacade returnerar samma Todo-objekt som förväntat.
     */
    @Test
    void getTodoById_exist() {
        String todoId = "1";
        Todo expectedTodo = new Todo(todoId, "Bada", false, "Hanadi");

        when(todoDB.findById(todoId)).thenReturn(expectedTodo);

        Todo resultTodo = todoFacade.getTodoById(todoId);

        Mockito.verify(todoDB, times(1)).findById(todoId);

        assertEquals(expectedTodo, resultTodo);
    }

    /**
     * I getTodoById_notExist()-metoden testas scenariot när inget Todo-objekt finns med det givna todoId. Mock-objektet
     * todoDB förväntas att findById()-metoden anropas exakt en gång med todoId som argument och returnerar null.
     * Det kontrolleras också om getTodoById()-metoden i todoFacade returnerar null.
     */
    @Test
    void getTodoById_notExist(){
        String todoId = "1";

        when(todoDB.findById(todoId)).thenReturn(null);

        Todo resultTodo = todoFacade.getTodoById(todoId);

        Mockito.verify(todoDB, times(1)).findById(todoId);

        assertNull(resultTodo);
    }

    /**
     * I getAllTodos()-metoden förväntas findAll()-metoden på mock-objektet todoDB anropas exakt en gång och returnera
     * expectedTodos. Det kontrolleras också om getAllTodos()-metoden i todoFacade returnerar samma lista med
     * Todo-objekt som förväntat.
     */
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

    /**
     * I updateTodoText()-metoden förväntas findById()-metoden på mock-objektet todoDB anropas exakt en gång med todoId
     * som argument och returnera todo. Sedan uppdateras texten för todo och update()-metoden på todoDB anropas också
     * exakt en gång med todo som argument. Det kontrolleras också om texten i todo har uppdaterats korrekt.
     */
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

    /**
     * I updateTodoStatus()-metoden förväntas findById()-metoden på mock-objektet todoDB anropas exakt en gång med
     * todoId som argument och returnera todo. Sedan uppdateras statusen för todo och update()-metoden på todoDB
     * anropas också exakt en gång med todo som argument. Det kontrolleras också om statusen i todo har uppdaterats korrekt.
     */
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

    /**
     * I deleteTodo()-metoden förväntas delete()-metoden på mock-objektet todoDB anropas exakt en gång med todoId
     * som argument.
     */
    @Test
    void deleteTodo() {
        String todoId = "1";

        doNothing().when(todoDB).delete(todoId);

        todoFacade.deleteTodo(todoId);

        Mockito.verify(todoDB, times(1)).delete(todoId);
    }
}