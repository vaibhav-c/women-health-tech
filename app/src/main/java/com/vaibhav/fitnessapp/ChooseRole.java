package com.vaibhav.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChooseRole extends AppCompatActivity {

    ImageView imgDr, imgChemist, imgUser;
    Button buttonDr, buttonChemist, buttonUser;
    boolean loggedIn;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("loggedInStatus", MODE_PRIVATE);
            loggedIn = sharedPreferences.getBoolean("loggedIn", false);
            SharedPreferences sharedPreferences1 = getSharedPreferences("Role", MODE_PRIVATE);
            role = sharedPreferences1.getString("Role", "");
            Log.d("MSg", "" + loggedIn);
        } catch(Exception e) {
            Log.d("MSg catch", "" + loggedIn);
        }

        if(loggedIn) {
            if(role.equals("User")) {
                Intent intent = new Intent(ChooseRole.this, DrawerUser.class);
                startActivity(intent);
                finish();
            } else if(role.equals("Doctor")) {
                Intent intent = new Intent(ChooseRole.this, HomeDoctor.class);
                startActivity(intent);
                finish();
            } else if(role.equals("Chemist")) {
                Intent intent = new Intent(ChooseRole.this, HomeChemist.class);
                startActivity(intent);
                finish();
            }
        }

        imgDr = findViewById(R.id.drImg);
        imgChemist = findViewById(R.id.imgChemist);
        imgUser = findViewById(R.id.imgUser);

        buttonDr = findViewById(R.id.buttonDr);
        buttonUser = findViewById(R.id.buttonUser);
        buttonChemist = findViewById(R.id.buttonChemist);

        buttonChemist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseRole.this, RegisterChemistActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("Role", MODE_PRIVATE);
                SharedPreferences.Editor editStatus = sharedPreferences.edit();
                editStatus.putString("Role", "Chemist");
                editStatus.apply();
                startActivity(intent);
                finish();
            }
        });

        imgChemist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseRole.this, RegisterChemistActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("Role", MODE_PRIVATE);
                SharedPreferences.Editor editStatus = sharedPreferences.edit();
                editStatus.putString("Role", "Chemist");
                editStatus.apply();
                startActivity(intent);
                finish();
            }
        });

        buttonDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseRole.this, RegisterDoctorActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("Role", MODE_PRIVATE);
                SharedPreferences.Editor editStatus = sharedPreferences.edit();
                editStatus.putString("Role", "Doctor");
                editStatus.apply();
                startActivity(intent);
                finish();
            }
        });

        imgDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseRole.this, RegisterDoctorActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("Role", MODE_PRIVATE);
                SharedPreferences.Editor editStatus = sharedPreferences.edit();
                editStatus.putString("Role", "Doctor");
                editStatus.apply();
                startActivity(intent);
                finish();
            }
        });

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseRole.this, RegisterActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("Role", MODE_PRIVATE);
                SharedPreferences.Editor editStatus = sharedPreferences.edit();
                editStatus.putString("Role", "User");
                editStatus.apply();
                startActivity(intent);
                finish();
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseRole.this, RegisterActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("Role", MODE_PRIVATE);
                SharedPreferences.Editor editStatus = sharedPreferences.edit();
                editStatus.putString("Role", "User");
                editStatus.apply();
                startActivity(intent);
                finish();
            }
        });
    }
}