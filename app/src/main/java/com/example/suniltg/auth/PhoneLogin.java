package com.example.suniltg.auth;

import android.content.Intent;
import android.opengl.EGLDisplay;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PhoneLogin extends AppCompatActivity {

    private EditText password,phone_no;
    private Button login;
    private Button gotoregister;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        gotoregister=(Button)findViewById(R.id.gotoregister);
        gotoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhoneLogin.this,Phone_Register.class));
            }
        });
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("auth_phone");
        phone_no=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        login=(Button)findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("loginsfff","login ");

                databaseReference.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChild(phone_no.getText().toString()))
                                {Log.d("loginsfff","login asf");
                                    databaseReference.child(phone_no.getText().toString()).child("password").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            Log.d("loginsfff","loginsf ");
                                            if(dataSnapshot.getValue().toString().equals(password.getText().toString()))
                                            {
                                                Log.d("login","login succesfuk");
                                                startActivity(new Intent(PhoneLogin.this,WelcomeBackActivity.class).putExtra("phoneno",phone_no.getText().toString()).putExtra("calling","PhoneLogin"));
                                            }
                                            else
                                            {
                                                Log.d("dsfsdf","sfdsf"+dataSnapshot.getValue()+" "+password.getText().toString());
                                                Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.wrong),Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                                            Log.d("dfsf","faddddddd");
                                        }
                                    });
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.userde), Toast.LENGTH_SHORT).show();
                                    phone_no.setText("");
                                    password.setText("");
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("dfsf","faddddddd1");
                            }
                        }
                );

    }
}

        );
    }
}