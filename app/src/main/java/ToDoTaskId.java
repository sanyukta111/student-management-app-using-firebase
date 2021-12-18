package com.example.firebase_register;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class ToDoTaskId {
    @Exclude
    public String ToDoTaskId;

    public <T extends ToDoTaskId> T withId(@NonNull final String id){
        this.ToDoTaskId = id;
        return (T)this;
    }
}