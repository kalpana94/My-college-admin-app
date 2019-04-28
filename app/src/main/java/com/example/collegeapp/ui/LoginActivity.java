package com.example.collegeapp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.collegeapp.model.College;

import com.example.collegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText eTxtEmail, eTxtPassword;
    Button btnLogin;
    College colleges;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    void initViews(){
        eTxtEmail = findViewById(R.id.editTextEmail);
        eTxtPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        colleges = new College();
        btnLogin.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity .this, HomeActivity.class));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    @Override
    public void onClick(View v) {
        String email = eTxtEmail.getText().toString().trim();
        if(!email.matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+
                "\\@"+
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
                "("+
                "\\."+
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+
                ")+")){
            eTxtEmail.setError("Invalid Email Address");
        }
        if (eTxtPassword.getText().toString().trim().length() < 6) {
            eTxtPassword.setError("Minimum 6 Letters!!");
        }
        if (eTxtEmail.getText().toString().trim().length() == 0) {
            eTxtEmail.setError("Email is not entered");
            eTxtEmail.requestFocus();
        }
        if (eTxtPassword.getText().toString().trim().length() == 0) {
            eTxtPassword.setError("Password is not entered");
            eTxtPassword.requestFocus();
        }
        else {
            colleges.email = eTxtEmail.getText().toString();
            colleges.password = eTxtPassword.getText().toString();
            loginUser();
        }



    }
    void loginUser(){
        progressDialog.show();
        //Toast.makeText(getApplicationContext(),"Login Clicked2",Toast.LENGTH_LONG).show();
        auth.signInWithEmailAndPassword(colleges.email,colleges.password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.i("test",FirebaseAuth.getInstance().getUid());
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    //finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }
        });
    }

}


