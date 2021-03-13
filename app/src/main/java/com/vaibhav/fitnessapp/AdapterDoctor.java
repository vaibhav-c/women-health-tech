package com.vaibhav.fitnessapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Random;

public class AdapterDoctor extends RecyclerView.Adapter<AdapterDoctor. Holder> {

    Context context;
    int resource;
    ArrayList<Doctor> ArrayList;
    String nameOfPatient;

    public AdapterDoctor(Context context, int resource, ArrayList<Doctor>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterDoctor. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterDoctor. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDoctor. Holder holder, int position) {
        Doctor doctor = ArrayList.get(position);
        holder.nameCard.setText("Name: " + doctor.getName());
        holder.addressCard.setText("Address: " + doctor.getAddress());
        //holder.phoneCard.setText(doctor.getPhone());
        holder.phoneCard.setVisibility(View.GONE);
        holder.experienceCard.setText("Experience: " + doctor.getExperience()+"");
        holder.specializeCard.setText("Specialization: " + doctor.getSpecialize());
        holder.cityCard.setText("City: " + doctor.getCity());
        holder.stateCard.setText("State: " + doctor.getState());
        holder.feeCard.setText("Fee: " + doctor.getFee());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult() != null) {
                            nameOfPatient = task.getResult().toObject(User.class).getName();
                            Log.d("Patient", nameOfPatient);
                        }
                    }
                });
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.go.setVisibility(View.VISIBLE);
                holder.timeSlot.setVisibility(View.VISIBLE);
            }
        });
        holder.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.timeSlot.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Time cannot be empty", Toast.LENGTH_LONG).show();
                } else {
                    Random random = new Random();
                    AppointmentModel appointmentModel = new AppointmentModel();
                    appointmentModel.setPatientName(nameOfPatient);
                    appointmentModel.setDoctorUid(doctor.getUidDoctor());
                    appointmentModel.setTimeSlot(holder.timeSlot.getText().toString());
                    appointmentModel.setUidPatient(FirebaseAuth.getInstance().getUid());
                    appointmentModel.setId(appointmentModel.getUidPatient().substring(0, 5) + "" + appointmentModel.getDoctorUid().substring(0, 5) + "" + random.nextInt(1000));
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("appointments")
                            .document(appointmentModel.getId())
                            .set(appointmentModel)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "Appointment will be sent to Doctor", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(context, DrawerUser.class);
                                    context.startActivity(intent);
                                }
                            });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    class  Holder extends RecyclerView.ViewHolder {

        TextView nameCard, feeCard, experienceCard, specializeCard, cityCard, phoneCard, stateCard, addressCard;
        Button book, go;
        EditText timeSlot;

        public  Holder(@NonNull View itemView) {
            super(itemView);

            nameCard = itemView.findViewById(R.id.nameCard);
            addressCard = itemView.findViewById(R.id.addressCard);
            stateCard = itemView.findViewById(R.id.stateCard);
            phoneCard = itemView.findViewById(R.id.phoneCard);
            cityCard = itemView.findViewById(R.id.cityCard);
            experienceCard = itemView.findViewById(R.id.experience);
            specializeCard = itemView.findViewById(R.id.specialization);
            book = itemView.findViewById(R.id.book);
            timeSlot = itemView.findViewById(R.id.timeSlot);
            go = itemView.findViewById(R.id.go);
            feeCard = itemView.findViewById(R.id.fee);
        }
    }
}
