package com.example.collegeapp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.collegeapp.model.College;

import com.example.collegeapp.model.Util;
import com.example.collegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText eTxtName, eTxtEmail, eTxtPassword;
    TextView txtLogin;
    Button btnRegister;
    College colleges;

    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    void initViews() {
        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);
        eTxtPassword = findViewById(R.id.editTextPassword);

        btnRegister = findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener((View.OnClickListener) this);
        txtLogin = findViewById(R.id.textViewLogin);
        txtLogin.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);

        colleges = new College();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser=auth.getCurrentUser();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonRegister) {
            colleges.name = eTxtName.getText().toString();
            colleges.email = eTxtEmail.getText().toString();
            colleges.password = eTxtPassword.getText().toString();

            if (Util.isInternetConnected(this)) {
                progressDialog.show();
                registerUser();
            } else {
                Toast.makeText(this, "Please Connect to Internet and Try Again", Toast.LENGTH_LONG).show();
            }
        } else {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }

    void registerUser() {
        progressDialog.show();
        auth.createUserWithEmailAndPassword(colleges.email, colleges.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                         /* Toast.makeText(RegistrationActivity.this,colleges.name+"Registered Sucessfully",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();*/
                            saveUserInCloudDB();
                        }
                    }
                });
    }

    void saveUserInCloudDB() {
       /* db.collection("Colleges").add(colleges)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isComplete()) {
                            Toast.makeText(RegistrationActivity.this, colleges.name+" Registered Sucessfully", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(RegistrationActivity.this,CollegeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                });*/
         firebaseUser= auth.getCurrentUser();
        db.collection("Colleges").document(firebaseUser.getUid()).set(colleges)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(RegistrationActivity.this, colleges.name + " Registered Successful", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        //Intent intent = new Intent(RegisterActivity.this, SuccessActivity.class);
                        Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

}