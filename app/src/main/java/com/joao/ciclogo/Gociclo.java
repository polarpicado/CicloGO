package com.joao.ciclogo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class Gociclo extends Activity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gociclo);
        mAuth = FirebaseAuth.getInstance();

        TimerTask tarea= new TimerTask(){
            @Override
            public void run(){
                if(mAuth.getCurrentUser()!=null){
                    Intent intent = new Intent(Gociclo.this, menuActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(Gociclo.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        Timer tiempo= new Timer();
        tiempo.schedule(tarea,5000);
    }
}