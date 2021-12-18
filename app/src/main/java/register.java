package com.example.firebase_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText first_name,last_name,prn_no,roll_no;
    TextView reg;
    int c =0;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner spinner1 = findViewById(R.id.class_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.classes, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.dept_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.depts, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        first_name = findViewById(R.id.FirstName);
        last_name = findViewById(R.id.LastName);
        roll_no = findViewById(R.id.RollNo);
        prn_no = findViewById(R.id.PRNno);
        reg = findViewById(R.id.nextbtn);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c++;
                if(c<=1) {
                    String fname = first_name.getText().toString();
                    String lname = last_name.getText().toString();
                    String rno = roll_no.getText().toString();
                    String num = prn_no.getText().toString();

                    HashMap<String, String> userMap = new HashMap<>();

                    userMap.put("firstname", fname);
                    userMap.put("lastname", lname);
                    userMap.put("rollno", rno);
                    userMap.put("prnno", num);

                    root.push().setValue(userMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(), "Data uploaded successfully", Toast.LENGTH_SHORT).show();

                                }

                            });
//                Intent intent = new Intent(getApplicationContext(),student.class);
//                startActivity(intent);


                    first_name.setText(fname);
//                String ln = last_name.getText().toString();
//                String rn = roll_no.getText().toString();
//                String nu = prn_no.getText().toString();
//
                    Intent i = new Intent(getApplicationContext(), update_profile.class);
                    i.putExtra("firstname", fname);
                    i.putExtra("lastname", lname);
                    i.putExtra("rollno", rno);
                    i.putExtra("prnno", num);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"data is already uploaded",Toast.LENGTH_SHORT).show();
                }

                //startActivity(new Intent(MainActivity.this,Update_profile.class));
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        String text = "Testing";
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}