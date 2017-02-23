package com.example.suniltg.auth;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    private Button signwithno;
    private static final int RC_SIGN_IN = 76;
    private SignInButton signInButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final String TAG = "signinActivity";
    private GoogleApiClient mGoogleApiClient;
    private CallbackManager mcallbackmanager;
    private LoginButton loginButton;
// ...
private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken ;
    private FacebookCallback<LoginResult> mcallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            Intent a = new Intent(MainActivity.this, WelcomeBackActivity.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);

        }

        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "hello login cancelled", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(FacebookException error) {
            error.printStackTrace();
            Toast.makeText(getApplicationContext(), "login error" + error.toString(), Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signwithno=(Button)findViewById(R.id.button);
        signwithno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("dfasdfasdfasdfd","dfsasd");
                startActivity(new Intent(MainActivity.this,PhoneLogin.class));
            }
        });
        mAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
            @Override
            public void onInitialized() {

                accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken == null) {
                    Log.d(TAG, "not logged in yet");
                } else {
                    Log.d(TAG, "Logged in");
                    Intent main = new Intent(MainActivity.this,WelcomeBackActivity.class);
                    startActivity(main);

                }
            }
        });
        signInButton = (SignInButton) findViewById(R.id.googlesignb);
        mAuthListener = new FirebaseAuth.AuthStateListener() {

//Or else you will get null object error

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    Intent signedin = new Intent(getApplicationContext(), WelcomeBackActivity.class);
                    startActivity(signedin);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(MainActivity.this).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(getApplicationContext(), "got an error", Toast.LENGTH_LONG).show();
            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        mcallbackmanager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends","public_profile");


        loginButton.registerCallback(mcallbackmanager, mcallback);
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mcallbackmanager.onActivityResult(requestCode,resultCode,data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        Intent signedin=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(signedin);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        if(item.getItemId()==R.id.action_settings)
        {
            Intent i = new Intent( android.provider.Settings.ACTION_LOCALE_SETTINGS ); startActivity( i );

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);

    }
}
