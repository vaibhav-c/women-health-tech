package com.vaibhav.fitnessapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class AdapterPrescriptionNew extends RecyclerView.Adapter<AdapterPrescriptionNew. Holder> {

    Context context;
    int resource;
    ArrayList<Prescription> ArrayList;

    public AdapterPrescriptionNew(Context context, int resource, ArrayList<Prescription>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterPrescriptionNew. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterPrescriptionNew. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPrescriptionNew. Holder holder, int position) {
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

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.go.setVisibility(View.VISIBLE);
                holder.amount.setVisibility(View.VISIBLE);
            }
        });
        holder.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.amount.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Amount cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("prescription")
                        .document(prescription.getId())
                        .update("status", 2, "orderValue", Integer.parseInt(holder.amount.getText().toString()), "uidChemist", FirebaseAuth.getInstance().getUid())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Accepted", Toast.LENGTH_LONG).show();
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
        TextView morningMeds, lunchMeds, eveningMeds, afterMeds, nightMeds, name, address;
        EditText amount;
        Button go, accept;

        public  Holder(@NonNull View itemView) {
            super(itemView);
            morningMeds = itemView.findViewById(R.id.morningMeds);
            eveningMeds = itemView.findViewById(R.id.eveningMeds);
            afterMeds = itemView.findViewById(R.id.afterMeds);
            nightMeds = itemView.findViewById(R.id.nightMeds);
            lunchMeds = itemView.findViewById(R.id.lunchMeds);
            amount  = itemView.findViewById(R.id.amount);
            go = itemView.findViewById(R.id.go);
            accept = itemView.findViewById(R.id.confirm);
            name = itemView.findViewById(R.id.nameCard);
            address = itemView.findViewById(R.id.address);
        }
    }
}
