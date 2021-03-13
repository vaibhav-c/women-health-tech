package com.vaibhav.fitnessapp;

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

public class AdapterType extends RecyclerView.Adapter<AdapterType. Holder> {

    Context context;
    int resource;
    ArrayList<Types> ArrayList;

    public AdapterType(Context context, int resource, ArrayList<Types>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterType. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterType. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterType. Holder holder, int position) {
        Types type = ArrayList.get(position);
        holder.nameCard.setText(type.getType());
        holder.desc.setText(type.getDescription());
    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    class  Holder extends RecyclerView.ViewHolder {

        TextView nameCard, desc;

        public  Holder(@NonNull View itemView) {
            super(itemView);

            nameCard = itemView.findViewById(R.id.nameCard);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}
