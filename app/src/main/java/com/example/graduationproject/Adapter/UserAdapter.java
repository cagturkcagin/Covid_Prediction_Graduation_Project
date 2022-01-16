package com.example.graduationproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.graduationproject.MessageActivity;
import com.example.graduationproject.Model.Users;
import com.example.graduationproject.R;

import java.time.temporal.Temporal;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private Context context;
    private List<Users> mUsers;

    //Constructor


    public UserAdapter(Context context, List<Users> mUsers) {
        this.context = context;
        this.mUsers = mUsers;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,
                parent,
                false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Users users = mUsers.get(position);
        holder.doctor_name.setText(users.getDoctor_Name());
        holder.hospital_name.setText(users.getHospital_Name());

        if(users.getImageURL().equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(context)
                    .load(users.getImageURL())
                    .into(holder.profile_image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MessageActivity.class);
                i.putExtra("userid", users.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView doctor_name;
        public TextView hospital_name;
        public ImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            doctor_name = itemView.findViewById(R.id.name_surname);
            hospital_name = itemView.findViewById(R.id.hospital_name);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }
}
