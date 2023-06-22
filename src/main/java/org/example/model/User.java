package org.example.model;

import java.util.List;

public class User {
    private String id;
    private String name;
    private int age;
    private List<Todo> todos;

    public User(String id, String name, int age, List<Todo> todos) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.todos= todos;

    }

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
