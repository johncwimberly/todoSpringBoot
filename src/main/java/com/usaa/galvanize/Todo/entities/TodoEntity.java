package com.usaa.galvanize.Todo.entities;

import javax.persistence.*;

@Entity
@Table(name = "TODO_TABLE")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private boolean isCompleted;

    public TodoEntity(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public TodoEntity(){
        this.name = null;
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

}
