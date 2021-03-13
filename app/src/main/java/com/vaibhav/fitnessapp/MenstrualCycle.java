package com.vaibhav.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MenstrualCycle extends AppCompatActivity {

    Button addcycle;
    RecyclerView recyclerView;
    ArrayList<Cycle> menstrualcycle;
    TextView OTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menstrual_cycle);

        addcycle = findViewById(R.id.addcycle);
        recyclerView = findViewById(R.id.recyclerView);
        menstrualcycle = new ArrayList<>();
        final int[] otp = new int[1];
        otp[0] = -1;
        OTP = findViewById(R.id.OTP);
        OTP.setText("No Pending Requirements");
        addcycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenstrualCycle.this, "This date is noted as the start date", Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MenstrualCycle.this);
                builder.setTitle("Free pads for All");
                builder.setMessage("Would you like to order sanitary pads?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Firebase
                        Cycle cycle = new Cycle();
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        cycle.setDateStart(formatter.format(date));
                        cycle.setUid(FirebaseAuth.getInstance().getUid());
                        cycle.setPads(true);
                        int x = (int)(Math.random() * (10000000 - 100000 + 1) + 100000);
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("MenstrualCycles")
                                .document(FirebaseAuth.getInstance().getUid())
                                .collection("MenstrualCycles")
                                .document(x + "" + FirebaseAuth.getInstance().getUid())
                                .set(cycle)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(MenstrualCycle.this, "Noted", Toast.LENGTH_LONG).show();
                                    }
                                });

                        FreeOrder freeOrder = new FreeOrder();
                        freeOrder.setOrderTime(Timestamp.now().toDate().toString());
                        freeOrder.setUid(FirebaseAuth.getInstance().getUid());
                        freeOrder.setOTP((int)(Math.random() * (10000000 - 100000 + 1) + 100000));
                        db.collection("SanitaryNapkins")
                                .document(FirebaseAuth.getInstance().getUid())
                                .set(freeOrder)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(MenstrualCycle.this, "Noted", Toast.LENGTH_LONG).show();
                                    }
                                });


                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Cycle cycle = new Cycle();
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        cycle.setDateStart(formatter.format(date));
                        cycle.setUid(FirebaseAuth.getInstance().getUid());
                        cycle.setPads(false);
                        int x = (int)(Math.random() * (10000000 - 100000 + 1) + 100000);
                        cycle.setId(x + "" + FirebaseAuth.getInstance().getUid());
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("MenstrualCycles")
                                .document(FirebaseAuth.getInstance().getUid())
                                .collection("MenstrualCycles")
                                .document(x + "" + FirebaseAuth.getInstance().getUid())
                                .set(cycle)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(MenstrualCycle.this, "Noted", Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                });
                builder.show();
            }
        });
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("MenstrualCycles")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("MenstrualCycles")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                menstrualcycle.add(documentSnapshot.toObject(Cycle.class));
                            }
                            setAdapter(menstrualcycle);
                            if(menstrualcycle.size() != 0) {
                                try {
                                    db.collection("SanitaryNapkins")
                                            .document(FirebaseAuth.getInstance().getUid())
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.getResult() != null) {
                                                        if(task.getResult().toObject(FreeOrder.class).getDeliveryTime().length() == 0) {
                                                            otp[0] = task.getResult().toObject(FreeOrder.class).getOTP();
                                                            OTP.setText("OTP : " + otp[0]);
                                                        } else {
                                                            otp[0] = -1;
                                                        }
                                                    }
                                                }
                                            });
                                } catch(Exception e) {
                                    otp[0] = -1;
                                }
                            }
                        }
                    }
                });


    }

    void setAdapter(ArrayList<Cycle> arrayList) {
        AdapterCycle adapterCycle = new AdapterCycle(MenstrualCycle.this, R.layout.card_cycle, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(MenstrualCycle.this);
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager1);
            recyclerView.setAdapter(adapterCycle);
        } catch (Exception e) {

        }
    }
}