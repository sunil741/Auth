package com.example.suniltg.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private EditText title;
    private EditText description;
    private Button submit,languageb;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    String locale;
    TextView textView;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        textView=(TextView)findViewById(R.id.post_lan);
        progressDialog = new ProgressDialog(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Blog");
//        title.setText(Locale.getDefault().getDisplayLanguage());
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadfile();
            }
        });
        title = (EditText) findViewById(R.id.post_title);
        description = (EditText) findViewById(R.id.post_desc);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
        locale = ims.getLocale();
        //textView.setText(locale);
      //  textView.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName().toString());
        user=getIntent().getStringExtra("user");
        textView.setText(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addblog)
            startActivity(new Intent(PostActivity.this,PostActivity.class));
        if(item.getItemId()==R.id.action_settings)
        {
            Intent i = new Intent( android.provider.Settings.ACTION_LOCALE_SETTINGS ); startActivity( i );

        }
        if(item.getItemId()==R.id.infoapp)
        {
            AlertDialog.Builder a= new AlertDialog.Builder(this);
            a.setCancelable(true);
            a.setNegativeButton(getApplicationContext().getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });
            a.setTitle(getApplicationContext().getString(R.string.infotitle));
            a.setMessage("1."+getApplicationContext().getString(R.string.infomessage1)+"\n"+"2."+getApplicationContext().getString(R.string.infomessage2)+"\n"+"3."+getApplicationContext().getString(R.string.infomessage3)+"\n"+"4."+getApplicationContext().getString(R.string.infomessage4)+"\n"+"5."+getApplicationContext().getString(R.string.infomessage5)+"\n"+"6."+getApplicationContext().getString(R.string.infomessage6)+"\n"+"7."+getApplicationContext().getString(R.string.infomessage7)+"\n"+"1."+getApplicationContext().getString(R.string.languagesavail1)+"\n"+"2."+getApplicationContext().getString(R.string.languagesavail2)+"\n"+"3."+getApplicationContext().getString(R.string.languagesavail3)+"\n"+"4."+getApplicationContext().getString(R.string.languagesavail4)+"\n"+"5."+getApplicationContext().getString(R.string.languagesavail5)+"\n"+"6."+getApplicationContext().getString(R.string.languagesavail6)+"\n"+"7."+getApplicationContext().getString(R.string.languagesavail7)+"\n"+"8."+getApplicationContext().getString(R.string.languagesavail8)+"\n"+"9."+getApplicationContext().getString(R.string.languagesavail9));
            a.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void uploadfile() {
        final String title1 = title.getText().toString().trim();
        final String desc1 = description.getText().toString().trim();
        if (title.getText() != null && description.getText() != null) {
            progressDialog.setMessage("posting");

            progressDialog.show();
          //  DatabaseReference databaseReference2 = databaseReference.child(locale).push();
            //databaseReference2.child("desc").setValue(desc1);
            //databaseReference2.child("title").setValue(title1);
            progressDialog.dismiss();
            RetrieveTask retrieveTask=new RetrieveTask(user,title1,desc1,databaseReference,locale);
            retrieveTask.execute();
         /*   RetrieveFeedTask retrieveFeedTask=new RetrieveFeedTask("desc",desc1,databaseReference,locale);
            retrieveFeedTask.execute();
            RetrieveFeedTask retrieveFeedTask1=new RetrieveFeedTask("title",title1,databaseReference,locale);
            retrieveFeedTask1.execute();*/
            startActivity(new Intent(PostActivity.this, WelcomeBackActivity.class).putExtra("phoneno",user));
        }
        else {
            Toast.makeText(getApplicationContext(), "Still need to fill the psot", Toast.LENGTH_LONG).show();
        }
    }


}





