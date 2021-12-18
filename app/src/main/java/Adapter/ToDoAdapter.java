package com.example.firebase_register.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase_register.Model.ToDoModel;
import com.example.firebase_register.R;
import com.example.firebase_register.ToDo;
import com.example.firebase_register.ToDoAddNewTask;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<com.example.firebase_register.Adapter.ToDoAdapter.MyViewHolder> {

    private List<ToDoModel> todoList;
    private ToDo activity;
    private FirebaseFirestore firestore;

    public ToDoAdapter(ToDo mainActivity, List<ToDoModel>todoList){
        this.todoList = todoList;
        activity = mainActivity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.to_do_each_task, parent, false);
        firestore = FirebaseFirestore.getInstance();
        return new MyViewHolder(view);
    }

    public void deleteTask( int position){
        ToDoModel toDoModel = todoList.get(position);
        firestore.collection("task").document(toDoModel.ToDoTaskId).delete();
        todoList.remove(position);
        notifyItemRemoved(position);
    }
    public Context getContext(){
        return activity;
    }

    public void editTask(int position){
        ToDoModel toDoModel = todoList.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("task", toDoModel.getTask());
        bundle.putString("due", toDoModel.getDue());
        bundle.putString("id", toDoModel.ToDoTaskId);

        ToDoAddNewTask addNewTask = new ToDoAddNewTask();
        addNewTask.setArguments(bundle);
        addNewTask.show(activity.getSupportFragmentManager(), addNewTask.getTag());
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ToDoModel toDoModel= todoList.get(position);
        holder.mCheckBox.setText(toDoModel.getTask());
        holder.mDueDateTv.setText("Due on "+ toDoModel.getDue());

        holder.mCheckBox.setChecked(toBoolean(toDoModel.getStatus()));

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    firestore.collection("tasks").document(toDoModel.ToDoTaskId).update("status", 1);
                }else{
                    firestore.collection("tasks").document(toDoModel.ToDoTaskId).update("status",0);
                }
            }
        });
    }

    private boolean toBoolean(int status){
        return status !=0;
    }
    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mDueDateTv;
        CheckBox mCheckBox;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            mDueDateTv = itemView.findViewById(R.id.due_date_tv);
            mCheckBox = itemView.findViewById(R.id.mcheckbox);
        }
    }
}
