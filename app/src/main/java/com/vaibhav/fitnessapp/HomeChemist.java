package com.vaibhav.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class HomeChemist extends AppCompatActivity {

    RecyclerView recyclerView, napkins;
    Button logout;
    ArrayList<Prescription> presNew, presPaid, presDeli;
    ArrayList<FreeOrder> sanNew, sanDeli, sanAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_chemist);

        recyclerView  = findViewById(R.id.orderHolder);
        napkins = findViewById(R.id.napkins);

        Button newOrder, paid, delivered, deliveredSanitary, newSanitary, acceptedSanitary;

        newOrder = findViewById(R.id.newOrder);
        paid = findViewById(R.id.paid);
        delivered = findViewById(R.id.delivered);
        deliveredSanitary = findViewById(R.id.deliveredSanitary);
        newSanitary = findViewById(R.id.newSanitaryOrder);
        acceptedSanitary = findViewById(R.id.acceptedSanitary);
        logout = findViewById(R.id.logout);

        presNew = new ArrayList<>();
        presPaid = new ArrayList<>();
        presDeli = new ArrayList<>();

        sanNew = new ArrayList<>();
        sanDeli = new ArrayList<>();
        sanAcc = new ArrayList<>();

        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presNew.clear();
                newOrder.setBackground(getResources().getDrawable(R.drawable.button_background_selected));
                paid.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));
                delivered.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("prescription")
                        .whereEqualTo("status", 1)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.getResult() != null) {
                                    for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                        presNew.add(documentSnapshot.toObject(Prescription.class));
                                    }
                                }
                                setAdapterNew(presNew);
                            }
                        });
            }
        });

        paid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presPaid.clear();
                paid.setBackground(getResources().getDrawable(R.drawable.button_background_selected));
                newOrder.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));
                delivered.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("prescription")
                        .whereEqualTo("status", 3)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.getResult() != null) {
                                    for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                        presPaid.add(documentSnapshot.toObject(Prescription.class));
                                    }
                                }
                                setAdapterPaid(presPaid);
                            }
                        });
            }
        });

        delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presDeli.clear();
                delivered.setBackground(getResources().getDrawable(R.drawable.button_background_selected));
                paid.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));
                newOrder.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("prescription")
                        .whereEqualTo("status", 4)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.getResult() != null) {
                                    for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                        presDeli.add(documentSnapshot.toObject(Prescription.class));
                                    }
                                }
                                setAdapterDelivered(presDeli);
                            }
                        });
            }
        });

        newSanitary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanNew.clear();
                newSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_selected));
                deliveredSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));
                acceptedSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("SanitaryNapkins")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.getResult() != null) {
                                    for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                        if(documentSnapshot.toObject(FreeOrder.class).getDeliveryTime().length() == 0
                                                && documentSnapshot.toObject(FreeOrder.class).getUidChemist().length() == 0) {
                                            sanNew.add(documentSnapshot.toObject(FreeOrder.class));
                                        }
                                    }
                                }
                                setAdapterSanNew(sanNew);
                            }
                        });
            }
        });

        acceptedSanitary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanAcc.clear();
                acceptedSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_selected));
                deliveredSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));
                newSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("SanitaryNapkins")
                        .whereEqualTo("uidChemist", FirebaseAuth.getInstance().getUid())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.getResult() != null) {
                                    for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                        if(documentSnapshot.toObject(FreeOrder.class).getDeliveryTime().length() == 0) {
                                            sanAcc.add(documentSnapshot.toObject(FreeOrder.class));
                                        }
                                    }
                                }
                                setAdapterSanAccepted(sanAcc);
                            }
                        });
            }
        });

        deliveredSanitary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanDeli.clear();
                deliveredSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_selected));
                acceptedSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));
                newSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("SanitaryNapkins")
                        .whereEqualTo("uidChemist", FirebaseAuth.getInstance().getUid())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.getResult() != null) {
                                    for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                        if(documentSnapshot.toObject(FreeOrder.class).getDeliveryTime().length() != 0) {
                                            sanDeli.add(documentSnapshot.toObject(FreeOrder.class));
                                        }
                                    }
                                }
                                setAdapterSanDelivered(sanDeli);
                            }
                        });
            }
        });


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("prescription")
                .whereEqualTo("status", 1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null) {
                            for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                presNew.add(documentSnapshot.toObject(Prescription.class));
                            }
                        }
                        setAdapterNew(presNew);
                    }
                });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("loggedInStatus", Context.MODE_PRIVATE);
                SharedPreferences.Editor editStatus = sharedPreferences.edit();
                editStatus.putBoolean("loggedInSuperAdmin", false);
                editStatus.apply();
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeChemist.this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = getSharedPreferences("loggedInStatus", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editStatus = sharedPreferences.edit();
                        editStatus.putBoolean("loggedIn", false);
                        editStatus.apply();
                        SharedPreferences sharedPreferences1 = getSharedPreferences("Role", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editStatus1 = sharedPreferences1.edit();
                        editStatus1.putString("Role", "None");
                        editStatus1.apply();
                        Intent intent1 = new Intent(HomeChemist.this, ChooseRole.class);
                        startActivity(intent1);
                        finish();
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

        sanNew.clear();
        newSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_selected));
        deliveredSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));
        acceptedSanitary.setBackground(getResources().getDrawable(R.drawable.button_background_unselected));

        db = FirebaseFirestore.getInstance();
        db.collection("SanitaryNapkins")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null) {
                            for(DocumentSnapshot documentSnapshot : task.getResult()) {
                                if(documentSnapshot.toObject(FreeOrder.class).getDeliveryTime().length() == 0
                                        && documentSnapshot.toObject(FreeOrder.class).getUidChemist().length() == 0) {
                                    sanNew.add(documentSnapshot.toObject(FreeOrder.class));
                                }
                            }
                        }
                        setAdapterSanNew(sanNew);
                    }
                });



    }
    void setAdapterNew(ArrayList<Prescription> arrayList) {
        AdapterPrescriptionNew adapterPrescriptionNew = new AdapterPrescriptionNew(HomeChemist.this, R.layout.card_prescription_order_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeChemist.this);
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager1);
            recyclerView.setAdapter(adapterPrescriptionNew);
        } catch (Exception e) {

        }
    }

    void setAdapterPaid(ArrayList<Prescription> arrayList) {
        AdapterPrescriptionPaid adapterPrescriptionPaid = new AdapterPrescriptionPaid(HomeChemist.this, R.layout.card_prescription_paidorder_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeChemist.this);
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager1);
            recyclerView.setAdapter(adapterPrescriptionPaid);
        } catch (Exception e) {

        }
    }

    void setAdapterDelivered(ArrayList<Prescription> arrayList) {
        AdapterPrescriptionDelivered adapterPrescriptionDelivered = new AdapterPrescriptionDelivered(HomeChemist.this, R.layout.card_prescription_deliveredorder_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeChemist.this);
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager1);
            recyclerView.setAdapter(adapterPrescriptionDelivered);
        } catch (Exception e) {

        }
    }

    void setAdapterSanNew(ArrayList<FreeOrder> arrayList) {
        AdapterPrescriptionSanitaryNew adapterPrescriptionNew = new AdapterPrescriptionSanitaryNew(HomeChemist.this, R.layout.card_sanitary_order_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeChemist.this);
        try {
            napkins.setHasFixedSize(true);
            napkins.setLayoutManager(layoutManager1);
            napkins.setAdapter(adapterPrescriptionNew);
        } catch (Exception e) {

        }
    }

    void setAdapterSanAccepted(ArrayList<FreeOrder> arrayList) {
        AdapterPrescriptionSanitaryAccepted adapterPrescriptionPaid = new AdapterPrescriptionSanitaryAccepted(HomeChemist.this, R.layout.card_sanitary_accepted_order_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeChemist.this);
        try {
            napkins.setHasFixedSize(true);
            napkins.setLayoutManager(layoutManager1);
            napkins.setAdapter(adapterPrescriptionPaid);
        } catch (Exception e) {

        }
    }

    void setAdapterSanDelivered(ArrayList<FreeOrder> arrayList) {
        AdapterPrescriptionSanitaryDelivered adapterPrescriptionDelivered = new AdapterPrescriptionSanitaryDelivered(HomeChemist.this, R.layout.card_sanitary_delivered_order_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(HomeChemist.this);
        try {
            napkins.setHasFixedSize(true);
            napkins.setLayoutManager(layoutManager1);
            napkins.setAdapter(adapterPrescriptionDelivered);
        } catch (Exception e) {

        }
    }
}