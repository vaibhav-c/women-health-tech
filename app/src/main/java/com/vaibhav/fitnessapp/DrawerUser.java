package com.vaibhav.fitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONObject;

public class DrawerUser extends AppCompatActivity implements PaymentResultListener {

    private AppBarConfiguration mAppBarConfiguration;
    double amount1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_help, R.id.nav_profile, R.id.nav_wallet)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_user, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void typeOfDoctors(View view) {
        Intent intent = new Intent(DrawerUser.this, TypeOfDoctors.class);
        startActivity(intent);
    }

    public void skinDisease(View view) {
        Intent intent = new Intent(DrawerUser.this, ClassifierActivity.class);
        startActivity(intent);
    }

    public void confirmAppointments(View view) {
        Intent intent = new Intent(DrawerUser.this, ViewConfirmAppointments.class);
        startActivity(intent);
    }

    public void bookAppointment(View view) {
        Intent intent = new Intent(DrawerUser.this, BookAppointment.class);
        startActivity(intent);
    }


    public void payForOrder(View view) {
        Intent intent = new Intent(DrawerUser.this, PayForOrder.class);
        startActivity(intent);
    }


    public void note(View view) {
        Intent intent = new Intent(DrawerUser.this, MenstrualCycle.class);
        startActivity(intent);
    }

    public void startPayment(double amount) {
        final Activity activity = DrawerUser.this;
        amount1 = amount;
        final Checkout checkout = new Checkout();
        try {
            JSONObject options = new JSONObject();
            options.put("name", "CWS");
            options.put("description", "Adding Money");
            options.put("currency", "INR");
            double payment = amount1 * 100;
            options.put("amount", payment);
            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("Payment", e.toString());
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult() != null) {
                            User user = task.getResult().toObject(User.class);
                            FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                            db1.collection("users")
                                    .document(FirebaseAuth.getInstance().getUid())
                                    .update("wallet", user.getWallet() + amount1)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(DrawerUser.this,"Money added to wallet", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(DrawerUser.this, DrawerUser.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                        }
                    }
                });
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(DrawerUser.this,"Some error occured", Toast.LENGTH_LONG).show();
    }
}