package com.vaibhav.fitnessapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vaibhav.fitnessapp.BookAppointment;
import com.vaibhav.fitnessapp.ClassifierActivity;
import com.vaibhav.fitnessapp.DrawerUser;
import com.vaibhav.fitnessapp.MedicalMyths;
import com.vaibhav.fitnessapp.MenstrualCycle;
import com.vaibhav.fitnessapp.PayForOrder;
import com.vaibhav.fitnessapp.R;
import com.vaibhav.fitnessapp.Symptoms;
import com.vaibhav.fitnessapp.TypeOfDoctors;
import com.vaibhav.fitnessapp.User;
import com.vaibhav.fitnessapp.ViewConfirmAppointments;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ArrayList<String> list;
    CardView menstrual;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        menstrual = root.findViewById(R.id.menstrual);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult() != null) {
                            if(task.getResult().toObject(User.class).getGender().equals("Female")) {
                                menstrual.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
        return root;
    }

    public void typeOfDoctors(View view) {
        Intent intent = new Intent(getContext(), TypeOfDoctors.class);
        startActivity(intent);
    }

    public void confirmAppointments(View view) {
        Intent intent = new Intent(getContext(), ViewConfirmAppointments.class);
        startActivity(intent);
    }

    public void payForOrder(View view) {
        Intent intent = new Intent(getContext(), PayForOrder.class);
        startActivity(intent);
    }

    public void bookAppointment(View view) {
        Intent intent = new Intent(getContext(), BookAppointment.class);
        startActivity(intent);
    }

    public void note(View view) {
        Intent intent = new Intent(getContext(), MenstrualCycle.class);
        startActivity(intent);
    }

    public void skinDisease(View view) {
        Intent intent = new Intent(getContext(), ClassifierActivity.class);
        startActivity(intent);
    }

}