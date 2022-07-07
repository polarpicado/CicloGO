package com.joao.ciclogo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Gociclo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gociclo);

        TimerTask tarea= new TimerTask(){
            @Override
            public void run(){
            Intent intent= new Intent(Gociclo.this,MainActivity.class);
            startActivity(intent);
            finish();
            }
        };
        Timer tiempo= new Timer();
        tiempo.schedule(tarea,5000);
    }
}