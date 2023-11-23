package com.example.mrbarberadmin.services;

import android.app.AlertDialog;
import android.app.Dialog;
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

import com.example.mrbarberadmin.MainActivity;
import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Service;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ServicesFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton add_service_btn;
    MainActivity mainActivity;

    ServicesCustomAdapter servicesCustomAdapter;
    DBHelper dbHelper;
    ArrayList<Service> services;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_services);
        add_service_btn = view.findViewById(R.id.add_service_btn);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        mainActivity = (MainActivity)getActivity();

        reloadData();


        add_service_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddServicesActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
    void reloadData(){
        dbHelper = new DBHelper(mainActivity);

        services = dbHelper.getServices();

        servicesCustomAdapter = new ServicesCustomAdapter(mainActivity,services);
        recyclerView.setAdapter(servicesCustomAdapter);
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
                    .setTitle("Eliminar servicio")
                    .setMessage("¿Estás seguro que deseas eliminar este servicio?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int id = services.get(position).getId();

                    dbHelper.deleteService(id);

                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            adapter.notifyDataSetChanged();
                        }
                    }).show();
        }
    };
}