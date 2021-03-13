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

public class AdapterPrescriptionSanitaryDelivered extends RecyclerView.Adapter<AdapterPrescriptionSanitaryDelivered. Holder> {

    Context context;
    int resource;
    ArrayList<FreeOrder> ArrayList;

    public AdapterPrescriptionSanitaryDelivered(Context context, int resource, ArrayList<FreeOrder>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterPrescriptionSanitaryDelivered. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterPrescriptionSanitaryDelivered. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPrescriptionSanitaryDelivered. Holder holder, int position) {
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

    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    class  Holder extends RecyclerView.ViewHolder {
        TextView name, address;

        public  Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameCard);
            address = itemView.findViewById(R.id.address);
        }
    }
}
