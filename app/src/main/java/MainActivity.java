package com.example.firebase_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    EditText email,pass;
    Button sign;
    TextView yessign;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        pass= findViewById(R.id.password);
        sign = findViewById(R.id.sign_in);
        yessign = findViewById(R.id.yes_sign);

        mAuth = FirebaseAuth.getInstance();
        //register
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });


        //already registered
        yessign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,login.class));
            }
        });
    }

    private void createUser()
    {
        String e_mail = email.getText().toString();
        String password = pass.getText().toString();

        if(!e_mail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(e_mail).matches())
        {
            if(!password.isEmpty())
            {
                mAuth.createUserWithEmailAndPassword(e_mail,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,login.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"not successful",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else
            {
                pass.setError("empty field");
            }
        }
        else if(e_mail.isEmpty())
        {
            email.setError("empty field");
        }
        else
        {
            email.setError("enter correct email");
        }
    }

    //setVisible

    public void setVisible(View view) {
        if (view.getId() == R.id.passVisible) {

            if (pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.visible);

                //Show Password
                pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.hidden);

                //Hide Password
                pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                // pass_visible.setImageDrawable(getResources().getDrawable(R.drawable.visible));

            }
        }

    }
}