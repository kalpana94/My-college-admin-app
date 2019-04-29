package com.example.collegeapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.example.collegeapp.R;
import com.google.firebase.auth.FirebaseAuth;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth auth;
    Button btnaddclg,btnviewclg,btnviewstd,btnviewpaymnt;

    void initViews(){
        btnaddclg = findViewById(R.id.buttonaddcollege);
        btnviewclg=findViewById(R.id.buttonviewcolleges);
        btnviewstd = findViewById(R.id.buttonViewstudent);
        btnviewpaymnt=findViewById(R.id.buttonviewPayment);

        btnaddclg.setOnClickListener(this);
        btnviewclg.setOnClickListener(this);
        btnviewstd.setOnClickListener(this);
        btnviewpaymnt.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("E-College");
        auth=FirebaseAuth.getInstance();
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,101,0,"Logout");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==101){
            auth.signOut();// Clear Logged In Users Data from Shared Preferences
            Intent intent=new Intent(HomeActivity.this,SplashActivity.class);
            startActivity(intent);
            finish();

        }else{
            Intent intent=new Intent(HomeActivity.this,AllCollegeActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.buttonaddcollege:
                Intent intent= new Intent(HomeActivity.this, CollegeActivity.class);
                startActivity(intent);
                break;

            case R.id.buttonviewcolleges:
                Intent intent1= new Intent(HomeActivity.this,AllCollegeActivity.class);
                startActivity(intent1);
                break;
            case R.id.buttonViewstudent:
               Intent intent2= new Intent(HomeActivity.this,AllStudentActivity.class);
                startActivity(intent2);
                break;
            case R.id.buttonviewPayment:
                //Intent intent3= new Intent(HomeActivity.this,AllCollegeActivity.class);
                //startActivity(intent3);
                break;
        }

    }
}
