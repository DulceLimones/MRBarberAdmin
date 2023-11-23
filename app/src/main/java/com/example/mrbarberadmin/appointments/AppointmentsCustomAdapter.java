package com.example.mrbarberadmin.appointments;

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
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Appointment;
import com.example.mrbarberadmin.model.Barber;
import com.example.mrbarberadmin.model.Client;
import com.example.mrbarberadmin.model.Service;
import com.example.mrbarberadmin.services.EditServicesActivity;

import java.util.ArrayList;

public class AppointmentsCustomAdapter extends RecyclerView.Adapter<AppointmentsCustomAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Appointment> appointments;

    AppointmentsCustomAdapter(Context context, ArrayList<Appointment> appointments){
        this.context = context;
        this.appointments = appointments;
    }
    @NonNull
    @Override
    public AppointmentsCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.appointment_item,parent,false);
        return new AppointmentsCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentsCustomAdapter.MyViewHolder holder, int position) {

        DBHelper dbHelper = new DBHelper(context);
        Service service = dbHelper.findServiceById(appointments.get(position).getServiceId());
        Barber barber =dbHelper.findBarberById(appointments.get(position).getBarberId());
        Client client = dbHelper.findClientById(appointments.get(position).getClientId());


        holder.labelService.setText(String.valueOf(service.getName()));
        holder.labelBarber.setText(String.valueOf(barber.getName()));
        holder.labelClient.setText(client.toString());
        holder.labelDate.setText(String.valueOf(appointments.get(position).getDate()));
        holder.labelTime.setText(String.valueOf(appointments.get(position).getTimeSlot()));

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, EditAppointmentActivity.class);
//                intent.putExtra("id", String.valueOf(appointments.get(position).getId()));
//                intent.putExtra("serviceId", String.valueOf(appointments.get(position).getServiceId()));
//                intent.putExtra("barberId", String.valueOf(appointments.get(position).getBarberId()));
//                intent.putExtra("clientId", String.valueOf(appointments.get(position).getClientId()));
//                intent.putExtra("date", "");
//                intent.putExtra("time", "");
//
//                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (appointments == null){
            return 0;
        }
        return appointments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView labelService;
        TextView labelBarber;
        TextView labelClient;
        TextView labelDate;
        TextView labelTime;
        RelativeLayout relativeLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            labelService = itemView.findViewById(R.id.label_ap_service);
            labelBarber = itemView.findViewById(R.id.label_ap_barber);
            labelClient = itemView.findViewById(R.id.label_ap_client);
            labelDate = itemView.findViewById(R.id.label_date);

            labelTime = itemView.findViewById(R.id.label_time);
            relativeLayout = itemView.findViewById(R.id.appointmentItem);
        }
    }

}
