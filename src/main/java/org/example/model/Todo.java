package org.example.model;

public class Todo {
    private String id;
    private String text;
    private boolean done;
    private String assignedTo;

    public Todo(String id, String text, boolean done, String assignedTo) {
        this.id = id;
        this.text = text;
        this.done = done;
        this.assignedTo = assignedTo;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getText() {return text;}

    public void setText(String text) {this.text = text;}

    public boolean isDone() {return done;}

    public void setDone(boolean done) {this.done = done;}

    public String getAssignedTo() {return assignedTo;}

    public void setAssignedTo(String assignedTo) {this.assignedTo = assignedTo;}

    @Override
    public String toString(){
        return "Todo [id=" + id
                + ", text=" + text
                + ", done=" + done
                + ", assignedTo=" + assignedTo
                + "]";
    }
}
