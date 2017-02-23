package com.example.suniltg.auth;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeBackActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    String locale;

    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_back);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
        locale = ims.getLocale();

           if (getIntent().getStringExtra("phoneno") != null) {
               user = getIntent().getStringExtra("phoneno");
           } else if (AccessToken.getCurrentAccessToken() != null) {
               AccessToken accessToken = AccessToken.getCurrentAccessToken();
               user = accessToken.toString();
           }
           else {
               user = FirebaseAuth.getInstance().getCurrentUser().getEmail();

           }
//       }

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Blog").child(locale.toString().substring(0,2));

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog,BlogviewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogviewHolder>(Blog.class,R.layout.blogscroll,BlogviewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(final BlogviewHolder viewHolder, Blog model, final int position) {
                final String titlee=model.getTitle();
                final String descc=model.getDesc();
               final String userr=model.getUser();
                viewHolder.set_desc(descc);
                viewHolder.set_title(titlee);
                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(WelcomeBackActivity.this,BlogViewActivity.class).putExtra("phoneno",user).
                                putExtra("title",titlee)
                        .putExtra("desc",descc))
                        ;
                    }
                });
                viewHolder.mview.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if(user.equals(userr))
                        startActivity(new Intent(WelcomeBackActivity.this,EditActivity.class).putExtra("title",titlee)
                                .putExtra("desc",descc).putExtra("user",user).putExtra("ref",getRef(position).getKey()).putExtra("language",locale.toString().substring(0,2)
                                ));
                        else
                        Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.cantedit),Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());



    }

    public static class BlogviewHolder extends RecyclerView.ViewHolder {
        View mview;

        public BlogviewHolder(View itemView) {
            super(itemView);
            mview=itemView;
        }
        public void set_title(String title)
        {


            TextView textView=(TextView)mview.findViewById(R.id.blog_title);
            textView.setText(mview.getContext().getString(R.string.title)+" : "+title);
        }
        public void set_desc(String desc)
        {
            TextView textView=(TextView)mview.findViewById(R.id.blog_desc);
            textView.setText(mview.getContext().getString(R.string.description)+": "+desc);
        }
        public void set_image(Context ctx,String image)
        {
            // ImageView imageView=(ImageView)mview.findViewById(R.id.blog_image);
            //Picasso.with(ctx).load(image).into(imageView);

        }


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(WelcomeBackActivity.this,getString(R.string.needto), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addblog) {
            startActivity(new Intent(WelcomeBackActivity.this, PostActivity.class).putExtra("user",user));

        }
        if(item.getItemId()==R.id.action_settings)
        {
            Intent i = new Intent( android.provider.Settings.ACTION_LOCALE_SETTINGS ); startActivity( i );

        }
        if(item.getItemId()==R.id.action_settings_logout)
        {
            FirebaseAuth.getInstance().signOut();
            if(AccessToken.getCurrentAccessToken()!=null)
                LoginManager.getInstance().logOut();
            startActivity(new Intent(WelcomeBackActivity.this,MainActivity.class));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
