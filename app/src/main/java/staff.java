package com.example.firebase_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class staff extends AppCompatActivity
{
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    //"(?=.*[a-zA-Z])" +        //any letter
                    "(?=.*[@#$%^&+=])" +      //at least 1 special character
                    "(?=\\S+$)" +             //no white spaces
                    ".{8,}" +                 //at least 8 characters
                    "$");

    EditText staff_email, staff_pass;
    TextView staff_login1;
    ImageView arrow_image, pass_visible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        arrow_image = (ImageView) findViewById(R.id.arrow);
        staff_email = (EditText) findViewById(R.id.staff_email);
        staff_pass = (EditText) findViewById(R.id.staff_password);
        staff_login1 = (TextView) findViewById(R.id.staff_login);

        staff_login1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validateEmail();
                validatePassword();
                String email = staff_email.getText().toString();
                String pass = staff_pass.getText().toString();
                if(validateEmail() == true && validatePassword() == true && email.equals("admin@gmail.com") && pass.equals("123"))
                {
                    Toast.makeText(getApplicationContext(),"Login successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(staff.this,admin_module.class);
                    startActivity(i);
                }
            }
        });

        arrow_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent i = new Intent(com.example.academyish.staff_login.this, com.example.academyish.login_option.class);
//                startActivity(i);
            }
        });
    }

    //validate email address
    private boolean validateEmail() {
        String inputEmail = staff_email.getText().toString().trim();
        if (inputEmail.isEmpty()) {
            staff_email.setError("Enter email address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
            staff_email.setError("Enter valid email address");
            return false;
        } else {
            staff_email.setError(null);
            return true;
        }
    }

    //validate password
    private boolean validatePassword() {
        String inputPassword = staff_pass.getText().toString().trim();
        if (inputPassword.isEmpty()) {
            staff_pass.setError("Enter password");
            return false;
        }
//        } else if (!PASSWORD_PATTERN.matcher(inputPassword).matches()) {
//            staff_pass.setError("Password too weak");
//            return false;
//    }
        else {
            staff_pass.setError(null);
            return true;
        }
    }

    //setVisible

    public void setVisible(View view) {
        if (view.getId() == R.id.passVisible) {

            if (staff_pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.visible);

                //Show Password
                staff_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.hidden);

                //Hide Password
                staff_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                // pass_visible.setImageDrawable(getResources().getDrawable(R.drawable.visible));

            }
        }

    }
}