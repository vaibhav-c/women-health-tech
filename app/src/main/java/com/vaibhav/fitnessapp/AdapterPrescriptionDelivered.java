package com.vaibhav.fitnessapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AdapterPrescriptionDelivered extends RecyclerView.Adapter<AdapterPrescriptionDelivered. Holder> {

    Context context;
    int resource;
    ArrayList<Prescription> ArrayList;

    public AdapterPrescriptionDelivered(Context context, int resource, ArrayList<Prescription>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterPrescriptionDelivered. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterPrescriptionDelivered. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPrescriptionDelivered. Holder holder, int position) {
        Prescription prescription = ArrayList.get(position);

        Log.d("Pres", prescription.toString());
        holder.afterMeds.setText(prescription.getAfterBreakfastMeds());
        holder.morningMeds.setText(prescription.getMorningMeds());
        holder.eveningMeds.setText(prescription.getEveningMeds());
        holder.nightMeds.setText(prescription.getNightMeds());
        holder.lunchMeds.setText(prescription.getAfterLunchMeds());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(prescription.getPatientUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult() != null) {
                            User user = task.getResult().toObject(User.class);
                            holder.name.setText("Patient: " + user.getName());
                            holder.address.setText("Address: " + user.getAddress() + ", " + user.getCity() + ", " + user.getState() + "\n" + user.getPhone());
                        }
                    }
                });

    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    class  Holder extends RecyclerView.ViewHolder {
        TextView morningMeds, lunchMeds, eveningMeds, afterMeds, nightMeds, name, address;

        public  Holder(@NonNull View itemView) {
            super(itemView);
            morningMeds = itemView.findViewById(R.id.morningMeds);
            eveningMeds = itemView.findViewById(R.id.eveningMeds);
            afterMeds = itemView.findViewById(R.id.afterMeds);
            nightMeds = itemView.findViewById(R.id.nightMeds);
            lunchMeds = itemView.findViewById(R.id.lunchMeds);
            name = itemView.findViewById(R.id.nameCard);
            address = itemView.findViewById(R.id.address);
        }
    }
}
