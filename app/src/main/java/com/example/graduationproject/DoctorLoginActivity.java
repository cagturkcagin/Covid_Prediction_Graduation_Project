package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DoctorLoginActivity extends AppCompatActivity {
    private TextView doctor_login, doctor_register;

    EditText doctor_emailLogin, doctor_passLogin;
    ImageView patient_page;

    //Firabase

    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    TextView doctor_forgot_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);


        doctor_emailLogin = findViewById(R.id.doctor_email_input);
        doctor_passLogin = findViewById(R.id.doctor_pass);
        doctor_register = (TextView) findViewById(R.id.doctor_register);
        doctor_login = (TextView) findViewById(R.id.doctor_login);
        doctor_forgot_password = (TextView) findViewById(R.id.doctor_forgot);
        patient_page = (ImageView) findViewById(R.id.patient_icon);

        auth = FirebaseAuth.getInstance();

        doctor_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorLoginActivity.this, ResetPasswordActivity.class));
            }
        });

        doctor_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorLoginActivity.this, DoctorRegisterActivity.class);
                startActivity(i);
            }
        });

        patient_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorLoginActivity.this, Login_Activity.class);
                startActivity(i);
                finish();
            }
        });

        doctor_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_text = doctor_emailLogin.getText().toString();
                String pass_text = doctor_passLogin.getText().toString();

                if(TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)){
                    Toast.makeText(DoctorLoginActivity.this, "Please Fill the Fields", Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(email_text, pass_text)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(DoctorLoginActivity.this, DoctorsMainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }else{
                                        Toast.makeText(DoctorLoginActivity.this, "Check Your Informations!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}