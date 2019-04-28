package com.example.collegeapp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    TextView collegeInfo, deadline,fees,contact_us;
    EditText etxtinf;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    void initViews(){
        deadline = findViewById(R.id.textViewDeadline);
        fees = findViewById(R.id.textViewFees);
        collegeInfo = findViewById(R.id.textViewcollege_info);
        contact_us = findViewById(R.id.textViewContact_us);
        etxtinf=findViewById(R.id.editTextinfo);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        deadline.setOnClickListener(this);
        fees.setOnClickListener(this);
        contact_us.setOnClickListener(this);
        collegeInfo.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initViews();
        saveInfoInDb();
    }
    void saveInfoInDb(){
        db.collection("Colleges").document(firebaseUser.getUid())
                .collection("Info").add(collegeInfo)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isComplete()){
                            Toast.makeText(InfoActivity.this, "Info Saved in DB", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id) {
            case R.id.textViewDeadline:
            Intent intent = new Intent(InfoActivity.this, DeadlineActivity.class);
            startActivity(intent);
            break;

            case R.id.textViewFees:
                Intent intent1= new Intent(InfoActivity.this,feesActivity.class);
                startActivity(intent1);
                break;

            case R.id.textViewContact_us:
                Intent intent2= new Intent(InfoActivity.this,ContactUsActivity.class);
                startActivity(intent2);
                break;

            case R.id.textViewcollege_info:
                Intent intent3= new Intent(InfoActivity.this,collegeInfoActivity.class);
                startActivity(intent3);
                break;

        }



    }
}
