package com.vaibhav.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.razorpay.PaymentResultListener;

import java.util.ArrayList;

public class PayForOrder extends AppCompatActivity {

    ArrayList<Prescription> presOld;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_for_order);

        presOld = new ArrayList<>();
        recyclerView = findViewById(R.id.current);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("prescription")
                .whereEqualTo("patientUid", FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null) {
                            for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                if(documentSnapshot.toObject(Prescription.class).getStatus() == 2) {
                                    presOld.add(documentSnapshot.toObject(Prescription.class));
                                }
                            }
                        }
                        Log.d("PresOld", presOld.toString());
                        setAdapterDelivered(presOld);
                    }
                });
    }

    void setAdapterDelivered(ArrayList<Prescription> arrayList) {
        AdapterPrescriptionPayNow adapterPrescriptionPayNow = new AdapterPrescriptionPayNow(PayForOrder.this, R.layout.card_pay_order_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(PayForOrder.this);
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager1);
            recyclerView.setAdapter(adapterPrescriptionPayNow);
        } catch (Exception e) {

        }
    }
}