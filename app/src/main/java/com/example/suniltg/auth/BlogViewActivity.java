package com.example.suniltg.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BlogViewActivity extends AppCompatActivity {

    private TextView textView,textView1;
    private Button button;
    private String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_view);
        String title,desc;
        title=getIntent().getStringExtra("title");
        desc=getIntent().getStringExtra("desc");
        user=getIntent().getStringExtra("phoneno");
        button=(Button)findViewById(R.id.returnb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BlogViewActivity.this,WelcomeBackActivity.class).putExtra("phoneno",user));
            }
        });
        textView=(TextView)findViewById(R.id.post_descc);
        textView1=(TextView)findViewById(R.id.post_titlee);
        textView.setText(desc);
        textView1.setText(title);
    }
}
