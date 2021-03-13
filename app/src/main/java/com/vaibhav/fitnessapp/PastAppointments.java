package com.vaibhav.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PastAppointments extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<AppointmentModel> past;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_appointments);

        recyclerView = findViewById(R.id.past);
        past = new ArrayList<>();
        Log.d("Past", past.toString());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("appointments")
                .whereEqualTo("uidPatient", FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null) {
                            for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                AppointmentModel appointmentModel = documentSnapshot.toObject(AppointmentModel.class);
                                if(appointmentModel.isRemove()) {
                                    past.add(documentSnapshot.toObject(AppointmentModel.class));
                                }
                            }
                            setAdapter(past);
                            Log.d("Past", past.toString());
                        }
                    }
                });
    }

    void setAdapter(ArrayList<AppointmentModel> arrayList) {
        AdapterPast adapterPast = new AdapterPast(PastAppointments.this, R.layout.card_past_appointment_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(PastAppointments.this);
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager1);
            recyclerView.setAdapter(adapterPast);
        } catch (Exception e) {

        }
    }
}