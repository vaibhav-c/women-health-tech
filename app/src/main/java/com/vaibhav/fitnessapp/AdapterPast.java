package com.vaibhav.fitnessapp;

import android.content.Context;
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

public class AdapterPast extends RecyclerView.Adapter<AdapterPast. Holder> {

    Context context;
    int resource;
    ArrayList<AppointmentModel> ArrayList;

    public AdapterPast(Context context, int resource, ArrayList<AppointmentModel>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterPast. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterPast. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPast. Holder holder, int position) {
        AppointmentModel appointmentModel = ArrayList.get(position);
        holder.nameCard.setText("Patient: " + appointmentModel.getPatientName());
        holder.timeSlot.setText("Timeslot: "  + appointmentModel.getTimeSlot());
    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    class  Holder extends RecyclerView.ViewHolder {

        TextView nameCard, timeSlot;

        public  Holder(@NonNull View itemView) {
            super(itemView);

            nameCard = itemView.findViewById(R.id.nameCard);
            timeSlot = itemView.findViewById(R.id.timeSlot);
        }
    }
}
