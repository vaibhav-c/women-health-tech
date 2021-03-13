package com.vaibhav.fitnessapp.ui.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.vaibhav.fitnessapp.DrawerUser;
import com.vaibhav.fitnessapp.R;
import com.vaibhav.fitnessapp.User;

import org.json.JSONObject;

public class WalletFragment extends Fragment {

    private WalletViewModel walletViewModel;
    TextView amount;
    Button addMoneyButton, addButton;
    LinearLayout addMoneyLayout;
    EditText enterAmount;
    double amount1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        walletViewModel =
                new ViewModelProvider(this).get(WalletViewModel.class);
        View root = inflater.inflate(R.layout.fragment_wallet, container, false);
        //final TextView textView = root.findViewById(R.id.text_slideshow);
        walletViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        amount = root.findViewById(R.id.amount);
        addButton = root.findViewById(R.id.addButton);
        addMoneyButton = root.findViewById(R.id.addMoneyButton);
        addMoneyLayout = root.findViewById(R.id.addMoneyLayout);
        enterAmount = root.findViewById(R.id.enterAmount);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                       if(task.getResult() != null) {
                           User user = task.getResult().toObject(User.class);
                           amount.setText(user.getWallet()+"");
                       }
                    }
                });

        addMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMoneyLayout.setVisibility(View.VISIBLE);
                addMoneyButton.setVisibility(View.GONE);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount1 = Double.parseDouble(enterAmount.getText().toString());
                ((DrawerUser)getActivity()).startPayment(amount1);
            }
        });
        return root;
    }

    /*private void startPayment() {
        final Activity activity = getActivity();
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
                                            Toast.makeText(getContext(),"Money added to wallet", Toast.LENGTH_LONG).show();
                                            amount.setText(user.getWallet() + "");
                                        }
                                    });

                        }
                    }
                });
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getContext(),"Some error occured", Toast.LENGTH_LONG).show();
    }*/
}