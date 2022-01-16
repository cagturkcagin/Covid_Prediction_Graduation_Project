package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SymtomsActivity extends AppCompatActivity {

    //Firabase
    FirebaseAuth auth;
    DatabaseReference myRef;

    RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8,rg9,rg10,rg11,rg12,rg13,rg14,rg15,rg16,rg17,rg18,rg19,rg20,rg21,rg22,rg23,rg24,rg25,rg26,rg27,rg28,rg29,rg30;
    TextView send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symtoms);

        auth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = auth.getCurrentUser();
        assert firebaseUser != null;
        String userid = firebaseUser.getUid();

        myRef = FirebaseDatabase.getInstance()
                .getReference("MyUsers")
                .child(userid).child("Symptoms");

        rg1 = findViewById(R.id.group_1); rg2 = findViewById(R.id.group_2); rg3 = findViewById(R.id.group_3);
        rg4 = findViewById(R.id.group_4); rg5 = findViewById(R.id.group_5); rg6 = findViewById(R.id.group_6);
        rg7 = findViewById(R.id.group_7); rg8 = findViewById(R.id.group_8); rg9 = findViewById(R.id.group_9);
        rg10 = findViewById(R.id.group_10); rg11 = findViewById(R.id.group_11); rg12 = findViewById(R.id.group_12);
        rg13 = findViewById(R.id.group_13); rg14 = findViewById(R.id.group_14); rg15 = findViewById(R.id.group_15);
        rg16 = findViewById(R.id.group_16); rg17 = findViewById(R.id.group_17); rg18 = findViewById(R.id.group_18);
        rg19 = findViewById(R.id.group_19); rg20 = findViewById(R.id.group_20); rg21 = findViewById(R.id.group_21);
        rg22 = findViewById(R.id.group_22); rg23 = findViewById(R.id.group_23); rg24 = findViewById(R.id.group_24);
        rg25 = findViewById(R.id.group_25); rg26 = findViewById(R.id.group_26); rg27 = findViewById(R.id.group_27);
        rg28 = findViewById(R.id.group_28); rg29 = findViewById(R.id.group_29); rg30 = findViewById(R.id.group_30);

        send = findViewById(R.id.send_text);
        HashMap<String, String> hashMap = new HashMap<>();

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton1){
                    hashMap.put("Abdominal Pain", "Severe");
                }else if(i==R.id.radioButton2){
                    hashMap.put("Abdominal Pain", "Moderate");
                }else{
                    hashMap.put("Abdominal Pain", "Low");
                }
            }
        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton4){
                    hashMap.put("Anorexia", "Severe");
                }else if(i==R.id.radioButton5){
                    hashMap.put("Anorexia", "Moderate");
                }else{
                    hashMap.put("Anorexia", "Low");
                }
            }
        });
        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton7){
                    hashMap.put("Bluish Face and Lips", "Severe");
                }else if(i==R.id.radioButton8){
                    hashMap.put("Bluish Face and Lips", "Moderate");
                }else{
                    hashMap.put("Bluish Face and Lips", "Low");
                }
            }
        });
        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton10){
                    hashMap.put("Body Aches", "Severe");
                }else if(i==R.id.radioButton11){
                    hashMap.put("Body Aches", "Moderate");
                }else{
                    hashMap.put("Body Aches", "Low");
                }
            }
        });
        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton13){
                    hashMap.put("Chest Pain or Chest Tightness", "Severe");
                }else if(i==R.id.radioButton14){
                    hashMap.put("Chest Pain or Chest Tightness", "Moderate");
                }else{
                    hashMap.put("Chest Pain or Chest Tightness", "Low");
                }
            }
        });
        rg6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton16){
                    hashMap.put("Chills with Repeated Shaking", "Severe");
                }else if(i==R.id.radioButton17){
                    hashMap.put("Chills with Repeated Shaking", "Moderate");
                }else{
                    hashMap.put("Chills with Repeated Shaking", "Low");
                }
            }
        });
        rg7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton19){
                    hashMap.put("Confusion or Unresponsiveness", "Severe");
                }else if(i==R.id.radioButton20){
                    hashMap.put("Confusion or Unresponsiveness", "Moderate");
                }else{
                    hashMap.put("Confusion or Unresponsiveness", "Low");
                }
            }
        });
        rg8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton22){
                    hashMap.put("Delirium", "Severe");
                }else if(i==R.id.radioButton23){
                    hashMap.put("Delirium", "Moderate");
                }else{
                    hashMap.put("Delirium", "Low");
                }
            }
        });
        rg9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton25){
                    hashMap.put("Diarrhea", "Severe");
                }else if(i==R.id.radioButton26){
                    hashMap.put("Diarrhea", "Moderate");
                }else{
                    hashMap.put("Diarrhea", "Low");
                }
            }
        });
        rg10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton28){
                    hashMap.put("Dizziness", "Severe");
                }else if(i==R.id.radioButton29){
                    hashMap.put("Dizziness", "Moderate");
                }else{
                    hashMap.put("Dizziness", "Low");
                }
            }
        });
        rg11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton31){
                    hashMap.put("Fatigue or Weakness", "Severe");
                }else if(i==R.id.radioButton32){
                    hashMap.put("Fatigue or Weakness", "Moderate");
                }else{
                    hashMap.put("Fatigue or Weakness", "Low");
                }
            }
        });
        rg12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton34){
                    hashMap.put("Fever", "Severe");
                }else if(i==R.id.radioButton35){
                    hashMap.put("Fever", "Moderate");
                }else{
                    hashMap.put("Fever", "Low");
                }
            }
        });
        rg13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton37){
                    hashMap.put("General Feeling of Being Unwell", "Severe");
                }else if(i==R.id.radioButton38){
                    hashMap.put("General Feeling of Being Unwell", "Moderate");
                }else{
                    hashMap.put("General Feeling of Being Unwell", "Low");
                }
            }
        });
        rg14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton40){
                    hashMap.put("Headache", "Severe");
                }else if(i==R.id.radioButton41){
                    hashMap.put("Headache", "Moderate");
                }else{
                    hashMap.put("Headache", "Low");
                }
            }
        });
        rg15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton43){
                    hashMap.put("Hoarse Voice (Hoarseness)", "Severe");
                }else if(i==R.id.radioButton44){
                    hashMap.put("Hoarse Voice (Hoarseness)", "Moderate");
                }else{
                    hashMap.put("Hoarse Voice (Hoarseness)", "Low");
                }
            }
        });
        rg16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton46){
                    hashMap.put("Loss of Taste and Smell (Anosmia)", "Severe");
                }else if(i==R.id.radioButton47){
                    hashMap.put("Loss of Taste and Smell (Anosmia)", "Moderate");
                }else{
                    hashMap.put("Loss of Taste and Smell (Anosmia)", "Low");
                }
            }
        });
        rg17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton49){
                    hashMap.put("Muscle Pain", "Severe");
                }else if(i==R.id.radioButton50){
                    hashMap.put("Muscle Pain", "Moderate");
                }else{
                    hashMap.put("Muscle Pain", "Low");
                }
            }
        });
        rg18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton52){
                    hashMap.put("Nasal Discharge or Runny Nose", "Severe");
                }else if(i==R.id.radioButton53){
                    hashMap.put("Nasal Discharge or Runny Nose", "Moderate");
                }else{
                    hashMap.put("Nasal Discharge or Runny Nose", "Low");
                }
            }
        });
        rg19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton55){
                    hashMap.put("Nasal Stuffiness", "Severe");
                }else if(i==R.id.radioButton56){
                    hashMap.put("Nasal Stuffiness", "Moderate");
                }else{
                    hashMap.put("Nasal Stuffiness", "Low");
                }
            }
        });
        rg20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton58){
                    hashMap.put("Nausea", "Severe");
                }else if(i==R.id.radioButton59){
                    hashMap.put("Nausea", "Moderate");
                }else{
                    hashMap.put("Nausea", "Low");
                }
            }
        });
        rg21.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton61){
                    hashMap.put("Ocular Reaction (Eye Inflammation and Red Eye)", "Severe");
                }else if(i==R.id.radioButton62){
                    hashMap.put("Ocular Reaction (Eye Inflammation and Red Eye)", "Moderate");
                }else{
                    hashMap.put("Ocular Reaction (Eye Inflammation and Red Eye)", "Low");
                }
            }
        });
        rg22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton64){
                    hashMap.put("Persistent Cough", "Severe");
                }else if(i==R.id.radioButton65){
                    hashMap.put("Persistent Cough", "Moderate");
                }else{
                    hashMap.put("Persistent Cough", "Low");
                }
            }
        });
        rg23.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton67){
                    hashMap.put("Rhinorrhea (Cerebrospinal Fluid from the Nose)", "Severe");
                }else if(i==R.id.radioButton68){
                    hashMap.put("Rhinorrhea (Cerebrospinal Fluid from the Nose)", "Moderate");
                }else{
                    hashMap.put("Rhinorrhea (Cerebrospinal Fluid from the Nose)", "Low");
                }
            }
        });
        rg24.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton70){
                    hashMap.put("Shortness of Breath or Dyspnea or Dyspnoea", "Severe");
                }else if(i==R.id.radioButton71){
                    hashMap.put("Shortness of Breath or Dyspnea or Dyspnoea", "Moderate");
                }else{
                    hashMap.put("Shortness of Breath or Dyspnea or Dyspnoea", "Low");
                }
            }
        });
        rg25.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton73){
                    hashMap.put("Skin Rash", "Severe");
                }else if(i==R.id.radioButton74){
                    hashMap.put("Skin Rash", "Moderate");
                }else{
                    hashMap.put("Skin Rash", "Low");
                }
            }
        });
        rg26.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton76){
                    hashMap.put("Skipped Meals or Loss of Appetite (Inappetence)", "Severe");
                }else if(i==R.id.radioButton77){
                    hashMap.put("Skipped Meals or Loss of Appetite (Inappetence)", "Moderate");
                }else{
                    hashMap.put("Skipped Meals or Loss of Appetite (Inappetence)", "Low");
                }
            }
        });
        rg27.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton79){
                    hashMap.put("Sneeze", "Severe");
                }else if(i==R.id.radioButton80){
                    hashMap.put("Sneeze", "Moderate");
                }else{
                    hashMap.put("Sneeze", "Low");
                }
            }
        });
        rg28.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton82){
                    hashMap.put("Sore Throat", "Severe");
                }else if(i==R.id.radioButton83){
                    hashMap.put("Sore Throat", "Moderate");
                }else{
                    hashMap.put("Sore Throat", "Low");
                }
            }
        });
        rg29.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton85){
                    hashMap.put("Sputum", "Severe");
                }else if(i==R.id.radioButton86){
                    hashMap.put("Sputum", "Moderate");
                }else{
                    hashMap.put("Sputum", "Low");
                }
            }
        });
        rg30.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton88){
                    hashMap.put("Vomiting", "Severe");
                }else if(i==R.id.radioButton89){
                    hashMap.put("Vomiting", "Moderate");
                }else{
                    hashMap.put("Vomiting", "Low");
                }
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((rg1.getCheckedRadioButtonId() == -1) && (rg2.getCheckedRadioButtonId() == -1) && (rg3.getCheckedRadioButtonId() == -1)
                        && (rg4.getCheckedRadioButtonId() == -1) && (rg5.getCheckedRadioButtonId() == -1) && (rg6.getCheckedRadioButtonId() == -1)
                        && (rg7.getCheckedRadioButtonId() == -1) && (rg8.getCheckedRadioButtonId() == -1) && (rg9.getCheckedRadioButtonId() == -1)
                        && (rg10.getCheckedRadioButtonId() == -1) && (rg11.getCheckedRadioButtonId() == -1) && (rg12.getCheckedRadioButtonId() == -1)
                        && (rg13.getCheckedRadioButtonId() == -1) && (rg14.getCheckedRadioButtonId() == -1) && (rg15.getCheckedRadioButtonId() == -1)
                        && (rg16.getCheckedRadioButtonId() == -1) && (rg17.getCheckedRadioButtonId() == -1) && (rg18.getCheckedRadioButtonId() == -1)
                        && (rg19.getCheckedRadioButtonId() == -1) && (rg20.getCheckedRadioButtonId() == -1) && (rg21.getCheckedRadioButtonId() == -1)
                        && (rg22.getCheckedRadioButtonId() == -1) && (rg23.getCheckedRadioButtonId() == -1) && (rg24.getCheckedRadioButtonId() == -1)
                        && (rg25.getCheckedRadioButtonId() == -1) && (rg26.getCheckedRadioButtonId() == -1) && (rg27.getCheckedRadioButtonId() == -1)
                        && (rg28.getCheckedRadioButtonId() == -1) && (rg29.getCheckedRadioButtonId() == -1) && (rg30.getCheckedRadioButtonId() == -1)){
                    Toast.makeText(SymtomsActivity.this, "Please Choose All Symptoms!", Toast.LENGTH_SHORT).show();
                }else {
                    myRef.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>(){

                        @Override
                        public void onSuccess(Void unused) {
                            finish();
                        }
                    });
                }

            }
        });
    }
}