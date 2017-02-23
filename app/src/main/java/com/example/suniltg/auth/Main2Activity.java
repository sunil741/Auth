package com.example.suniltg.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {
    private Button logout;
    private TextView textView;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        logout=(Button)findViewById(R.id.logout);
        textView=(TextView)findViewById(R.id.textView);
        textView.setText("Sd");
        if(AccessToken.getCurrentAccessToken()!=null)
        {
            textView.setText(Profile.getCurrentProfile().getName());
        }
        // textView.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                if(AccessToken.getCurrentAccessToken()!=null)
                LoginManager.getInstance().logOut();
                startActivity(new Intent(Main2Activity.this,MainActivity.class));
            }
        });
    }
}
