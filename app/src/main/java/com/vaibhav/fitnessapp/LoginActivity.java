package com.vaibhav.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    EditText emailId, password;
    Button login;
    TextView register;
    ImageView registerImage;
    boolean loggedIn = false;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailId = findViewById(R.id.emailId);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerText);
        registerImage = findViewById(R.id.registerImage);

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
                Intent intent = new Intent(LoginActivity.this, DrawerUser.class);
                startActivity(intent);
                finish();
            } else if(role.equals("Doctor")) {
                Intent intent = new Intent(LoginActivity.this, HomeDoctor.class);
                startActivity(intent);
                finish();
            } else if(role.equals("Chemist")) {
                Intent intent = new Intent(LoginActivity.this, HomeChemist.class);
                startActivity(intent);
                finish();
            }
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(Patterns.EMAIL_ADDRESS.matcher(emailId.getText().toString().trim()).matches())) {
                    emailId.setText("");
                    emailId.setFocusable(true);
                    emailId.setError("Invalid Email");
                    return;
                }
                else if (password.getText().toString().length() < 7) {
                    password.setText("");
                    password.setFocusable(true);
                    password.setError("Password is too short");
                    return;
                }
                loginUserfromFirebase();
            }
        });
    }

    void loginUserfromFirebase() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(emailId.getText().toString().trim(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Success!!", Toast.LENGTH_LONG).show();
                            SharedPreferences sharedPreferences = getSharedPreferences("loggedInStatus", MODE_PRIVATE);
                            SharedPreferences.Editor editStatus = sharedPreferences.edit();
                            editStatus.putBoolean("loggedIn", true);
                            editStatus.apply();
                            if(role.equals("User")) {
                                Intent intent = new Intent(LoginActivity.this, DrawerUser.class);
                                startActivity(intent);
                                finish();
                            } else if(role.equals("Doctor")) {
                                Intent intent = new Intent(LoginActivity.this, HomeDoctor.class);
                                startActivity(intent);
                                finish();
                            } else if(role.equals("Chemist")) {
                                Intent intent = new Intent(LoginActivity.this, HomeChemist.class);
                                startActivity(intent);
                                finish();
                            }
                            finish();
                        } else {
                            if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                                emailId.setText("");
                                password.setText("");
                            }else if(task.getException() instanceof FirebaseAuthInvalidUserException){
                                Toast.makeText(LoginActivity.this,"User Not Found",Toast.LENGTH_LONG).show();
                                emailId.setText("");
                                password.setText("");
                            }else {
                                Toast.makeText(LoginActivity.this, "Something Went Wrong !!", Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });
    }
}