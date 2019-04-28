package com.example.collegeapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collegeapp.model.Guidelines;
import com.example.collegeapp.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class GuidelinesActivity extends AppCompatActivity {
      EditText etxtguideline;
      Button btnview;

      Guidelines guidelines;

      FirebaseUser firebaseUser;
      FirebaseAuth auth;
      FirebaseFirestore db;

    void initViews(){
        etxtguideline =findViewById(R.id.editTextguidelines);
        btnview = findViewById(R.id.buttonView);

        guidelines=new Guidelines();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              guidelines.guideln = etxtguideline.getText().toString();
              saveguidelines();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidelines);
        initViews();
    }
    void saveguidelines(){
        db.collection("Admission").document(firebaseUser.getUid())
                .collection("Guidelines").add(guidelines)
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isComplete()){
                            Toast.makeText(GuidelinesActivity.this, "Guidelines Saved in DB", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
