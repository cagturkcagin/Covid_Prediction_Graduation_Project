package com.example.graduationproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.graduationproject.Adapter.MessageAdapter;
import com.example.graduationproject.Model.Chat;
import com.example.graduationproject.Model.Users;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {



    //Recording
    private MediaRecorder mediaRecorder;
    public static String filename="recorded.3gp";
    String file = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+filename;
    UploadTask uploadTask;
    DatabaseReference rootref1, rootref2;
    String receiver_uid,sender_uid;
    public static final int RECORD_AUDIO = 0;
    //


    TextView name_surname;
    TextView hospital_name;
    ImageView imageView;

    RecyclerView recyclerViewy;
    EditText msg_editText;
    ImageButton sendBtn;
    CircleImageView icon1, icon2, icon3, icon4, icon5, icon6;

    FirebaseUser fuser;
    DatabaseReference reference;
    Intent intent;

    MessageAdapter messageAdapter;
    List<Chat> mchat;

    RecyclerView recyclerView;
    String userid;

    Chat chat;


    Uri uri;
    private static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        imageView = findViewById(R.id.imageview_profile);
        name_surname = findViewById(R.id.name);
        hospital_name = findViewById(R.id.hospital);

        sendBtn = findViewById(R.id.btn_send);
        msg_editText = findViewById(R.id.text_send);


        //RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager (getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        intent = getIntent();
        userid = intent.getStringExtra("userid");

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("MyUsers").child(userid);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue (Users.class);
                name_surname.setText(user.getDoctor_Name());
                hospital_name.setText(user.getHospital_Name());

                if(user.getImageURL().equals("default")){
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }else{
                    Glide.with( MessageActivity.this)
                            .load (user.getImageURL ())
                            .into(imageView);
                }

                readMessages(fuser.getUid(), userid, user.getImageURL());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = msg_editText.getText().toString();
                if(!msg.equals("")){
                    sendMessage(fuser.getUid(), userid, msg);
                }else{
                    Toast.makeText(MessageActivity.this, "You can't send an empty message.", Toast.LENGTH_SHORT).show();
                }

                msg_editText.setText("");
            }
        });


        icon1 = findViewById(R.id.icon_1);
        icon2 = findViewById(R.id.icon_2);
        icon3 = findViewById(R.id.icon_3);
        icon4 = findViewById(R.id.icon_4);
        icon5 = findViewById(R.id.icon_5);
        icon6 = findViewById(R.id.icon_6);

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MessageActivity.this, SymtomsActivity.class);
                startActivity(i);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);

            }
        });

        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder (MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setAudioSamplingRate(16000);
        mediaRecorder.setOutputFile(file);




        icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createDialog();

               
            }
        });

    }

    private void createDialog() {
        LayoutInflater inflater = LayoutInflater.from(MessageActivity.this);
        View view = inflater.inflate(R.layout.recorder_layout,  null);
        view.findViewById (R.id.tv_record_status);
        view.findViewById (R.id.btn_start_rc);
        TextView textView = view.findViewById (R.id.tv_record_status);
        Button start = view.findViewById (R.id.btn_start_rc);
        Button stop = view.findViewById(R.id.btn_stop_rc);
        Button send = view.findViewById(R.id.btn_send_rc);

        AlertDialog alertDialog = new AlertDialog.Builder(MessageActivity.this)
                .setView(view)
                .create();

        alertDialog.show();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textView.setText("Audio recording.....");
            }
        });

        stop.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                try {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                } catch(RuntimeException stopException) {
                    // handle cleanup here
                }

                textView.setText("Recording Stopped");
            }
        });

        send.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                rootref1 = database.getReference("Chats");



                Uri audiofile = Uri.fromFile(new File(file));
                StorageReference storageReference = FirebaseStorage.getInstance().getReference( "Audio files");
                final StorageReference reference = storageReference.child(System.currentTimeMillis () + filename);
                uploadTask = reference.putFile(audiofile);

                Task<Uri> urlTask = uploadTask.continueWithTask (new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        return reference.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();



                            chat.setMessage(downloadUri.toString());
                            chat.setReceiver(receiver_uid);
                            chat.setSender(sender_uid);

                            String id = rootref1.push().getKey();
                            rootref1.child(id).setValue(chat);

                        } else {
                            Toast.makeText(MessageActivity.this, "Not working!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    private void sendMessage(String sender, String receiver, String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<> ();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);

        reference.child("Chats").push().setValue(hashMap);


        //Adding User to chat fragment: Latest Chats
        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("ChatList")
                .child(fuser.getUid())
                .child(userid);

        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    chatRef.child("id").setValue(userid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void readMessages(String myid, String userid, String imageurl) {
        mchat = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(myid) && chat.getSender().equals(userid) ||
                            chat.getReceiver().equals(userid) && chat.getSender().equals(myid)) {
                        mchat.add(chat);
                    }
                    messageAdapter = new MessageAdapter(MessageActivity.this, mchat, imageurl);
                    recyclerView.setAdapter(messageAdapter);
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError) {

            }
        });
    }

}