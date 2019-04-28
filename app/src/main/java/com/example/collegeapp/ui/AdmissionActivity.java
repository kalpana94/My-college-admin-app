package com.example.collegeapp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.example.collegeapp.model.Admission;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdmissionActivity extends AppCompatActivity implements View.OnClickListener {
    TextView guidelines,feeStructure;

    Button btnForm;
    Admission admission;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

   public  void initViews(){
       guidelines = findViewById(R.id.textViewGuidelines);
       feeStructure=findViewById(R.id.textViewFees);
       btnForm = findViewById(R.id.buttonForms);

       admission=new Admission();

       firebaseAuth = FirebaseAuth.getInstance();
       db = FirebaseFirestore.getInstance();
       firebaseUser = firebaseAuth.getCurrentUser();

       guidelines.setOnClickListener(this);
       feeStructure.setOnClickListener(this);
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission);
        initViews();
        saveAdmissionInfoInDb();
    }
    void saveAdmissionInfoInDb(){
        db.collection("Colleges").document(firebaseUser.getUid())
                .collection("Admission").add(admission)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isComplete()){
                            Toast.makeText(AdmissionActivity.this, "Admission Info Saved in DB", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
       int id=v.getId();
       switch (id){
           case R.id.textViewguidlines:
               Intent intent= new Intent(AdmissionActivity.this,GuidelinesActivity.class);
               startActivity(intent);
               break;
           case R.id.textViewFees:
               Intent intent1= new Intent(AdmissionActivity.this, FeesStructureActivity.class);
               startActivity(intent1);
               break;
           case R.id.buttonForms:
               Intent intent2 = new Intent(AdmissionActivity.this,StudentProfile.class);
               startActivity(intent2);
               break;


       }

    }
}
