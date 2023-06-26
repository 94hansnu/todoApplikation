package org.example.helper;

import org.example.model.Todo;
import org.example.model.User;
import org.example.mongodbFacade.TodoFacade;
import org.example.mongodbFacade.UserFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klassen Menu hanterar användargränssnittet för en Todo-applikation. Den har referenser till UserFacade och TodoFacade
 * för att utföra operationer på användare och todo-objekt. Dessutom används en Scanner för att läsa inmatning
 * från användaren.
 */
public class Menu {
    UserFacade userFacade;
    TodoFacade todoFacade;
    Scanner scanner;

    public Menu(UserFacade userFacade, TodoFacade todoFacade){
        this.userFacade = userFacade;
        this.todoFacade= todoFacade;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Metoden displayMenu visar huvudmenyn för Todo-applikationen och låter användaren välja olika handlingar.
     * Det använder en loop för att kontinuerligt visa menyn tills användaren väljer att avsluta applikationen genom att
     * ange 13.
     */
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

    /**
     * Metoden printMenuOptions skriver ut alla tillgängliga handlingar som användaren kan välja från menyn.
     */
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

    /**
     * Metoden läser in användarens val från inmatningen och returnerar det som en heltalsvärde. Om användaren anger
     * ogiltig inmatning fångas NumberFormatException och användaren uppmanas att försöka igen.
     * @return
     */
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

    /**
     * Metoden låter användaren ange information om en ny todo och skapar sedan en Todo-instans med den angivna
     * informationen. Sedan används todoFacade för att skapa todo-objektet i databasen
     */
    public void createTodo() {
        System.out.println("Ange todo-id: ");
        String id = scanner.nextLine();
        while (todoFacade.getTodoById(id)!= null){
            System.out.println("Todo-id finns redan. Vänligen ange ett annat ID:");
            id = scanner.nextLine();}

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

    /**
     * Metoden hämtar och visar alla tillgängliga todo-objekt från databasen genom att använda todoFacade-metoden
     * getAllTodos. Varje todo-objekt skrivs ut på en ny rad.
     */
    public void viewAllTodos() {
        System.out.println("Alla TODO: ");
        List<Todo> todos = todoFacade.getAllTodos();
        todos.forEach(System.out::println);
    }

    /**
     * Metoden låter användaren ange ett todo-id och hämtar sedan motsvarande todo-objekt från databasen genom att
     * använda todoFacade-metoden getTodoById. Om ett todo-objekt hittas skrivs det ut, annars skrivs ett meddelande
     * ut att ingen todo hittades.
     */
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

    /**
     * Metoden låter användaren ange ett todo-id och hämtar sedan det motsvarande todo-objektet från databasen genom att
     * använda todoFacade-metoden getTodoById. Om todo-objektet finns kan användaren ange en ny text för todo och sedan
     * uppdateras todo-objektet i databasen med den nya texten genom att använda todoFacade-metoden updateTodoText.
     * Annars skrivs ett meddelande ut att ingen todo hittades.
     */
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

    /**
     * Metoden låter användaren ange ett todo-id och sedan om todo är klar eller inte genom att ange "ja" eller "nej".
     * Beroende på användarens inmatning används todoFacade för att uppdatera statusen för det angivna todo-objektet i
     * databasen genom att använda metoden updateTodoStatus. Ett meddelande skrivs ut för att bekräfta att statusen har
     * uppdaterats.
     */
    public void updateTodoStatus() {
        System.out.println("Ange todo-id: ");
        String id = scanner.nextLine();
        System.out.println("Är todo klar (ja/nej): ");
        boolean done = scanner.nextLine().equalsIgnoreCase("ja");

        todoFacade.updateTodoStatus(id, done);
        System.out.println("Todo status uppdaterad");
    }

    /**
     * Metoden låter användaren ange ett todo-id och försöker sedan radera det motsvarande todo-objektet från databasen
     * genom att använda todoFacade-metoden deleteTodo. Om ett ogiltigt todo-id anges fångas IllegalArgumentException
     * och ett felmeddelande skrivs ut.
     */
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

    /**
     * Metoden låter användaren ange information om en ny användare och skapar sedan en User-instans med den angivna
     * informationen. Sedan används userFacade för att skapa användarobjektet i databasen.
     */
    public void createUser() {
        System.out.println("Ange användar-id: ");
        String id = scanner.nextLine();
        while (userFacade.getUserById(id)!= null){
            System.out.println("Användar-id finns redan. Vänligen ange ett annat ID:");
            id = scanner.nextLine();}

        System.out.println("Ange namn:");
        String name = scanner.nextLine();

        System.out.println("Ange ålder: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        User user = new User(id, name, age, new ArrayList<>());

        userFacade.createUser(user);
        System.out.println("Användare skapad! ");
    }

    /**
     * Metoden hämtar och visar alla tillgängliga användarobjekt från databasen genom att använda userFacade-metoden
     * getAllUsers. Varje användarobjekt skrivs ut på en ny rad.
     */
    public void viewAllUsers() {
        System.out.println("Alla användare: ");
        List<User> users = userFacade.getAllUsers();
        users.forEach(System.out::println);
    }

    /**
     * Metoden låter användaren ange ett användar-id och hämtar sedan det motsvarande användarobjektet från databasen
     * genom att använda userFacade-metoden getUserById. Om ett användarobjekt hittas skrivs det ut, annars skrivs ett
     * meddelande ut att ingen användare hittades.
     */
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

    /**
     * Metoden låter användaren ange ett användar-id och hämtar sedan det motsvarande användarobjektet från databasen
     * genom att använda userFacade-metoden getUserById. Om användarobjektet finns kan användaren ange ett nytt namn och
     * en ny ålder för användaren. Därefter uppdateras användarobjektet i databasen med det nya namnet och åldern genom
     * att använda userFacade-metoden updateUser. Annars skrivs ett meddelande ut att ingen användare hittades.
     */
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

    /**
     * Metoden låter användaren ange ett användar-id och hämtar sedan det motsvarande användarobjektet från databasen
     * genom att använda userFacade-metoden getUserById. Om användarobjektet finns kan användaren ange ett nytt namn för
     * användaren. Därefter uppdateras användarnamnet för användarobjektet i databasen genom att använda
     * userFacade-metoden updateUserName. Annars skrivs ett meddelande ut att ingen användare hittades.
     */
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

    /**
     * Metoden låter användaren ange ett användar-id och försöker sedan radera det motsvarande användarobjektet från
     * databasen genom att använda userFacade-metoden deleteUser. Om ett ogiltigt användar-id anges fångas
     * IllegalArgumentException och ett felmeddelande skrivs ut.
     */
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
