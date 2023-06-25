package org.example.model;

import java.util.List;

/**
 * Denna User klassen representerar en användare och innehåller information om
 * id, namn, ålder och en lista av att göra-uppgifter
 */
public class User {
    /**
     * Instansvariablerna deklareras i klassen. Id representerar användarens ID, namn innehåller
     * användarens namn, age är användarens ålder och todos är en lista av todo-objekt som representerar
     * användarens att göra-uppgifter
     */
    private String id;
    private String name;
    private int age;
    private List<Todo> todos;

    /**
     * En konstruktor för klassen, som tar emot värden för alla instansvariabler och används för att
     * skapa ny instans av User med de givna värdena
     * @param id
     * @param name
     * @param age
     * @param todos
     */
    public User(String id, String name, int age, List<Todo> todos) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.todos= todos;

    }

    /**
     * Getters och setter metoder för instansvariablerna
     * @return
     */
    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public List<Todo> getTodos() {return todos;}

    public void setTodos(List<Todo> todos) {this.todos = todos;}

    @Override
    public String toString(){
        return "User [id=" + id
                + ", name=" + name
                + ", age=" + age
                + ", todos=" + todos
                + "]";
    }
}
