package com.example.firebase_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_module extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_module);

        RelativeLayout calendar_module,add_notice,addcomp,addAss;
        ImageView sign_out;
        addAss= findViewById(R.id.addAss);
        add_notice = findViewById(R.id.addnot);
        addcomp = findViewById(R.id.add_comp);
        sign_out = findViewById(R.id.toolbar);
        calendar_module = findViewById(R.id.calendar);

        calendar_module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_module.this,cal.class));
            }
        });

       sign_out.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(admin_module.this,launch.class));
               Toast.makeText(getApplicationContext(),"Logged out successfully",Toast.LENGTH_SHORT).show();
           }
       });

       add_notice.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(admin_module.this,ToDo.class));
           }
       });

       addcomp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(admin_module.this,assignment.class));
           }
       });
        addAss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_module.this,addMod.class));
            }
        });
    }
}