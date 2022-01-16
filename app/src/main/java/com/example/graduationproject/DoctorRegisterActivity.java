package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DoctorRegisterActivity extends AppCompatActivity {

    EditText doctor_NameSurname, doctor_email, doctor_pass, doctor_password, doctor_address1, doctor_address2, doctor_city, doctor_postalCode, doctor_phone;
    TextView doctor_register, doctor_policy;

    //Firabase
    FirebaseAuth auth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);


        doctor_NameSurname = findViewById(R.id.doctor_name_surname);
        doctor_email = findViewById(R.id.doctor_username_input);
        doctor_pass = findViewById(R.id.doctor_pass);
        doctor_password = findViewById(R.id.doctor_password);
        doctor_address1 = findViewById(R.id.doctor_address);
        doctor_address2 = findViewById(R.id.doctor_address2);
        doctor_city = findViewById(R.id.doctor_city);
        doctor_postalCode = findViewById(R.id.doctor_postalcode);
        doctor_phone = findViewById(R.id.doctor_phone);
        doctor_register = findViewById(R.id.doctor_registerBtn);
        doctor_policy = findViewById(R.id.doctor_policy);

        String text = "I confirm that I have read and understand the information and consent texts clearly and agree to the User Agreement and Privacy Policy of mOntCov19PS app.";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent i = new Intent(DoctorRegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        };

        ss.setSpan(clickableSpan1,101, 134, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        doctor_policy.setText(ss);
        doctor_policy.setMovementMethod(LinkMovementMethod.getInstance());


        //Firabase Auth
        auth = FirebaseAuth.getInstance();

        // Adding Event Listener to Button Register
        doctor_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_text = doctor_NameSurname.getText().toString();
                String email_text = doctor_email.getText().toString();
                String pass_text = doctor_pass.getText().toString();
                String doctor_pass_text = doctor_password.getText().toString();
                String address1_text = doctor_address1.getText().toString();
                String address2_text = doctor_address2.getText().toString();
                String city_text = doctor_city.getText().toString();
                String pc_text = doctor_postalCode.getText().toString();
                String phone_text = doctor_phone.getText().toString();


                    if(TextUtils.isEmpty(name_text) || TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text) || TextUtils.isEmpty(doctor_pass_text) || TextUtils.isEmpty(address1_text) || TextUtils.isEmpty(address2_text) || TextUtils.isEmpty(city_text) || TextUtils.isEmpty(pc_text) || TextUtils.isEmpty(phone_text)){
                        Toast.makeText(DoctorRegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                    }else {
                        if(doctor_pass_text.contains("12345"))
                        {
                            RegisterNow(name_text, email_text, pass_text, doctor_pass_text, address1_text, address2_text, city_text, pc_text, phone_text);
                            Toast.makeText(DoctorRegisterActivity.this, "Register Completed!", Toast.LENGTH_SHORT).show();

                        }else{
                            doctor_NameSurname.setError("Name is compulsary");
                        }

                    }
            }
        });


    }


    private void RegisterNow(final String NameSurname, String email, String password, String doctor_password, String address, String address2, String city, String postalcode, String phone){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            myRef = FirebaseDatabase.getInstance()
                                    .getReference("MyUsers")
                                    .child(userid);


                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("doctor_name", NameSurname);
                            hashMap.put("imageURL", "default");
                            hashMap.put("search", NameSurname.toLowerCase());

                            myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(DoctorRegisterActivity.this, DoctorLoginActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(DoctorRegisterActivity.this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}