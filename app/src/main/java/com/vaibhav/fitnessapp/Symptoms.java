package com.vaibhav.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Symptoms extends AppCompatActivity {

    TextView q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, result, advice;
    Button y1, y2, y3, y4, y5, y6, y7, y8, y9 ,y10, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, submit;
    int x = 0;
    boolean isDiabetes = false;
    boolean isBP = false;
    boolean isAsthama = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        result = findViewById(R.id.result);
        advice = findViewById(R.id.advice);

        q1 = findViewById(R.id.q1);
        y1 = findViewById(R.id.y1);
        n1 = findViewById(R.id.n1);
        a1 = findViewById(R.id.a1);

        q2 = findViewById(R.id.q2);
        y2 = findViewById(R.id.y2);
        n2 = findViewById(R.id.n2);
        a2 = findViewById(R.id.a2);

        q3 = findViewById(R.id.q3);
        y3 = findViewById(R.id.y3);
        n3 = findViewById(R.id.n3);
        a3 = findViewById(R.id.a3);

        q4 = findViewById(R.id.q4);
        y4 = findViewById(R.id.y4);
        n4 = findViewById(R.id.n4);
        a4 = findViewById(R.id.a4);

        q5 = findViewById(R.id.q5);
        y5 = findViewById(R.id.y5);
        n5 = findViewById(R.id.n5);
        a5 = findViewById(R.id.a5);

        q6 = findViewById(R.id.q6);
        y6 = findViewById(R.id.y6);
        n6 = findViewById(R.id.n6);
        a6 = findViewById(R.id.a6);

        q7 = findViewById(R.id.q7);
        y7 = findViewById(R.id.y7);
        n7 = findViewById(R.id.n7);
        a7 = findViewById(R.id.a7);

        q8 = findViewById(R.id.q8);
        y8 = findViewById(R.id.y8);
        n8 = findViewById(R.id.n8);
        a8 = findViewById(R.id.a8);

        q9 = findViewById(R.id.q9);
        y9 = findViewById(R.id.y9);
        n9 = findViewById(R.id.n9);
        a9 = findViewById(R.id.a9);

        q10 = findViewById(R.id.q10);
        y10 = findViewById(R.id.y10);
        n10 = findViewById(R.id.n10);
        a10 = findViewById(R.id.a10);

        submit = findViewById(R.id.submit);

        y1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x++;
                Toast.makeText(Symptoms.this, "Yes", Toast.LENGTH_SHORT).show();
                y1.setVisibility(View.GONE);
                n1.setVisibility(View.GONE);
                a1.setVisibility(View.VISIBLE);
                a1.setText("Yes");
            }
        });

        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "No", Toast.LENGTH_SHORT).show();
                y1.setVisibility(View.GONE);
                n1.setVisibility(View.GONE);
                a1.setVisibility(View.VISIBLE);
                a1.setText("No");
            }
        });

        y2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x++;
                Toast.makeText(Symptoms.this, "Yes", Toast.LENGTH_SHORT).show();
                y2.setVisibility(View.GONE);
                n2.setVisibility(View.GONE);
                a2.setVisibility(View.VISIBLE);
                a2.setText("Yes");
            }
        });

        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "No", Toast.LENGTH_SHORT).show();
                y2.setVisibility(View.GONE);
                n2.setVisibility(View.GONE);
                a2.setVisibility(View.VISIBLE);
                a2.setText("No");
            }
        });

        y3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = x + 2;
                Toast.makeText(Symptoms.this, "Yes", Toast.LENGTH_SHORT).show();
                y3.setVisibility(View.GONE);
                n3.setVisibility(View.GONE);
                a3.setVisibility(View.VISIBLE);
                a3.setText("Yes");
            }
        });

        n3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "No", Toast.LENGTH_SHORT).show();
                y3.setVisibility(View.GONE);
                n3.setVisibility(View.GONE);
                a3.setVisibility(View.VISIBLE);
                a3.setText("No");
            }
        });

        y4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = x + 2;
                Toast.makeText(Symptoms.this, "Yes", Toast.LENGTH_SHORT).show();
                y4.setVisibility(View.GONE);
                n4.setVisibility(View.GONE);
                a4.setVisibility(View.VISIBLE);
                a4.setText("Yes");
            }
        });

        n4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "No", Toast.LENGTH_SHORT).show();
                y4.setVisibility(View.GONE);
                n4.setVisibility(View.GONE);
                a4.setVisibility(View.VISIBLE);
                a4.setText("No");
            }
        });

        y5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = x + 2;
                Toast.makeText(Symptoms.this, "Yes", Toast.LENGTH_SHORT).show();
                y5.setVisibility(View.GONE);
                n5.setVisibility(View.GONE);
                a5.setVisibility(View.VISIBLE);
                a5.setText("Yes");
            }
        });

        n5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "No", Toast.LENGTH_SHORT).show();
                y5.setVisibility(View.GONE);
                n5.setVisibility(View.GONE);
                a5.setVisibility(View.VISIBLE);
                a5.setText("No");
            }
        });

        y6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x++;
                Toast.makeText(Symptoms.this, "Yes", Toast.LENGTH_SHORT).show();
                y6.setVisibility(View.GONE);
                n6.setVisibility(View.GONE);
                a6.setVisibility(View.VISIBLE);
                a6.setText("Yes");
            }
        });

        n6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "No", Toast.LENGTH_SHORT).show();
                y6.setVisibility(View.GONE);
                n6.setVisibility(View.GONE);
                a6.setVisibility(View.VISIBLE);
                a6.setText("No");
            }
        });

        y7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "Yes", Toast.LENGTH_SHORT).show();
                y7.setVisibility(View.GONE);
                n7.setVisibility(View.GONE);
                a7.setVisibility(View.VISIBLE);
                a7.setText("Yes");
                isDiabetes = true;
            }
        });

        n7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "No", Toast.LENGTH_SHORT).show();
                y7.setVisibility(View.GONE);
                n7.setVisibility(View.GONE);
                a7.setVisibility(View.VISIBLE);
                a7.setText("No");
            }
        });

        y8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "Yes", Toast.LENGTH_SHORT).show();
                y8.setVisibility(View.GONE);
                n8.setVisibility(View.GONE);
                a8.setText("Yes");
                a8.setVisibility(View.VISIBLE);
                isBP = true;
            }
        });

        n8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "No", Toast.LENGTH_SHORT).show();
                y8.setVisibility(View.GONE);
                n8.setVisibility(View.GONE);
                a8.setVisibility(View.VISIBLE);
                a8.setText("No");
            }
        });

        y9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "Yes", Toast.LENGTH_SHORT).show();
                y9.setVisibility(View.GONE);
                n9.setVisibility(View.GONE);
                a9.setVisibility(View.VISIBLE);
                isAsthama = true;
                a9.setText("Yes");
            }
        });

        n9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "No", Toast.LENGTH_SHORT).show();
                y9.setVisibility(View.GONE);
                n9.setVisibility(View.GONE);
                a9.setVisibility(View.VISIBLE);
                a9.setText("No");
            }
        });

        y10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "Yes", Toast.LENGTH_SHORT).show();
                y10.setVisibility(View.GONE);
                n10.setVisibility(View.GONE);
                a10.setVisibility(View.VISIBLE);
                a10.setText("Yes");
            }
        });

        n10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Symptoms.this, "No", Toast.LENGTH_SHORT).show();
                y10.setVisibility(View.GONE);
                n10.setVisibility(View.GONE);
                a10.setVisibility(View.VISIBLE);
                a10.setText("No");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advice.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);
                result.setText(((x * 100) / 9) + "%");
                if(isAsthama || isBP || isDiabetes) {
                    if(isDiabetes) {
                        if(isAsthama && isBP) {
                            advice.setText("We recommend you to get tested if you have over 30% chances. Since you are in the high risk zone due to diabetes, hypertension and lung disorders.");
                        } else if(isAsthama) {
                            advice.setText("We recommend you to get tested if you have over 40% chances. Since you are in the high risk zone due to diabetes and lung disorders.");
                        } else if(isBP) {
                            advice.setText("We recommend you to get tested if you have over 40% chances. Since you are in the high risk zone due to diabetes and hypertension.");
                        } else {
                            advice.setText("We recommend you to get tested if you have over 40% chances. Since you are in the high risk zone due to diabetes.");
                        }
                    } else if(isAsthama) {
                        if(isDiabetes && isBP) {
                            advice.setText("We recommend you to get tested if you have over 30% chances. Since you are in the high risk zone due to diabetes, hypertension and lung disorders.");
                        } else if(isDiabetes) {
                            advice.setText("We recommend you to get tested if you have over 40% chances. Since you are in the high risk zone due to diabetes and lung disorders.");
                        } else if(isBP) {
                            advice.setText("We recommend you to get tested if you have over 40% chances. Since you are in the high risk zone due to lung disorders and hypertension.");
                        } else {
                            advice.setText("We recommend you to get tested if you have over 40% chances. Since you are in the high risk zone due to lung disorders.");
                        }
                    } else if(isBP) {
                        if(isAsthama && isDiabetes) {
                            advice.setText("We recommend you to get tested if you have over 30% chances. Since you are in the high risk zone due to diabetes, hypertension and lung disorders.");
                        } else if(isAsthama) {
                            advice.setText("We recommend you to get tested if you have over 40% chances. Since you are in the high risk zone due to hypertension and lung disorders.");
                        } else if(isDiabetes) {
                            advice.setText("We recommend you to get tested if you have over 40% chances. Since you are in the high risk zone due to diabetes and hypertension.");
                        } else {
                            advice.setText("We recommend you to get tested if you have over 40% chances. Since you are in the high risk zone due to hypertension.");
                        }
                    }
                } else {
                    advice.setText("We recommend you to get tested if you have over 50% chances. Since you are in the low risk zone.");
                }
            }
        });

    }
}