package com.vaibhav.fitnessapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AdapterPrescription extends RecyclerView.Adapter<AdapterPrescription. Holder> {

    Context context;
    int resource;
    ArrayList<Prescription> ArrayList;

    public AdapterPrescription(Context context, int resource, ArrayList<Prescription>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterPrescription. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterPrescription. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPrescription. Holder holder, int position) {
        Prescription prescription = ArrayList.get(position);

        Log.d("Pres", prescription.toString());
        holder.afterMeds.setText(prescription.getAfterBreakfastMeds());
        holder.morningMeds.setText(prescription.getMorningMeds());
        holder.eveningMeds.setText(prescription.getEveningMeds());
        holder.nightMeds.setText(prescription.getNightMeds());
        holder.lunchMeds.setText(prescription.getAfterLunchMeds());
        if(prescription.getStatus() != 3) {
            holder.delivered.setVisibility(View.GONE);
        } else {
            holder.delivered.setVisibility(View.VISIBLE);
        }
        if(prescription.getStatus() == 0) {
            holder.yesNo.setVisibility(View.VISIBLE);
        } else {
            holder.yesNo.setVisibility(View.GONE);
        }

        holder.delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delivery");
                builder.setMessage("Are your medicines delivered?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("prescription")
                                .document(prescription.getId())
                                .update("status", 4)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(context, "Your order is delivered", Toast.LENGTH_SHORT).show();
                                    }
                                });
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

        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("prescription")
                        .document(prescription.getId())
                        .update("status", 1)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                holder.yesNo.setVisibility(View.GONE);
                                Toast.makeText(context, "Chemist will send the bill shortly", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("prescription")
                        .document(prescription.getId())
                        .update("status", -1)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                holder.yesNo.setVisibility(View.GONE);
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
        TextView morningMeds, lunchMeds, eveningMeds, afterMeds, nightMeds;
        Button yes, no, delivered;
        LinearLayout yesNo;

        public  Holder(@NonNull View itemView) {
            super(itemView);
            morningMeds = itemView.findViewById(R.id.morningMeds);
            eveningMeds = itemView.findViewById(R.id.eveningMeds);
            afterMeds = itemView.findViewById(R.id.afterMeds);
            nightMeds = itemView.findViewById(R.id.nightMeds);
            lunchMeds = itemView.findViewById(R.id.lunchMeds);
            yes = itemView.findViewById(R.id.yes);
            no = itemView.findViewById(R.id.no);
            yesNo = itemView.findViewById(R.id.yesNo);
            delivered = itemView.findViewById(R.id.delivered);
        }
    }
}
