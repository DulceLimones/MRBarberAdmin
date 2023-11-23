package com.example.mrbarberadmin.appointments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mrbarberadmin.MainActivity;
import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Appointment;
import com.example.mrbarberadmin.model.Barber;
import com.example.mrbarberadmin.model.Client;
import com.example.mrbarberadmin.model.Service;
import com.example.mrbarberadmin.services.AddServicesActivity;
import com.example.mrbarberadmin.services.ServicesCustomAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AppointmentsFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton addAppontment;
    MainActivity mainActivity;

    AppointmentsCustomAdapter appointmentsCustomAdapter;
    DBHelper dbHelper;
    ArrayList<Appointment> appointments;
    ArrayList<Service> services ;
    ArrayList<Client> clients ;
    ArrayList<Barber> barbers;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_appointments, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_appointments);
        addAppontment = view.findViewById(R.id.add_appointment_btn);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        mainActivity = (MainActivity)getActivity();

        reloadData();

        addAppontment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                if (services!=null && clients!= null && barbers!=null){
                Intent intent = new Intent(getActivity(), AddAppointmentActivity.class);
                startActivity(intent);
                } else {
                    Toast.makeText(
                            mainActivity,
                            "Debe agregar datos a servicios, clientes y barberos para crear citas",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    void reloadData(){
        dbHelper = new DBHelper(mainActivity);

        appointments = dbHelper.getAppointment();
        ArrayList<Service> services =dbHelper.getServices();
        ArrayList<Client> clients = dbHelper.getClients();
        ArrayList<Barber> barbers = dbHelper.getBarbers();

        appointmentsCustomAdapter = new AppointmentsCustomAdapter(mainActivity,appointments);
        recyclerView.setAdapter(appointmentsCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            RecyclerView.Adapter adapter= recyclerView.getAdapter();

            new AlertDialog.Builder(mainActivity)
                    .setTitle("Eliminar cita")
                    .setMessage("¿Estás seguro que deseas eliminar esta cita?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int id = appointments.get(position).getId();

                            dbHelper.deleteAppointment(id);

                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            adapter.notifyDataSetChanged();
                        }
                    }).show();
        }
    };
    void getData(){
        DBHelper dbHelper = new DBHelper(mainActivity);
        services = dbHelper.getServices();
        clients = dbHelper.getClients();
        barbers = dbHelper.getBarbers();
    }
}