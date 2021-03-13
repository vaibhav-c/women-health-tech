package com.vaibhav.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewPrescriptions extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Prescription> pres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prescriptions);

        recyclerView = findViewById(R.id.pres);

        pres = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("prescription")
                .whereEqualTo("patientUid", FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null) {
                            for(DocumentSnapshot documentSnapshot : task.getResult()) {
                               pres.add(documentSnapshot.toObject(Prescription.class));
                            }
                        }
                        setAdapter(pres);
                    }
                });

    }
    void setAdapter(ArrayList<Prescription> arrayList) {
        AdapterPrescription adapterPrescription = new AdapterPrescription(ViewPrescriptions.this, R.layout.card_prescription_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(ViewPrescriptions.this);
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager1);
            recyclerView.setAdapter(adapterPrescription);
        } catch (Exception e) {

        }
    }
}