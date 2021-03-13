package com.vaibhav.fitnessapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RegisterDoctorActivity extends AppCompatActivity {

    EditText name, emailId, phone, OTP, password, confirmPassword, idProof, address, city, state, experience, fee;
    Button register, resend;
    TextView loginText;
    FirebaseAuth mAuth;
    private String verificationID;
    PhoneAuthProvider.ForceResendingToken token;
    private Uri filePath;
    ProgressDialog dialog;
    private final int PICK_IMAGE_REQUEST = 22;
    int TAKE_IMAGE_CODE = 10001;
    boolean loggedIn = false;
    Spinner spinner, spinner1;
    String specialize, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_doctor);

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("loggedInStatus", MODE_PRIVATE);
            loggedIn = sharedPreferences.getBoolean("loggedIn", false);
            Log.d("MSg", "" + loggedIn);
        } catch(Exception e) {
            Log.d("MSg catch", "" + loggedIn);
        }
        if(loggedIn) {
            Intent intent=new Intent(RegisterDoctorActivity.this, DrawerUser.class);
            startActivity(intent);
            finish();
        }

        spinner = findViewById(R.id.category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.specialize, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specialize = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                specialize = "None";
            }
        });

        spinner1 = findViewById(R.id.category3);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = "None";
            }
        });

        name = findViewById(R.id.name);
        emailId = findViewById(R.id.emailId);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        idProof = findViewById(R.id.idProof);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        experience = findViewById(R.id.experience);
        OTP = findViewById(R.id.otp);
        fee = findViewById(R.id.fee);

        register = findViewById(R.id.registerButton);
        resend = findViewById(R.id.resend);

        loginText = findViewById(R.id.loginText);

        mAuth = FirebaseAuth.getInstance();

        Doctor doctor= new Doctor();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendVerificationCode("+91"+ phone.getText().toString().trim(), token);
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDoctorActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        String otp = OTP.getText().toString().trim();

        OTP.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(otp.length()==6)     //size as per your requirement
                {
                    String otp = OTP.getText().toString().trim();
                    verifyVerificationCode(otp);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {

            }
        });

        phone.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(phone.getText().toString().length() == 10)     //size as per your requirement
                {
                    sendVerificationCode("+91" + phone.getText().toString());
                    String uid = phone.getText().toString();
                    Log.i("/myUidddd",""+uid);


                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {

            }
        });

        idProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp = OTP.getText().toString().trim();

                if(otp.isEmpty() || otp.length() <6) {
                    OTP.setText("");
                    OTP.setFocusable(true);
                    OTP.setError("Enter correct OTP");
                    return;
                }
                if (experience.getText().toString() == null) {
                    experience.setText("");
                    experience.setFocusable(true);
                    experience.setError("Experience cannot be empty");
                    return;
                }
                if (fee.getText().toString() == null) {
                    fee.setText("");
                    fee.setFocusable(true);
                    fee.setError("Fee cannot be empty");
                    return;
                }
                if (city.getText().toString().isEmpty()) {
                    city.setText("");
                    city.setFocusable(true);
                    city.setError("City cannot be empty");
                    return;
                }
                if (state.getText().toString().isEmpty()) {
                    state.setText("");
                    state.setFocusable(true);
                    state.setError("State cannot be empty");
                    return;
                }
                if (address.getText().toString().isEmpty()) {
                    address.setText("");
                    address.setFocusable(true);
                    address.setError("Address cannot be empty");
                    return;
                }
                if (name.getText().toString().isEmpty()) {
                    name.setText("");
                    name.setFocusable(true);
                    name.setError("Name cannot be empty");
                    return;
                }
                if (password.getText().toString().length() <= 6) {
                    password.setText("");
                    password.setFocusable(true);
                    password.setError("Weak Password. Too short");
                    return;
                }
                if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    confirmPassword.setText("");
                    confirmPassword.setFocusable(true);
                    confirmPassword.setError("Must be same as password");
                    return;
                }
                if (!(Patterns.EMAIL_ADDRESS.matcher(emailId.getText().toString()).matches())) {
                    emailId.setText("");
                    emailId.setFocusable(true);
                    emailId.setError("Invalid Email");
                    return;
                } else if (phone.getText().toString().length() != 10) {
                    phone.setError("Enter a valid mobile");
                    phone.setFocusable(true);
                    phone.setText("");
                    return;
                }
                doctor.setName(name.getText().toString().trim());
                doctor.setEmailId(emailId.getText().toString().trim());
                doctor.setPhone(phone.getText().toString().trim());
                doctor.setAddress(address.getText().toString().trim());
                doctor.setPassword(password.getText().toString());
                doctor.setCity(city.getText().toString().trim());
                doctor.setState(state.getText().toString().trim());
                doctor.setExperience(Integer.parseInt(experience.getText().toString().trim()));
                doctor.setSpecialize(specialize);
                doctor.setGender(gender);
                doctor.setFee(Integer.parseInt(fee.getText().toString().trim()));

                register(doctor);
            }
        });

    }

    private void register(Doctor doctor) {
        AuthCredential credential = EmailAuthProvider.getCredential(doctor.getEmailId(), doctor.getPassword());

        mAuth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(RegisterDoctorActivity.this,"User Already Registered",Toast.LENGTH_LONG).show();
                            }else {
                                Log.i("Done register", task.getResult().toString());
                                saveUserInFirebase(doctor);
                            }
                            //    FirebaseUser user = task.getResult().getUser();
                        } else {
                            Toast.makeText(RegisterDoctorActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void verifyVerificationCode(String otp) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, otp);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(RegisterDoctorActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterDoctorActivity.this,"Done",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile,
                30,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }





    private void resendVerificationCode(String phoneNumber,
                                          PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                30,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }



    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();


            if (code != null) {
                OTP.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }


        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(RegisterDoctorActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationID = s;
            token = forceResendingToken;

        }

    };

    private void showPictureDialog(){

        AlertDialog.Builder picDialog = new AlertDialog.Builder(this);
        picDialog.setTitle("Choose an Action");
        String[] picDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        picDialog.setItems(picDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                switch(which){

                    case 0:
                        SelectImage();
                        break;
                    case 1:
                        handleImageClick(idProof);
                        break;
                }
            }
        });
        picDialog.show();

    }
    public void handleImageClick(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, TAKE_IMAGE_CODE);
        }
    }

    private void SelectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_IMAGE_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    handleUpload(bitmap);
                    dialog = new ProgressDialog(RegisterDoctorActivity.this);
                    dialog.setMessage("Uploading");
                    dialog.show();
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            filePath = data.getData();
            try {
                final Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                handleUpload(bitmap);
                dialog = new ProgressDialog(RegisterDoctorActivity.this);
                dialog.setMessage("Uploading");
                dialog.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleUpload (Bitmap bitmap){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        final StorageReference reference = FirebaseStorage.getInstance().getReference()
                .child("Doctor")
                .child("License")
                .child(name.getText().toString().trim() + phone.getText().toString().trim() + ".jpeg");

        reference.putBytes(baos.toByteArray())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        getDownloadUrl(reference);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

    }



    private void getDownloadUrl(StorageReference reference) {
        reference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        setUserProfileUrl(uri);
                    }
                });
    }

    private void setUserProfileUrl(Uri uri) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dialog.dismiss();
                        idProof.setText("Uploaded");
                        Toast.makeText(RegisterDoctorActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterDoctorActivity.this, "Profile image failed...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    void saveUserInFirebase(Doctor doctor) {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid(); // This is uid of User which we have just created

        doctor.setUidDoctor(uid);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("doctors").document(uid).set(doctor)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()) {
                            SharedPreferences sharedPreferences = getSharedPreferences("loggedInStatus", MODE_PRIVATE);
                            SharedPreferences.Editor editStatus = sharedPreferences.edit();
                            editStatus.putBoolean("loggedIn", true);
                            editStatus.apply();
                            Intent intent = new Intent(RegisterDoctorActivity.this, HomeDoctor.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(RegisterDoctorActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RegisterDoctorActivity.this, "Something Went Wrong !!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}