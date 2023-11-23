package com.example.mrbarberadmin.clients;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.model.Client;
import com.example.mrbarberadmin.services.EditServicesActivity;

import java.util.ArrayList;

public class ClientsCustomAdapter  extends RecyclerView.Adapter<ClientsCustomAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Client> clients;

    ClientsCustomAdapter(Context context, ArrayList<Client> clients){
        this.context = context;
        this.clients = clients;
    }
    @NonNull
    @Override
    public ClientsCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.client_item,parent,false);
        return new ClientsCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsCustomAdapter.MyViewHolder holder, int position) {

        holder.labelName.setText(
                clients.get(position).getFirstName() +" "+ clients.get(position).getLastName());

        holder.labelPhone.setText(
                String.valueOf(clients.get(position).getPhone()));
        holder.distinguished.setVisibility(isVisible(position));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditClientsActivity.class);
                intent.putExtra("id", String.valueOf(clients.get(position).getId()));
                intent.putExtra("firstName", String.valueOf(clients.get(position).getFirstName()));
                intent.putExtra("lastName", String.valueOf(clients.get(position).getLastName()));
                intent.putExtra("phone", String.valueOf(clients.get(position).getPhone()));
                intent.putExtra("distinguished", String.valueOf(clients.get(position).isDistiguished()));
                intent.putExtra("lastVisit", String.valueOf(clients.get(position).getLastVisit()));
                intent.putExtra("amountVisits", String.valueOf(clients.get(position).getAmountVisits()));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (clients == null){
            return 0;
        }
        return clients.size();
    }

    public int isVisible(int position){
        int distinguished = clients.get(position).isDistiguished();

        if (distinguished == 1){
            return View.VISIBLE;
        } else {
            return View.INVISIBLE;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView labelName;
        TextView labelPhone;
        ImageView distinguished;
        RelativeLayout relativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            labelName = itemView.findViewById(R.id.label_client);
            labelPhone = itemView.findViewById(R.id.label_phone);
            distinguished = itemView.findViewById(R.id.distinguished_image);
            relativeLayout = itemView.findViewById(R.id.clientItem);
        }
    }


}
