package com.vaibhav.fitnessapp;

import android.content.Context;
import android.content.Intent;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AdapterConfirm extends RecyclerView.Adapter<AdapterConfirm. Holder> {

    Context context;
    int resource;
    ArrayList<AppointmentModel> ArrayList;

    public AdapterConfirm(Context context, int resource, ArrayList<AppointmentModel>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterConfirm. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterConfirm. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterConfirm. Holder holder, int position) {
        AppointmentModel appointmentModel = ArrayList.get(position);
        holder.nameCard.setText("Name: " + appointmentModel.getPatientName());
        holder.timeSlot.setText("Timeslot: " + appointmentModel.getTimeSlot());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("appointments")
                        .document(appointmentModel.getId())
                        .update("remove", true)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Removed", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.pres.setVisibility(View.VISIBLE);
            }
        });

        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prescription prescription = new Prescription();
                prescription.setMorningMeds(holder.morningMeds.getText().toString());
                prescription.setEveningMeds(holder.eveningMeds.getText().toString());
                prescription.setAfterBreakfastMeds(holder.afterMeds.getText().toString());
                prescription.setNightMeds(holder.nightMeds.getText().toString());
                prescription.setAfterLunchMeds(holder.lunchMeds.getText().toString());
                prescription.setPatientUid(appointmentModel.getUidPatient());
                prescription.setId(appointmentModel.getId());
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("prescription")
                        .document(appointmentModel.getId())
                        .set(prescription)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });

                db.collection("appointments")
                        .document(appointmentModel.getId())
                        .update("prescription", prescription)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                holder.pres.setVisibility(View.GONE);
                            }
                        });
            }
        });
        int code = (int)(Math.random() * (20000 - 19000) + 19000);
        holder.linkShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("appointments")
                        .document(appointmentModel.getId())
                        .update("link", "https://stormy-tor-69508.herokuapp.com/" + code)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Link Shared", Toast.LENGTH_LONG).show();
                            }
                        });
                Intent intent = new Intent(context, VideoCallActivityDoctor.class);
                intent.putExtra("link", "https://stormy-tor-69508.herokuapp.com/" + code);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    class  Holder extends RecyclerView.ViewHolder {

        TextView nameCard, timeSlot;
        Button confirm, remove, submit, linkShare;
        EditText morningMeds, lunchMeds, eveningMeds, afterMeds, nightMeds;
        LinearLayout pres;

        public  Holder(@NonNull View itemView) {
            super(itemView);

            nameCard = itemView.findViewById(R.id.nameCard);
            timeSlot = itemView.findViewById(R.id.timeSlot);
            confirm = itemView.findViewById(R.id.confirm);
            morningMeds = itemView.findViewById(R.id.morningMeds);
            eveningMeds = itemView.findViewById(R.id.eveningMeds);
            afterMeds = itemView.findViewById(R.id.afterMeds);
            nightMeds = itemView.findViewById(R.id.nightMeds);
            lunchMeds = itemView.findViewById(R.id.lunchMeds);
            remove = itemView.findViewById(R.id.remove);
            pres = itemView.findViewById(R.id.writePrescription);
            submit = itemView.findViewById(R.id.submit);
            linkShare = itemView.findViewById(R.id.shareLink);
        }
    }
}
