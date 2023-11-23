package com.example.mrbarberadmin.barbers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.model.Barber;
import com.example.mrbarberadmin.services.EditServicesActivity;

import java.util.ArrayList;

public class BarbersCustomAdapter extends RecyclerView.Adapter<BarbersCustomAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Barber> barbers;

    BarbersCustomAdapter(Context context, ArrayList<Barber> barbers){
        this.context = context;
        this.barbers = barbers;
    }
    @NonNull
    @Override
    public BarbersCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.barber_item,parent,false);
        return new BarbersCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarbersCustomAdapter.MyViewHolder holder, int position) {

        holder.labelName.setText(String.valueOf(barbers.get(position).getName()));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditBarberActivity.class);
                intent.putExtra("id", String.valueOf(barbers.get(position).getId()));
                intent.putExtra("name", String.valueOf(barbers.get(position).getName()));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (barbers == null){
            return 0;
        }
        return barbers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView labelName;
        RelativeLayout relativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            labelName = itemView.findViewById(R.id.label_barber);
            relativeLayout = itemView.findViewById(R.id.barberItem);
        }
    }

}
