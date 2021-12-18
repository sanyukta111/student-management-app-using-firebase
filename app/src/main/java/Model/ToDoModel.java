package com.example.firebase_register.Model;

public class ToDoModel extends com.example.firebase_register.ToDoTaskId {

    private String task, due;
    private int status;

    public String getTask() {
        return task;
    }
    public String getDue() {
        return due;
    }
    public int getStatus() {
        return status;
    }
}
