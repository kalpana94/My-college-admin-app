package com.example.collegeapp.ui;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CollegeActivity extends AppCompatActivity {
    EditText eTxtName,eTxtEmail,eTxtCity,eTxtState;
    TextView txtTitle;
    Button btnSubmit;
    College colleges;
    boolean updateMode;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    FirebaseFirestore db;

    void initViews() {
        txtTitle = findViewById(R.id.textViewTitle);
        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);
        eTxtCity = findViewById(R.id.editTextCity);
        eTxtState = findViewById(R.id.editTextState);

        btnSubmit = findViewById(R.id.buttonAdd);

        colleges = new College();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colleges.name = eTxtName.getText().toString();
                colleges.email=eTxtEmail.getText().toString();
                colleges.city = eTxtCity.getText().toString();
                colleges.state = eTxtState.getText().toString();
                if (Util.isInternetConnected(CollegeActivity.this)) {
                    SaveCollegesInCloudDb();
                } else {
                    Toast.makeText(CollegeActivity.this, "Please Connect to Internet and Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });
        Intent rcv = getIntent();
        updateMode = rcv.hasExtra("keyCollege");
        if (updateMode) {
            getSupportActionBar().setTitle("E-College");
            colleges = (College) rcv.getSerializableExtra("keyCollege");
            txtTitle.setText("Update College");
            eTxtName.setText(colleges.name);
            eTxtEmail.setText(colleges.email);
            eTxtCity.setText(colleges.city);
            eTxtState.setText(colleges.state);
            btnSubmit.setText("Update College");

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);
        getSupportActionBar().setTitle("E-College");
        initViews();
    }
    void SaveCollegesInCloudDb() {
        if (updateMode) {
            db.collection("Colleges").document(colleges.docID)
                    .set(colleges)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isComplete()) {
                                Toast.makeText(CollegeActivity.this, "Updation Finished", Toast.LENGTH_LONG).show();
                               //Intent intent = new Intent(CollegeActivity.this, CoursesActivity.class);
                                Intent intent1=new Intent(CollegeActivity.this,AllCollegeActivity.class);
                                startActivity(intent1);
                               // startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(CollegeActivity.this, "Updation Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 101, 1, "All Colleges");
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ();
        if (id == 101) {
            Intent intent = new Intent(CollegeActivity.this, AllCollegeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    void clearFields() {
        eTxtName.setText("");
        eTxtCity.setText("");
        eTxtState.setText("");
        eTxtEmail.setText("");
    }


}
