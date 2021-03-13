package com.vaibhav.fitnessapp.ui.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
import com.vaibhav.fitnessapp.User;
import com.vaibhav.fitnessapp.ViewPrescriptions;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    Button logout, help, viewPres;
    TextView nameProfile, addressProfile, ageProfile, genderProfile, preExistProfile, mobileProfile;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        logout = root.findViewById(R.id.logout);
        viewPres = root.findViewById(R.id.prescription);
        help = root.findViewById(R.id.improve);
        nameProfile = root.findViewById(R.id.nameProfile);
        addressProfile = root.findViewById(R.id.addressProfile);
        ageProfile = root.findViewById(R.id.ageProfile);
        mobileProfile = root.findViewById(R.id.mobileProfile);
        genderProfile = root.findViewById(R.id.genderProfile);
        preExistProfile = root.findViewById(R.id.preExistProfile);

        final User[] user = {new User()};

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult() != null) {
                            user[0] = task.getResult().toObject(User.class);
                            nameProfile.setText(user[0].getName());
                            addressProfile.setText(user[0].getAddress());
                            genderProfile.setText("Gender : " + user[0].getGender());
                            mobileProfile.setText("Mobile : " + user[0].getPhone());
                            ageProfile.setText("Age : " + user[0].getAge()+"");
                            preExistProfile.setText("Pre-existing Conditions : " + user[0].getPreExist());
                        }
                    }
                });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loggedInStatus", Context.MODE_PRIVATE);
                SharedPreferences.Editor editStatus = sharedPreferences.edit();
                editStatus.putBoolean("loggedInSuperAdmin", false);
                editStatus.apply();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loggedInStatus", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editStatus = sharedPreferences.edit();
                        editStatus.putBoolean("loggedIn", false);
                        editStatus.apply();
                        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("Role", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editStatus1 = sharedPreferences1.edit();
                        editStatus1.putString("Role", "None");
                        editStatus1.apply();
                        Intent intent1 = new Intent(getContext(), ChooseRole.class);
                        startActivity(intent1);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PastAppointments.class);
                startActivity(intent);
            }
        });

        viewPres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ViewPrescriptions.class);
                startActivity(intent);
            }
        });

        return root;
    }
}