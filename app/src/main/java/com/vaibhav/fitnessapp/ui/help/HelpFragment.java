package com.vaibhav.fitnessapp.ui.help;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vaibhav.fitnessapp.ChooseRole;
import com.vaibhav.fitnessapp.PastAppointments;
import com.vaibhav.fitnessapp.R;
import com.vaibhav.fitnessapp.ReviewModel;
import com.vaibhav.fitnessapp.User;
import com.vaibhav.fitnessapp.ViewPrescriptions;

public class HelpFragment extends Fragment {

    private HelpViewModel helpViewModel;
    EditText best, worst, complaint;
    Button submit;
    String name, email;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        helpViewModel =
                new ViewModelProvider(this).get(HelpViewModel.class);
        View root = inflater.inflate(R.layout.fragment_help, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        helpViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        best = root.findViewById(R.id.one);
        worst = root.findViewById(R.id.two);
        complaint = root.findViewById(R.id.three);
        submit = root.findViewById(R.id.submit);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult() != null) {
                            name = task.getResult().toObject(User.class).getName();
                            email = task.getResult().toObject(User.class).getEmailId();
                        }
                    }
                });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewModel reviewModel = new ReviewModel();
                reviewModel.setBest(best.getText().toString());
                reviewModel.setWorst(worst.getText().toString());
                reviewModel.setComplaint(complaint.getText().toString());
                reviewModel.setEmail(email);
                reviewModel.setName(name);
                reviewModel.setUid(FirebaseAuth.getInstance().getUid());

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("reviews")
                        .document(reviewModel.getUid())
                        .set(reviewModel)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getContext(), "Your review is helpful to us", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


        return root;
    }
}