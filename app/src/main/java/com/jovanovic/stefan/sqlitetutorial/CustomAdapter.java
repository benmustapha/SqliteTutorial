package com.jovanovic.stefan.sqlitetutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList client_id, name, age, phone;

    CustomAdapter(Activity activity, Context context, ArrayList client_id, ArrayList name, ArrayList age,
                  ArrayList phone){
        this.activity = activity;
        this.context = context;
        this.client_id = client_id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.client_id_txt.setText(String.valueOf(client_id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.age_txt.setText(String.valueOf(age.get(position)));
        holder.phone_txt.setText(String.valueOf(phone.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(client_id.get(position)));
                intent.putExtra("title", String.valueOf(name.get(position)));
                intent.putExtra("author", String.valueOf(age.get(position)));
                intent.putExtra("pages", String.valueOf(phone.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return client_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView client_id_txt, name_txt, age_txt, phone_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            client_id_txt = itemView.findViewById(R.id.client_id_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            age_txt = itemView.findViewById(R.id.age_txt);
            phone_txt = itemView.findViewById(R.id.phone_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
