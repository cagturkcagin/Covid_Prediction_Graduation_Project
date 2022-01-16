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
import android.widget.CheckBox;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {

    EditText IDNumberET, NameSurnameET, emailET, passET, genderET, ageET, weightET, heightET, cityET, phoneET, maritalET, workET;
    TextView register, policy;
    CheckBox checkBox;

    //Firabase
    FirebaseAuth auth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        emailET = findViewById(R.id.username_input);
        passET = findViewById(R.id.pass);
        IDNumberET = findViewById(R.id.IDNumber);
        NameSurnameET = findViewById(R.id.name_surname);
        genderET = findViewById(R.id.gender);
        ageET = findViewById(R.id.age);
        weightET = findViewById(R.id.weight);
        heightET = findViewById(R.id.height);
        maritalET = findViewById(R.id.marital_status);
        workET = findViewById(R.id.work);
        cityET = findViewById(R.id.city);
        phoneET = findViewById(R.id.phone);
        register = findViewById(R.id.registerBtn);
        policy = findViewById(R.id.policy);
        checkBox = (CheckBox) findViewById(R.id.checkbox);

        String text = "I confirm that I have read and understand the information and consent texts clearly and agree to the User Agreement and Privacy Policy of mOntCov19PS app.";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        };

        ss.setSpan(clickableSpan1,101, 134, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        policy.setText(ss);
        policy.setMovementMethod(LinkMovementMethod.getInstance());


        //Firabase Auth
        auth = FirebaseAuth.getInstance();

        // Adding Event Listener to Button Register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_text = emailET.getText().toString();
                String pass_text = passET.getText().toString();
                String id_text = IDNumberET.getText().toString();
                String name_text = NameSurnameET.getText().toString();
                String gender_text = genderET.getText().toString();
                String age_text = ageET.getText().toString();
                String weight_text = weightET.getText().toString();
                String height_text = heightET.getText().toString();
                String marital_text = maritalET.getText().toString();
                String work_text = workET.getText().toString();
                String city_text = cityET.getText().toString();
                String phone_text = phoneET.getText().toString();


                Integer height = Integer.parseInt(height_text);
                Integer age = Integer.parseInt(age_text);

                if((TextUtils.isEmpty(id_text) || TextUtils.isEmpty(name_text) || TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)
                        || TextUtils.isEmpty(gender_text) || TextUtils.isEmpty(age_text) || TextUtils.isEmpty(weight_text)
                        || TextUtils.isEmpty(height_text) || TextUtils.isEmpty(city_text) || TextUtils.isEmpty(phone_text)
                        || TextUtils.isEmpty(marital_text)|| TextUtils.isEmpty(work_text)) || (!checkBox.isChecked())){
                    Toast.makeText(RegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }else {
                    if((TextUtils.equals(gender_text,"Male" ) || TextUtils.equals(gender_text,"male" ) || TextUtils.equals(gender_text,"Female" ) || TextUtils.equals(gender_text,"female" ))
                            && (TextUtils.equals(work_text,"Yes" ) || TextUtils.equals(work_text,"yes" ) || TextUtils.equals(work_text,"No" ) || TextUtils.equals(work_text,"no" ))
                            && (height >= 0 && height <= 300 )
                            && (age >= 0 && age <= 200 )){
                        RegisterNow(name_text, id_text, email_text, pass_text, gender_text, age_text, weight_text, height_text, city_text, phone_text, marital_text, work_text);
                        Toast.makeText(RegisterActivity.this, "Register Completed!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Please Check Your Informations!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }


    private void RegisterNow(final String NameSurname, String IDNumber, String email, String password, String gender, String age, String weight, String height, String city, String phone, String marital_status, String work){
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

                            Float x = Float.parseFloat(weight);
                            Float y = Float.parseFloat(height);
                            Float sum = x / (y*y);
                            String bmi = sum.toString();

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("national_id", IDNumber);
                            hashMap.put("name_surname", NameSurname);
                            hashMap.put("gender", gender);
                            hashMap.put("weight", weight);
                            hashMap.put("height", height);
                            hashMap.put("bmi", bmi);
                            hashMap.put("work_status", work);
                            hashMap.put("marital_status", marital_status);
                            hashMap.put("imageURL", "default");

                            myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(RegisterActivity.this, Login_Activity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}