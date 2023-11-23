package com.example.mrbarberadmin.clients;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrbarberadmin.MainActivity;
import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Client;
import com.example.mrbarberadmin.services.ServicesCustomAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ClientsFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_client_btn;
    MainActivity mainActivity;

    ClientsCustomAdapter clientsCustomAdapter;

    DBHelper dbHelper;
    ArrayList<Client> clients;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clients, container, false);


        recyclerView = view.findViewById(R.id.recycler_view_clients);
        add_client_btn = view.findViewById(R.id.add_client_btn);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        mainActivity = (MainActivity)getActivity();

        reloadData();

        add_client_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddClientsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    void reloadData(){
        dbHelper = new DBHelper(mainActivity);

        clients = dbHelper.getClients();

        clientsCustomAdapter = new ClientsCustomAdapter(mainActivity,clients);
        recyclerView.setAdapter(clientsCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL,false));
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
                    .setTitle("Eliminar cliente")
                    .setMessage("¿Estás seguro que deseas eliminar este cliente?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int id = clients.get(position).getId();

                            dbHelper.deleteClient(id);

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