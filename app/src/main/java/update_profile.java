package com.example.firebase_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class update_profile extends AppCompatActivity {

    EditText name,la_name,roll,prr;
    String _FIRSTNAME,_LASTNAME,_ROLLNO,_PRNNO;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        name = findViewById(R.id.fi_name);
        la_name = findViewById(R.id.la_name);
        roll = findViewById(R.id.roll);
        prr = findViewById(R.id.prn);

        reference = FirebaseDatabase.getInstance().getReference("users");

        showAllUserData();

//        @Override
//        protected void onBackPressed(){
//            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
//            editor.putString("text", name.getText().toString());
//            editor.commit();
//            super.onBackPressed();
//        }
    }

    private void showAllUserData()
    {
        Intent intent = getIntent();
        _FIRSTNAME = intent.getStringExtra("firstname");
        _LASTNAME = intent.getStringExtra("lastname");
        _ROLLNO = intent.getStringExtra("rollno");
        _PRNNO = intent.getStringExtra("prnno");

        name.setText(_FIRSTNAME);
        la_name.setText(_LASTNAME);
        roll.setText(_ROLLNO);
        prr.setText(_PRNNO);

    }

    public void update(View view)
    {
        if(isNameChanged() || isLastNameChanged() || isRnoChanged() || isPrnChanged())
        {
            Toast.makeText(getApplicationContext(),"Data has been updated",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(update_profile.this,student.class));
        }
    }

    //name changed
    private boolean isNameChanged()
    {
        String f = name.getText().toString();
        if(!_FIRSTNAME.equals(f))
        {
            reference.child(_FIRSTNAME).child("firstname").setValue(f);
            _FIRSTNAME = f;
            return true;
        }
        else
        {
            return false;
        }
    }

    //last name changed
    private boolean isLastNameChanged()
    {
        String l = la_name.getText().toString();
        if(!_LASTNAME.equals(l))
        {
            reference.child(_LASTNAME).child("lastname").setValue(l);
            _LASTNAME = l;
            return true;
        }
        else
        {
            return false;
        }
    }

    //roll no changed
    private boolean isRnoChanged()
    {
        String r = roll.getText().toString();
        if(!_ROLLNO.equals(r))
        {
            reference.child(_ROLLNO).child("rollno").setValue(r);
            _ROLLNO = r;
            return true;
        }
        else
        {
            return false;
        }
    }

    //prn no changed
    private boolean isPrnChanged()
    {
        String p = prr.getText().toString();
        if(!_PRNNO.equals(p))
        {
            reference.child(_PRNNO).child("prnno").setValue(p);
            _PRNNO = p;
            return true;
        }
        else
        {
            return false;
        }
    }

}