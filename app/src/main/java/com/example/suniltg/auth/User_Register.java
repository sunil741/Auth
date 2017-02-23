package com.example.suniltg.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_Register extends AppCompatActivity {
    private TextView textView;
    private EditText password1,password2;
    private Button userregister;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__register);
       firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("auth_phone");
        password1=(EditText)findViewById(R.id.editText3);
        password2=(EditText)findViewById(R.id.editText4);
       // textView=(TextView)findViewById(R.id.textView10);
       // textView.setText("welcome"+getIntent().getStringExtra("phone_no"));
       // textView.setText("hello");
       userregister=(Button)findViewById(R.id.usercreate);
        userregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password1.getText().toString().equals(password2.getText().toString()))
                {
                    String phone_no=getIntent().getStringExtra("phone_no");
                databaseReference.child(phone_no).child("password").setValue(password1.getText().toString());
                    startActivity(new Intent(User_Register.this,PhoneLogin.class));
            }
                else
                {
                    Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.passno),Toast.LENGTH_SHORT).show();
                    password1.setText("");
                    password2.setText("");
                }
            }

        });


    }
}
