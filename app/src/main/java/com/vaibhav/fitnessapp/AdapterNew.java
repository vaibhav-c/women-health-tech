package com.vaibhav.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AdapterNew extends RecyclerView.Adapter<AdapterNew. Holder> {

    Context context;
    int resource;
    ArrayList<AppointmentModel> ArrayList;

    public AdapterNew(Context context, int resource, ArrayList<AppointmentModel>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterNew. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterNew. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNew. Holder holder, int position) {
        AppointmentModel appointmentModel = ArrayList.get(position);
        holder.nameCard.setText("Patient: " + appointmentModel.getPatientName());
        holder.timeSlot.setText("Timeslot: "  + appointmentModel.getTimeSlot());
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.go.setVisibility(View.VISIBLE);
                holder.amountEnter.setVisibility(View.VISIBLE);
            }
        });

        holder.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.amountEnter.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Amount cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("appointments")
                        .document(appointmentModel.getId())
                        .update("confirmStatus", 1, "payment", Integer.parseInt(holder.amountEnter.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Confirmed", Toast.LENGTH_LONG).show();
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

        TextView nameCard, timeSlot;
        Button book, go;
        EditText amountEnter;

        public  Holder(@NonNull View itemView) {
            super(itemView);

            nameCard = itemView.findViewById(R.id.nameCard);
            timeSlot = itemView.findViewById(R.id.timeSlot);
            book = itemView.findViewById(R.id.book);
            amountEnter = itemView.findViewById(R.id.amountEnter);
            go = itemView.findViewById(R.id.go);
        }
    }
}
