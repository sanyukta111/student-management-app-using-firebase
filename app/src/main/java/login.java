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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity
{
    EditText email,pass;
    TextView signup;
    TextView sign;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass= findViewById(R.id.password);
        sign = findViewById(R.id.sign_in);
        signup = findViewById(R.id.no_sign);

        mAuth = FirebaseAuth.getInstance();

        //login
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        //sign up
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,MainActivity.class));
            }
        });
    }

    //login
    private void loginUser()
    {
        String e_mail = email.getText().toString();
        String password = pass.getText().toString();

        if(!e_mail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(e_mail).matches())
        {
            if(!password.isEmpty())
            {
                mAuth.signInWithEmailAndPassword(e_mail,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this,student.class));
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