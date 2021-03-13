package com.vaibhav.fitnessapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AdapterPrescriptionPayNow extends RecyclerView.Adapter<AdapterPrescriptionPayNow. Holder> {

    Context context;
    int resource;
    ArrayList<Prescription> ArrayList;

    public AdapterPrescriptionPayNow(Context context, int resource, ArrayList<Prescription>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterPrescriptionPayNow. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterPrescriptionPayNow. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPrescriptionPayNow. Holder holder, int position) {
        Prescription prescription = ArrayList.get(position);

        Log.d("Pres", prescription.toString());
        holder.afterMeds.setText(prescription.getAfterBreakfastMeds());
        holder.morningMeds.setText(prescription.getMorningMeds());
        holder.eveningMeds.setText(prescription.getEveningMeds());
        holder.nightMeds.setText(prescription.getNightMeds());
        holder.lunchMeds.setText(prescription.getAfterLunchMeds());
        holder.orderValue.setText("Order Value: " + prescription.getOrderValue());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("chemists")
                .document(prescription.getUidChemist())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult() != null) {
                            Chemist chemist = task.getResult().toObject(Chemist.class);
                            holder.name.setText("Chemist: " + chemist.getName());
                            holder.address.setText("Address: " + chemist.getAddress() + ", " + chemist.getCity() + ", " + chemist.getState() + "\n" + chemist.getPhone());
                        }
                    }
                });
        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("users")
                        .document(FirebaseAuth.getInstance().getUid())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.getResult() != null) {
                                    if(task.getResult().toObject(User.class).getWallet() < prescription.getOrderValue()) {
                                        Toast.makeText(context, "Insufficient Balance in Wallet", Toast.LENGTH_LONG).show();
                                    } else {
                                        FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                                        db1.collection("users")
                                                .document(FirebaseAuth.getInstance().getUid())
                                                .update("wallet", task.getResult().toObject(User.class).getWallet() - prescription.getOrderValue())
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        //if(task.getResult() != null) {
                                                        Toast.makeText(context, "Processing...", Toast.LENGTH_LONG).show();
                                                            FirebaseFirestore db2 = FirebaseFirestore.getInstance();
                                                            db2.collection("chemists")
                                                                    .document(prescription.getUidChemist())
                                                                    .get()
                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                            if (task.getResult() != null) {
                                                                                FirebaseFirestore db3 = FirebaseFirestore.getInstance();
                                                                                db3.collection("chemists")
                                                                                        .document(prescription.getUidChemist())
                                                                                        .update("moneyOwed", task.getResult().toObject(Chemist.class).getMoneyOwed() + prescription.getOrderValue())
                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                Toast.makeText(context, "Order will be delivered soon", Toast.LENGTH_LONG).show();
                                                                                                holder.pay.setVisibility(View.GONE);
                                                                                                FirebaseFirestore db4 = FirebaseFirestore.getInstance();
                                                                                                db4.collection("prescription")
                                                                                                        .document(prescription.getId())
                                                                                                        .update("status", 3)
                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                            @Override
                                                                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                                                            }
                                                                                                        });
                                                                                            }
                                                                                        });
                                                                            }
                                                                        }
                                                                    });
                                                        //}
                                                    }
                                                });
                                    }
                                }
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    class  Holder extends RecyclerView.ViewHolder {
        TextView morningMeds, lunchMeds, eveningMeds, afterMeds, nightMeds, name, address, orderValue;
        Button pay;

        public  Holder(@NonNull View itemView) {
            super(itemView);
            morningMeds = itemView.findViewById(R.id.morningMeds);
            eveningMeds = itemView.findViewById(R.id.eveningMeds);
            afterMeds = itemView.findViewById(R.id.afterMeds);
            nightMeds = itemView.findViewById(R.id.nightMeds);
            lunchMeds = itemView.findViewById(R.id.lunchMeds);
            name = itemView.findViewById(R.id.nameCard);
            address = itemView.findViewById(R.id.address);
            pay = itemView.findViewById(R.id.confirm);
            orderValue = itemView.findViewById(R.id.orderValue);
        }
    }
}
