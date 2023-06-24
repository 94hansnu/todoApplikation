package org.example.helper;

import org.example.model.Todo;
import org.example.model.User;
import org.example.mongodbFacade.TodoFacade;
import org.example.mongodbFacade.UserFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    UserFacade userFacade;
    TodoFacade todoFacade;
    Scanner scanner;

    public Menu(UserFacade userFacade, TodoFacade todoFacade){
        this.userFacade = userFacade;
        this.todoFacade= todoFacade;
        this.scanner = new Scanner(System.in);
    }
    public void displayMenu(){
        System.out.println("Välkommen till Todo applikationen");
        System.out.println("---------------------------------");

        int choice;
        do {
            printMenuOptions();
            choice = getUserChoice();
            switch (choice){
                case 1:
                    createTodo();
                    break;
                case 2:
                    viewAllTodos();
                    break;
                case 3:
                    viewTodoById();
                    break;
                case 4:
                    updateTodo();
                    break;
                case 5:
                    updateTodoStatus();
                    break;
                case 6:
                    deleteTodo();
                    break;
                case 7:
                    createUser();
                    break;
                case 8:
                    viewAllUsers();
                    break;
                case 9:
                    viewUserById();
                    break;
                case 10:
                    updateUser();
                    break;
                case 11:
                    updateUserName();
                    break;
                case 12:
                    deleteUser();
                    break;
                case 13:
                    System.out.println("Du har valt att avsluta applikationen. ");
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen");
            }
        } while (choice != 13);
    }
    public void printMenuOptions(){
        System.out.println("\nVälj en handling:");
        System.out.println("1. Skapa ny TODO");
        System.out.println("2. Visa alla TODO");
        System.out.println("3. Visa en enskild TODO");
        System.out.println("4. Uppdatera en TODO");
        System.out.println("5. Uppdatera om en todo är klar eller inte");
        System.out.println("6. Ta bort en TODO");
        System.out.println("7. Skapa en användare");
        System.out.println("8. Visa alla användare");
        System.out.println("9. Visa en enskild användare");
        System.out.println("10. Uppdatera en användare");
        System.out.println("11. Uppdatera användarnamn");
        System.out.println("12. Ta bort en användare");
        System.out.println("13. Avsluta");

        System.out.println("Ange ditt val: ");
    }
    public int getUserChoice(){
        int choice;
        while (true){
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Ogiltigt val, försök igen! ");
            }
        } return choice;
    }

    public void createTodo() {
        System.out.println("Ange todo-id: ");
        String id = scanner.nextLine();

        System.out.println("Ange text: ");
        String text = scanner.nextLine();

        System.out.println("Är todo klar (ja/nej): ");
        boolean done = scanner.nextLine().equals("ja");

        System.out.println("Ange tilldelad användare: ");
        String assignedTo = scanner.nextLine();

        Todo todo = new Todo(id, text, done, assignedTo);
        todoFacade.createTodo(todo);
        System.out.println("TODO skapad! ");
    }
    public void viewAllTodos() {
        System.out.println("Alla TODO: ");
        List<Todo> todos = todoFacade.getAllTodos();
        todos.forEach(System.out::println);
    }
    public void viewTodoById() {
        System.out.println("Ange todo-id: ");
        String id = scanner.nextLine();

        Todo todo = todoFacade.getTodoById(id);
        if (todo != null){
            System.out.println(todo);
        }else {
            System.out.println("Ingen Todo hittades med det angivna id:t.");
        }
    }

    public void updateTodo() {
        System.out.println("Ange todo-id: ");
        String id = scanner.nextLine();

        Todo todo = todoFacade.getTodoById(id);

        if (todo != null){
            System.out.println("Ange ny text: ");
            String newText = scanner.nextLine();

            todoFacade.updateTodoText(id, newText);
            System.out.println("Todo är uppdaterad");
        }else {
            System.out.println("Ingen Todo hittades med det angivna id:t. ");
        }
    }
    public void updateTodoStatus() {
        System.out.println("Ange todo-id: ");
        String id = scanner.nextLine();
        System.out.println("Är todo klar (ja/nej): ");
        boolean done = scanner.nextLine().equalsIgnoreCase("ja");

        todoFacade.updateTodoStatus(id, done);
        System.out.println("Todo status uppdaterad");
    }

    public void deleteTodo() {
        System.out.println("Ange todo-id: ");
        String id = scanner.nextLine();
    try{
        todoFacade.deleteTodo(id);
        System.out.println("Todo raderad!");
    } catch(IllegalArgumentException e ){
        System.out.println(e.getMessage());
    }

    }

    public void createUser() {
        System.out.println("Ange användar-id: ");
        String id = scanner.nextLine();

        System.out.println("Ange namn:");
        String name = scanner.nextLine();

        System.out.println("Ange ålder: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        User user = new User(id, name, age, new ArrayList<>());

        userFacade.createUser(user);
        System.out.println("Användare skapad! ");
    }

    public void viewAllUsers() {
        System.out.println("Alla användare: ");
        List<User> users = userFacade.getAllUsers();
        users.forEach(System.out::println);
    }

    public void viewUserById() {
        System.out.println("Ange användar-id: ");
        String id = scanner.nextLine();

        User user = userFacade.getUserById(id);
        if (user != null){
            System.out.println(user);
        }else {
            System.out.println("Ingen användare hittades med det angivna id:t. ");
        }
    }

    public void updateUser() {
        System.out.println("Ange användar-id: ");
        String id = scanner.nextLine();

        User user = userFacade.getUserById(id);

        if (user != null){
            System.out.println("Ange nytt namn: ");
            String newName = scanner.nextLine();

            System.out.println("Ange ny ålder: ");
            int newAge = scanner.nextInt();
            scanner.nextLine();

            user.setName(newName);
            user.setAge(newAge);

            userFacade.updateUser(user);
            System.out.println("Användaren uppdaterad!");
        }else {
            System.out.println("Ingen användare hittades med det angivna id:t. ");
        }
    }

    public void updateUserName() {
        System.out.println("Ange användar-id: ");
        String id = scanner.nextLine();

        System.out.println("Ange det nya namnet: ");
        String newName = scanner.nextLine();

        User user = userFacade.updateUserName(id, newName);
        if (user != null ){
            user.setName(newName);
            userFacade.updateUser(user);
            System.out.println("Användaren uppdaterad. ");
        }else {
            System.out.println("Användaren hittades inte. ");
        }
    }

    public void deleteUser() {
        System.out.println("Ange användar-id: ");
        String id = scanner.nextLine();

        try {
            userFacade.deleteUser(id);
            System.out.println("Användare raderad! ");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
