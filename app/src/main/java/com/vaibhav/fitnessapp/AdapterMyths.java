package com.vaibhav.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMyths extends RecyclerView.Adapter<AdapterMyths. Holder> {

    Context context;
    int resource;
    ArrayList<String> ArrayList;

    public AdapterMyths(Context context, int resource, ArrayList<String>  ArrayList) {
        this.context = context;
        this.resource = resource;
        this.ArrayList = ArrayList;
    }

    @NonNull
    @Override
    public AdapterMyths. Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterMyths. Holder(LayoutInflater.from(context).inflate(resource,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyths. Holder holder, int position) {
        String s = ArrayList.get(position);
        holder.nameCard.setText(s);
    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    class  Holder extends RecyclerView.ViewHolder {

        TextView nameCard;

        public  Holder(@NonNull View itemView) {
            super(itemView);
            nameCard = itemView.findViewById(R.id.nameCard);
        }
    }
}
