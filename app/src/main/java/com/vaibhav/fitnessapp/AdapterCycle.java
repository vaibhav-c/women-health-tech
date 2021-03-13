package com.vaibhav.fitnessapp;

import android.content.Context;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AdapterCycle extends RecyclerView.Adapter<AdapterCycle. Holder> {

    Context context;
    int resource;
    ArrayList<Cycle> ArrayList;

    public AdapterCycle(Context context, int resource, ArrayList<Cycle>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterCycle. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterCycle. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCycle. Holder holder, int position) {
        Cycle cycle = ArrayList.get(position);
        holder.nameCard.setText("Start : " + cycle.getDateStart());
        if(cycle.getEndDate() != null) {
            holder.save.setVisibility(View.GONE);
            holder.enterEnd.setVisibility(View.GONE);
            holder.endDate.setVisibility(View.VISIBLE);
        }
        holder.endDate.setText("End : " + cycle.getEndDate());
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = holder.enterEnd.getText().toString();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("MenstrualCycles")
                        .document(FirebaseAuth.getInstance().getUid())
                        .collection("MenstrualCycles")
                        .document(cycle.getId())
                        .update("endDate", s)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                holder.save.setVisibility(View.GONE);
                holder.enterEnd.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    class  Holder extends RecyclerView.ViewHolder {

       TextView nameCard, endDate, save;
       EditText enterEnd;

        public  Holder(@NonNull View itemView) {
            super(itemView);

            nameCard = itemView.findViewById(R.id.nameCard);
            endDate = itemView.findViewById(R.id.endCard);
            save = itemView.findViewById(R.id.save);
            enterEnd = itemView.findViewById(R.id.enterEnd);
        }
    }
}
