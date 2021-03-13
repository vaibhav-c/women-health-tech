package com.vaibhav.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewConfirmAppointments extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<AppointmentModel> confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_confirm_appointments);

        recyclerView = findViewById(R.id.confirmed);

        confirm = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("appointments")
                .whereEqualTo("confirmStatus", 1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null) {
                            for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                if(!documentSnapshot.toObject(AppointmentModel.class).isRemove()) {
                                    confirm.add(documentSnapshot.toObject(AppointmentModel.class));
                                }
                            }
                        }
                        setAdapter(confirm);
                    }
                });


    }
    void setAdapter(ArrayList<AppointmentModel> arrayList) {
        AdapterPatientAppointment adapterPatientAppointment = new AdapterPatientAppointment(ViewConfirmAppointments.this, R.layout.card_patient_appointment_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(ViewConfirmAppointments.this);
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager1);
            recyclerView.setAdapter(adapterPatientAppointment);
        } catch (Exception e) {

        }
    }
}