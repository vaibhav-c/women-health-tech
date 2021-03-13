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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeDoctor extends AppCompatActivity {

    RecyclerView confirmAppointment, newAppointment;
    ArrayList<AppointmentModel> confirmAppointments;
    ArrayList<AppointmentModel> newAppointments;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_doctor);

        confirmAppointment = findViewById(R.id.confirmAppointment);
        newAppointment = findViewById(R.id.newAppointment);
        logout = findViewById(R.id.logout);

        confirmAppointments = new ArrayList<>();
        newAppointments = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("appointments")
                .whereEqualTo("doctorUid", FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null) {
                            for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                AppointmentModel appointmentModel = documentSnapshot.toObject(AppointmentModel.class);
                                if(!appointmentModel.isRemove()) {
                                    if (appointmentModel.getConfirmStatus() == 0) {
                                        newAppointments.add(appointmentModel);
                                    } else {
                                        confirmAppointments.add(appointmentModel);
                                    }
                                }
                            }
                        }
                        setAdapterConfirm(confirmAppointments);
                        setAdapterNew(newAppointments);
                    }
                });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("loggedInStatus", Context.MODE_PRIVATE);
                SharedPreferences.Editor editStatus = sharedPreferences.edit();
                editStatus.putBoolean("loggedInSuperAdmin", false);
                editStatus.apply();
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeDoctor.this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = getSharedPreferences("loggedInStatus", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editStatus = sharedPreferences.edit();
                        editStatus.putBoolean("loggedIn", false);
                        editStatus.apply();
                        SharedPreferences sharedPreferences1 = getSharedPreferences("Role", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editStatus1 = sharedPreferences1.edit();
                        editStatus1.putString("Role", "None");
                        editStatus1.apply();
                        Intent intent1 = new Intent(HomeDoctor.this, ChooseRole.class);
                        startActivity(intent1);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });



    }

    void setAdapterNew(ArrayList<AppointmentModel> arrayList) {
        AdapterNew adapterNew = new AdapterNew(HomeDoctor.this, R.layout.card_appointment_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeDoctor.this);
        try {
            newAppointment.setHasFixedSize(true);
            newAppointment.setLayoutManager(layoutManager1);
            newAppointment.setAdapter(adapterNew);
        } catch (Exception e) {

        }
    }

    void setAdapterConfirm(ArrayList<AppointmentModel> arrayList) {
        AdapterConfirm adapterConfirm = new AdapterConfirm(HomeDoctor.this, R.layout.card_confirm_appointment_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeDoctor.this);
        try {
            confirmAppointment.setHasFixedSize(true);
            confirmAppointment.setLayoutManager(layoutManager1);
            confirmAppointment.setAdapter(adapterConfirm);
        } catch (Exception e) {

        }
    }
}