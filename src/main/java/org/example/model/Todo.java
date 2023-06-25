package org.example.model;

/**
 * Klassen Todo presenterar en att göra-uppgift
 */
public class Todo {
    /**
     * Här deklareras instansvariablerna i klassen, id representerar ID:t för att göra-uppgiften,
     * text innehåller skälva texten för uppgiften, done är en boolesk variabel som anger om uppgiften
     * är klar eller inte och assignedTo innehåller namnet på personen som uppgiften är tilldelas till.
     */
    private String id;
    private String text;
    private boolean done;
    private String assignedTo;

    /**
     * Detta är konstruktorn för klassen och den tar emot värden för alla instansvariabler
     * och används för att skapa en ny instans av Todo med de angivna värdena.
     * @param id
     * @param text
     * @param done
     * @param assignedTo
     */
    public Todo(String id, String text, boolean done, String assignedTo) {
        this.id = id;
        this.text = text;
        this.done = done;
        this.assignedTo = assignedTo;
    }

    /**
     * Metoder getters och setters för variablerna
     * @return
     */
    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getText() {return text;}

    public void setText(String text) {this.text = text;}

    public boolean isDone() {return done;}

    public void setDone(boolean done) {this.done = done;}

    public String getAssignedTo() {return assignedTo;}

    public void setAssignedTo(String assignedTo) {this.assignedTo = assignedTo;}

    /**
     * Denna metoden toString används för att skapa en strängrepresentation av objektet av typen Todo.
     * @return
     */
    @Override
    public String toString(){
        return "Todo [id=" + id
                + ", text=" + text
                + ", done=" + done
                + ", assignedTo=" + assignedTo
                + "]";
    }
}
