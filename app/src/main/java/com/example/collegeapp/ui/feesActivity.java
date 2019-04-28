package com.example.collegeapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.model.Fees;
import com.example.collegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class feesActivity extends AppCompatActivity {
    TextView newStudent,transerStudent;
    Button btnsavefee;
    Fees fees;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;


    void initViews(){
        newStudent = findViewById(R.id.newStudent);
        transerStudent=findViewById(R.id.transferStudent);
        btnsavefee = findViewById(R.id.buttonsavefee);

        fees = new Fees();

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        btnsavefee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fees.newstu =newStudent.getText().toString();
                fees.transferstu = transerStudent.getText().toString();
                savefeeinfo();
            }

        });


    }

    void savefeeinfo(){
        db.collection("Info").document(firebaseUser.getUid()).collection("Fees").add(fees)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isComplete()){
                            Toast.makeText(feesActivity.this, "fees Saved in DB", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);
        initViews();
    }
}
