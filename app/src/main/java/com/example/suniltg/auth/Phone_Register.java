package com.example.suniltg.auth;

import android.content.Intent;
import android.drm.ProcessedData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.msg91.sendotp.library.SendOtpVerification;
import com.msg91.sendotp.library.Verification;
import com.msg91.sendotp.library.VerificationListener;

public class Phone_Register extends AppCompatActivity implements VerificationListener{
    private EditText mobile,countrycode,verifcode;
    private Button verify1,verify2;
    private Verification mVerification;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone__register);
        mobile=(EditText)findViewById(R.id.mobile);
        countrycode=(EditText)findViewById(R.id.countrycode);
        textView=(TextView)findViewById(R.id.textView8);
        textView.setVisibility(View.INVISIBLE);
        verifcode=(EditText)findViewById(R.id.verifcode);
        verifcode.setVisibility(View.INVISIBLE);
        verify1=(Button)findViewById(R.id.button3);
        verify1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVerification = SendOtpVerification.createSmsVerification(getApplicationContext(),mobile.getText().toString(),Phone_Register.this , countrycode.getText().toString());

                mVerification.initiate();
            }
        });
        verify2=(Button)findViewById(R.id.button4);
        verify2.setVisibility(View.INVISIBLE);
        verify2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVerification.verify(verifcode.getText().toString());
            }
        });

    }

    @Override
    public void onInitiated(String response) {

        verifcode.setVisibility(View.VISIBLE);
        verify2.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onInitiationFailed(Exception paramException) {

    }

    @Override
    public void onVerified(String response) {
        Toast.makeText(this,getApplicationContext().getString(R.string.successver),Toast.LENGTH_LONG).show();
        startActivity(new Intent(Phone_Register.this,User_Register.class).putExtra("phone_no",countrycode.getText().toString()+mobile.getText().toString()));

    }

    @Override
    public void onVerificationFailed(Exception paramException) {
        Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.tryaga),Toast.LENGTH_SHORT).show();

    }
}
