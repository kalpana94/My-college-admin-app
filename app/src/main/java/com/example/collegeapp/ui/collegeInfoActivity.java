package com.example.collegeapp.ui;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collegeapp.model.CollegeInfo;
import com.example.collegeapp.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class collegeInfoActivity extends AppCompatActivity {
    EditText etxtinfo;
    Button btnsubmit;

    CollegeInfo collegeInfo;

    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    FirebaseFirestore db;

    ProgressDialog progressDialog;

    void initViews(){
        etxtinfo = findViewById(R.id.editTextinfo);
        btnsubmit= findViewById(R.id.buttonsubmit);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);

        collegeInfo = new CollegeInfo();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collegeInfo.info = etxtinfo.getText().toString();
                submitInformation();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_info);
        initViews();
    }
    void submitInformation(){
        progressDialog.show();
        db.collection("Info").document(firebaseUser.getUid()).collection("Collegeinfo").add(collegeInfo)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isComplete()){
                            Toast.makeText(collegeInfoActivity.this, "Collegeinfo Saved in DB", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        progressDialog.dismiss();
    }
}
