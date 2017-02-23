package com.example.suniltg.auth;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {
    private Button submit;
    private EditText editText,editText1;
    String language;
    private String oldt,newt,oldd,newd,post_id,user;
    String locale;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Blog");
        submit=(Button)findViewById(R.id.returnbb);
        editText=(EditText)findViewById(R.id.post_titleee);
        editText1=(EditText)findViewById(R.id.post_desccc);
        oldt=getIntent().getStringExtra("title");
        oldd=getIntent().getStringExtra("desc");
        language=getIntent().getStringExtra("language");
        user=getIntent().getStringExtra("user");
        post_id=getIntent().getStringExtra("ref");
        editText.setText(oldt);
        editText1.setText(oldd);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newt=editText.getText().toString();
                newd=editText1.getText().toString();
                DatabaseReference databaseReference1=databaseReference.child(language).child(post_id);
                databaseReference1.child("title").setValue(newt);
                databaseReference1.child("desc").setValue(newd);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
                locale = ims.getLocale();
                //RetrieveTask retrieveTask=new RetrieveTask(user,newt,newd,databaseReference,locale);
                //retrieveTask.execute();
                startActivity(new Intent(EditActivity.this,WelcomeBackActivity.class).putExtra("phoneno",user));
            }
        });

        ;

    }
}
