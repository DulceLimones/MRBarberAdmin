package com.example.mrbarberadmin.services;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.model.Service;

import java.util.ArrayList;

public class ServicesCustomAdapter extends RecyclerView.Adapter<ServicesCustomAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Service> services;

    ServicesCustomAdapter(Context context, ArrayList<Service> services){
        this.context = context;
        this.services = services;
    }
    @NonNull
    @Override
    public ServicesCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.service_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesCustomAdapter.MyViewHolder holder, int position) {

        holder.labelService.setText(String.valueOf(services.get(position).getName()));
        holder.labelCost.setText("$"+ services.get(position).getPrice());
        holder.labelDuration.setText(services.get(position).getDuration() +" mins");
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(context, EditServicesActivity.class);
                    intent.putExtra("id", String.valueOf(services.get(position).getId()));
                intent.putExtra("name", String.valueOf(services.get(position).getName()));
                intent.putExtra("cost", String.valueOf(services.get(position).getPrice()));
                intent.putExtra("duration", String.valueOf(services.get(position).getDuration()));

                    context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (services == null){
            return 0;
        }
        return services.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView labelService;
        TextView labelCost;
        TextView labelDuration;
        RelativeLayout relativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            labelService = itemView.findViewById(R.id.label_service);
            labelCost = itemView.findViewById(R.id.label_cost);
            labelDuration = itemView.findViewById(R.id.label_duration);
            relativeLayout = itemView.findViewById(R.id.serviceItem);
        }
    }

}
