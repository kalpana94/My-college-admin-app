package com.example.collegeapp.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.collegeapp.adapter.StudentAdapter;
import com.example.collegeapp.listener.OnRecyclerItemClickListener;


import com.example.collegeapp.model.Student;
import com.example.collegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class AllStudentActivity extends AppCompatActivity implements OnRecyclerItemClickListener {
    RecyclerView recyclerView;
    ArrayList<Student> studentArrayList;
    int position;
    StudentAdapter studentAdapter;
    Student student;

    ProgressDialog progressDialog;


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    void initViews() {

        recyclerView = findViewById(R.id.StudentRecyclerView);
        recyclerView.setAdapter(studentAdapter);


        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        student = new Student();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_student);
        initViews();
        fetchStudentsFromCloudDb();
    }

   void fetchStudentsFromCloudDb() {
       db.collection("students").get().addOnCompleteListener(AllStudentActivity.this, new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if (task.isComplete()) {
                   studentArrayList = new ArrayList<>();

                   QuerySnapshot querySnapshot = task.getResult();

                   List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();
                   for (DocumentSnapshot snapshot : documentSnapshots) {
                       String docId = snapshot.getId();
                       Student student = snapshot.toObject(Student.class);
                       student.docID = docId;
                       studentArrayList.add(student);
                       //       Courses courses = Snapshot.toObject(Colleges.class);
                       Log.i("size", Integer.toString(studentArrayList.size()));
                   }

                   getSupportActionBar().setTitle("Total Students: " + studentArrayList.size());
                   studentAdapter = new StudentAdapter(AllStudentActivity.this,R.layout.student, studentArrayList);
                   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllStudentActivity.this);
                   recyclerView.setAdapter(studentAdapter);
                   studentAdapter.setOnRecyclerItemClickListener((OnRecyclerItemClickListener) AllStudentActivity.this);

                   recyclerView.setLayoutManager(linearLayoutManager);

               } else {
                   Toast.makeText(AllStudentActivity.this, "Some Error", Toast.LENGTH_LONG).show();
               }

           }
       });
   }


       void showStudentDetails() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(student.Name + "Details:");
        builder.setMessage(student.toString());
        builder.setPositiveButton("Done", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void deleteStudentFromCloudDB(){
        db.collection("students").document(student.docID)
                .delete()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            Toast.makeText(AllStudentActivity.this,"Deletion Finished",Toast.LENGTH_LONG).show();
                            studentArrayList.remove(position);
                            studentAdapter.notifyDataSetChanged(); // Refresh Your RecyclerView
                        }else{
                            Toast.makeText(AllStudentActivity.this,"Deletion Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    void askForDeletion(){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("Delete "+student.Name);
        builder.setMessage("Are You Sure ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteStudentFromCloudDB();
            }
        });
        builder.setNegativeButton("Cancel",null);
        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
    }
    void showOptions() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        String[] items = {"View " + student.Name,"Delete " + student.Name,"Cancel"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                       Intent intent=new Intent(AllStudentActivity.this,StudentProfile.class);
                       startActivity(intent);
                        break;

                    case 1:
                        askForDeletion();
                        break;
                }
            }
        });
        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();

    }


    @Override
    public void onItemClick(int position) {
        this.position = position;
        student = studentArrayList.get(position);
        Toast.makeText(this,"You Clicked on Position:"+position,Toast.LENGTH_LONG).show();
        //showStudentDetails();
        showOptions();


    }
}
