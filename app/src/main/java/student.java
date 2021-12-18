package com.example.firebase_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class student extends AppCompatActivity {
    int c = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        ImageView sign_out;
        RelativeLayout student_calendar,student_update,student_notices,student_competition,chat_bot,student_assign;
        student_assign=findViewById(R.id.assign);
        chat_bot = findViewById(R.id.chat);
        student_notices = findViewById(R.id.notices);
        student_calendar = findViewById(R.id.stud_calendar);
        student_update = findViewById(R.id.update_stud);
        student_competition = findViewById(R.id.comp);
        sign_out = findViewById(R.id.toolbar1);

        //sign out
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(student.this,launch.class));
                Toast.makeText(getApplicationContext(),"Successfully Logged Out",Toast.LENGTH_SHORT).show();
                                   }
        });

        //student calendar
        student_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(student.this,student_cal.class));
            }
        });

        //student update
       student_update.setOnClickListener(new View.OnClickListener() {
         @Override
          public void onClick(View v) {
             c++;
             if(c<=1)
              startActivity(new Intent(student.this, register.class));

              else
              {
                  Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
              }
           }
       });


        //student notices
        student_notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(student.this, ToDo.class));
            }
        });

        //student competition
        student_competition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(student.this,ViewUploadsActivity.class));
            }
        });

        //chat bot
        chat_bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(student.this,chatBot.class));
            }
        });

        student_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(student.this, newMod.class));
            }
        });
    }
}