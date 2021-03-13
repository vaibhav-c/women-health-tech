package com.vaibhav.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.logo);
        CountDownTimer t = new CountDownTimer(3000, 100) {
            public void onTick(long millisUntilFinished) {
                x++;
                Log.d("x=", x +"");
                img.setAlpha((float) x/30);
            }

            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, ChooseRole.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}