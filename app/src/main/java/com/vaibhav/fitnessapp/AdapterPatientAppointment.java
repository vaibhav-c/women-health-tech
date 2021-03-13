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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AdapterPatientAppointment extends RecyclerView.Adapter<AdapterPatientAppointment. Holder> {

    Context context;
    int resource;
    ArrayList<AppointmentModel> ArrayList;

    public AdapterPatientAppointment(Context context, int resource, ArrayList<AppointmentModel>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterPatientAppointment. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterPatientAppointment. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPatientAppointment. Holder holder, int position) {
        AppointmentModel appointmentModel = ArrayList.get(position);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("doctors")
                .document(appointmentModel.getDoctorUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult() != null) {
                            holder.nameCard.setText("Doctor: " + task.getResult().toObject(Doctor.class).getName());
                            holder.timeSlot.setText("Timeslot: "  + appointmentModel.getTimeSlot());
                            holder.feeCard.setText("Fee: " + appointmentModel.getPayment());
                        }
                    }
                });
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoCallActivity.class);
                intent.putExtra("link", appointmentModel.getLink());
                context.startActivity(intent);
            }
        });
        if(appointmentModel.isPaid() && appointmentModel.getLink().length() != 0) {
            holder.link.setVisibility(View.VISIBLE);
        }
        if(appointmentModel.isPaid()) {
            holder.pay.setVisibility(View.GONE);
        }
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
                                    if(task.getResult().toObject(User.class).getWallet() < appointmentModel.getPayment()) {
                                        Toast.makeText(context, "Insufficient balance in wallet", Toast.LENGTH_LONG).show();
                                    } else {
                                        FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                                        db1.collection("users")
                                                .document(FirebaseAuth.getInstance().getUid())
                                                .update("wallet", task.getResult().toObject(User.class).getWallet() - appointmentModel.getPayment())
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        //if(task.getResult() != null) {
                                                            Toast.makeText(context, "Money deducted from wallet", Toast.LENGTH_LONG).show();
                                                            FirebaseFirestore db2 = FirebaseFirestore.getInstance();
                                                            db2.collection("doctors")
                                                                    .document(appointmentModel.getDoctorUid())
                                                                    .get()
                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                            if (task.getResult() != null) {
                                                                                Toast.makeText(context, "Processing...", Toast.LENGTH_LONG).show();
                                                                                FirebaseFirestore db3 = FirebaseFirestore.getInstance();
                                                                                db3.collection("doctors")
                                                                                        .document(appointmentModel.getDoctorUid())
                                                                                        .update("moneyOwed", task.getResult().toObject(Doctor.class).getMoneyOwed() + appointmentModel.getPayment())
                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                Toast.makeText(context, "Payment Received", Toast.LENGTH_LONG).show();
                                                                                                if(appointmentModel.getLink().length() != 0) {
                                                                                                    holder.link.setVisibility(View.VISIBLE);
                                                                                                }
                                                                                                holder.pay.setVisibility(View.GONE);
                                                                                            }
                                                                                        });
                                                                                db3.collection("appointments")
                                                                                        .document(appointmentModel.getId())
                                                                                        .update("paid", true)
                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<Void> task) {

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

        TextView nameCard, timeSlot, feeCard;
        Button pay, link;

        public  Holder(@NonNull View itemView) {
            super(itemView);

            nameCard = itemView.findViewById(R.id.nameCard);
            timeSlot = itemView.findViewById(R.id.timeSlot);
            feeCard = itemView.findViewById(R.id.feeCard);
            pay = itemView.findViewById(R.id.pay);
            link = itemView.findViewById(R.id.link);

        }
    }
}
