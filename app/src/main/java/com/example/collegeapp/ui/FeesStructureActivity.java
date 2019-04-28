package com.example.collegeapp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.example.collegeapp.model.Fees;
import com.example.collegeapp.model.Feesstructure;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FeesStructureActivity extends AppCompatActivity {
    TextView txtpaymnt;
    Feesstructure feesstructure;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    void initViews(){
         txtpaymnt= findViewById(R.id.textViewpayment);
         feesstructure = new Feesstructure();

         txtpaymnt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent= new Intent(FeesStructureActivity.this,GuidelinesActivity.class);
                 startActivity(intent);

             }
         });
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_structure);
        initViews();
        saveFreestructureinDb();
    }
    void saveFreestructureinDb(){
        db.collection("Admission").document(firebaseUser.getUid())
                .collection("FeeStructure").add(feesstructure)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isComplete()){
                            Toast.makeText(FeesStructureActivity.this, "feestructure Saved in DB", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
