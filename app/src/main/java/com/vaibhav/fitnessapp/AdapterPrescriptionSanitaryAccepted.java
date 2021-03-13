package com.vaibhav.fitnessapp;

import android.content.Context;
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
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AdapterPrescriptionSanitaryAccepted extends RecyclerView.Adapter<AdapterPrescriptionSanitaryAccepted. Holder> {

    Context context;
    int resource;
    ArrayList<FreeOrder> ArrayList;

    public AdapterPrescriptionSanitaryAccepted(Context context, int resource, ArrayList<FreeOrder>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterPrescriptionSanitaryAccepted. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterPrescriptionSanitaryAccepted. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPrescriptionSanitaryAccepted. Holder holder, int position) {
        FreeOrder freeOrder = ArrayList.get(position);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(freeOrder.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult() != null) {
                            User user = task.getResult().toObject(User.class);
                            holder.name.setText("Name: " + user.getName());
                            holder.address.setText("Address: " + user.getAddress() + ", " + user.getCity() + ", " + user.getState() + "\n" + user.getPhone());
                        }
                    }
                });

        holder.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(freeOrder.getOTP() == Integer.parseInt(holder.otp.getText().toString())) {
                    db.collection("SanitaryNapkins")
                            .document(freeOrder.getUid())
                            .update("deliveryTime", Timestamp.now().toDate().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "Thanks for delivering", Toast.LENGTH_LONG).show();
                                }
                            });
                } else {
                    Toast.makeText(context, "Incorrect OTP", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    class  Holder extends RecyclerView.ViewHolder {
        TextView name, address;
        Button go;
        EditText otp;

        public  Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameCard);
            address = itemView.findViewById(R.id.address);
            otp = itemView.findViewById(R.id.amount);
            go = itemView.findViewById(R.id.go);
        }
    }
}
