package com.example.collegeapp.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.example.collegeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    TextView txtTitle;

    TextToSpeech tts;
    void initViews(){
        txtTitle=findViewById(R.id.textViewTitle);

        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(tts.getEngines().size() == 0){
                    Toast.makeText(SplashActivity.this, "Please enable TTS in your settings", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    tts.setLanguage(Locale.US);
                    speak("Hey!Welcome to e-college");
                    //speak("Enter your name");
                }
            }
        });


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        auth = FirebaseAuth.getInstance();
       // tts();
        user = auth.getCurrentUser();
        getSupportActionBar().hide();
        if(user==null){
            handler.sendEmptyMessageDelayed(101,3000);
        }else{
            handler.sendEmptyMessageDelayed(202,3000);
        }
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==101){
                Intent intent=new Intent(SplashActivity.this,RegistrationActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }

        }
    };

    private void speak(String s) {
        tts.speak(s,TextToSpeech.QUEUE_FLUSH,null);
    }
    @Override
    protected void onPause() {
        super.onPause();
        tts.shutdown();
    }
}

